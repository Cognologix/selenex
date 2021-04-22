package com.test.page;

import com.test.annotation.Page;
import com.test.controller.WebBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


/**
 * Represents Identity System page.
 */
@Page
public class SystemLogin  {

    @Autowired
    public LoginComponent loginComponent;

    @Autowired
    public ResultPage resultPage;

    @Autowired
    private WebBase webBase;


    @Value("${application.url}")
    private String url;

    public void goTo(){
        webBase.get(url);
        webBase.maximizeWindow();
    }

    public LoginComponent getLoginComponent() {
        return loginComponent;
    }

    public ResultPage getResultPage() {
        return resultPage;
    }


    public void close(){

        this.webBase.close();
    }

    public void quite(){
        this.webBase.quit();
    }


}
