package framework.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class Reporter {
    private static final String REPORT_PATH = "report/report.html";
    private static final String REPORT_TITLE = "TESTMUSALA - AUTOMATED TESTS REPORT";

    private ExtentHtmlReporter extentHtmlReporter;
    private ExtentReports extentReports;
    private ExtentTest extentTest;

    public Reporter() {
        extentHtmlReporter = new ExtentHtmlReporter(REPORT_PATH);
        extentReports = new ExtentReports();
    }

    public void setupReport(final String browser) {
        extentHtmlReporter.config().setDocumentTitle(REPORT_TITLE);
        extentHtmlReporter.config().setTheme(Theme.STANDARD);

        extentReports.attachReporter(extentHtmlReporter);
        extentReports.setSystemInfo("Browser", browser);
    }

    public void flush() {
        extentReports.flush();
    }

    public void createTest(final String name) {
        extentTest = extentReports.createTest(name);
    }

    public ExtentTest getExtentTest() {
        return extentTest;
    }
}
