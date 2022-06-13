package framework.test;

import com.aventstack.extentreports.Status;
import framework.utils.DriverManager;
import framework.utils.GetProperties;
import framework.utils.Reporter;
import framework.utils.ScreenShotUtils;
import musala.po.pages.HomePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.util.Arrays;

public class BaseTest {
    private static final Logger LOGGER = Logger.getLogger(BaseTest.class);
    protected static GetProperties properties = new GetProperties();
    private static final String URL = properties.getString("URL");
    protected WebDriver driver;
    protected DriverManager driverManager = new DriverManager();


    @BeforeSuite(alwaysRun = true)
    public void setupReport(ITestContext context) {
        Reporter reporter = new Reporter();
        //reporter.setupReport(driverManager.getBrowser());
        context.setAttribute("reporter", reporter);
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = driverManager.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result, ITestContext context) {
        Reporter reporter = getReporter(context);
        try {
            switch (result.getStatus()){
                case ITestResult.FAILURE:
                    reporter.getExtentTest().log(Status.FAIL, "Test failed");
                    break;
                case ITestResult.SKIP:
                    reporter.getExtentTest().log(Status.SKIP, "Test skipped");
                    break;
                case ITestResult.SUCCESS:
                    reporter.getExtentTest().log(Status.PASS, "Test passed");
                    break;
                default:
                    reporter.getExtentTest().log(Status.FAIL, "Test incomplete");
            }

            if(result.getStatus() != ITestResult.SUCCESS && result.getThrowable() != null){
                getReporter(context).getExtentTest().log(Status.FAIL, result.getThrowable().getMessage());
                ScreenShotUtils.takeScreenShot(driverManager.getDriver(), reporter, Status.FAIL, "Image");
            }

        } catch (Exception e) {
            LOGGER.error("Something failed taking the screenshot: \n" + Arrays.toString(e.getStackTrace()));
        }finally {
            if(driverManager.getDriver() != null)
                driverManager.quitDriver();
        }

    }

    @AfterSuite(alwaysRun = true)
    public void flush(ITestContext context) {
        getReporter(context).flush();
    }
    protected HomePage goToHomePage() {
        driver.get(URL);
        return new HomePage(driver);
    }
    protected Reporter getReporter(ITestContext context){
        return ((Reporter)context.getAttribute("reporter"));
    }
}
