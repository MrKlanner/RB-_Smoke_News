package ru.rbc.kskabort.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SecondPage extends StaticPageObjects {
    public SecondPage () {
        toplineLogo.shouldBe(Condition.visible);
    }
    /*@FindBy(how = How.CSS, className = "div.search-item")*/
    //private SelenideElement searchQuerys = $(".search-item:nth-child(1) .search-item__text");

    public String serchQuerys(int i) {
        return $(".search-item:nth-child(" + Integer.toString(i) + ") span.search-item__text").getText();
    }

}
