package framework.po;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class BasePO {
    protected WebDriver driver;
    protected Actions actions;

    public BasePO(final WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(this.driver);
    }

    public void scrollAndClick(final WebElement element) {
        JavascriptExecutor jsExecutor = ((JavascriptExecutor) driver);
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
        jsExecutor.executeScript("arguments[0].click();", element);
    }
}
