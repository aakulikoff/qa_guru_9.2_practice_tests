package demoqa.properties;

import config.CredentialsConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;

import static io.qameta.allure.Allure.step;
import static java.lang.String.format;

@Tag("properties")
public class OwnerTests {
    public CredentialsConfig credentials =
            ConfigFactory.create(CredentialsConfig.class);

    @Test
    void readCredentialsTest() {
        step("Берем данные из credentials.properties", () -> {
            String login = credentials.login();
            String password = credentials.password();

            String message = format("https://%s:%s@selenoid.autotests.cloud/wd/hub/", login, password);

            System.out.println("Логин" + login);
            System.out.println("Пароль" + password);
            System.out.println("Адрес удаленного доступа:" + message);
        });
    }
}
