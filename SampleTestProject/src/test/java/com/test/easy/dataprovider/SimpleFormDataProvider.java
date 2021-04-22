package com.test.easy.dataprovider;

import com.test.annotation.excel.ExcelRows;
import com.test.easy.bean.SimpleForm;
import com.test.service.cmn.ExcelRowFactory;
import org.testng.annotations.DataProvider;

public class SimpleFormDataProvider {
    public SimpleFormDataProvider() {
        ExcelRowFactory.initRows(this);
    }

    @ExcelRows(bean = SimpleForm.class, filterBy = "group", filterValue = "bvt")
    Object[][] filteredFormData;

    @DataProvider
    public Object[][] filteredFormData() {
        return filteredFormData;
    }

    @ExcelRows(bean = SimpleForm.class)
    Object[][] allFormData;

    @DataProvider
    public Object[][] allFormData() {
        return allFormData;
    }

}
