package ru.rbc.kskabort.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.rbc.kskabort.tests.ConsoleColors;

import static com.codeborne.selenide.Selenide.$;

public class StaticPageObjects {

    public StaticPageObjects () {
        toplineLogo.shouldBe(Condition.visible);
    }

    //Лого РБК
    //@FindBy(css = ".topline__logo") //.topline__logo
    public SelenideElement toplineLogo = $(".topline__logo");

    /*//Ссылки в топлайне
    @FindBy(css = ".topline__item")
    public SelenideElement topItem;
    @FindBy(css = ".topline__item_special")
    public SelenideElement topItemSpecial;*/

    //Поиск
    /*@FindBy(css = "div.topline__search__menu.js-search-open")*/
    /*@FindBy(css = "input.topline__search__input")*/
    /*@FindBy(css = "input.topline__search__button")*/
    private SelenideElement mainSearchButton = $("div.topline__search__menu.js-search-open");
    private SelenideElement inputSearch = $("input.topline__search__input");
    private SelenideElement searchButton = $("input.topline__search__button");

    /*//Лента новостей
    @FindBy(css = ".news-feed__item") //
    private SelenideElement lentaElement;*/

    public void searchQuery(String query) {
        mainSearchButton.click();
        inputSearch.sendKeys(query);
        searchButton.click();
    }

    public String getLentaUrl( int i) {
        if (i != 4)
            return $(".news-feed__item:nth-child(" + Integer.toString(i) + ")").attr("href");
        else return new Error(ConsoleColors.RED_BOLD + "No such lenta element"+ ConsoleColors.RESET).getMessage();
    }
    public void clickLenta( int i) {
        $(".news-feed__item:nth-child(" + Integer.toString(i) + ")").click();
    }
    public String getLentaText( int i) {
        if (i != 4)
            return $(".news-feed__item:nth-child(" + Integer.toString(i) + ") .news-feed__item__title").getText();
        else return new Error(ConsoleColors.RED_BOLD + "No such lenta element"+ ConsoleColors.RESET).getMessage();
    }

    public SelenideElement getTopItem(int i, String s, int max_c, int max_add) {
        /*final int MAX_INDEX_TOP = 22;
        final int MAX_INDEX_TOP_ADD = 2;*/
         if (i < max_c && s.equals("common")) {
             i += 1;
             return $(".topline__item:nth-child(" + Integer.toString(i) + ")");
         }
         else if (i < max_add && s.equals("add")) {
             i += 1;
             return $(".topline__item_special:nth-child(" + Integer.toString(i) + ")");
         }
         else throw new Error("Something going wrong in getTopItem Case!");
    }
}
//.topline__item_special:nth-child(2)
//.topline__item_cnews:nth-child(8)