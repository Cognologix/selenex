package com.cadent.test.annotation.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates Excel Rows to be filtered from excel sheet while preparing data providers.
 * eg. @ExcelRows(bean = DatasetBaseBean.class, filterBy = "groups", filterValue = "sanity")
 * returns all row from the sheet having 'groups' named column's value 'sanity'.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ExcelRows {
    //ExcelSheet bean();
    /**
     * The bean class that represents excel sheet name.
     */
    Class<?> bean();

    /**
     * The filterBy string that represents column name from which values are to be filtered .
     */
    String filterBy() default "";

    /**
            * The filterValue string that represents values to be filtered.
     */
    String filterValue() default "";
}