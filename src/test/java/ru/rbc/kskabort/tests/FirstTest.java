/**Планы:
 * 1) Проверка подгрузки пока что в разработке. Хотелось бы реализовать с таймером,
 *    параллельно считающим во время работы программы и в определенный момент (5-10 мин.)
 *    срабтывающий, давая тем самым отмашку допроверять автоподгрузку)
 * 2) Обработка исключений (не найден элемент и т.д.)
 * 3) Выполнять проверку загрузки каждый раз и приостанавливать загрузку если больше какого-то определенного промежутка времени
 * */

package ru.rbc.kskabort.tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ComparisonFailure;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import ru.rbc.kskabort.pages.FirstPage;
import ru.rbc.kskabort.pages.SecondPage;
import ru.rbc.kskabort.pages.StaticPageObjects;
import ru.rbc.kskabort.tests.URLs.Prod;
import ru.rbc.kskabort.tests.URLs.Staging;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static ru.rbc.kskabort.tests.URLs.SPLIT;



public class FirstTest {

    private static WebDriver driver;
    //private StringBuffer verificationErrors = new StringBuffer();
    public static FirstPage firstPage;
    public static SecondPage secondPage;
    public static StaticPageObjects staticPageObjects;
/*    private final String site = TestSite();*/

    @BeforeClass
    public static void setup() throws InterruptedException, AWTException {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Kosta/Documents/chrome_driver/chromedriver.exe");
/*       //Для режима инкогнито
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);*/
        driver = new ChromeDriver(); //новое окно инкогнито
        //driver = new ChromeDriver();
        //driver.manage().deleteAllCookies(); //чистим куки
        //driver.manage().window().maximize();
        firstPage = new FirstPage(driver);
        secondPage = new SecondPage(driver);
        staticPageObjects = new StaticPageObjects(driver);

        // устанавливаем таймаут ожидания загрузки
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "-----------------------------------------------------------" + ConsoleColors.RESET);

        //Проверка Автоподгрузки (Проверка в ручном режиме, продолжение в конце)
        Actions actions = new Actions(driver);
        try {driver.get(SPLIT(Prod.NEWS, "10A"));}
        catch (TimeoutException ingore)
        {actions.sendKeys(Keys.ESCAPE);}

        //Закрываем поп ап с предложением подписки
        firstPage.closeSub();

