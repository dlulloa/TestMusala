package musala.po.pages;

import framework.po.BasePage;
import org.openqa.selenium.WebDriver;


public class HomePage extends BasePage {

   public HomePage(WebDriver driver) {
       super(driver);
    }

    private Form_ContactUs goToContact(final String contact) {
        switch (contact) {
            case "ContactUs":
                driver.get("https://www.musala.com/#contact_form_pop");
                break;
        }
        return new Form_ContactUs(driver);
    }
}
