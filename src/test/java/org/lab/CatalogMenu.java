package org.lab;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CatalogMenu {
    private WebDriver driver;
    private Actions actions;

    @FindBy(xpath = "//*[@class = \"schema-filter-control__item schema-filter__number-input schema-filter__number-input_price\" and @placeholder= \"от\"]")
    private WebElement priceFromFilter;

    @FindBy(xpath = "//div[@class = \"schema-filter-control schema-filter-control_input\"][2]/input[@class = \"schema-filter-control__item schema-filter__number-input schema-filter__number-input_price\"]")
    private WebElement priceToFilter;

    @FindBy(xpath = "//div[@class = \"schema-filter-control schema-filter-control_select\"][1]/select[@class = \"schema-filter-control__item\"]")
    private WebElement diagonalFrom;

    @FindBy(xpath = "//div[@class = \"schema-filter-control schema-filter-control_select\"][2]/select[@class = \"schema-filter-control__item\"]")
    private WebElement diagonalTo;

    public CatalogMenu(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        actions = new Actions(driver);
    }

    public WebElement getFilterWebElementByName(String filterName) {
        return driver.findElement(
                By.xpath("//*[@class=\"schema-filter__checkbox-text\" and contains(.,'" + filterName + "')]")
        );
    }

    public void findAndClickFilterByName(String name) {
        WebElement filter = this.getFilterWebElementByName(name);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", filter);
        filter.click();
    }

    public void enterPrices(String priceFrom, String priceTo) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", priceFromFilter);
        priceFromFilter.click();
        priceFromFilter.sendKeys(priceFrom);
        priceToFilter.click();
        priceToFilter.sendKeys(priceTo);
    }

    public void enterDiagonals(String diagonalFrom, String diagonalTo) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", this.diagonalFrom);
        this.diagonalFrom.sendKeys(diagonalFrom);
        this.diagonalTo.sendKeys(diagonalTo);
    }
}
