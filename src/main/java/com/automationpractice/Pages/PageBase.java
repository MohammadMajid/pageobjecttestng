package com.automationpractice.Pages;

import com.automationpractice.utils.WebElementUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;


public class PageBase extends WebElementUtils{

    static final Integer DEFAULT_TIMEOUT = Integer.parseInt(System.getProperty("selenium.defaultTimeout", "5"));
    public Properties prop;
    public PageBase(){
        super();
    }

    public void verifyPageTitle(String expectedTitle){
        String title = driver.getTitle();
        Assert.assertEquals(expectedTitle,title);
    }

    public boolean isPageReady() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
            wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        } catch (WebDriverException e) {
            return false;
        }
        return true;
    }

    public Properties init_prop(String env) {
        prop = new Properties();
        String path = null;

        try {
            {
                System.out.println("env value is--->" + env);
                switch (env) {
                    case "qa":
                        path = "./src/main/resources/page.qa.properties";
                        break;
                    case "dev":
                        path = "./src/main/resources/page.dev.properties";
                        break;
                    case "stage":
                        path = "./src/main/resources/page.properties";
                        break;
                    default:
                        path = "./src/main/resources/page.properties";
                        //System.out.println("Please pass the correct env value----> " + env);
                        break;
                }
            }

            FileInputStream ip = new FileInputStream(path);
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;
    }
}
