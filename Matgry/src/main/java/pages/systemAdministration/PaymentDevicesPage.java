package pages.systemAdministration;

import driverFactory.Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.Commons;

public class PaymentDevicesPage {

    public Driver driver;
    public Commons commons;

    public PaymentDevicesPage(Driver driver) {
        this.driver = driver;
        commons = new Commons(driver);
    }

    private final By paymentDevicesTab = By.xpath("//li[@id=\"PaymentDevices\"]");
    private final By bankAccountsList = By.xpath("//select[@id='bank-select-modal']");
    private final By paymentDeviceNumberField = By.xpath("//input[@id='DeviceNo']");
    private final By isActivatedToggle = By.xpath("//input[@id='IsActivated']");
    private final By paymentDeviceNotesField = By.xpath("//textarea[@id='Notes']");

    @Step("Navigate to Payment Devices tab")
    public PaymentDevicesPage clickOnPaymentDevicesTab() {
        commons.navigateToSystemAdministrationModule();
        driver.element().click(paymentDevicesTab);
        return this;
    }

    @Step("Click on Add Payment Device button")
    public PaymentDevicesPage clickOnAddPaymentDeviceButton() {
        commons.clickOnAddBtn();
        return this;
    }

    @Step("Select bank account")
    public PaymentDevicesPage selectBankAccount(String bankAccountName) {
        driver.element().isDisplayed(bankAccountsList);
        driver.element().selectByIndex(bankAccountsList, 1);
        return this;
    }

    @Step("Fill Payment Device names")
    public PaymentDevicesPage fillPaymentDeviceNames(String paymentDeviceNameAR , String paymentDeviceNameEN) {
        commons.fillNameFields(paymentDeviceNameAR, paymentDeviceNameEN);
        return this;
    }

    @Step("Fill Payment Device number")
    public PaymentDevicesPage fillPaymentDeviceNumber(String paymentDeviceNumber) {
        driver.element().fillField(paymentDeviceNumberField, paymentDeviceNumber);
        return this;
    }

    @Step("Toggle Is Activated")
    public PaymentDevicesPage toggleIsActivated(boolean isActivated) {
        if(isActivated && !driver.element().isSelected(isActivatedToggle)) {
            driver.element().click(isActivatedToggle);
        } else if(!isActivated && driver.element().isSelected(isActivatedToggle)) {
            driver.element().click(isActivatedToggle);
        }
        return this;
    }

    @Step("Fill Payment Device notes")
    public PaymentDevicesPage fillPaymentDeviceNotes(String paymentDeviceNotes) {
        driver.element().fillField(paymentDeviceNotesField, paymentDeviceNotes);
        return this;
    }

    @Step("Click on Save button")
    public PaymentDevicesPage clickOnSaveBtn() {
        commons.clickOnSaveBtn();
        return this;
    }

    @Step("Check success message and click OK")
    public PaymentDevicesPage checkSuccessMessageAndClickOk() {
        commons.checkSuccessMessageAndClickOk();
        return this;
    }

    @Step("Create new Payment Device")
    public PaymentDevicesPage createNewPaymentDevice(String bankAccountName, String paymentDeviceNameAR, String paymentDeviceNameEN, String paymentDeviceNumber, boolean isActivated, String paymentDeviceNotes) {
        clickOnPaymentDevicesTab()
                .clickOnAddPaymentDeviceButton()
                .fillPaymentDeviceNames(paymentDeviceNameAR, paymentDeviceNameEN)
                .selectBankAccount(bankAccountName)
                .fillPaymentDeviceNumber(paymentDeviceNumber)
                .toggleIsActivated(isActivated)
                .fillPaymentDeviceNotes(paymentDeviceNotes)
                .clickOnSaveBtn()
                .checkSuccessMessageAndClickOk();
        return this;
    }

    @Step("Update Payment Device")
    public PaymentDevicesPage updatePaymentDevice(String bankAccountName, String paymentDeviceNameAR, String paymentDeviceNameEN, String paymentDeviceNumber, boolean isActivated, String paymentDeviceNotes) {
        clickOnPaymentDevicesTab();
        commons.goToUpdateForm();
        fillPaymentDeviceNames(paymentDeviceNameAR, paymentDeviceNameEN);
        selectBankAccount(bankAccountName);
        fillPaymentDeviceNumber(paymentDeviceNumber);
        toggleIsActivated(isActivated);
        fillPaymentDeviceNotes(paymentDeviceNotes);
        clickOnSaveBtn()
                .checkSuccessMessageAndClickOk();
        return this;
    }

    @Step("Delete Payment Device")
    public PaymentDevicesPage deletePaymentDevice() {
        clickOnPaymentDevicesTab();
        commons.delete();
        return this;
    }

}
