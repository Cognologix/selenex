package com.test.service.cmn;

public abstract class AbstractDataProvider {


    private ExcelRowFactory excelRowFactory;

    public AbstractDataProvider()  {

        excelRowFactory = new ExcelRowFactory();

            excelRowFactory.initRows(this);

    }

}
