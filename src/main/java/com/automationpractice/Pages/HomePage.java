package com.automationpractice.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HomePage extends PageBase {
/*     @FindBy(how= How.XPATH, using= "//a[@class='login']" )
    private WebElement loginLink;
    @FindBy(how= How.XPATH, using= "//img[@class='logo img-responsive']" )
    private WebElement pic;
    @FindBy(xpath = "//*[@id='contact-link']//a[contains(text(),'Contact us')]")
    private WebElement contactUs;
    @FindBy(xpath = "//button[@name='submit_search']")
    private WebElement searchButton;
    @FindBy(xpath = "//input[@id='search_query_top']")
    private WebElement searchBox;
    @FindBy(xpath = "//p[@class='alert alert-warning']")
    private WebElement NoResultFound;*/

    public HomePage(){
        super();
        PageFactory.initElements(driver,this);
    }

    /*public void navigateToLoginPage(){
        highlight(loginLink);
        loginLink.click();
    }

    public void navigateToContactPage(){
        highlight(contactUs);
        contactUs.click();
    }

    public void searchFunctionality(String element){
        highlight(searchBox);
        searchBox.sendKeys(element);
        searchButton.click();
    }

    public void verifyNoResultFound(){
        String msg = searchBox.getAttribute("value");
        String name = "No results were found for your search "+ "\"" + msg + "\"";
        String msg1 = NoResultFound.getText();
        Assert.assertEquals(msg1,name);
    }
    public void verifyPageLogo(){
        highlight(pic);
        boolean logo = pic.isDisplayed();
        Assert.assertEquals(true,logo);
    } */
// Form Elements
    private By nameInput = By.xpath("//input[@id='name']");
    private By emailInput = By.xpath("//input[@id='email']");
    private By phoneInput = By.xpath("//input[@id='phone']");
    private By textareaInput = By.xpath("//textarea[@id='textarea']");
    
    // Radio Buttons
    private By maleRadio = By.xpath("//input[@id='male']");
    private By femaleRadio = By.xpath("//input[@id='female']");
    
    // Checkboxes
    private By mondayCheckbox = By.xpath("//input[@id='monday']");
    private By tuesdayCheckbox = By.xpath("//input[@id='tuesday']");
    private By wednesdayCheckbox = By.xpath("//input[@id='wednesday']");
    private By thursdayCheckbox = By.xpath("//input[@id='thursday']");
    private By fridayCheckbox = By.xpath("//input[@id='friday']");
    private By saturdayCheckbox = By.xpath("//input[@id='saturday']");
    private By sundayCheckbox = By.xpath("//input[@id='sunday']");
    
    // Dropdowns
    private By countryDropdown = By.xpath("//select[@id='country']");
    private By colorDropdown = By.xpath("//select[@id='colors']");
    
    // Date and Range Inputs
    private By dateInput = By.xpath("//input[@id='datepicker']");
    private By speedSlider = By.xpath("//input[@id='slider']");
    private By volumeSlider = By.xpath("//input[@id='volume']");
    
    // Buttons
    private By submitBtn = By.xpath("//button[@type='submit' or text()='Submit']");
    private By copyTextBtn = By.xpath("//button[contains(text(),'Copy Text')]");
    private By newWindowBtn = By.xpath("//button[@onclick=\"window.open('https://www.example.com')\"]");
    private By alertBtn = By.xpath("//button[@onclick='myFunctionAlert()']");
    private By confirmBtn = By.xpath("//button[@onclick='myFunctionConfirm()']");
    private By promptBtn = By.xpath("//button[@onclick='myFunctionPrompt()']");
    private By doubleClickBtn = By.xpath("//button[contains(text(),'Copy Text')]");
    
    // Links
    private By wikiLink = By.xpath("//a[@href='https://en.wikipedia.org/wiki/Automation']");
    private By openWindowLink = By.xpath("//a[@href='https://www.example.com']");
    
    // Search Elements
    private By searchBox = By.xpath("//input[@class='wikipedia-search-input']");
    private By searchWikiInput = By.xpath("//input[@id='Wikipedia1_wikipedia-search-input']");
    private By searchBtn = By.xpath("//input[@type='submit' and @class='wikipedia-search-button']");
    
    // File Upload
    private By fileUpload = By.xpath("//input[@type='file']");
    
    // Drag and Drop
    private By dragElement = By.xpath("//div[@id='draggable']");
    private By dropElement = By.xpath("//div[@id='droppable']");
    
    // Tables
    private By paginationTable = By.xpath("//table[@id='productTable']");
    private By paginationRows = By.xpath("//table[@id='productTable']//tbody/tr");
    private By nextPageBtn = By.xpath("//a[@id='nextBtn']");
    private By prevPageBtn = By.xpath("//a[@id='prevBtn']");
    
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void enterName(String name) {
        driver.findElement(nameInput).clear();
        driver.findElement(nameInput).sendKeys(name);
    }
    
    public void enterEmail(String email) {
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
    }
    
    public void enterPhone(String phone) {
        driver.findElement(phoneInput).clear();
        driver.findElement(phoneInput).sendKeys(phone);
    }
    
    public void enterTextarea(String text) {
        driver.findElement(textareaInput).clear();
        driver.findElement(textareaInput).sendKeys(text);
    }
    
    public void selectMaleGender() {
        driver.findElement(maleRadio).click();
    }
    
    public void selectFemaleGender() {
        driver.findElement(femaleRadio).click();
    }
    
    public void checkMonday() {
        WebElement checkbox = driver.findElement(mondayCheckbox);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }
    
    public void checkTuesday() {
        WebElement checkbox = driver.findElement(tuesdayCheckbox);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }
    
    public void checkWednesday() {
        WebElement checkbox = driver.findElement(wednesdayCheckbox);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }
    
    public void checkThursday() {
        WebElement checkbox = driver.findElement(thursdayCheckbox);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }
    
    public void checkFriday() {
        WebElement checkbox = driver.findElement(fridayCheckbox);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }
    
    public void checkSaturday() {
        WebElement checkbox = driver.findElement(saturdayCheckbox);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }
    
    public void checkSunday() {
        WebElement checkbox = driver.findElement(sundayCheckbox);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }
    
    public void uncheckMonday() {
        WebElement checkbox = driver.findElement(mondayCheckbox);
        if (checkbox.isSelected()) {
            checkbox.click();
        }
    }
    
    public void selectCountry(String country) {
        Select select = new Select(driver.findElement(countryDropdown));
        select.selectByVisibleText(country);
    }
    
    public void selectColor(String color) {
        Select select = new Select(driver.findElement(colorDropdown));
        select.selectByVisibleText(color);
    }
    
    public void selectColorByValue(String value) {
        Select select = new Select(driver.findElement(colorDropdown));
        select.selectByValue(value);
    }
    
    public void enterDate(String date) {
        driver.findElement(dateInput).clear();
        driver.findElement(dateInput).sendKeys(date);
    }
    
    public void setSpeedSlider(String value) {
        driver.findElement(speedSlider).sendKeys(value);
    }
    
    public void setVolumeSlider(String value) {
        driver.findElement(volumeSlider).sendKeys(value);
    }
    
    public void clickSubmit() {
        driver.findElement(submitBtn).click();
    }
    
    public void clickCopyText() {
        driver.findElement(copyTextBtn).click();
    }
    
    public void clickNewWindow() {
        driver.findElement(newWindowBtn).click();
    }
    
    public void clickAlert() {
        driver.findElement(alertBtn).click();
    }
    
    public void clickConfirm() {
        driver.findElement(confirmBtn).click();
    }
    
    public void clickPrompt() {
        driver.findElement(promptBtn).click();
    }
    
    public void doubleClickButton() {
        Actions actions = new Actions(driver);
        actions.doubleClick(driver.findElement(doubleClickBtn)).perform();
    }
    
    public void clickWikiLink() {
        driver.findElement(wikiLink).click();
    }
    
    public void clickOpenWindowLink() {
        driver.findElement(openWindowLink).click();
    }
    
    public void searchWikipedia(String searchTerm) {
        driver.findElement(searchWikiInput).clear();
        driver.findElement(searchWikiInput).sendKeys(searchTerm);
        driver.findElement(searchBtn).click();
    }
    
    public void uploadFile(String filePath) {
        driver.findElement(fileUpload).sendKeys(filePath);
    }
    
    public void dragAndDrop() {
        Actions actions = new Actions(driver);
        WebElement source = driver.findElement(dragElement);
        WebElement target = driver.findElement(dropElement);
        actions.dragAndDrop(source, target).perform();
    }
    
    public int getTableRowCount() {
        java.util.List<WebElement> rows = driver.findElements(paginationRows);
        return rows.size();
    }
    
    public String getTableCellValue(int row, int column) {
        By cellLocator = By.xpath("//table[@id='productTable']//tbody/tr[" + row + "]/td[" + column + "]");
        return driver.findElement(cellLocator).getText();
    }
    
    public void clickNextPage() {
        driver.findElement(nextPageBtn).click();
    }
    
    public void clickPreviousPage() {
        driver.findElement(prevPageBtn).click();
    }
    
    public boolean isElementDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getNameValue() {
        return driver.findElement(nameInput).getAttribute("value");
    }
    
    public String getEmailValue() {
        return driver.findElement(emailInput).getAttribute("value");
    }
    
    public boolean isMaleSelected() {
        return driver.findElement(maleRadio).isSelected();
    }
    
    public boolean isFemaleSelected() {
        return driver.findElement(femaleRadio).isSelected();
    }
    
    public void fillForm(String name, String email, String phone, String textarea, String country) {
        enterName(name);
        enterEmail(email);
        enterPhone(phone);
        enterTextarea(textarea);
        selectCountry(country);
    }
}
