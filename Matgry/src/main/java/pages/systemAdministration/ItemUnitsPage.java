package pages.systemAdministration;

import driverFactory.Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.Commons;

public class ItemUnitsPage {
    public Driver driver;
    public Commons commons;

    public ItemUnitsPage(Driver driver) {
        this.driver = driver;
        commons = new Commons(driver);
    }

    private final By itemUnitsTab = By.xpath("//li[@id=\"ItemUnits\"]");
    private final By isActivatedToggle = By.xpath("//input[@id='IsActivated']");

    @Step("Navigate to Item Units tab")
    public ItemUnitsPage navigateToItemUnitsTab() {
        commons.navigateToSystemAdministrationModule();
        driver.element().click(itemUnitsTab);
        return this;
    }

    @Step("Click on Add Item Unit button")
    public ItemUnitsPage clickOnAddItemUnitButton() {
        commons.clickOnAddBtn();
        return this;
    }

    @Step("Fill Arabic and English Name")
    public ItemUnitsPage fillNameFields(String nameAR, String nameEN) {
        commons.fillNameFields(nameAR, nameEN);
        return this;
    }

    @Step("Toggle Is Activated")
    public ItemUnitsPage toggleIsActivated(boolean isActivated) {
        if(isActivated && !driver.element().isSelected(isActivatedToggle)) {
            driver.element().click(isActivatedToggle);
        } else if(!isActivated && driver.element().isSelected(isActivatedToggle)) {
            driver.element().click(isActivatedToggle);
        }
        return this;
    }

    @Step("Click on Save button")
    public ItemUnitsPage clickOnSaveBtn() {
        commons.clickOnSaveBtn();
        return this;
    }

    @Step("check if the item unit is added successfully")
    public ItemUnitsPage checkSuccessMessageAndClickOk() {
        commons.checkSuccessMessageAndClickOk();
        return this;
    }

    @Step("Create new item unit")
    public ItemUnitsPage createNewItemUnit(String nameAR, String nameEN, boolean isActivated) {
        navigateToItemUnitsTab()
                .clickOnAddItemUnitButton()
                .fillNameFields(nameAR, nameEN)
                .toggleIsActivated(isActivated)
                .clickOnSaveBtn()
                .checkSuccessMessageAndClickOk();
        return this;
    }

    @Step("Edit item unit")
    public ItemUnitsPage updateItemUnit(String nameAR, String nameEN, boolean isActivated) {
        navigateToItemUnitsTab();
        commons.goToUpdateForm();
                fillNameFields(nameAR, nameEN)
                .toggleIsActivated(isActivated)
                .clickOnSaveBtn()
                .checkSuccessMessageAndClickOk();
        return this;
    }

    @Step("Delete item unit")
    public ItemUnitsPage deleteItemUnit() {
        commons.delete();
        return this;
    }
}
