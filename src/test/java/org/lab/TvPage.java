package org.lab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.lang.reflect.Array;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TvPage {
    @FindBy(xpath = "//h1[@class = \"schema-header__title\" and contains(.,\"Телевизоры\")]")
    private WebElement tvPageTitle;

    private WebDriver driver;
    private CatalogMenu menu;

    public TvPage(WebDriver driver, CatalogMenu menu) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.menu = menu;
    }

    public void verifyAllElements() {
        tvPageTitle.isDisplayed();
        // add URL check
    }

    public void setManufacturer(String name){
        menu.findAndClickFilterByName(name);
    }

    public void setResolution(String resolution){
        menu.findAndClickFilterByName(resolution);
    }

    public void setPrices(String priceFrom, String priceTo) {
        menu.enterPrices(priceFrom, priceTo);
    }

    public void setDiagonals(String diagonalFrom, String diagonalTo) {
        menu.enterDiagonals(diagonalFrom, diagonalTo);
    }

    public void checkResultTvs (String manufacturer) {
        List<WebElement> titles = driver.findElements(By.cssSelector("#schema-products > div > div > div.schema-product__part.schema-product__part_2 > div.schema-product__part.schema-product__part_4 > div.schema-product__title > a > span"));

        for (int i = 0; i < titles.size(); i++) {
            Assert.assertTrue(titles.get(i).getText().contains(manufacturer));
        }
    }
}
