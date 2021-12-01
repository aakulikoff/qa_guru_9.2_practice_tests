package demoqa.tests;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class PracticeForm {

    @BeforeAll
    static void beforeAll () {
        Configuration.startMaximized = true;
    }


    @BeforeEach
    void openPracticeForm(){
        open("https://demoqa.com/automation-practice-form");
    }

    String  firstname = "Name",
            lastName = "Surname",
            userEmail = "testemail@mail.com",
            userNumber = "9449440101",
            gender = "Female",
            monthOfBirth = "August",
            yearOfBirth = "1988",
            dayOfBirth = "3",
            subject = "Arts",
            picture = "pic.jpg",
            hobby = "Music",
            currentAddress = "Russia, Moscow, Red Square",
            state = "NCR",
            city = "Delhi";



    @Test
    void registrationFormTest () {

        step("Open students registration form", () ->
            $(".practice-form-wrapper")
                .shouldHave(text("Student Registration Form")));

        step("Fill students registration form", () -> {
            step("Fill user data", () -> {
                $("#firstName").setValue(firstname);
                $("#lastName").setValue(lastName);
                $("#userEmail").setValue(userEmail);
                $(byText(gender)).click();
                $("#userNumber").setValue(userNumber);
            });

            step("Set date", () -> {
                $("#dateOfBirthInput").click();
                $(".react-datepicker__month-select").selectOption(monthOfBirth);
                $(".react-datepicker__year-select").selectOption(yearOfBirth);
                $("[aria-label='Choose Wednesday, August 3rd, 1988']").click();
            });
            step("Set subject", () ->
                $("#subjectsInput").val(subject).pressEnter());
            step("Set hobbies", () ->
                $("#hobbiesWrapper").$(byText(hobby)).click());
//        $(byText(hobby)).click();

            step("Upload image", () ->
                $("#uploadPicture").uploadFromClasspath("img/" + picture));

            step("Set address", () -> {
                $("#currentAddress").val(currentAddress);
                $("#state").scrollTo().click();
                $("#stateCity-wrapper").$(byText(state)).click();
//        $(byText(state)).click();
                $("#city").click();
//        $(byText(city)).click();
                $("#stateCity-wrapper").$(byText(city)).click();
            });

            step("Submit form", () ->
                $("#submit").click());
        });

        step("Verify successful form submit", () -> {
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
            $(".table-responsive").shouldHave(text(firstname + " " + lastName),
                text(userEmail), text(gender));
//        Проверка данных во всплывающем окне
            $(byText("Student Name")).parent()
                .shouldHave(text(firstname + " " + lastName));
//        $x("//td[text()='Student Name']").parent().shouldHave(text(firstname + " " + lastName));
            $x("//td[text()='Student Email']").parent().shouldHave(text(userEmail));
            $x("//td[text()='Gender']").parent().shouldHave(text(gender));
            $x("//td[text()='Mobile']").parent().shouldHave(text(userNumber));
            $x("//td[text()='Date of Birth']").parent().shouldHave(text(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth));
            $x("//td[text()='Subjects']").parent().shouldHave(text(subject));
            $x("//td[text()='Hobbies']").parent().shouldHave(text(hobby));
            $x("//td[text()='Picture']").parent().shouldHave(text(picture));
            $x("//td[text()='Address']").parent().shouldHave(text(currentAddress));
            $x("//td[text()='State and City']").parent().shouldHave(text(state + " " + city));
        });
    }
}
