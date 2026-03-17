package com.automationpractice.utils;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Updated DriverFactory for modern Selenium and thread safety.
 */

public class DriverFactory {

    public enum BrowserType {
        CHROME,
        FIREFOX,
        CLOUD_CHROME,
        CLOUD_FIREFOX,
        CLOUD_IE,
        GRID_CHROME,
        GRID_FIREFOX,
        GRID_IE
    }

    private static DriverFactory instance;

    public static final String USERNAME = "";
    public static final String AUTOMATE_KEY = "";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
    public static final String LOCAL_GRID_URL = "http://localhost:4444/wd/hub";

    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverFactory() {
    }

    public static synchronized DriverFactory getInstance() {
        if (instance == null) {
            instance = new DriverFactory();
        }
        return instance;
    }

    public static synchronized DriverFactory getInstance(String browserName) {
        DriverFactory factory = getInstance();
        factory.createDriver(browserName);
        return factory;
    }

    private void createDriver(String browserName) {
        if (browserName == null || browserName.trim().isEmpty()) {
            throw new IllegalArgumentException("browserName cannot be null or empty");
        }

        if (driver.get() != null) {
            return; // Already initialized for the current thread
        }

        BrowserType type;
        try {
            type = BrowserType.valueOf(browserName.trim().toUpperCase().replace('-', '_'));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unsupported browser type: " + browserName, e);
        }

        switch (type) {
            case CHROME:
                driver.set(createChrome(false));
                break;
            case FIREFOX:
                driver.set(createFirefox(true));
                break;
            case CLOUD_CHROME:
            case CLOUD_FIREFOX:
            case CLOUD_IE:
                driver.set(createCloudDriver(type));
                break;
            case GRID_CHROME:
            case GRID_FIREFOX:
            case GRID_IE:
                driver.set(createGridDriver(type));
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browserName);
        }
    }

    private WebDriver createChrome(boolean headless) {
        ChromeDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if (headless) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--disable-gpu", "--window-size=1920,1080");
        return new ChromeDriver(options);
    }

    private WebDriver createFirefox(boolean headless) {
        FirefoxDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        if (headless) {
            options.addArguments("-headless");
        }
        options.addArguments("--width=1920", "--height=1080");
        return new FirefoxDriver(options);
    }

    private WebDriver createCloudDriver(BrowserType type) {
        if (USERNAME.isEmpty() || AUTOMATE_KEY.isEmpty()) {
            throw new IllegalStateException("Cloud credentials are not configured.");
        }
        DesiredCapabilities caps = new DesiredCapabilities();
        switch (type) {
            case CLOUD_CHROME:
                caps.setCapability("browser", "Chrome");
                break;
            case CLOUD_FIREFOX:
                caps.setCapability("browser", "Firefox");
                break;
            case CLOUD_IE:
                caps.setCapability("browser", "IE");
                break;
            default:
                throw new IllegalArgumentException("Unsupported cloud type: " + type);
        }
        caps.setCapability("browser_version", "latest");
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "11");
        caps.setCapability("resolution", "1920x1080");

        try {
            return new RemoteWebDriver(new URL(URL), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Malformed cloud URL: " + URL, e);
        }
    }

    private WebDriver createGridDriver(BrowserType type) {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setPlatform(Platform.ANY);

        switch (type) {
            case GRID_CHROME:
                caps.setBrowserName("chrome");
                break;
            case GRID_FIREFOX:
                caps.setBrowserName("firefox");
                break;
            case GRID_IE:
                caps.setBrowserName("internet explorer");
                break;
            default:
                throw new IllegalArgumentException("Unsupported grid type: " + type);
        }

        try {
            return new RemoteWebDriver(new URL(LOCAL_GRID_URL), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Malformed grid URL: " + LOCAL_GRID_URL, e);
        }
    }

    public WebDriver getDriver() {
        WebDriver current = driver.get();
        if (current == null) {
            current = createChrome(true);
            driver.set(current);
        }
        return current;
    }

    public void removeDriver() {
        WebDriver current = driver.get();
        if (current != null) {
            current.quit();
            driver.remove();
        }
    }
}
