/**Планы:
 * 1) Проверка подгрузки пока что в разработке. Хотелось бы реализовать с таймером,
 *    параллельно считающим во время работы программы и в определенный момент (5-10 мин.)
 *    срабтывающий, давая тем самым отмашку допроверять автоподгрузку)
 * 2) Обработка исключений (не найден элемент и т.д.)
 * 3) Выполнять проверку загрузки каждый раз и приостанавливать загрузку если больше какого-то определенного промежутка времени
 * */

package ru.rbc.kskabort.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.*;
/*import org.openqa.selenium.*;
/*import org.openqa.selenium.chrome.ChromeDriver;*/
/*import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;*/
/*import org.openqa.selenium.interactions.Actions;*/
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ru.rbc.kskabort.pages.FirstPage;
import ru.rbc.kskabort.pages.SecondPage;
import ru.rbc.kskabort.pages.StaticPageObjects;
import ru.rbc.kskabort.tests.URLs.Prod;
import ru.rbc.kskabort.tests.URLs.Staging;

import java.awt.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.WebDriverRunner.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.WebDriverRunner.*;
import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static ru.rbc.kskabort.tests.URLs.SPLIT;

public class FirstTest {

    public static FirstPage firstPage;
    public static SecondPage secondPage;
    public static StaticPageObjects staticPageObjects;

    /*private static WebDriver driver;*/
    //private StringBuffer verificationErrors = new StringBuffer();
/*    public static FirstPage firstPage;
    public static SecondPage secondPage;
    public static StaticPageObjects staticPageObjects;*/
/*    private final String site = TestSite();*/

    @BeforeClass
    public static void setup() throws InterruptedException, AWTException {
        System.setProperty("webdriver.chrome.driver", "C:/Users/kskabort/Documents/chrome_driver/chromedriver.exe");
        Configuration.browser = CHROME;
        Configuration.startMaximized = true;
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

        open(SPLIT(Prod.NEWS, "10A"));

        firstPage = new FirstPage();
        secondPage = new SecondPage();
        staticPageObjects = new StaticPageObjects();

        //Закрываем поп ап с предложением подписки
        //firstPage.closeSub();
        firstPage.closeSub();

        //Проверка Автоподгрузки (Проверка в ручном режиме, продолжение в конце)
        Actions actions = new Actions(getWebDriver());
        actions.sendKeys(Keys.ESCAPE).release().build().perform();
        TabActions.New();
        ArrayList<String> tabs2 = new ArrayList<>(getWebDriver().getWindowHandles()); // Получение списка вкладок
        switchTo().window(tabs2.get(1));// Переключение на вторую вкладку
        //open(SPLIT(Prod.NEWS, "10A"));
    }

   /* @Before
    public void setup_before()
    {
        firstPage.closeSub();
        System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "PushUp 2 закрыт" + ConsoleColors.RESET);
    }*/

