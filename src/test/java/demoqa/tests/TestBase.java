package demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    @BeforeAll
    static void beforeAll() {

        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.startMaximized = true;
    }

    @BeforeEach
    void openPracticeForm() {
        open("https://demoqa.com/automation-practice-form");
    }
}
