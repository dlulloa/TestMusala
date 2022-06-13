package musala.po.pages;

import framework.po.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Form_ContactUs extends BasePage {
    private By form_pop = By.id("contact_form_pop");

    public Form_ContactUs(WebDriver driver) {
        super(driver);
    }
}
