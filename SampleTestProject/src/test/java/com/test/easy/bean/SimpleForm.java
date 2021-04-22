package com.test.easy.bean;

import com.test.annotation.excel.ExcelSheet;

@ExcelSheet(workbook = "InputForms.xlsx", sheet = "SimpleForm")
public class SimpleForm {
    public String group;
    public String message;
    public String a;
    public String b;
    public String total;
}
