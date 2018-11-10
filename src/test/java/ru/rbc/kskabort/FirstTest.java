/**Планы:
 * 1) Проверка подгрузки пока что в разработке. Хотелось бы реализовать с таймером,
 *    параллельно считающим во время работы программы и в определенный момент (5-10 мин.)
 *    срабтывающий, давая тем самым отмашку допроверять автоподгрузку)
 * 2) Обработка исключений (не найден элемент и т.д.)
 * 3) Выполнять проверку загрузки каждый раз и приостанавливать загрузку если больше какого-то определенного промежутка времени
 * */

package ru.rbc.kskabort;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import ru.rbc.kskabort.URLs.Prod;
import ru.rbc.kskabort.URLs.Staging;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;


public class FirstTest {

    private static WebDriver driver;
    //private StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass
    public static void setup() throws InterruptedException, AWTException {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Kosta/Documents/chrome_driver/chromedriver.exe");

/*        //Для режима инкогнито
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);*/
        driver = new ChromeDriver(); //новое окно инкогнито
        //driver = new ChromeDriver();
        //driver.manage().deleteAllCookies(); //чистим куки
        //driver.manage().window().maximize();

        // устанавливаем таймаут ожидания загрузки
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "-----------------------------------------------------------" + ConsoleColors.RESET);

        //Проверка Автоподгрузки (Проверка в ручном режиме, продолжение в конце)
        try {driver.get(Prod.NEWS);}
        catch (TimeoutException ignore)
        {}

        //Закрываем поп ап с предложением подписки
        driver.findElement(By.cssSelector(".push-allow__item:nth-child(2)")).click();

