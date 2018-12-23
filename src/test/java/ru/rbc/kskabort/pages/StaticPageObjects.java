package ru.rbc.kskabort.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.rbc.kskabort.tests.ConsoleColors;

public class StaticPageObjects {

    public StaticPageObjects (WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    private WebDriver driver;

    //Лого РБК
    @FindBy(css = ".topline__logo") //.topline__logo
    public WebElement toplineLogo;

    /*//Ссылки в топлайне
    @FindBy(css = ".topline__item")
    public WebElement topItem;
    @FindBy(css = ".topline__item_special")
    public WebElement topItemSpecial;*/

    //Поиск
    @FindBy(css = "div.topline__search__menu.js-search-open")
    private WebElement mainSearchButton;
    @FindBy(css = "input.topline__search__input")
    private WebElement inputSearch;
    @FindBy(css = "input.topline__search__button")
    private WebElement searchButton;

    /*//Лента новостей
    @FindBy(css = ".news-feed__item") //
    private WebElement lentaElement;*/

    public void searchQuery(String query) {
        mainSearchButton.click();
        inputSearch.sendKeys(query);
        searchButton.click();
    }

    public String getLentaUrl( int i) {
        if (i != 4)
            return driver.findElement(By.cssSelector(".news-feed__item:nth-child(" + Integer.toString(i) + ")")).getAttribute("href");
        else return new Error(ConsoleColors.RED_BOLD + "No such lenta element"+ ConsoleColors.RESET).getMessage();
    }
    public void clickLenta( int i) {
        driver.findElement(By.cssSelector(".news-feed__item:nth-child(" + Integer.toString(i) + ")")).click();
    }
    public String getLentaText( int i) {
        if (i != 4)
            return driver.findElement(By.cssSelector(".news-feed__item:nth-child(" + Integer.toString(i) + ") .news-feed__item__title")).getText();
        else return new Error(ConsoleColors.RED_BOLD + "No such lenta element"+ ConsoleColors.RESET).getMessage();
    }

    public WebElement getTopItem(int i, String s, int max_c, int max_add) {
        /*final int MAX_INDEX_TOP = 22;
        final int MAX_INDEX_TOP_ADD = 2;*/
         if (i < max_c && s.equals("common")) {
             i += 1;
             return driver.findElement(By.cssSelector(".topline__item:nth-child(" + Integer.toString(i) + ")"));
         }
         else if (i < max_add && s.equals("add")) {
             i += 1;
             return driver.findElement(By.cssSelector(".topline__item_special:nth-child(" + Integer.toString(i) + ")"));
         }
         else throw new Error("Something going wrong in getTopItem Case!");
    }
}
//.topline__item_special:nth-child(2)
//.topline__item_cnews:nth-child(8)