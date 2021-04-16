package com.cadent.test.utils;


import com.cadent.test.annotation.excel.ExcelColumn;
import com.cadent.test.annotation.excel.ExcelSheet;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Reads data from excel sheets to data base beans.
 * This uses @ExcelSheet and @ExcelColumn annotation to return the list of beans prepared from excel rows.
 * This class is used ExcelRowFactory.
 */
@Lazy
@Component
public class ExcelReader {

    public <T> List<T> readRows(Class<T> beanClass) {
        if (!beanClass.isAnnotationPresent(ExcelSheet.class)) {
            return null;
        }

        try {
            String workbookName = "testdata/"+beanClass.getAnnotation(ExcelSheet.class).workbook();
            Workbook workbook;

            workbook = new XSSFWorkbook(beanClass.getClassLoader().getResourceAsStream(workbookName));
            String sheetName = beanClass.getAnnotation(ExcelSheet.class).sheet();
            if (sheetName.equals("")) {
                sheetName = beanClass.getSimpleName();
            }
            Sheet sheet = workbook.getSheet(sheetName);

            DataFormatter formatter = new DataFormatter(Locale.US);
            FormulaEvaluator evaluator = sheet.getWorkbook().getCreationHelper().createFormulaEvaluator();

            int headerRowNum = sheet.getFirstRowNum();

            // collecting the column headers as a Map of header names to column indexes
            Map<Integer, String> colHeaders = new HashMap<>();
            Row row = sheet.getRow(headerRowNum);
            for (Cell cell : row) {
                int colIdx = cell.getColumnIndex();
                String value = formatter.formatCellValue(cell, evaluator);
                colHeaders.put(colIdx, value);
            }

            // collecting the content rows
            List<T> result = new ArrayList<>();
            String cellValue;
            Date date;
            Double num;
            for (int r = headerRowNum + 1; r <= sheet.getLastRowNum(); r++) {
                row = sheet.getRow(r);
                if (row == null) row = sheet.createRow(r);
                T bean = beanClass.getDeclaredConstructor().newInstance();

                for (Map.Entry<Integer, String> entry : colHeaders.entrySet()) {
                    int colIdx = entry.getKey();
                    Cell cell = row.getCell(colIdx);
                    if (cell == null) cell = row.createCell(colIdx);
                    cellValue = formatter.formatCellValue(cell, evaluator); // string values and formatted numbers
                    // make some differences for numeric or formula content
                    date = null;
                    num = null;
                    if (cell.getCellType() == CellType.NUMERIC) {
                        if (DateUtil.isCellDateFormatted(cell)) { // date
                            date = cell.getDateCellValue();
                        } else { // other numbers
                            num = cell.getNumericCellValue();
                        }
                    } else if (cell.getCellType() == CellType.FORMULA) {
                        // if formula evaluates to numeric
                        if (evaluator.evaluateFormulaCell(cell) == CellType.NUMERIC) {
                            if (DateUtil.isCellDateFormatted(cell)) { // date
                                date = cell.getDateCellValue();
                            } else { // other numbers
                                num = cell.getNumericCellValue();
                            }
                        }
                    }

                    // fill the bean
                    for (Field f : beanClass.getDeclaredFields()) {
                        String name = f.getName();
                        if (f.isAnnotationPresent(ExcelColumn.class)) {
                            name = f.getAnnotation(ExcelColumn.class).name();
                        }
                        if (entry.getValue().equals(name)) {
                            f.setAccessible(true);
                            if (f.getType() == String.class) {
                                f.set(bean, cellValue);
                            } else if (f.getType() == Double.class) {
                                f.set(bean, num);
                            } else if (f.getType() == Date.class) {
                                f.set(bean, date);
                            } else if (f.getType() == Set.class) {
                                f.set(bean, Arrays.stream(cellValue.split(",")).collect(Collectors.toSet()));
                            } else { // this is for all other; Integer, Boolean, ...
                                if (!"".equals(cellValue)) {
                                    Method valueOf = f.getType().getDeclaredMethod("valueOf", String.class);
                                    f.set(bean, valueOf.invoke(f.getType(), cellValue));
                                }
                            }
                        }
                    }
                }
                result.add(bean);
            }
            return result;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
