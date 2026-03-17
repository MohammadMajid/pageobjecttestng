package com.automationpractice.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.util.Date;


public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports createInstance() {

        String fineName = getReportName();
        String directory = System.getProperty("user.dir") + "/reports/";
        new File(directory).mkdir();
        String path = directory + fineName;

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path);
        //sparkReporter = new ExtentSparkReporter("./reports/extent.html");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("Automation Reports");
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setReportName("Automation Test Results");

        extent = new ExtentReports();
        extent.setSystemInfo("Automation Tester", "Mohammad Majid");
        extent.setSystemInfo("Organization", "MMAJID");
        extent.attachReporter(sparkReporter);
        return extent;
    }

    public static String getReportName(){
        Date d = new Date();
        String fileName = "AutomationReport_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
        return fileName;
    }

}

