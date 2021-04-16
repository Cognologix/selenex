package com.cadent.test.service.cmn;

import com.cadent.test.annotation.PageFragment;
import com.cadent.test.annotation.excel.ExcelRows;
import com.cadent.test.utils.ExcelReader;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Produces and filters rows from excel sheet by using @ExcelRows annotation used in data provider.
 */
@PageFragment
public class ExcelRowFactory {

    private ExcelReader excelReader;

    public ExcelRowFactory(){

        excelReader = new ExcelReader();
    }
    public String getExcel(){

        return "abc";
    }

    /**
     * Initializes data providers all fields.
     *
     * @param T the data provider instance.
     */
    public void initRows(Object T)  {

        for (Field f : T.getClass().getDeclaredFields()) {
            try {

                refresh(f.getName(),T);

            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private  void refresh(String name,Object T) throws NoSuchFieldException, IllegalAccessException {
        Field f = T.getClass().getDeclaredField(name);
        if (!f.isAnnotationPresent(ExcelRows.class)) {
            return;
        }
        f.setAccessible(true);
        String filterBy = f.getAnnotation(ExcelRows.class).filterBy();
        String filterValue = f.getAnnotation(ExcelRows.class).filterValue();
        Class<?> beanType = f.getAnnotation(ExcelRows.class).bean();

        Field filter = beanType.getDeclaredField(filterBy);
        List<?> rows = excelReader.readRows(beanType);
        //Field list = f.getClass().getDeclaredConstructor().newInstance();
        if (rows == null) {
            return;
        }

        if (!filterBy.equals("")) {
            rows = filterRows(rows, filter, filterValue);
        }

        Object[][] data = new Object[rows.size()][];
        int i = 0;
        for (Object d : rows) {
            data[i++] = new Object[]{d};
        }

        f.set(T, data);
    }

    private static List<?> filterRows(List<?> rows, Field filter, String filterValue) {
        Object list = rows.parallelStream()
                .filter(c -> {
                    boolean result = false;
                    try {
                        result = filter.get(c).equals(filterValue);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return result;
                })
                .collect(Collectors.toList());
        return (List<?>) list;
    }
}
