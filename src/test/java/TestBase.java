import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.*;

public class TestBase {

    @BeforeAll
    static void beforeAll() {;
        Configuration.baseUrl = "https://www.acron.ru/";
        Configuration.browserSize = "1920x1080";
        Configuration.browser = System.getProperty("browser", "chrome");

//        Configuration.browserVersion = System.getProperty("version", "91");

//        //password and user for remote browser
//        String user = System.getProperty("user", "user1");
//        String password = System.getProperty("password", "1234");
//
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("enableVNC", true);
//        capabilities.setCapability("enableVideo", true);
//        Configuration.browserCapabilities = capabilities;
//        Configuration.remote = "https://" + user + ":" + password + "@" + System.getProperty("remoteBrowser");

        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Скриншот");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        sleep(5000);
        closeWebDriver();
    }
}