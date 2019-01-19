package ru.rbc.kskabort.tests;

/** Небольшой пример правильного использования Actions!
 * Actions actions = new Actions(driver);
 * actions
 *     .keyDown(Keys.LEFT_CONTROL)
 *     .click(first_WebElement)
 *     .click(second_WebElement)
 *     .click(third_WebElement)
 *     .keyUp(Keys.LEFT_CONTROL)
 *     .build()
 *     .perform(); */

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.event.KeyEvent;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class TabActions {

/*
    private static Actions actions = new Actions(getWebDriver());
    private static Robot robot;

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
*/

    public static void PressEscape() {
        Actions actions = new Actions(getWebDriver());
        {
            actions.sendKeys(Keys.ESCAPE)
                    .release()
                    .build()
                    .perform();
        }
    }

    static void New() throws InterruptedException, AWTException {
        Robot robot = new Robot();
        /*actions.keyDown(Keys.CONTROL).sendKeys("t").keyUp(Keys.CONTROL).perform();*/
        /*sleep(500);
        ((JavascriptExecutor)getWebDriver()).executeScript("window.open()");
        sleep(500);*/
        robot.keyPress(KeyEvent.VK_CONTROL);
        Thread.sleep(500);
        robot.keyPress(KeyEvent.VK_T);
        Thread.sleep(500);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_T);
    }

/*    static void Switch() throws AWTException, InterruptedException
    {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        Thread.sleep(500);
        robot.keyPress(KeyEvent.VK_TAB);
        Thread.sleep(500);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_TAB);
    }*/

    static void Close() throws InterruptedException, AWTException {
        Robot robot = new Robot();
        //(JavascriptExecutor)getWebDriver()).executeScript("window.close()");
        //sleep(500);
        robot.keyPress(KeyEvent.VK_CONTROL);
        Thread.sleep(500);
        robot.keyPress(KeyEvent.VK_F4);
        Thread.sleep(500);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_F4);
    }

/*    static void Open_in_new_tab() throws AWTException, InterruptedException
    {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        Thread.sleep(500);
        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(500);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }*/

    static void Mose_move(SelenideElement elem) throws AWTException {
        Robot robot = new Robot();
        Point coordinates = $(elem).getLocation();
        robot.mouseMove(coordinates.getX(), coordinates.getY() + 120);
    }
}
