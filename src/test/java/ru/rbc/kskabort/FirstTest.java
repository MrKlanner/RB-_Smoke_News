package ru.rbc.kskabort;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class FirstTest {

    private static WebDriver driver;
    //private StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Kosta/Documents/chrome_driver/chromedriver.exe");
        //Для режима инкогнито
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities); //новое окно инкогнито
        //driver = new ChromeDriver();
        driver.manage().deleteAllCookies(); //чистим куки
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void test_smoke() throws InterruptedException, AWTException {
        driver.get("https://rbc.ru");

        //Проверка Тайтла
        assertEquals(driver.getTitle(), "РБК — новости, акции, курсы валют, доллар, евро");
        System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Проверка TITLE успешно завершена" + ConsoleColors.RESET);

        /*try {
            assertEquals(driver.getTitle(), "РБК — новости, акции, курсы валют, доллар, евро");
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }*/

        //Проверка поиска
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='News'])[2]/following::span[1]")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='News'])[2]/following::input[2]")).click();
        assertEquals(driver.getTitle(), "РБК — новости, акции, курсы валют, доллар, евро");
        System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Проверка ПОИСКА успешно завершена" + ConsoleColors.RESET);

        //Лента новостей
        driver.get("https://rbc.ru");
        String lenta_text = driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Лента новостей'])[1]/following::span[1]")).getText();

        /*TabActions.New();
        TabActions.Switch();
        TabActions.Close();*/
        //String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,"t");
        //driver.findElement(By.cssSelector("body")).sendKeys(selectLinkOpeninNewTab);
        //driver.findElement(By.cssSelector("body")).sendKeys(Keys.chord(Keys.CONTROL, Keys.getKeyFromUnicode('\u0061')));
        //driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t"); //новая вкладка

        driver.get("https://rbc.ru"); // https://staging.rbc.ru
        String lenta_text1 = driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Лента новостей'])[1]/following::span[1]")).getText();
        assertEquals(lenta_text, lenta_text1);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.MINUTES);
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.ESCAPE);
        System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Проверка ленты новостей успешно завершена" + ConsoleColors.RESET);

        /* Проверка тайтла статьи из ленты
        String test = driver.findElement(By.cssSelector(".news-feed__item:nth-child(2) .news-feed__item__title")).getText();
        String for_ver_title = driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Лента новостей'])[1]/following::span[1]")).getText();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Лента новостей'])[1]/following::span[1]")).click();
        String real_title = driver.getTitle();
        */

    }


    @AfterClass()
    public static void tearDown() throws Exception {
        System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Смок-тест завершен" + ConsoleColors.RESET);
        driver.quit();
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
