package com.test.utils;

import com.test.annotation.excel.ExcelColumn;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellUtil;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides utility to write data to excel files.
 */
@Component
public class ExcelWriter {

    public  <T> void writeRows(Sheet sheet, List<T> rows) throws Exception {
        if (rows.size() > 0) {
            Row row = null;
            Cell cell = null;
            int r = 0;
            int c = 0;
            int colCount = 0;
            Map<String, Object> properties = null;
            DataFormat dataFormat = sheet.getWorkbook().createDataFormat();

            Class beanClass = rows.get(0).getClass();

            // header row
            row = sheet.createRow(r++);
            for (Field f : beanClass.getDeclaredFields()) {
                if (!f.isAnnotationPresent(ExcelColumn.class)) {
                    continue;
                }
                ExcelColumn ec = f.getAnnotation(ExcelColumn.class);
                cell = row.createCell(c++);
                // do formatting the header row
                properties = new HashMap<String, Object>();
                properties.put(CellUtil.FILL_PATTERN, FillPatternType.SOLID_FOREGROUND);
                properties.put(CellUtil.FILL_FOREGROUND_COLOR, IndexedColors.GREY_25_PERCENT.getIndex());
                CellUtil.setCellStyleProperties(cell, properties);
                cell.setCellValue(ec.name());
            }

            colCount = c;

            // contents
            for (T bean : rows) {
                c = 0;
                row = sheet.createRow(r++);
                for (Field f : beanClass.getDeclaredFields()) {
                    cell = row.createCell(c++);
                    if (!f.isAnnotationPresent(ExcelColumn.class)) {
                        continue;
                    }
                    ExcelColumn ec = f.getAnnotation(ExcelColumn.class);
                    // do number formatting the contents
                    String numberFormat = ec.numberFormat();
                    properties = new HashMap<String, Object>();
                    properties.put(CellUtil.DATA_FORMAT, dataFormat.getFormat(numberFormat));
                    CellUtil.setCellStyleProperties(cell, properties);

                    f.setAccessible(true);
                    Object value = f.get(bean);
                    if (value != null) {
                        if (value instanceof String) {
                            cell.setCellValue((String) value);
                        } else if (value instanceof Double) {
                            cell.setCellValue((Double) value);
                        } else if (value instanceof Integer) {
                            cell.setCellValue((Integer) value);
                        } else if (value instanceof Date) {
                            cell.setCellValue((Date) value);
                        } else if (value instanceof Boolean) {
                            cell.setCellValue((Boolean) value);
                        }
                    }
                }
            }

            // auto size columns
            for (int col = 0; col < colCount; col++) {
                sheet.autoSizeColumn(col);
            }
        }
    }
}
