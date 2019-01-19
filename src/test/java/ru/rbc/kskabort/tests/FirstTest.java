package ru.rbc.kskabort.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.rbc.kskabort.ConsoleColors;
import ru.rbc.kskabort.pages.FirstPage;
import ru.rbc.kskabort.pages.SecondPage;
import ru.rbc.kskabort.pages.StaticPageObjects;
import ru.rbc.kskabort.pages.URLs;
import ru.rbc.kskabort.pages.URLs.Prod;
import ru.rbc.kskabort.pages.URLs.Staging;

import java.awt.*;
import java.util.ArrayList;

import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;
import static ru.rbc.kskabort.pages.URLs.SPLIT;

//import static org.junit.Assert.assertEquals;

//import org.junit.*;

/*import org.openqa.selenium.*;
/*import org.openqa.selenium.chrome.ChromeDriver;*/
/*import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;*/
/*import org.openqa.selenium.interactions.Actions;*/

/**Планы:
 * 1) Проверка подгрузки пока что в разработке. Хотелось бы реализовать с таймером,
 *    параллельно считающим во время работы программы и в определенный момент (5-10 мин.)
 *    срабтывающий, давая тем самым отмашку допроверять автоподгрузку)
 * 2) Обработка исключений (не найден элемент и т.д.) - частично реализовано в Фраймфорке Selenide
 * 3) Выполнять проверку загрузки каждый раз и приостанавливать загрузку если больше какого-то определенного промежутка времени
 *
 *
 * СМОК:**/

public class FirstTest extends Assert {

    private static FirstPage firstPage;
    private static SecondPage secondPage;
    private static StaticPageObjects staticPageObjects;

    /*private static WebDriver driver;*/
    //private StringBuffer verificationErrors = new StringBuffer();
/*    public static FirstPage firstPage;
    public static SecondPage secondPage;
    public static StaticPageObjects staticPageObjects;*/
/*    private final String site = TestSite();*/

    /**
     * 1. Общие элементы
     *
     * Единый топлайн (прокликивание ссылок)
     * Прямой эфир (перетыкать все переключатели)
     * Логика отображения данных в карточках на проекте rbc.ru (Порядок как в публикаторе главное (?))
     * Лента новостей (Редирект + 404)
     * Ключевые индикаторы (Проверка значений)
     * Подвал (Наличие ссылок + всех блоков)
     * --------------------------
     *
     * 2. Главная страница
     *
     * Блоки главной страницы
     * Блоки главное
     * Блок Центральная колонка
     *
     * Бесконечная главная
     * Блок Опросы
     * Главные страницы регионов
     * Блок Подписка на рассылку:
     * - Подтверждение подписки из письма
     * - Отписка от рассылки
     * ---------------------------
     *
     * 3. Логика формирования новостей на главной (Типы пользователей)
     *
     * Новые пользователи. При куках main-short и main-long отображается блок "Главное за сутки"
     * (на 01.01.19) - отображается пебликатор Главное
     *
     * При закрытии и открытии браузера как "Новый пользователей" продолжает отображаться "Главное за сутки".
     *
     * Старые пользовтели. Если удалить куку main-short отображается блок "Главное".
     * ----------------------------
     *
     * 4.Страница с удаленным текстом по решению суда
     * Открыть страницу и проверить, что ничего не разъехалось
     * -----------------------------
     *
     * 5. Статичные страницы
     * Проверить ссылки из подвала (Прокликивание ссылок)
     **/

    @BeforeSuite (description = "setup", alwaysRun = true)
    public static void setup()
    {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Kosta/Documents/chrome_driver/chromedriver.exe");
        Configuration.browser = CHROME;
        Configuration.reopenBrowserOnFail = true;
        //Configuration.startMaximized = true;
        //Configuration.baseUrl = SPLIT(Prod.NEWS, "10A");
        /*Для режима инкогнито
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);*/
        /*driver = new ChromeDriver(); //новое окно инкогнито*/
        //driver = new ChromeDriver();
        //driver.manage().deleteAllCookies(); //чистим куки
        //driver.window().maximize();

        // устанавливаем таймаут ожидания загрузки
/*        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();*/

        System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "-----------------------------------------------------------" + ConsoleColors.RESET);

        open("");
        getWebDriver().manage().window().maximize();

        try {open(SPLIT(Prod.NEWS, "10A"));}
        catch (TimeoutException e)
        {
            TabActions.PressEscape();
        }

        firstPage = new FirstPage();
        secondPage = new SecondPage();
        staticPageObjects = new StaticPageObjects();

