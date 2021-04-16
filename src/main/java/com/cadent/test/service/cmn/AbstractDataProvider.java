package com.cadent.test.service.cmn;

public abstract class AbstractDataProvider {


    private ExcelRowFactory excelRowFactory;

    public AbstractDataProvider()  {

        excelRowFactory = new ExcelRowFactory();

            excelRowFactory.initRows(this);

    }

}
