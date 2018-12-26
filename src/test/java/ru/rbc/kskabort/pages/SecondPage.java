package ru.rbc.kskabort.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SecondPage {
/*    public SecondPage (WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public WebDriver driver;*/

    @FindBy(how = How.CSS, className = "div.search-item")
    private WebElement searchQuerys;

    public String serchQuerys(int i) {
        return $(searchQuerys + ":nth-child(" + Integer.toString(i) + ") span.search-item__text").toString();
    }

}
