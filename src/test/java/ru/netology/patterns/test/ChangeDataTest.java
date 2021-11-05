package ru.netology.patterns.test;

import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.patterns.data.DataGenerator;
import java.time.Duration;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.$;

public class ChangeDataTest {
    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldBookMeetingAndChangeData() {

        int daysBeforeFirstMeeting = 4; // дни до первой встречи
        int daysBeforeSecondMeeting = 7; // дни до второй встречи
        var firstMeeting = DataGenerator.generateDate(daysBeforeFirstMeeting); // дата первой встречи
        var secondMeeting = DataGenerator.generateDate(daysBeforeSecondMeeting); // дата второй встречи

        $("[data-test-id=city] input").setValue(DataGenerator.generateCity("ru"));

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE, firstMeeting);
        $("[name='name']").setValue(DataGenerator.generateName());
        $("[name='phone']").setValue(DataGenerator.generatePhone());
        $(withText("Я соглашаюсь с условиями обработки и использования моих персональных данных")).click();
        $(withText("Запланировать")).click();

        //проверка, что первая встреча забронирована
        $("[data-test-id='success-notification'] .notification__content").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Встреча успешно запланирована на " + firstMeeting));

        //меняем дату
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE, secondMeeting);
        $(withText("Запланировать")).click();

        //есть всплывающее окно с кнопкой?
        $(withText("У вас уже запланирована встреча на другую дату. Перепланировать?")).shouldBe(visible);

        // жмем на кнопку
        $(Selectors.byText("Перепланировать")).click();

        //проверка, что встреча перепланирована
        $("[data-test-id='success-notification'] .notification__content").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Встреча успешно запланирована на " + secondMeeting));

    }
}
