package pages.systemAdministration;

import driverFactory.Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.Commons;

public class BranchesAndWarehousesPage {

    private Driver driver;
    private Commons commons;

    public BranchesAndWarehousesPage(Driver driver){
        this.driver = driver;
        commons = new Commons(driver);
    }

    private final By branchesAndWarehousesTabSelector = By.xpath("//li[@id='Branches_Warehouses'] //a[@class='btnClickShowLoader']");
    private final By nameARFieldSelector = By.xpath("//input[@id='NameAR']");
    private final By nameENFieldSelector = By.xpath("//input[@id='Name']");
    private final By branchAddressARFieldSelector = By.xpath("//input[@id='AddressAR']");
    private final By branchAddressENFieldSelector = By.xpath("//input[@id='Address']");
    private final By branchCommercialRegistrationNoFieldSelector = By.xpath("//input[@id='CommercialRegisterationNo']");
    private final By branchPhoneFieldSelector = By.xpath("//input[@id='Phone']");
    private final By branchMobile1FieldSelector = By.xpath("//input[@id='Mobile1']");
    private final By branchMobile2FieldSelector = By.xpath("//input[@id='Mobile2']");
    private final By branchResponsibleUserFieldSelector = By.xpath("//input[@id='ResponsibleUser']");
    private final By branchActiveCheckboxSelector = By.xpath("//input[@id='IsActivated']");
    private final By branchIsBranchCheckboxSelector = By.xpath("//input[@id='IsSubBranch']");
    private final By deleteBtnSelector = By.xpath("//tbody/tr[1]/td[8]/div[1]/div[1]/a[4]");

    // update branches
    private final By settingsIconSelector = By.xpath("//tbody/tr[1]/td[8]/div[1]/span[1]/i[1]");
    private final By updateBranchBtnSelector = By.xpath("//*[@id=\"branchTableBody\"]/tr[1]/td[8]/div/div/a[2]");


    @Step("Navigate to Branches and Warehouses page")
    public BranchesAndWarehousesPage navigateToBranchesAndWarehousesPage()  {
        commons.navigateToSystemAdministrationModule();
        driver.element().click(branchesAndWarehousesTabSelector);
        return this;
    }
    @Step("Click on Add Branch")
    public BranchesAndWarehousesPage clickOnAddBranch() {
        commons.clickOnAddBtn();
        return this;
    }
    @Step("Add new branch")
    public BranchesAndWarehousesPage fillBranchForm(String branchNameAR, String branchNameEN, String branchAddressAR, String branchAddressEN, String branchCommercialRegistrationNo, String branchPhone, String branchMobile1, String branchMobile2, String branchResponsibleUser, boolean branchActive, boolean branchIsBranch) {
        commons.fillNameFields(branchNameAR, branchNameEN);
        driver.element().fillField(branchAddressARFieldSelector, branchAddressAR);
        driver.element().fillField(branchAddressENFieldSelector, branchAddressEN);
        driver.element().fillField(branchCommercialRegistrationNoFieldSelector, branchCommercialRegistrationNo);
        driver.element().fillField(branchPhoneFieldSelector, branchPhone);
        driver.element().fillField(branchMobile1FieldSelector, branchMobile1);
        driver.element().fillField(branchMobile2FieldSelector, branchMobile2);
        driver.element().fillField(branchResponsibleUserFieldSelector, branchResponsibleUser);
        if (branchActive) {
            driver.element().click(branchActiveCheckboxSelector);
        }
        if (branchIsBranch) {
            driver.element().click(branchIsBranchCheckboxSelector);
        }
        commons.clickOnSaveBtn().checkSuccessMessageAndClickOk();
        return this;
    }
    @Step("Click on update branch")
    public BranchesAndWarehousesPage clickOnUpdateBranch() {
        driver.element().click(settingsIconSelector);
        driver.element().click(updateBranchBtnSelector);
        return this;
    }

    @Step("Delete branch")
    public BranchesAndWarehousesPage deleteBranch() {
        driver.element().click(settingsIconSelector);
        driver.element().click(deleteBtnSelector);
        driver.element().acceptAlert();

        commons.checkSuccessMessageAndClickOk();
        return this;
    }

    @Step("Check Arabic Validation")
    public BranchesAndWarehousesPage checkArabicValidation(String englishText) {
        clickOnAddBranch();
        commons.checkArabicValidation(englishText);
        return this;
    }

    @Step("Check English Validation")
    public BranchesAndWarehousesPage checkEnglishValidation(String arabicText) {
        clickOnAddBranch();
        commons.checkEnglishValidation(arabicText);
        return this;
    }
}

