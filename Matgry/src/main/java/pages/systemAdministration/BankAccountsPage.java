package pages.systemAdministration;

import driverFactory.Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.Commons;

public class BankAccountsPage {
    private Driver driver;
    private Commons commons;

    public BankAccountsPage(Driver driver) {
        this.driver = driver;
        commons = new Commons(driver);
    }

    // Selectors
    private final By bankAccountsTabSelector = By.xpath("//li[@id='BankAccounts'] //a[@class='btnClickShowLoader']");
    private final By banksListSelector = By.xpath("//select[@id='bank-select-modal']");
    private final By iBanInputSelector = By.xpath("//input[@id='Iban']");
    private final By notesInputSelector = By.xpath("//textarea[@id='Notes']");

    // Methods

    @Step("Click on Bank Accounts tab")
    public BankAccountsPage clickBankAccountsTab() {
        commons.navigateToSystemAdministrationModule();
        driver.element().click(bankAccountsTabSelector);
        return this;
    }

    @Step("Click on Add Bank Accounts BTN")
    public BankAccountsPage clickAddBankAccountsBtn() {
        commons.clickOnAddBtn();
        return this;
    }

    @Step("Select bank ")
    public BankAccountsPage selectBank(String bankName) {
        driver.element().selectByVisibilityOfText(banksListSelector, bankName);
        return this;
    }

    @Step("Fill Bank Accounts Name")
    public BankAccountsPage fillBankAccountsName(String bankAccountsNameAR, String bankAccountsNameEN) {
        commons.fillNameFields(bankAccountsNameAR, bankAccountsNameEN);
        return this;
    }

    @Step("Fill IBAN")
    public BankAccountsPage fillIban(String iban) {
        driver.element().fillField(iBanInputSelector, iban);
        return this;
    }

    @Step("Fill Notes")
    public BankAccountsPage fillNotes(String notes) {
        driver.element().fillField(notesInputSelector, notes);
        return this;
    }

    @Step("Create New bank account")
    public BankAccountsPage createNewBankAccount(String bankName, String bankAccountsNameAR, String bankAccountsNameEN, String iban, String notes) {
        clickBankAccountsTab();
        clickAddBankAccountsBtn();
        selectBank(bankName);
        fillBankAccountsName(bankAccountsNameAR, bankAccountsNameEN);
        fillIban(iban);
        fillNotes(notes);
        commons.clickOnSaveBtn();
        commons.checkSuccessMessageAndClickOk();
        return this;
    }

    @Step("Edit bank account")
    public BankAccountsPage editBankAccount(String bankName, String bankAccountsNameAR, String bankAccountsNameEN, String iban, String notes) {
        clickBankAccountsTab();
        commons.goToUpdateForm();
        selectBank(bankName);
        fillBankAccountsName(bankAccountsNameAR, bankAccountsNameEN);
        fillIban(iban);
        fillNotes(notes);
        commons.clickOnSaveBtn();
        commons.checkSuccessMessageAndClickOk();
        return this;
    }

}
