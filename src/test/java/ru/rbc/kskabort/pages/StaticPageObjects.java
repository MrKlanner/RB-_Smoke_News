package ru.rbc.kskabort.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ru.rbc.kskabort.ConsoleColors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

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
    private SelenideElement PauseButton = $(".vjs-play-control");
    //TOPLINE размер плеера
    private SelenideElement TopPlayButton = $(".live-tv__main__inner"); //запуск прямого эфира из топлайна
    private SelenideElement TopMute = $(".vjs-mute-control");
    private SelenideElement TopPlay_v8 = $(".topline__video-block");
    private SelenideElement TopChangeView = $(".vjs-change-view-control");
    //MEDIUM размер плеера
    private SelenideElement PrePollSkip = $(".vast-skip-button");
    private SelenideElement MidSizeClose = $(".live-tv__overlay__navbar__close");
    private SelenideElement BigPlayButton = $(".vjs-big-play-button");
    private SelenideElement MidQualityBtn = $(".js-videojs-quality-span");
    private SelenideElement MidQualityElement = $(".vjs-select-quality-item"); //вызов обязательно из функции
    //FULLSCREEN размер плеера
    private SelenideElement FullQualityBtn = $(".js-videojs-quality-btn");

    /*
    //Проверка ползунка громкости
    // http://internetka.in.ua/selenium-drag-and-drop/
    SelenideElement sliderTrack = $(".vjs-volume-horizontal");
    SelenideElement slider = $(".vjs-volume-level");
    assertEquals(getCurrentPosition(sliderTrack, slider), 100);
    //        setSliderPosition(20, sliderTrack, slider);
    assertEquals(getCurrentPosition(sliderTrack, slider), 20);

    */
    /*FULLSCREEN размер плеера
    $(".vjs-fullscreen-control").click(); sleep(500);
    //Проверка смены качества
        for (int i = 1; i<= 4; i++) {
        $(".js-videojs-quality-btn").click();
        $(".vjs-select-quality-item:nth-child(" + Integer.toString(i) + ")").click();
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
            $(MidQualityElement.append(Integer.toString(i)).append(")")).click();
        }
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