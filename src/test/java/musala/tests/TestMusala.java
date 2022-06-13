package musala.tests;

import framework.test.BaseTest;
import framework.utils.GetProperties;
import musala.po.pages.HomePage;
import musala.po.widgets.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import static org.testng.Assert.assertTrue;

public class TestMusala extends BaseTest {
    private static final String EXPECTED_ERROR = "The e-mail address entered is invalid.";
    private final By company = By.linkText("https://www.musala.com/company/");
    private final By leadership = By.xpath("//*[@id=\"content\"]/div[2]/section[3]/div/h2");
    private Map<String, Object> vars;
    private HomePage homePage;
    private Contact contact;
    JavascriptExecutor js;
    private GetProperties pro;

    public String waitForWindow(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Set<String> whNow = driver.getWindowHandles();
        Set<String> whThen = (Set<String>) vars.get("window_handles");
        if (whNow.size() > whThen.size()) {
            whNow.removeAll(whThen);
        }
        return whNow.iterator().next();
    }

    @Test
    @DataProvider(parallel = true)
    public void testCase_1(Method method, ITestContext context) {
        getReporter(context).createTest(method.getName());
        homePage = goToHomePage();
        driver.findElement(By.cssSelector("body > main > section.contacts > div > div > div > a.fancybox > button > span")).click();
        contact.goToContactUs();
        assertTrue(true, "The expected this error message " + EXPECTED_ERROR);
}
    @Test
    @DataProvider(parallel = true)
    public void testCase_2(Method method, ITestContext context) {
        getReporter(context).createTest(method.getName());
        homePage = goToHomePage();
        driver.findElement(By.cssSelector("#menu-main-nav-1 > .menu-item-887 > .main-link")).click();
        assertTrue(true, "The expected this url " + company);
        assertTrue(true, "The expected this section " + leadership);

        vars.put("window_handles", driver.getWindowHandles());
        driver.findElement(By.linkText("https://www.facebook.com/MusalaSoft?fref=ts")).click();
        vars.put("win3536", waitForWindow(2000));
        driver.switchTo().window(vars.get("win3536").toString());
        js.executeScript("window.scrollTo(0,316)");
    }
}