        //Закрываем поп ап с предложением подписки
        //firstPage.closeSub();
        firstPage.closeSub();

        //Проверка Автоподгрузки (Проверка в ручном режиме, продолжение в конце)
        //TabActions.PressEscape();
        // Переключение на вторую вкладку
        //open(SPLIT(Prod.NEWS, "10A"));
    }


   @BeforeTest (description = "Create new tab", alwaysRun = true)
    public void setup_before() throws InterruptedException, AWTException {
        TabActions.New();
        ArrayList<String> tabs2 = new ArrayList<>(getWebDriver().getWindowHandles()); // Получение списка вкладок
        switchTo().window(tabs2.get(tabs2.size()-1));
        /*firstPage.closeSub();
        System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "PushUp 2 закрыт" + ConsoleColors.RESET);*/
    }

   /**Первая - простейшая проверка Title:*/

   @Test (description = "Title")
    public void test_title() {
        open(SPLIT(Prod.NEWS, "v10"));
        assertEquals(title(), "РБК — новости, акции, курсы валют, доллар, евро");
        System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "Проверка TITLE успешно завершена" + ConsoleColors.RESET);
    }

    /**
     * 1) ОБЩИЕ ЭЛЕМЕНТЫ
     *
     * Топлайн (+)*/

    @Test (description = "Topline links")
    //ГЛАВНАЯ. ТОПЛАЙН.
    public void test_topline() throws InterruptedException, AWTException {
        Actions actions = new Actions(getWebDriver());
        open(SPLIT(Prod.NEWS, "10A"));
        firstPage.closeOpin();/*TabActions.PressEscape();*/

        //Проверка эмблемы из топлайна
        actions.keyDown(Keys.LEFT_CONTROL).click(staticPageObjects.toplineLogo).keyUp(Keys.LEFT_CONTROL).build().perform();

        ArrayList<String> tabs = new ArrayList<>(getWebDriver().getWindowHandles()); // Получение списка вкладок
        switchTo().window(tabs.get(2));// Переключение на третью вкладку
        assertEquals(url(), Prod.NEWS);
        TabActions.Close();
        switchTo().window(tabs.get(1));// Переключение на вторую вкладку
        tabs.remove(2);

        String[] mass = URLs.Mass.topline; // Получение всех возможных ссылок на проекты в топлайне
        String[] mass_add = URLs.Mass.top_add; // Получение всех возможных рекламных ссылок в топлайне
        int len_add = (mass_add).length;
        //int j = 0;

        final int MAX_INDEX_TOP = 17;
        final int MAX_INDEX_TOP_ADD = 2;
        int max_index_c = 0;
        int max_index_add = 0;

        //Проверка количества ссылок на проекты в топлайне
        for (int i = 0; i < MAX_INDEX_TOP; ++i) {
            if (staticPageObjects.getTopItem(i, "common", MAX_INDEX_TOP, MAX_INDEX_TOP_ADD).isEnabled())
                max_index_c++;
            else {
                max_index_c = i - 1;
                throw new Error("Количество ссылок в топлайне = ".concat(Integer.toString(max_index_c)));
            }
        }
        //Проверка количества рекламных ссылок в топлайне
        for (int i = 0; i < MAX_INDEX_TOP_ADD; ++i) {
            if (staticPageObjects.getTopItem(i, "add", MAX_INDEX_TOP, MAX_INDEX_TOP_ADD).isEnabled())
                max_index_add++;
            else {
                max_index_add = i - 1;
                throw new Error("Количество рекламных ссылок = ".concat(Integer.toString(max_index_add)));
            }
        }

        boolean allUrlsIsCorrect = true;
        //Проверка ссылок на проекты в топлайне
        for (int i = 0; i < MAX_INDEX_TOP; i++) {
            actions.keyDown(Keys.LEFT_CONTROL).click(staticPageObjects.getTopItem(i, "common", MAX_INDEX_TOP, MAX_INDEX_TOP_ADD)).keyUp(Keys.LEFT_CONTROL).build().perform();

            tabs = new ArrayList<>(getWebDriver().getWindowHandles());
            switchTo().window(tabs.get(2));// Переключение на третью вкладку
            try {
                assertEquals(mass[i] + "?utm_source=topline", url());
            } catch (AssertionError c) {
                //if (!url().contains(cleanUrlSimple(mass[i])))
                System.out.println(c);
                allUrlsIsCorrect = false;
            }
            /*sleep(500);*/
            TabActions.Close();
            switchTo().window(tabs.get(1));// Перключение на вторую вкладку
            tabs.remove(2);
            tabs = new ArrayList<>(getWebDriver().getWindowHandles());
            if (tabs.size() >= 3) {
                for (int tb_i = tabs.size() - 1; tb_i >= 2; tb_i--)// Проверка количества вкладок
                {
                    switchTo().window(tabs.get(tb_i));// Перключение на последнюю вкладку
                    TabActions.Close();
                    switchTo().window(tabs.get(tb_i - 1));// Перключение на вторую вкладку
                    tabs.remove(tb_i);
                }
            }
        }

        //Проверка рекламных ссылок в топлайне
        System.out.println();
        for (int i = 0; i < MAX_INDEX_TOP_ADD; i++) {
            actions.keyDown(Keys.LEFT_CONTROL).click(staticPageObjects.getTopItem(i, "add", MAX_INDEX_TOP, MAX_INDEX_TOP_ADD)).keyUp(Keys.LEFT_CONTROL).build().perform();

            tabs = new ArrayList<>(getWebDriver().getWindowHandles());
            switchTo().window(tabs.get(2));// Переключение на третью вкладку

            int k;
            for (k = 0; k < len_add; k++){
                if (url().contains(mass_add[k])) {
                    try {
                        assertEquals(mass_add[k], url());
                    } catch (AssertionError c) {
                        //if (!url().contains(cleanUrlSimple(mass[i])))
                        System.out.println(c);
                        allUrlsIsCorrect = false;
                    }
                    break;
                }
            }
            TabActions.Close();
            switchTo().window(tabs.get(1));// Перключение на вторую вкладку
            tabs.remove(2);
            tabs = new ArrayList<>(getWebDriver().getWindowHandles());
            if (tabs.size() >= 3) {
                for (int tb_i = tabs.size() - 1; tb_i >= 2; tb_i--)// Проверка количества вкладок
                {
                    switchTo().window(tabs.get(tb_i));// Перключение на последнюю вкладку
                    TabActions.Close();
                    switchTo().window(tabs.get(tb_i - 1));// Перключение на вторую вкладку
                    tabs.remove(tb_i);
                }
            }
        }
        if (allUrlsIsCorrect) System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Проверка ТОПЛАЙНА успешно завершена!" + ConsoleColors.RESET);
        else throw new Error ("Проверка ТОПЛАЙНА провалена!");
    }

    @Test
    //ПОИСК
    public void test_search() {
        open(SPLIT(Staging.NEWS, "v10"));
        String t = "Путин";
        firstPage.closeOpin();
        staticPageObjects.searchQuery(t);

        test_title();// проверка error 404
        String p;
        int i;
        for (i = 1; i <= 5; i++) {
            p = secondPage.serchQuerys(i);
            if (!p.contains(t)) {
                throw new Error ("Проверка поиска провалена!");
            }
        }
        System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "Проверка ПОИСКА успешно завершена" + ConsoleColors.RESET);
    }

    /** Лента новостей (+) */

    @Test
    //ЛЕНТА НОВОСТЕЙ
    public void test_lenta() {

        int n = 2;
        //Лента новостей. Часть 1 (Сравнить url ленты новостей с продом)
        open(SPLIT(Prod.NEWS, "10A"));
        String lenta_text = staticPageObjects.getLentaUrl(n);
        open(SPLIT(Staging.NEWS, "10A"));
        String lenta_text1 = staticPageObjects.getLentaUrl(n);
        assertEquals(cleanUrl(lenta_text), cleanUrl(lenta_text1));
        System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Проверка ЛЕНТЫ НОВОСТЕЙ (Часть 1) успешно завершена" + ConsoleColors.RESET);

        //Лента новостей. Часть 2 (Проверка редиректа)
        open(SPLIT(Staging.NEWS, "10A"));
        String lenta_url = staticPageObjects.getLentaUrl(n); // доработать
        staticPageObjects.clickLenta(n);
        if (url().contains(cleanUrl(lenta_url)))
            System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Проверка ЛЕНТЫ НОВОСТЕЙ (Часть 2) успешно завершена" + ConsoleColors.RESET);
    }

    /** Прямой эфир (+-) */

    @Test
    public void test_online() {
        open(SPLIT(Prod.NEWS, "10A"));
        TabActions.PressEscape();
        /*try {open(SPLIT(Prod.NEWS, "10A"));}
        catch (TimeoutException ingore)
        {actions.sendKeys(Keys.ESCAPE);}*/


        //TOPLINE размер плеера
        staticPageObjects.TopPlayButton.click(); sleep(1000); //Запуск прямого эфира из топлайна
        staticPageObjects.PauseButton.click(); sleep(1000);
        staticPageObjects.TopPlayButton.click(); sleep(1000);
        staticPageObjects.Mute.click(); sleep(1000);
        staticPageObjects.Mute.click(); sleep(1000);

        //Переключение на средний размер окна
        staticPageObjects.TopChangeView.click(); sleep(1000);

        //MEDIUM размер плеера
        if (staticPageObjects.PreRollSkip.is(visible)) {
            staticPageObjects.PreRollSkip.waitWhile(visible, 1000).click();
        }
        staticPageObjects.PauseButton.click(); sleep(1000);
        staticPageObjects.BigPlayButton.click(); sleep(1000);
        staticPageObjects.Mute.click(); sleep(1000);
        staticPageObjects.Mute.click(); sleep(1000);
        /*staticPageObjects.MidPlayer.hover(); TabActions.Mose_move(staticPageObjects.Mute);*/
        /*TabActions.Mose_move(staticPageObjects.PauseButton);*/
        //staticPageObjects.MidQualityBtn.click(); sleep(1000);
        staticPageObjects.ChangeQuality();

        //Проверка ползунка громкости
        // http://internetka.in.ua/selenium-drag-and-drop/
        staticPageObjects.setSliderPosition(70);

        /*WebElement sliderTrack = $(".vjs-volume-horizontal");
        WebElement slider = $(".vjs-volume-level"); .vjs-volume-level
        assertEquals(getCurrentPosition(sliderTrack, slider), 100);
//        setSliderPosition(20, sliderTrack, slider);
        assertEquals(getCurrentPosition(sliderTrack, slider), 20);*/

        //Проверка смены качества

        /*FULLSCREEN размер плеера
        $(".vjs-fullscreen-control").click(); sleep(500);
        //Проверка смены качества
        for (int i = 1; i<= 4; i++) {
            $(".js-videojs-quality-btn").click();
            $(".vjs-select-quality-item:nth-child(" + Integer.toString(i) + ")");
            $(".vjs-select-quality-item:nth-child(" + Integer.toString(i) + ")").click();
            sleep(1000);
        }
        $(".vjs-play-control").click(); sleep(1000);
        $(".vjs-big-play-button").click();sleep(1000);
        $(".vjs-fullscreen-control").click(); //Выход из фуллскрина
        */
    }

    @Test
    public void test_second_pages ()
    {
        for (String i : secondPage.getSecondPagesUrls("prod"))
        {
            open(i);
            assertFalse(title().contains("РБК") && !title().contains("404"),ConsoleColors.RED_BOLD_BRIGHT + "Страница недоступна!\nТайтл страницы: " + title()
                    + ConsoleColors.RESET);
        }
    }

    @AfterClass()
    public static void tearDown() {
        System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Смок-тест завершен" + ConsoleColors.RESET);
    }

