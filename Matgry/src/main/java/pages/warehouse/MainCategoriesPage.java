package pages.warehouse;

import driverFactory.Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.Commons;

public class MainCategoriesPage {
    public Driver driver;
    Commons commons;

    public MainCategoriesPage(Driver driver) {
        this.driver = driver;
        commons = new Commons(driver);
    }

    private final By mainCategoriesTabSelector = By.xpath("//li[@id='CategoriesRegister'] //a[@class='btnClickShowLoader']");
    private final By deleteBtnSelector = By.xpath("//table[1]/tbody[1]/tr[1]/td[4]/div[1]/div[1]/a[3]");
    private final By nameARFieldSelector = By.xpath("//input[@id='NameAR']");
    private final By nameENFieldSelector = By.xpath("//input[@id='Name']");

    // update branches
    private final By settingsIconSelector = By.xpath("//table[1]/tbody[1]/tr[1]/td[4]/div[1]/span[1]");
    private final By updateBtnSelector = By.xpath("//table[1]/tbody[1]/tr[1]/td[4]/div[1]/div[1]/a[2]");


    @Step("Navigate to Main Categories page")
    public MainCategoriesPage navigateToMainCategoriesPage() {
        commons.navigateToWarehouseModule();
        driver.element().click(mainCategoriesTabSelector);
        return this;
    }

    @Step("Add Main Category")
    public MainCategoriesPage clickOnAddMainCategory() {
        commons.clickOnAddBtn();
        return this;
    }

    @Step("Fill Arabic and English Name")
    public MainCategoriesPage fillNameFields(String nameAR, String nameEN) {
        commons.fillNameFields(nameAR, nameEN);
        return this;
    }

    @Step("Save Main Category and check success message")
    public MainCategoriesPage clickOnSaveMainCategory() {
        commons.clickOnSaveBtn();
        commons.checkSuccessMessageAndClickOk();
        return this;
    }


    @Step("Update Main Category")
    public MainCategoriesPage clickOnUpdateMainCategory() {
        driver.element().click(settingsIconSelector);
        driver.element().click(updateBtnSelector);
        return this;
    }

    @Step("Delete Main Category and check success message")
    public MainCategoriesPage deleteMainCategory() {
        driver.element().click(settingsIconSelector);
        driver.element().click(deleteBtnSelector);
        driver.element().acceptAlert();
        commons.checkSuccessMessageAndClickOk();
        return this;
    }

    @Step("Check Arabic Validation")
    public MainCategoriesPage checkArabicValidation(String englishText) {
        clickOnAddMainCategory();
        commons.checkArabicValidation(englishText);
        return this;
    }

    @Step("Check English Validation")
    public MainCategoriesPage checkEnglishValidation(String arabicText) {
        clickOnAddMainCategory();
        commons.checkEnglishValidation(arabicText);
        return this;
    }
}
