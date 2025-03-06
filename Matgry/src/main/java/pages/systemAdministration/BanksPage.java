package pages.systemAdministration;

import driverFactory.Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.Commons;
import pages.warehouse.MainCategoriesPage;

public class BanksPage {

    private Driver driver;
    private Commons commons;

    public BanksPage(Driver driver) {
        this.driver = driver;
        commons = new Commons(driver);
    }

    // Selectors
    private final By banksTabSelector = By.xpath("//li[@id='Banks'] //a[@class='btnClickShowLoader']");
    private static final By nameARFieldSelector = By.xpath("//input[@id='NameAR']");
    private static final By nameENFieldSelector = By.xpath("//input[@id='Name']");

    // Steps
    @Step("Navigate to Banks page")
    public BanksPage navigateToBanksPage()  {
        commons.navigateToSystemAdministrationModule();
        driver.element().click(banksTabSelector);
        return this;
    }

    @Step("Click on Add Bank button")
    public BanksPage clickOnAddBankButton() {
        commons.clickOnAddBtn();
        return this;
    }

    @Step("Fill Bank Form")
    public BanksPage fillBankForm(String bankNameAR, String bankNameEN) {
        commons.fillNameFields(bankNameAR, bankNameEN);
        return this;
    }

    @Step("Save Bank")
    public BanksPage saveBank() {
        commons.clickOnSaveBtn();
        commons.checkSuccessMessageAndClickOk();
        return this;
    }

    @Step("Create Bank")
    public BanksPage createBank(String bankNameAR, String bankNameEN)  {
        navigateToBanksPage();
        clickOnAddBankButton();
        fillBankForm(bankNameAR, bankNameEN);
        saveBank();
        return this;
    }

    @Step("Update Bank")
    public BanksPage updateBank(String bankNameAR, String bankNameEN)  {
        navigateToBanksPage();
        commons.update(bankNameAR, bankNameEN);
        return this;
    }

    @Step("Delete Bank")
    public BanksPage deleteBank()  {
        navigateToBanksPage();
        commons.delete();
        return this;
    }

    @Step("Validate Language Bank Name")
    public BanksPage validateLanguageBankName(String bankNameEN, String bankNameAR) {
        navigateToBanksPage();
        commons.checkArabicValidation(bankNameEN);
        commons.checkEnglishValidation(bankNameAR);
        return this;
    }


}
