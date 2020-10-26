package com.automationpractice.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends PageBase {

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "passwd")
    private WebElement passwordField;

    @FindBy(id = "SubmitLogin")
    private WebElement signInButton;
    //public WebElement signInButton = waitForElementDisplayed(By.id("SubmitLogin']"),10);
    @FindBy(className = "logout")
    private WebElement signOutLink;

    @FindBy(className = "login")
    private WebElement signInLink;

    @FindBy(css = ".lost_password > a")
    private WebElement forgotPasswordLink;

    @FindBy(className = "alert-danger")
    private WebElement alertBarDanger;

    @FindBy(className = "alert-success")
    private WebElement alertBarSuccess;

    @FindBy(css = "#form_forgotpassword > fieldset > p > button")
    private WebElement retrievePasswordButton;

    @FindBy(className = "lnk_wishlist")
    private WebElement wishListButton;

    public LoginPage(){
        super();
        PageFactory.initElements(driver,this);
    }

    public void signInWithCredentials() {
        String email = System.getProperty("uid");
        String password = System.getProperty("pwd");
        highlight(emailField);
        emailField.sendKeys(email);
        highlight(passwordField);
        passwordField.sendKeys(password);
        click(signInButton);
    }

    public void forgotPasswordWithEmail(String email) {
        click(forgotPasswordLink);
        emailField.sendKeys(email);
        click(retrievePasswordButton);
    }

    public void signOut() {
        click(signOutLink);
    }

    public boolean isSignInLinkDisplayed() {
        return isElementDisplayed(signInButton,10);
    }

    public boolean isSignOutLinkDisplayed() {
        return isElementDisplayed(signOutLink,10);
    }

    public boolean isAlertBarDangerDisplayed() {
        return isElementDisplayed(alertBarDanger,10);
    }

    public boolean isAlertBarSuccessDisplayed() {
        return isElementDisplayed(alertBarSuccess,10);
    }

    public WishlistPage openWishListPage() {
        click(wishListButton);
        isPageReady();
        return new WishlistPage();
    }
}
