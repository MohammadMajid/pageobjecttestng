package com.automationpractice.ScriptBase;

import com.automationpractice.Pages.HomePage;
import com.automationpractice.Pages.LoginPage;
import com.automationpractice.Pages.PageBase;
import com.automationpractice.utils.DriverFactory;
import com.automationpractice.utils.RandomTestData;
import com.automationpractice.utils.WebElementUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ScriptBaseTestNG {

    public enum BrowserType{
        CHROME,
        FIREFOX,
        CLOUD_CHROME,
        CLOUD_FIREFOX,
        CLOUD_IE,
        GRID_CHROME,
        GRID_FIREFOX,
        GRID_IE
    }

    protected WebDriver driver;
    protected WebElementUtils webElementUtils;
    public PageBase pageBase;
    protected HomePage homePage;
    protected LoginPage loginPage;
    protected RandomTestData randomTestData;
    public Properties prop;

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {

    }
    //chrome,chromeHeadless,firefox,ie,grid_chrome_16,grid_firefox_16,grid_ie_16
    @BeforeMethod(alwaysRun = true)
    @Parameters({"browserName", "env"})
    public void beforeMethod(@Optional(value = ("chrome")) String browserName, @Optional(value = ("qa")) String env) throws InterruptedException, IOException {

        driver = DriverFactory.getInstance(browserName).getDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();

        pageBase = new PageBase();
        webElementUtils = new WebElementUtils();
        homePage = new HomePage();
        loginPage = new LoginPage();
        randomTestData = new RandomTestData();
        prop = pageBase.init_prop(env);
        driver.get(prop.getProperty("url"));
    }

    @AfterTest
    public void endReport() {
        //extent.flush();
    }

    @AfterMethod
    public void afterClass() {
        webElementUtils = null;
        homePage = null;
        loginPage = null;
        driver  =null;
        DriverFactory.getInstance().removeDriver();
    }

    protected String loadDataFromPropertiesFile(String string,String fileName) {
        final String file = System.getProperty("user.dir") + "/src/main/resources/" + fileName;
        Properties prop = new Properties();
        InputStream input;
        try {
            input = new FileInputStream(file);
            // load a properties file
            prop.load(input);
            return prop.getProperty(string);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "";
    }
}