/**-----------------------------------------------------------------------------------------------------------------------*/
/*    private String TestSite()
    {
        String res;
        System.out.println("Введите тестируемую площадку:\n Если Production = [p]\nStaging = [s]\nTest = [t]");
        String p = System.console().readLine();
        if (p.equals("s")) return SPLIT(Staging.NEWS, "v10");
        else return SPLIT( URLs.Test.NEWS_REGULAR, "v10");
    }*/

/*    private void closeFull () {
        try {
            if ($("body > div.news #closeButton").isEnabled()) //body > div.news #closeButton_1239
                $("body > div.news #closeButton").click();}
        catch (NoSuchElementException ignore) {
        }
    }*/

    private String cleanUrl (String lnk)
    {
        if (lnk.contains("www"))
            return lnk.substring(11);
        else if (lnk.contains("staging.")){
            if (lnk.contains("staging.v"))
                return lnk.substring(18);
            else
                return lnk.substring(15);
        }
        else if (lnk.contains(".test")){
            if (lnk.contains(".test.v"))
                return lnk.substring(15);
            for (int i=1; i <= 4; i++)
            {
                if (lnk.contains(".test".concat(Integer.toString(i)) + ('.')))
                        return lnk.substring(13);
            }
            return lnk.substring(12);
        }
        else
            return lnk.substring(7);
    }
    private String cleanUrlSimple(String lnk) {
        int k = 0;
        for (int i = 0; i < lnk.length(); i++) {
            if (lnk.toCharArray()[i] == '/' && k < 3)
                k++;
            else {k=i; break;}
        }
        return lnk.substring(0, k);
    }

    private long getValue(SelenideElement sliderTrack) {
        return sliderTrack.getSize().width / 100;
    }

}