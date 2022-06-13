package framework.utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class ScreenShotUtils {
    public static void takeScreenShot(final WebDriver webDriver, final Reporter reporter, final Status status, final String details) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot)webDriver;
        String screenshot = takesScreenshot.getScreenshotAs(OutputType.BASE64);
        reporter.getExtentTest().log(status, details, MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
    }
}