        TabActions.New();
        ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles()); // Получение списка вкладок
        driver.switchTo().window(tabs2.get(1));// Переключение на вторую вкладку
    }

    @Test
    //TITLE
    public void test_title() {
        driver.get(SPLIT(Prod.NEWS, "v10"));
        closeFull();
        assertEquals(driver.getTitle(), "РБК — новости, акции, курсы валют, доллар, евро");
        System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "Проверка TITLE успешно завершена" + ConsoleColors.RESET);
    }

    @Test
    //ПОИСК
    public void test_search() {
        driver.get(SPLIT(Staging.NEWS, "10A"));
        closeFull();
        String t = "Путин";
        staticPageObjects.searchQuery(t);
        closeFull();
        assertEquals(driver.getTitle(), "РБК — новости, акции, курсы валют, доллар, евро"); // проверка error 404
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
        try {driver.get(SPLIT(Prod.NEWS, "10A"));}
        catch (TimeoutException ignore) {}
        String lenta_text = staticPageObjects.getLentaUrl(n);
        driver.get(SPLIT(Staging.NEWS, "10A"));
        String lenta_text1 = staticPageObjects.getLentaUrl(n);
        assertEquals(cleanUrl(lenta_text), cleanUrl(lenta_text1));
        System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Проверка ЛЕНТЫ НОВОСТЕЙ (Часть 1) успешно завершена" + ConsoleColors.RESET);

        //Лента новостей. Часть 2 (Проверка редиректа)
        driver.get(SPLIT(Staging.NEWS, "10A"));
        closeFull();
        String lenta_url = staticPageObjects.getLentaUrl(n); // доработать
        staticPageObjects.clickLenta(n);
        closeFull();
        if (driver.getCurrentUrl().contains(cleanUrl(lenta_url)))
            System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Проверка ЛЕНТЫ НОВОСТЕЙ (Часть 2) успешно завершена" + ConsoleColors.RESET);
    }


    @Test
    //ГЛАВНАЯ. ТОПЛАЙН.
    public void test_topline() throws Exception {
        Actions actions = new Actions(driver);
        try {driver.get(SPLIT(Prod.NEWS, "10A"));}
        catch (TimeoutException ingore)
        {actions.sendKeys(Keys.ESCAPE);}
        //Проверка эмблемы из топлайна
        actions.keyDown(Keys.LEFT_CONTROL).click(staticPageObjects.toplineLogo).keyUp(Keys.LEFT_CONTROL).build().perform();

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles()); // Получение списка вкладок
        driver.switchTo().window(tabs.get(2));// Переключение на третью вкладку
        assertEquals(driver.getCurrentUrl(), Prod.NEWS);
        TabActions.Close();
        driver.switchTo().window(tabs.get(1));// Переключение на вторую вкладку
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

            tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(2));// Переключение на третью вкладку
            try {
                assertEquals(mass[i] + "?utm_source=topline", driver.getCurrentUrl());
            } catch (ComparisonFailure c) {
                //if (!driver.getCurrentUrl().contains(cleanUrlSimple(mass[i])))
                    System.out.println(c);
            }
            TabActions.Close();
            driver.switchTo().window(tabs.get(1));// Перключение на вторую вкладку
            tabs.remove(2);
            tabs = new ArrayList<>(driver.getWindowHandles());
            if (tabs.size() >= 3) {
                for (int tb_i = tabs.size() - 1; tb_i >= 2; tb_i--)// Проверка количества вкладок
                {
                    driver.switchTo().window(tabs.get(tb_i));// Перключение на последнюю вкладку
                    TabActions.Close();
                    driver.switchTo().window(tabs.get(tb_i - 1));// Перключение на вторую вкладку
                    tabs.remove(tb_i);
                }
            }
        }

        //Проверка рекламных ссылок в топлайне
        System.out.println();
        for (int i = 0; i < MAX_INDEX_TOP_ADD; i++) {
            actions.keyDown(Keys.LEFT_CONTROL).click(staticPageObjects.getTopItem(i, "add", MAX_INDEX_TOP, MAX_INDEX_TOP_ADD)).keyUp(Keys.LEFT_CONTROL).build().perform();

            tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(2));// Переключение на третью вкладку

            int k;
            for (k = 0; k < len_add; k++){
                if (driver.getCurrentUrl().contains(mass_add[k])) {
                    try {
                        assertEquals(mass_add[k], driver.getCurrentUrl());
                    } catch (ComparisonFailure c) {
                        //if (!driver.getCurrentUrl().contains(cleanUrlSimple(mass[i])))
                        System.out.println(c);
                    }
                    break;
                }
            }
            TabActions.Close();
            driver.switchTo().window(tabs.get(1));// Перключение на вторую вкладку
            tabs.remove(2);
            tabs = new ArrayList<>(driver.getWindowHandles());
            if (tabs.size() >= 3) {
                for (int tb_i = tabs.size() - 1; tb_i >= 2; tb_i--)// Проверка количества вкладок
                {
                    driver.switchTo().window(tabs.get(tb_i));// Перключение на последнюю вкладку
                    TabActions.Close();
                    driver.switchTo().window(tabs.get(tb_i - 1));// Перключение на вторую вкладку
                    tabs.remove(tb_i);
                }
            }
        }
        System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Проверка ТОПЛАЙНА успешно завершена" + ConsoleColors.RESET);
    }

       /** for (int i = 4; i<=21; i++)
        {
            if (i <= 6)
                actions.keyDown(Keys.LEFT_CONTROL).click(driver.findElement(By.cssSelector(".topline__item_important:nth-child(" + Integer.toString(i) + ")"))).keyUp(Keys.LEFT_CONTROL).build().perform();
            else {
                if (!driver.findElement(By.cssSelector(".topline__item:nth-child(" + Integer.toString(i) + ")")).getAttribute("href").contains(mass[j])) // проверка: содержится ли в проверяемой строке текущая
                { //Если нет, то проверяется наличие рекламных ссылок:
                    for (int l = 0; l <len_add; l++)
                    {
                        if (driver.findElement(By.cssSelector(".topline__item:nth-child(" + Integer.toString(i) + ")")).getAttribute("href").contains(mass_add[l])) // Если рекламная ссылка найдена, то продолжаем с новым индексом на ФО. Если нет, то бъем тревогу
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
                                actions.keyDown(Keys.LEFT_CONTROL).click(driver.findElement(By.cssSelector(".topline__item:nth-child(" + Integer.toString(i) + ")"))).keyUp(Keys.LEFT_CONTROL).build().perform();
                                break;
                            }
                            else throw new Exception("URL is not from Project or ADD's URL!");}
                    }
                }
                else
                    actions.keyDown(Keys.LEFT_CONTROL).click(driver.findElement(By.cssSelector(".topline__item:nth-child(" + Integer.toString(i) + ")"))).keyUp(Keys.LEFT_CONTROL).build().perform();
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
        Actions actions = new Actions(driver);
        try {driver.get(SPLIT(Prod.NEWS, "10A"));}
        catch (TimeoutException ingore)
        {actions.sendKeys(Keys.ESCAPE);}

        /**TOPLINE размер плеера*/
        driver.findElement(By.cssSelector(".topline__video-block")).click(); sleep(1000); //запуск прямого эфира из топлайна
        driver.findElement(By.cssSelector(".vjs-play-control")).click(); sleep(1000); //пауза
        driver.findElement(By.cssSelector(".topline__video-block")).click(); sleep(1000); //Play
        driver.findElement(By.cssSelector(".vjs-mute-control")).click(); sleep(1000); // отключение звука
        driver.findElement(By.cssSelector(".vjs-mute-control")).click(); sleep(1000); // включение звука

        /**MEDIUM размер плеера*/
        driver.findElement(By.cssSelector(".vjs-change-view-control")).click(); sleep(1000);
        driver.findElement(By.cssSelector(".vjs-mute-control")).click(); sleep(1000); // отключение звука
        driver.findElement(By.cssSelector(".vjs-mute-control")).click(); sleep(1000); // включение звука
        //Проверка ползунка громкости
        // http://internetka.in.ua/selenium-drag-and-drop/
        WebElement sliderTrack = driver.findElement(By.cssSelector(".vjs-volume-horizontal"));
        WebElement slider = driver.findElement(By.cssSelector(".vjs-volume-level"));
        assertEquals(getCurrentPosition(sliderTrack, slider), 100);
        setSliderPosition(20, sliderTrack, slider);
        assertEquals(getCurrentPosition(sliderTrack, slider), 20);

        //Проверка смены качества
        for (int i = 1; i<= 4; i++) {
            driver.findElement(By.cssSelector(".js-videojs-quality-btn")).click();
            actions.moveToElement(driver.findElement(By.cssSelector(".vjs-select-quality-item:nth-child(" + Integer.toString(i) + ")")));
            driver.findElement(By.cssSelector(".vjs-select-quality-item:nth-child(" + Integer.toString(i) + ")")).click();
            sleep(1000);
        }

        /**FULLSCREEN размер плеера*/
        driver.findElement(By.cssSelector(".vjs-fullscreen-control")).click(); sleep(500);
        //Проверка смены качества
        for (int i = 1; i<= 4; i++) {
            driver.findElement(By.cssSelector(".js-videojs-quality-btn")).click();
            actions.moveToElement(driver.findElement(By.cssSelector(".vjs-select-quality-item:nth-child(" + Integer.toString(i) + ")")));
            driver.findElement(By.cssSelector(".vjs-select-quality-item:nth-child(" + Integer.toString(i) + ")")).click();
            sleep(1000);
        }
        driver.findElement(By.cssSelector(".vjs-play-control")).click(); sleep(1000);
        driver.findElement(By.cssSelector(".vjs-big-play-button")).click();sleep(1000);
        driver.findElement(By.cssSelector(".vjs-fullscreen-control")).click(); //Выход из фуллскрина

    }

    @AfterClass()
    public static void tearDown() {
        System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Смок-тест завершен" + ConsoleColors.RESET);
        driver.quit();
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
            if (driver.findElement(By.cssSelector("body > div.news #closeButton_1239")).isEnabled())
                driver.findElement(By.cssSelector("body > div.news #closeButton_1239")).click();}
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
    private void setSliderPosition(long position, WebElement sliderTrack, WebElement slider) {
        if (position < 1 || position > 100) {
            throw new AssertionError("Slider position value should be in the range of 1 to 100");
        }
        long xOffset = (position - getCurrentPosition(sliderTrack, sliderTrack)) * getValue(sliderTrack);
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(slider, (int) xOffset, 0).perform();
    }
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
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
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
