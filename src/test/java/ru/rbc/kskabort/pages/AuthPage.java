package ru.rbc.kskabort.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class AuthPage {

    public AuthPage() {

    }

/*    public WebDriver driver;*/

/*    @FindBy(id = "login")
    @FindBy(id = "password")
    @FindBy(xpath = "//button[text()='Войти']")*/

    private SelenideElement loginField = $(By.id("login"));
    private SelenideElement passwordField = $(By.id("password"));
    private SelenideElement loginButton = $x("#//button[text()='Войти']");

    public void inputLogin(String login) {
        loginField.sendKeys(login);
    }

    public void inputPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }
}