        TabActions.New();
        ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles()); // Получение списка вкладок
        driver.switchTo().window(tabs2.get(1));// Переключение на вторую вкладку
    }

    @Test
    public void test_title() {
        //Проверка Тайтла
        driver.get(Prod.NEWS);
        closeFull();
        assertEquals(driver.getTitle(), "РБК — новости, акции, курсы валют, доллар, евро");
        System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Проверка TITLE успешно завершена" + ConsoleColors.RESET);
    }

    @Test
    public void test_search() {
        //Проверка поиска
        driver.findElement(By.cssSelector("div.topline__search__menu.js-search-open")).click();
        closeFull();
        String t = "Путин";
        driver.findElement(By.cssSelector("input.topline__search__input")).sendKeys(t);
        driver.findElement(By.cssSelector("input.topline__search__button")).click();
        closeFull();
        assertEquals(driver.getTitle(), "РБК — новости, акции, курсы валют, доллар, евро"); // проверка error 404
        String p;
        for (int i = 1; i <= 5; i++) {
            p = driver.findElement(By.cssSelector("div.search-item:nth-child(" + Integer.toString(i) + ") span.search-item__text")).getText();
            if (!p.contains(t)) {
                System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Проверка поиска провалена" + ConsoleColors.RESET);
                break;
            }
        }
        System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Проверка ПОИСКА успешно завершена" + ConsoleColors.RESET);
    }

    @Test
    public void test_lenta1() {
        //Лента новостей. Часть 1
        //String lnk = Prod.NEWS;
        try {
            driver.get(Prod.NEWS);
        } catch (TimeoutException ignore) {
        }
        closeFull();
        String lenta_text = driver.findElement(By.cssSelector(".news-feed__item:nth-child(2)")).getAttribute("href");
        driver.get(Staging.NEWS);
        closeFull();
        String lenta_text1 = driver.findElement(By.cssSelector(".news-feed__item:nth-child(2)")).getAttribute("href");
        assertEquals(modifyUrl(lenta_text), modifyUrl(lenta_text1));
        System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Проверка ЛЕНТЫ НОВОСТЕЙ (Часть 1) успешно завершена" + ConsoleColors.RESET);

        //Лента новостей. Часть 2
        driver.get(Staging.NEWS);
        closeFull();
        String lenta_url = driver.findElement(By.cssSelector(".news-feed__item:nth-child(2)")).getAttribute("href"); // доработать
        driver.get(lenta_url); //?
        closeFull();
        int k = Staging.NEWS.length();
        lenta_url = lenta_url.substring(k + 1, lenta_url.length() - "?from=newsfeed".length());
        if (driver.getCurrentUrl().contains(lenta_url))
            System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Проверка ЛЕНТЫ НОВОСТЕЙ (Часть 2) успешно завершена" + ConsoleColors.RESET);
    }


    @Test
    public void test_shapka() throws InterruptedException, AWTException {
        driver.get(Prod.NEWS);
        Actions actions = new Actions(driver);
        //Проверка эмблемы из топлайна
        actions.keyDown(Keys.LEFT_CONTROL).click(driver.findElement(By.cssSelector(".topline__item_logo"))).keyUp(Keys.LEFT_CONTROL).build().perform();
        ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles()); // Получение списка вкладок
        driver.switchTo().window(tabs2.get(2));// Переключение на третью вкладку
        assertEquals(driver.getCurrentUrl(), Prod.NEWS);
        TabActions.Close();
        driver.switchTo().window(tabs2.get(1));// Переключение на вторую вкладку
        tabs2.remove(2);

        String[] mass = URLs.i_need_mass("topline");

        for (int i = 4; i<=21; i++)
        {
            if (i <= 6)
                actions.keyDown(Keys.LEFT_CONTROL).click(driver.findElement(By.cssSelector(".topline__item_important:nth-child(" + Integer.toString(i) + ")"))).keyUp(Keys.LEFT_CONTROL).build().perform();
            else {
                if (driver.findElement(By.cssSelector(".topline__item:nth-child(" + Integer.toString(i) + ")")).getAttribute("href").contains(mass[i-4]))
                {continue;}
                actions.keyDown(Keys.LEFT_CONTROL).click(driver.findElement(By.cssSelector(".topline__item:nth-child(" + Integer.toString(i) + ")"))).keyUp(Keys.LEFT_CONTROL).build().perform();
            }
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(2));// Переключение на третью вкладку
            assertEquals(driver.getCurrentUrl(), mass[i-4]+"?utm_source=topline");
            TabActions.Close();
            driver.switchTo().window(tabs.get(1));// Перключение на вторую вкладку
            tabs.remove(2);
        }


        //Actions close = new Actions(driver);
        //close.keyDown(Keys.LEFT_CONTROL).pause(1).sendKeys(Keys.F4).keyUp(Keys.LEFT_CONTROL).build().perform();
        //driver.switchTo().window(tabs2.get(1));// Переключение на вторую вкладку

        //actions.keyDown(Keys.LEFT_CONTROL).click(driver.findElement(By.cssSelector(".topline__item_important:nth-child(4)"))).keyUp(Keys.LEFT_CONTROL).build().perform();
        //assertEquals(driver.getCurrentUrl(), Prod.TV);

    }
        /*//Переключение вкладок
        TabActions.New();
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles()); // Получение списка вкладок
        driver.switchTo().window(tabs2.get(1)); // Переклюение на вторую вкладку
        //driver.switchTo().window(tabs2.get(0)); // Переключение на первую вкладку*/


        //String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,"t");
        //driver.findElement(By.cssSelector("body")).sendKeys(selectLinkOpeninNewTab);

        //driver.findElement(By.cssSelector("body")).sendKeys(Keys.chord(Keys.CONTROL, Keys.getKeyFromUnicode('\u0061')));
        //driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t"); //новая вкладка

        /* Проверка тайтла статьи из ленты
        String test = driver.findElement(By.cssSelector(".news-feed__item:nth-child(2) .news-feed__item__title")).getText();
        String for_ver_title = driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Лента новостей'])[1]/following::span[1]")).getText();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Лента новостей'])[1]/following::span[1]")).click();
        String real_title = driver.getTitle();
        */



    @AfterClass()
    public static void tearDown() {
        System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Смок-тест завершен" + ConsoleColors.RESET);
        driver.quit();
    }


    private void closeFull () {
        try {
            if (driver.findElement(By.cssSelector("body > div.news #closeButton_1239")).isEnabled())
                driver.findElement(By.cssSelector("body > div.news #closeButton_1239")).click();
        } catch (NoSuchElementException ignore) {
        }
    }

    private String modifyUrl (String lnk)
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
