package ru.rbc.kskabort.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SecondPage {
    public SecondPage (WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public WebDriver driver;

    @FindBy(css = "div.search-item")
    private WebElement searchQuerys;

    public String serchQuerys(int i) {
        return driver.findElement(By.cssSelector(searchQuerys + ":nth-child(" + Integer.toString(i) + ") span.search-item__text")).getText();
    }

}
