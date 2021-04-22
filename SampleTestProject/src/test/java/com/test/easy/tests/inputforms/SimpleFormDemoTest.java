package com.test.easy.tests.inputforms;

import com.test.easy.bean.SimpleForm;
import com.test.easy.dataprovider.SimpleFormDataProvider;
import com.test.easy.page.inputforms.SimpleFormDemoPage;
import com.test.easy.page.HomePage;
import com.test.easy.tests.HomeTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SimpleFormDemoTest extends HomeTest {

    @Autowired
    HomePage homePage;

    @Autowired
    SimpleFormDemoPage simpleFormDemoPage;

    @BeforeClass
    public void setup() {
        homePage.selectForm(HomePage.FormType.SIMPLE_FORM);
    }
    
    @AfterClass
    public void tearDown() {
    	homePage.quit();
    }

    @Test
    public void testSingleMessage() {
        String msg = "selenex test";
        simpleFormDemoPage.enterMessage(msg);
        Assert.assertEquals(msg, simpleFormDemoPage.getMessage());
    }

    @Test(dataProviderClass = SimpleFormDataProvider.class, dataProvider = "filteredFormData")
    public void testMessageForFilteredExcelRows(SimpleForm form) {
        simpleFormDemoPage.enterMessage(form.message);
        Assert.assertEquals(form.message, simpleFormDemoPage.getMessage());
    }

    @Test(dataProviderClass = SimpleFormDataProvider.class, dataProvider = "allFormData")
    public void testTotalForAllExcelRows(SimpleForm form) {
        simpleFormDemoPage.enterAandB(form.a, form.b);
        Assert.assertEquals(form.total, simpleFormDemoPage.getTotal());
    }

}
