package com.automationpractice.tests;

import com.automationpractice.ScriptBase.ScriptBaseTestNG;
import com.automationpractice.utils.Constants;
import com.automationpractice.utils.GetPageScreenShot;
import org.testng.annotations.Test;

public class HomePageTest extends ScriptBaseTestNG {

    @Test(groups = {"smoke","regression"})
    public void navigateToLoginPage(){
        //test = extent.createTest("navigateToLoginPage", "PASSED test case");
        homePage.verifyPageLogo();
        homePage.navigateToLoginPage();
        homePage.verifyPageTitle(Constants.LOGIN_PAGE_TITLE);
        homePage.isPageReady();
        try {
            GetPageScreenShot.capture(driver,"picture");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke"})
    public void Search(){
        //test = extent.createTest("Search", "PASSED test case");
        homePage.verifyPageLogo();
        homePage.searchFunctionality(randomTestData.randomEmail());
        homePage.verifyNoResultFound();
        homePage.isPageReady();
    }
}
