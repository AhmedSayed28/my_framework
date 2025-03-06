package tests.systemAdministrationModuleTest;

import driverFactory.Driver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.systemAdministration.AreasPage;
import utilities.DataReader;

public class AreasTest {
    public Driver driver;
    private AreasPage area;
    String credentialsJsonFilePath = "src/test/resources/testData/credentials.json";
    String areasJsonFilePath = "src/test/resources/testData/systemAdministrationData/areasData.json";

    @BeforeClass
    public void setUp() {
        driver = new Driver();
        area = new AreasPage(driver);
        DataReader.loadFiles(credentialsJsonFilePath);
        DataReader.loadFiles(areasJsonFilePath);
        String username = DataReader.getValue(credentialsJsonFilePath, "username");
        String password = DataReader.getValue(credentialsJsonFilePath, "password");

        new LoginPage(driver)
                .fillUserNameFiled(username)
                .fillPasswordField(password)
                .clickLoginBtn();
    }

    @Test(priority = 1)
    public void testAddArea() throws InterruptedException {
        area.addArea(
                DataReader.getValue(areasJsonFilePath, "create.country"),
                DataReader.getValue(areasJsonFilePath, "create.nameAR"),
                DataReader.getValue(areasJsonFilePath, "create.nameEN")
        );
    }
    @Test(priority = 2)
    public void testUpdateArea() throws InterruptedException {
        area.updateArea(
                DataReader.getValue(areasJsonFilePath, "update.country"),
                DataReader.getValue(areasJsonFilePath, "update.nameAR"),
                DataReader.getValue(areasJsonFilePath, "update.nameEN")
        );
    }

    @Test(priority = 3)
    public void testDeleteArea() throws InterruptedException {
        area.deleteArea();
    }

    @Test(priority = 4)
    public void testValidateLanguage() throws InterruptedException {
        area.validateARLanguage(DataReader.getValue(areasJsonFilePath, "create.nameEN"));
        area.validateENLanguage(DataReader.getValue(areasJsonFilePath, "create.nameAR"));
    }


    @AfterClass
    public void tearDown() {
        driver.browser().deleteAllCookies();
        driver.quit();
    }
}
