package framework.utils;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class OptionsManager {
    public static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-extensions");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--allow-insecure-localhost");
        options.addArguments("--whitelisted-ips");
        options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking",
                "enable-automation"));
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        options.setExperimentalOption("prefs", prefs);
        return options;
    }

    public static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.setAcceptInsecureCerts(true);
        FirefoxProfile fprofile = new FirefoxProfile();
        fprofile.setAcceptUntrustedCertificates(true);
        fprofile.setAssumeUntrustedCertificateIssuer(false);
        options.setProfile(fprofile);
        return options;
    }
}
