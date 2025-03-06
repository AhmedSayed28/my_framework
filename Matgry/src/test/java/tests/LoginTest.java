package tests;

import driverFactory.Driver;
import listeners.testng.TestNGListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utilities.DataReader;

@Listeners(TestNGListener.class)
public class LoginTest {

    public Driver driver;
    String credentialsJsonFilePath = "src/test/resources/testData/credentials.json";

    @BeforeClass
    public void setUp() {
        driver = new Driver();
        DataReader.loadFiles(credentialsJsonFilePath);
    }

    @Test
    public void login() throws InterruptedException {
        String username = DataReader.getValue(credentialsJsonFilePath, "username");
        String password = DataReader.getValue(credentialsJsonFilePath, "password");
        String password = DataReader.getValue(credentialsJsonFilePath, "password");

        new LoginPage(driver)
                .fillUserNameFiled(username)
                .fillPasswordField(password)
                .clickLoginBtn()
                .checkErrorMessage();
    }

    @AfterClass
    public void tearDown() {
        driver.browser().deleteAllCookies();
        driver.quit();
    }

}