    @Test
    //TITLE
    public void test_title() {
        open(SPLIT(Prod.NEWS, "v10"));
        assertEquals(title(), "РБК — новости, акции, курсы валют, доллар, евро");
        System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "Проверка TITLE успешно завершена" + ConsoleColors.RESET);
    }

    @Test
    //ПОИСК
    public void test_search() {
        open(SPLIT(Staging.NEWS, "v10"));
        String t = "Путин";
        staticPageObjects.searchQuery(t);
        assertEquals(title(), "РБК — новости, акции, курсы валют, доллар, евро"); // проверка error 404
        String p;
        for (int i = 1; i <= 5; i++) {
            p = secondPage.serchQuerys(i);
            if (!p.contains(t)) {
                System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Проверка поиска провалена" + ConsoleColors.RESET);
                break;
            }
        }
        System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Проверка ПОИСКА успешно завершена" + ConsoleColors.RESET);
    }

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


    @Test
    //ГЛАВНАЯ. ТОПЛАЙН.
    public void test_topline() throws Exception {
        Actions actions = new Actions(getWebDriver());
        open(SPLIT(Prod.NEWS, "10A"));
        actions.sendKeys(Keys.ESCAPE).release().build().perform();//firstPage.closeOpin();

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
        int j = 0;

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
                throw new Error("Количество ссылок в топлайне = " + Integer.toString(max_index_c));
            }
        }
        //Проверка количества рекламных ссылок в топлайне
        for (int i = 0; i < MAX_INDEX_TOP_ADD; ++i) {
            if (staticPageObjects.getTopItem(i, "add", MAX_INDEX_TOP, MAX_INDEX_TOP_ADD).isEnabled())
                max_index_add++;
            else {
                max_index_add = i - 1;
                throw new Error("Количество рекламных ссылок = " + Integer.toString(max_index_add));
            }
        }

        //Проверка ссылок на проекты в топлайне
        for (int i = 0; i < MAX_INDEX_TOP; i++) {
            actions.keyDown(Keys.LEFT_CONTROL).click(staticPageObjects.getTopItem(i, "common", MAX_INDEX_TOP, MAX_INDEX_TOP_ADD)).keyUp(Keys.LEFT_CONTROL).build().perform();

            tabs = new ArrayList<>(getWebDriver().getWindowHandles());
            switchTo().window(tabs.get(2));// Переключение на третью вкладку
            try {
                assertEquals(mass[i] + "?utm_source=topline", url());
            } catch (ComparisonFailure c) {
                //if (!driver.getCurrentUrl().contains(cleanUrlSimple(mass[i])))
                    System.out.println(c);
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
                    } catch (ComparisonFailure c) {
                        //if (!driver.getCurrentUrl().contains(cleanUrlSimple(mass[i])))
                        System.out.println(c);
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
        System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Проверка ТОПЛАЙНА успешно завершена" + ConsoleColors.RESET);
    }

       /** for (int i = 4; i<=21; i++)
        {
            if (i <= 6)
                actions.keyDown(Keys.LEFT_CONTROL).click($(".topline__item_important:nth-child(" + Integer.toString(i) + ")"))).keyUp(Keys.LEFT_CONTROL).build().perform();
            else {
                if (!$(".topline__item:nth-child(" + Integer.toString(i) + ")")).getAttribute("href").contains(mass[j])) // проверка: содержится ли в проверяемой строке текущая
                { //Если нет, то проверяется наличие рекламных ссылок:
                    for (int l = 0; l <len_add; l++)
                    {
                        if ($(".topline__item:nth-child(" + Integer.toString(i) + ")")).getAttribute("href").contains(mass_add[l])) // Если рекламная ссылка найдена, то продолжаем с новым индексом на ФО. Если нет, то бъем тревогу
                            i++;
                        else {
                            tabs = new ArrayList<>(driver.getWindowHandles());
                            if (tabs.size() >= 3) {
                                for (int tb_i = tabs.size() - 1; tb_i >= 2; tb_i--)// Проверка количества вкладок
                                {
                                    driver.switchTo().window(tabs.get(tb_i));// Перключение на последнюю вкладку
                                    TabActions.Close();
                                    driver.switchTo().window(tabs.get(tb_i - 1));// Перключение на вторую вкладку
                                    tabs.remove(tb_i);
                                }
                                actions.keyDown(Keys.LEFT_CONTROL).click($(".topline__item:nth-child(" + Integer.toString(i) + ")"))).keyUp(Keys.LEFT_CONTROL).build().perform();
                                break;
                            }
                            else throw new Exception("URL is not from Project or ADD's URL!");}
                    }
                }
                else
                    actions.keyDown(Keys.LEFT_CONTROL).click($(".topline__item:nth-child(" + Integer.toString(i) + ")"))).keyUp(Keys.LEFT_CONTROL).build().perform();
            }
            tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(2));// Переключение на третью вкладку
            try {assertEquals(driver.getCurrentUrl(), mass[j]+"?utm_source=topline");}
            catch (ComparisonFailure c)
            {System.out.println(c);}
            TabActions.Close();
            driver.switchTo().window(tabs.get(1));// Перключение на вторую вкладку
            tabs.remove(2);
            j++;
        }
        System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Проверка ТОПЛАЙНА успешно завершена" + ConsoleColors.RESET);
        */

    @Test
    public void test_online() throws Exception {
        open(SPLIT(Prod.NEWS, "10A"));
/*        Actions actions = new Actions(driver);
        try {open(SPLIT(Prod.NEWS, "10A"));}
        catch (TimeoutException ingore)
        {actions.sendKeys(Keys.ESCAPE);}*/

        /**TOPLINE размер плеера*/
        $(".topline__video-block").click(); sleep(1000); //запуск прямого эфира из топлайна
        $(".vjs-play-control").click(); sleep(1000); //пауза
        $(".topline__video-block").click(); sleep(1000); //Play
        $(".vjs-mute-control").click(); sleep(1000); // отключение звука
        $(".vjs-mute-control").click(); sleep(1000); // включение звука

        /**MEDIUM размер плеера*/
        $(".vjs-change-view-control").click(); sleep(1000);
        $(".vjs-mute-control").click(); sleep(1000); // отключение звука
        $(".vjs-mute-control").click(); sleep(1000); // включение звука
        //Проверка ползунка громкости
        // http://internetka.in.ua/selenium-drag-and-drop/
        WebElement sliderTrack = $(".vjs-volume-horizontal");
        WebElement slider = $(".vjs-volume-level");
        assertEquals(getCurrentPosition(sliderTrack, slider), 100);
//        setSliderPosition(20, sliderTrack, slider);
        assertEquals(getCurrentPosition(sliderTrack, slider), 20);

        //Проверка смены качества
        for (int i = 1; i<= 4; i++) {
            $(".js-videojs-quality-btn").click();
            $(".vjs-select-quality-item:nth-child(" + Integer.toString(i) + ")").scrollTo();
            $(".vjs-select-quality-item:nth-child(" + Integer.toString(i) + ")").click();
            sleep(1000);
        }

        /**FULLSCREEN размер плеера*/
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

    }

    @AfterClass()
    public static void tearDown() {
        System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Смок-тест завершен" + ConsoleColors.RESET);
    }

/**-----------------------------------------------------------------------------------------------------------------------*/
/*    private String TestSite()
    {
        String res;
        System.out.println("Введите тестируемую площадку.\n Если staging, то <s>, если test то <t>");
        String p = System.console().readLine();
        if (p.equals("s")) return SPLIT(Staging.NEWS, "v10");
        else return SPLIT(URLs.Test.NEWS_REGULAR, "v10");
    }*/

    private void closeFull () {
        try {
            if ($("body > div.news #closeButton_1239").isEnabled())
                $("body > div.news #closeButton_1239").click();}
        catch (NoSuchElementException ignore) {
        }
    }

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
                if (lnk.contains(".test" + Integer.toString(i) + '.'))
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
            else {k=i; break;};
        }
        return lnk.substring(0, k);
    }

    private long getValue(WebElement sliderTrack) {
        return sliderTrack.getSize().width / 100;
    }
    private long getCurrentPosition(WebElement sliderTrack, WebElement slider) {
        // Позицию можно получить по значению атрибута left
        // getCssValue("left") возвращает абсолютное значение в px,
        long sliderCenterPx = Integer.parseInt(slider.getCssValue("aria-valuenow") + slider.getSize().width / 2);
        return sliderCenterPx / getValue(sliderTrack) + 1;
    }
/*    private void setSliderPosition(long position, WebElement sliderTrack, WebElement slider) {
        if (position < 1 || position > 100) {
            throw new AssertionError("Slider position value should be in the range of 1 to 100");
        }
        long xOffset = (position - getCurrentPosition(sliderTrack, sliderTrack)) * getValue(sliderTrack);
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(slider, (int) xOffset, 0).perform();
    }*/
   /* public void CloseFullscreen()
    {
        NotEmpty(driver.findElement())
    }*/
    /*
    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {

            switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert =
            switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
    */
}
