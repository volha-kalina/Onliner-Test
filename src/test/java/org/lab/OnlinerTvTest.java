package org.lab;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class OnlinerTvTest {
    public static MainPage mainPage;
    public static CatalogPage catalogPage;
    public static TvPage tvPage;
    public static WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromeDriver"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("mainPage"));

        mainPage = new MainPage(driver);
        CatalogMenu menu = new CatalogMenu(driver);
        catalogPage = new CatalogPage(driver);
        tvPage = new TvPage(driver, menu);
    }

    @Test
    public void testTvPage() {
        mainPage.clickCatalogLink();
        catalogPage.verifyAllElements();
        catalogPage.navigateToTvCategory();
        tvPage.verifyAllElements();
        tvPage.setManufacturer("Samsung");
        tvPage.setResolution("1920x1080 (Full HD)");
        tvPage.setPrices("0", "1000");
        tvPage.setDiagonals("40\"", "50\"");
        tvPage.checkResultTvs("Samsung");
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
