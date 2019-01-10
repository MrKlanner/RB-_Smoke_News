package ru.rbc.kskabort.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.rbc.kskabort.ConsoleColors;
import ru.rbc.kskabort.tests.TabActions;

import java.security.Key;

import static com.codeborne.selenide.Selenide.*;

public class FirstPage {

    public FirstPage () {
        CloseSubPushupButton.shouldBe(Condition.visible);
    }

    private SelenideElement CloseSubPushupButton = $(".push-allow__button_no");
    private SelenideElement CloseOpinionPushupButton = $(".dcfdddaf-close");
    /*SelenideElement SubPushup;*/
    /*@FindBy(css = ".push-allow__button_no")
    public SelenideElement SubPushup;*/

    /*@FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[text()='Войти']")
    private WebElement loginButton;*/

    public void closeSub() {CloseSubPushupButton.click(); System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "PushUp с подпиской закрыт" + ConsoleColors.RESET);}
    public void closeOpin() { TabActions.PressEscape();/*CloseOpinionPushupButton.click();*/ System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "PushUp с опросом закрыт " + ConsoleColors.RESET);}
    //#dcfdddaf_id_1546095617011 .dcfdddaf-close
    //#dcfdddaf_id_1546096300559 .dcfdddaf-close


   /* public void inputPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }*/
}
