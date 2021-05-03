package org.lab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
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
        Assert.assertTrue(tvPageTitle.isDisplayed());
    }

    public void setManufacturer(String name) {
        menu.findAndClickFilterByName(name);
    }

    public void setResolution(String resolution) {
        menu.findAndClickFilterByName(resolution);
    }

    public void setPrices(String priceFrom, String priceTo) {
        menu.enterPrices(priceFrom, priceTo);
    }

    public void setDiagonals(String diagonalFrom, String diagonalTo) {
        menu.enterDiagonals(diagonalFrom + "\"", diagonalTo + "\"");
    }

    public void checkResultTvs(
            String manufacturer,
            String resolution,
            String priceFrom,
            String priceTo,
            String diagonalFrom,
            String diagonalTo
    ) {
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                Boolean isManufacturerFilterSelected = driver.findElements(By.xpath("//*[@class=\"schema-tags__item\"]")).get(0).getText().contains(manufacturer);
                Boolean isFirstElementCorrect = driver.findElement(By.xpath("//*[@class=\"schema-product__title\"]//a/span")).getText().contains(manufacturer);

                if (isManufacturerFilterSelected == true && isFirstElementCorrect == true) {
                    return true;
                }

                return false;
            }
        });


        List<WebElement> Tvs = driver.findElements(By.cssSelector("div.schema-product"));

        for (int i = 0; i < Tvs.size(); i++) {
            WebElement currentTv = Tvs.get(i);
            WebElement tvTitle = currentTv.findElement(By.cssSelector("div.schema-product__title > a > span"));
            WebElement tvDescription = currentTv.findElement(By.cssSelector("div.schema-product__description > span"));
            Double price = Double.parseDouble(
                    currentTv.findElement(By.cssSelector("div.schema-product__price > a > span")).getText()
                            .replace(" р.", "")
                            .replace(",", ".")
            );
            Integer diagonal = Integer.parseInt(tvDescription.getText().substring(0, 2));

            Assert.assertTrue(tvTitle.getText().contains(manufacturer));
            Assert.assertTrue(tvDescription.getText().contains(resolution));
            Assert.assertTrue(Double.parseDouble(priceFrom) <= price && price < Double.parseDouble(priceTo));
            Assert.assertTrue(Integer.parseInt(diagonalFrom) < diagonal && diagonal < Integer.parseInt(diagonalTo));
        }
    }
}
