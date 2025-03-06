package pages;

import driverFactory.Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

public class LoginPage {
    private Driver driver;

    By userNameField = By.xpath("//input[@id='txtUserName']");
    By passwordField = By.xpath("//input[@id='txtPassword']");
    By loginButton = By.xpath("//button[@id='btnLogin']");
    By errorSelector = By.xpath("//span[@id='UNError']");

    public LoginPage(Driver driver) {
        this.driver = driver;
    }

    /*********************************  Actions  *****************************************************/


    @Step("Fill username filed")
    public LoginPage fillUserNameFiled(String username){
        driver.element().fillField(userNameField,username);
        return this;
    }
    @Step("Fill password filed")
    public LoginPage fillPasswordField(String password) {
        driver.element().fillField(passwordField, password);
        return this;
    }
    @Step("Click login button")
    public LoginPage clickLoginBtn(){
        driver.element().click(loginButton);
        return this;
    }

    /*********************************  Verifications  *****************************************************/

    @Step("Check error message")
    public LoginPage checkErrorMessage() throws InterruptedException {
        Thread.sleep(100);
        Assert.assertFalse(driver.element().isDisplayed(errorSelector));
        return this;
    }

}
