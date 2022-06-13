package musala.po.widgets;

import framework.po.BaseElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Contact extends BaseElement {
    private By titleBy = By.xpath("/html/body/main/section[2]/div/div/div/a[1]/button");
    public Contact(WebDriver driver, WebElement container) {
        super(driver, container);
    }

    public void goToContactUs() {
        WebElement article = getContainer().findElement(titleBy);
        scrollAndClick(article);
    }
}
