package framework.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BaseElement extends BasePO {
    private WebElement container;

    public BaseElement(final WebDriver driver, final WebElement container) {
        super(driver);
        this.container = container;
    }

    public WebElement getContainer() {
        return container;
    }
}
