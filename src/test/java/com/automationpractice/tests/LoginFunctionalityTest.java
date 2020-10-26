package com.automationpractice.tests;

import com.automationpractice.ScriptBase.ScriptBaseTestNG;
import com.automationpractice.utils.Constants;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class LoginFunctionalityTest extends ScriptBaseTestNG {

    //@Test(groups = {"regression"}, priority=1)
    public void signInWithInvalidCredentials() {
        //test = extent.createTest("signInWithInvalidCredentials", "PASSED test case");
        homePage.navigateToLoginPage();
        //loginPage.signInWithCredentials("majid.us", "majid.us");
        assertTrue(loginPage.isAlertBarDangerDisplayed(), "Error message was not displayed");
        homePage.verifyPageTitle(Constants.LOGIN_PAGE_TITLE);
    }

    //@Test(groups = {"smoke"}, priority=2)
    public void forgotPasswordWithInvalidCredentials() {
        //test = extent.createTest("forgotPasswordWithInvalidCredentials", "PASSED test case");
        homePage.navigateToLoginPage();
        homePage.verifyPageTitle(Constants.LOGIN_PAGE_TITLE);
        loginPage.forgotPasswordWithEmail("majid.us");
        assertTrue(loginPage.isAlertBarDangerDisplayed(), "Error message was not displayed");
    }

    @Test(groups = {"smoke"}, priority=3)
    public void signOut() {
        //test = extent.createTest("signOut", "PASSED test case");
        homePage.navigateToLoginPage();
        homePage.verifyPageTitle("Login - My Store");
        loginPage.signInWithCredentials();
        //loginPage.signOut();
        assertTrue(loginPage.isSignInLinkDisplayed(), "SignIn link was not displayed after sign out action");
    }
}
