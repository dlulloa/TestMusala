package framework.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class DriverManager {
    private static ThreadLocal<WebDriver> sharedDriver = new ThreadLocal<>();
    private static final Logger LOGGER = Logger.getLogger(DriverManager.class);
    private static final GetProperties properties = new GetProperties();
    private static final String browser = properties.getString("browser").toUpperCase();
    private static final int IMPLICIT_TIMEOUT = 20;

    public WebDriver getDriver() {
        if (sharedDriver.get() == null) {
            setWebDriver(createDriver());
            getWebDriver().manage().window().maximize();
            getWebDriver().manage().timeouts().implicitlyWait(IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
        }
        return getWebDriver();
    }

    private WebDriver createDriver() {
        WebDriver driver;
        if (browser.equals("FIREFOX")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(OptionsManager.getFirefoxOptions());
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(OptionsManager.getChromeOptions());
        }
        return driver;
    }

    private WebDriver getWebDriver() {
        return sharedDriver.get();
    }

    private void setWebDriver(WebDriver driver) {
        sharedDriver.set(driver);
    }

    public String getBrowser() {
        return browser;
    }

    public void quitDriver() {
        if (sharedDriver.get() != null) {
            LOGGER.info("Ending execution");
            getWebDriver().quit();
            sharedDriver.remove();
        }
    }
}
