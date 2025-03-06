package pages;

import driverFactory.Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.systemAdministration.BanksPage;
import pages.warehouse.MainCategoriesPage;

public class Commons {

    private static Driver driver;

    private static final By errorSelector = By.xpath("//div[@class='swal-icon swal-icon--warning']");
    private static final By errorARTextSelector = By.xpath("//span[@id='nameARError']");
    private static final By errorENTextSelector = By.xpath("//span[@id='nameError']");
    private static final By barsBtnSelector = By.xpath("//a[@role='button']");
    private static final By systemAdministrationModuleSelector = By.xpath("//li[@id='lnk_SystemAdministrator']//a[@href='#']");
    private static final By warehouseModuleSelector = By.xpath("//li[@id='lnk_Stock']//a[@href='#']");
    private static final By addBtnSelector = By.xpath("//a[@id='headerBtnAdd']");
    private static final By nameARFieldSelector = By.xpath("//input[translate(@id, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='namear']");
    private static final By nameENFieldSelector = By.xpath("//input[translate(@id, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='name']");
    private static final By saveBtnSelector = By.xpath("//button[@id='saveDetails']");
    private static final By successMSGSelector = By.xpath("//div[@class='swal-icon--success__ring']");
    private static final By okBtnSelector = By.xpath("//button[normalize-space()='OK']");


    // updating and deleting
    private final By settingsIconSelector = By.xpath("(//i[@class='fa fa-cog'])[1]");
    private final By updateBtnSelector = By.xpath("(//i[@class='fa fa-edit ml_e'])[1]");
    private final By deleteBtnSelector = By.xpath("(//i[@class='fa fa-trash-o ml_e'])[1]");

    public Commons(Driver driver) {
        Commons.driver = driver;
    }

    @Step("Check Arabic Validation")
    public Commons checkArabicValidation(String englishText) {
        // Check Lang validation
        clickOnAddBtn();
        driver.element().fillField(nameARFieldSelector, englishText);
        Assert.assertTrue(driver.element().isDisplayed(errorSelector));
        driver.element().click(okBtnSelector);
        return this;
    }

    @Step("Check English Validation")
    public Commons checkEnglishValidation(String arabicText) {
        // Check Lang validation
        clickOnAddBtn();
        driver.element().fillField(nameENFieldSelector, arabicText);
        Assert.assertTrue(driver.element().isDisplayed(errorSelector));
        driver.element().click(okBtnSelector);
        return this;
    }

    @Step("Check Arabic Validation")
    public Commons checkAreaArabicValidation(String englishText) {
        // Check Lang validation
        clickOnAddBtn();
        driver.element().fillField(nameARFieldSelector, englishText);
        Assert.assertTrue(driver.element().isDisplayed(errorARTextSelector));
        return this;
    }

    @Step("Check English Validation")
    public Commons checkAreaEnglishValidation(String arabicText) {
        // Check Lang validation
        driver.element().fillField(nameENFieldSelector, arabicText);
        Assert.assertTrue(driver.element().isDisplayed(errorENTextSelector));
        return this;
    }

    @Step("Navigate to System Administration Module")
    public Commons navigateToSystemAdministrationModule()  {
        driver.element().click(barsBtnSelector);
        driver.element().click(systemAdministrationModuleSelector);
        return this;
    }

    @Step("Navigate to Warehouse Module")
    public Commons navigateToWarehouseModule() {
        driver.element().click(barsBtnSelector);
        driver.element().click(warehouseModuleSelector);
        return this;
    }

    @Step("Click on Add Button")
    public Commons clickOnAddBtn() {
        driver.element().click(addBtnSelector);
        return this;
    }

    @Step("Fill Arabic and English Name")
    public Commons fillNameFields(String nameAR, String nameEN) {
        driver.element().fillField(nameARFieldSelector, nameAR);
        driver.element().fillField(nameENFieldSelector, nameEN);
        return this;
    }

    @Step("Click on Save Button")
    public Commons clickOnSaveBtn() {
        driver.element().click(saveBtnSelector);
        return this;
    }

    @Step("Check Success Message")
    public Commons checkSuccessMessageAndClickOk() {
        Assert.assertTrue(driver.element().isDisplayed(successMSGSelector));
        driver.element().click(okBtnSelector);
        return this;
    }

    @Step("Go To update form")
    public Commons goToUpdateForm() {
        driver.element().click(settingsIconSelector);
        driver.element().click(updateBtnSelector);
        return this;
    }

    @Step("Update")
    public Commons update(String nameAR, String nameEN) {
        goToUpdateForm();
        fillNameFields(nameAR, nameEN);
        clickOnSaveBtn();
        checkSuccessMessageAndClickOk();
        return this;
    }

    @Step("Delete")
    public Commons delete() {
        driver.element().click(settingsIconSelector);
        driver.element().click(deleteBtnSelector);
        driver.element().acceptAlert();
        checkSuccessMessageAndClickOk();
        return this;
    }
}
