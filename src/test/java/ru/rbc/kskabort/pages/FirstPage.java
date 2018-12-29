package ru.rbc.kskabort.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import ru.rbc.kskabort.tests.ConsoleColors;

import javax.xml.bind.Element;

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
    public void closeOpin() {CloseOpinionPushupButton.click(); System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "PushUp с опросом " + ConsoleColors.RESET);}
    //#dcfdddaf_id_1546095617011 .dcfdddaf-close
    //#dcfdddaf_id_1546096300559 .dcfdddaf-close


   /* public void inputPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }*/
}
