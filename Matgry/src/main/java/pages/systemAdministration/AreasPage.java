package pages.systemAdministration;

import driverFactory.Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.Commons;

public class AreasPage {
    public Driver driver;
    Commons commons;

    public AreasPage(Driver driver) {
        this.driver = driver;
        commons = new Commons(driver);
    }

    private final By areasTabSelector = By.xpath("//li[@id='Areas'] //a[@class='btnClickShowLoader']");
    private final By selectCountrySelector = By.xpath("//select[@id='location-select-modal']");

    @Step("Navigate to Areas page")
    public AreasPage navigateToAreasPage() throws InterruptedException {
        commons.navigateToSystemAdministrationModule();
        driver.element().click(areasTabSelector);
        return this;
    }

    @Step("Click on Add Area Button")
    public AreasPage clickOnAddAreaBtn() {
        commons.clickOnAddBtn();
        return this;
    }



    @Step("Fill Area Form")
    public AreasPage fillAreaForm(String country,String areaNameAR, String areaNameEN) {
        driver.element().selectByVisibilityOfText(selectCountrySelector,country);
        commons.fillNameFields(areaNameAR, areaNameEN);
        return this;
    }

    @Step("Save Area")
    public AreasPage saveArea() {
        commons.clickOnSaveBtn();
        return this;
    }

    @Step("Check Success Message and Click Ok")
    public AreasPage checkSuccessMessageAndClickOk() {
        commons.checkSuccessMessageAndClickOk();
        return this;
    }

    @Step("Add Area")
    public AreasPage addArea(String country,String areaNameAR, String areaNameEN) throws InterruptedException {
        navigateToAreasPage();
        clickOnAddAreaBtn();
        fillAreaForm(country,areaNameAR, areaNameEN);
        saveArea();
        checkSuccessMessageAndClickOk();
        return this;
    }

    @Step("Update Area")
    public AreasPage updateArea(String country,String areaNameAR, String areaNameEN) {
        commons.goToUpdateForm();
        fillAreaForm(country,areaNameAR, areaNameEN);
        saveArea();
        checkSuccessMessageAndClickOk();
        return this;
    }

    @Step("Delete Area")
    public AreasPage deleteArea() {
        commons.delete();
        return this;
    }

    @Step("Validate Arabic Language")
    public AreasPage validateARLanguage(String areaNameEN) throws InterruptedException {
        navigateToAreasPage();
        commons.checkAreaArabicValidation(areaNameEN);
        return this;
    }

    @Step("Validate English Language")
    public AreasPage validateENLanguage(String areaNameAR) {
        commons.checkAreaEnglishValidation(areaNameAR);
        return this;
    }



}
