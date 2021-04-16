package com.cadent.test.annotation.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates Excel workbook name and optional sheet name to be used on excel data beans.
 *
 * @ExcelSheet(workbook="Testdata.xlsx", sheet="Dataset")
 * This annotation will fill the bean with sheet name 'Dataset' from excel file 'testdata/Testdata.xlsx'
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ExcelSheet {

    String workbook();
    String sheet() default "";
}
