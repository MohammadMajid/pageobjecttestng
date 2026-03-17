package com.automationpractice.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

public class ExtentListeners implements ITestListener {

    private static ExtentReports extent = ExtentManager.createInstance();
    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

    public void onTestStart(ITestResult result) {

        ExtentTest test = extent.createTest("@TestCase : "+result.getMethod().getMethodName());
        extentTest.set(test);
    }

    public void onTestSuccess(ITestResult result) {

        String methodName=result.getMethod().getMethodName();
        String logText="<b>"+"TEST CASE:- "+ methodName.toUpperCase()+ "-> PASSED"+"</b>";
        Markup m= MarkupHelper.createLabel(logText, ExtentColor.GREEN);
        extentTest.get().log(Status.PASS, m);
    }

    public void onTestFailure(ITestResult result) {

        String methodName = result.getMethod().getMethodName();
        String excepionMessage = Arrays.toString(result.getThrowable().getStackTrace());
        extentTest.get().fail("<details><summary><b><font color='red'>" +
                "Exception Occurred: Click to see details:" +
                "</font></b></summary>" +
                excepionMessage.replaceAll(",", "<br>") + "</details>\n");

        WebDriver driver = DriverFactory.getInstance().getDriver();
        String path = takeScreenshot(driver, methodName);
        extentTest.get().fail("<b><font color=red>" + "Screenshot of failure" + "</font></b>",
                MediaEntityBuilder.createScreenCaptureFromBase64String(path).build());

        String failureLogg = "<b>Test Method : " + methodName.toUpperCase() + "-> Failed</b>";
        Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
        extentTest.get().log(Status.FAIL, m);
    }

    public void onTestSkipped(ITestResult result) {
        String methodName=result.getMethod().getMethodName();
        String logText="<b>"+"Test Case:- "+ methodName+ "-> Skipped"+"</b>";
        Markup m=MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
        extentTest.get().log(Status.SKIP, m);
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub

    }

    public void onStart(ITestContext context) {



    }

    public void onFinish(ITestContext context) {

        if (extent != null) {
            extent.flush();
        }
    }

    public String takeScreenshot(WebDriver driver, String methodName){
        String fileName = getScreenShotName(methodName);
        String directory = System.getProperty("user.dir") + "/screenshots/";

        new File(directory).mkdir();
        String path = directory + fileName;
        try {
            File screenshot = ((TakesScreenshot) DriverFactory.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(path));
            System.out.println("***********************");
            System.out.println(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    public static String getScreenShotName(String methodName){
        Date d = new Date();
        String fileName = methodName + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".jpeg";
        return fileName;
    }

}