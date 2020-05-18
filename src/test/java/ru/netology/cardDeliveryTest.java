package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class cardDeliveryTest {
    /*String city;
    int addDays;
    String pattern;
    String name;
    String phone;
    SelenideElement blockDate = $("[data-test-id='date']");*/

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    /*@AfterEach
    void teatDown() {
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='notification']").find(withText("Успешно!")).waitUntil(visible, 15000);
    }*/

    @ParameterizedTest
    @DisplayName("Should input the form for delivery a card (\"Happy Path\")")
    @CsvFileSource(resources = "/CardDeliveryData.csv", numLinesToSkip = 2)
    void shouldInputFormCorrectly(String city, int addDays, String pattern, String name, String phone) {
        SelenideElement blockDate = $("[data-test-id='date']");

        $("[data-test-id='city'] .input__control").setValue(city);
        blockDate.$(".input__control").sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
        blockDate.$(".input__control").setValue(setDateDelivery(addDays, pattern));
        $("[data-test-id='name'] .input__control").setValue(name);
        $("[data-test-id='phone'] .input__control").setValue(phone);
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='notification']").find(withText("Успешно!")).waitUntil(visible, 15000);
    }

    @Test
    @DisplayName("Should input the form for delivery a card as by hand")
    void shouldInputFormAsByHand() {
        String city = "Тверь";
        int addDays = 33;
        String pattern = "dd.MM.yyyy";
        String name = "Филиппов Петр";
        String phone = "+79195412583";
        SelenideElement blockDate = $("[data-test-id='date']");

        $("[data-test-id='city'] .input__control").setValue(city);
        blockDate.$(".input__control").sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
        blockDate.$(".input__control").setValue(setDateDelivery(addDays, pattern));
        $("[data-test-id='name'] .input__control").setValue(name);
        $("[data-test-id='phone'] .input__control").setValue(phone);
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='notification']").find(withText("Успешно!")).waitUntil(visible, 15000);
    }

    private String setDateDelivery(int addDays, String pattern) {
        LocalDate now = LocalDate.now();
        LocalDate dateDelivery = now.plusDays(addDays);
        DateTimeFormatter datePointsFormatter = DateTimeFormatter.ofPattern(pattern);
        String inputDateDelivery = datePointsFormatter.format(dateDelivery);
        return inputDateDelivery;
    }
}
