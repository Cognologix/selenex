package com.cadent.test.annotation.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates custom column name and optional number format to be used while fetching excel data.
 * If excel sheet contains column name 'Group' but bean contains field 'group' then the field
 * should be annotated like @ExcelColumn(name="Group").
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ExcelColumn {

    /**
     * The custom column name to be used to fetch excel column values.
     */
    String name();

    /**
     * The number format to be used to fetch coulmn values having this format .
     */
    String numberFormat() default "General";
}


