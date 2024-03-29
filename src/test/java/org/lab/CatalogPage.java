package org.lab;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CatalogPage {
    @FindBy(xpath = "//h1[@class = \"catalog-navigation__title\" and contains(.,\"Каталог\")]")
    private WebElement catalogTitle;

    @FindBy(xpath = "//li[@class=\"catalog-navigation-classifier__item \" and @data-id=\"1\"]")
    private WebElement electronicsLink;

    @FindBy(xpath = "//*[@class=\"catalog-navigation-list__category\"]//*[@class = \"catalog-navigation-list__aside-list\" and contains(.,\"Телевидение\")]")
    private WebElement catalogDropdown;

    @FindBy(xpath = "//*[@class = \"catalog-navigation-list__aside-title\" and contains(.,\"Телевидение\")]")
    private WebElement catalogDropdownItemTv;

    @FindBy(xpath = "//a[@href = \"https://catalog.onliner.by/tv\"]//span[@class = \"catalog-navigation-list__dropdown-data\" and contains(.,\"Телевизоры\")]")
    private WebElement tvMenu;

    private Actions actions;
    private WebDriver driver;

    public CatalogPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        actions = new Actions(driver);
    }

    public void verifyAllElements() {
        Assert.assertTrue(catalogTitle.isDisplayed());
    }

    public void navigateToTvCategory() {
        electronicsLink.click();
        actions.moveToElement(catalogDropdownItemTv).perform();
        tvMenu.click();
    }
}
