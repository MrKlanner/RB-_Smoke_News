package ru.rbc.kskabort.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.interactions.Actions;
import ru.rbc.kskabort.ConsoleColors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class StaticPageObjects {

    public StaticPageObjects () {
        toplineLogo.shouldBe(Condition.visible);
    }

    /**Инициализация*/

    //Лого РБК
    public SelenideElement toplineLogo = $(".topline__logo");

    //Поиск
    private SelenideElement mainSearchButton = $("div.topline__search__menu.js-search-open");
    private SelenideElement inputSearch = $("input.topline__search__input");
    private SelenideElement searchButton = $("input.topline__search__button");

    //ПРЯМОЙ ЭФИР
    //Общие элементы
    public SelenideElement PauseButton = $(".vjs-play-control"); //Общая пауза
    public SelenideElement Mute = $(".vjs-mute-control"); //Общий  mute
    //TOPLINE размер плеера
    public SelenideElement TopPlayButton = $(".live-tv__main__inner"); //запуск прямого эфира из топлайна
    private SelenideElement TopPlay_v8 = $(".topline__video-block");
    public SelenideElement TopChangeView = $(".vjs-change-view-control");
    //MEDIUM размер плеера
    public SelenideElement PreRollSkip = $(".vast-skip-button");
    private SelenideElement MidSizeClose = $(".live-tv__overlay__navbar__close");
    public SelenideElement BigPlayButton = $(".vjs-big-play-button");
    private SelenideElement MidQualityBtn = $(".vjs-set-quality");
    private SelenideElement MidQualityElement = $(".vjs-select-quality-item"); //вызов обязательно из функции
    public SelenideElement MidPlayer = $("#vjs_video");
    //FULLSCREEN размер плеера
    private SelenideElement FullQualityBtn = $(".js-videojs-quality-btn");

    //FOOTER
    public SelenideElement Footer_link = $(".footer__link");
    public SelenideElement Footer_item = $("footer__item");
    public SelenideElement Footer_col = $(".footer__col");
    //(weight < 480)
    public SelenideElement Active_lnk = $(".active");

    private SelenideElement sliderTrack = $(".vjs-volume-horizontal");
    private SelenideElement slider = $(".vjs-volume-level");

    /*.footer__subcol:nth-child(1) .footer__item:nth-child(4) > .footer__link
    .footer__col_custom .footer__item:nth-child(2) > .footer__link
    .active:nth-child(2) > .footer__link
    .active:nth-child(1) > .footer__link:nth-child(1)
    //Проверка ползунка громкости
    // http://internetka.in.ua/selenium-drag-and-drop/
    assertEquals(getCurrentPosition(sliderTrack, slider), 100);
    //        setSliderPosition(20, sliderTrack, slider);
    assertEquals(getCurrentPosition(sliderTrack, slider), 20);

    */
    /*FULLSCREEN размер плеера
    $(".vjs-fullscreen-control").click(); sleep(500);
    //Проверка смены качества
        for (int i = 1; i<= 4; i++) {
        $(".js-videojs-quality-btn").click();
        $(".vjs-select-quality-item:nth-child(".concat(Integer.toString(i)) + ")").click();
        sleep(1000);
    }
    $(".vjs-play-control").click(); sleep(1000);
    $(".vjs-big-play-button").click();sleep(1000);
    $(".vjs-fullscreen-control").click(); //Выход из фуллскрина*/

    /**Методы*/

    public void searchQuery(String query) {
        mainSearchButton.click();
        inputSearch.sendKeys(query);
        searchButton.click();
    }

    public void ChangeQuality()
    {
        //Проверка смены качества
        for (int i = 1; i<= 4; i++) {
            MidQualityBtn.click();
            $(".vjs-select-quality-item:nth-child(".concat(Integer.toString(i)) + ")").click(); sleep(1000); //.vjs-select-quality-item:nth-child(4)
        }
    }

    public String getLentaUrl( int i) {
        if (i != 4 && i !=16 && i != 28 && i != 40 && i != 52 && i != 64)
/*            return $(".news-feed__item:nth-child(".concat(Integer.toString(i)) + ")").attr("href");
        if (i%5 == 0)*/
            return $(".news-feed__item:nth-child(".concat(Integer.toString(i)) + ")").scrollTo().attr("href");
        else return new Error(ConsoleColors.RED_BOLD + "No such lenta element"+ ConsoleColors.RESET).getMessage();
    }
    public void clickLenta( int i) {
        //$(".js-news-feed-item:nth-child(".concat(Integer.toString(i)) + ")").click();
        $$(".news-feed__item").get(i).scrollTo().click();
    }
    /*public String getLentaText( int i) {
        if (i != 4)
            return $(".news-feed__item:nth-child(".concat(Integer.toString(i)) + ") .news-feed__item__title").getText();
        else return new Error(ConsoleColors.RED_BOLD + "No such lenta element"+ ConsoleColors.RESET).getMessage();
    }*/

    public SelenideElement getTopItem(int i, String s, int max_c, int max_add) {
        /*final int MAX_INDEX_TOP = 22;
        final int MAX_INDEX_TOP_ADD = 2;*/
         if (i < max_c && s.equals("common")) {
             i += 1;
             return $(".topline__item:nth-child(".concat(Integer.toString(i)) + ")");
         }
         else if (i < max_add && s.equals("add")) {
             i += 1;
             return $(".topline__item_special:nth-child(".concat(Integer.toString(i)) + ")");
         }
         else throw new Error("Something going wrong in getTopItem Case!");
    }

    private long getCurrentPosition(SelenideElement sliderTrack, SelenideElement slider) { //.vjs-volume-level
        // Позицию можно получить по значению атрибута left
        // getCssValue("left") возвращает абсолютное значение в px,
        long sliderCenterPx = Long.parseLong(slider.getCssValue("aria-valuenow") + slider.getSize().width / 2);
        return sliderCenterPx / sliderTrack.getSize().width + 1; //??
    }
    public void setSliderPosition(long position) {
        if (position < 1 || position > 100) {
            throw new AssertionError("Slider position value should be in the range of 1 to 100");
        }
        Actions actions = new Actions(getWebDriver());
        actions
                .dragAndDropBy(slider,10,0) // смещение в пикселях
                .build()
                .perform();
/*        *//*actions.mouse$(".vjs-volume-bar").*//*
        long xOffset = (position - getCurrentPosition(sliderTrack, slider)) * Long.parseLong(sliderTrack.getValue()); //??
        actions.dragAndDropBy(slider, (int) xOffset, 0).perform();*/
    }
}