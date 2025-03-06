package tests.systemAdministrationModuleTest;

import driverFactory.Driver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.systemAdministration.ItemUnitsPage;
import utilities.DataReader;

public class ItemUnitsTest {

    public Driver driver;
    public ItemUnitsPage itemUnitsPage;
    String credentialsJsonFilePath = "src/test/resources/testData/credentials.json";
    public String itemUnitsJsonFilePath = "src/test/resources/testData/systemAdministrationData/itemUnitsData.json";

    @BeforeClass
    public void setUp() {
        driver = new Driver();
        itemUnitsPage = new ItemUnitsPage(driver);
        DataReader.loadFiles(credentialsJsonFilePath);
        DataReader.loadFiles(itemUnitsJsonFilePath);
        String username = DataReader.getValue(credentialsJsonFilePath, "username");
        String password = DataReader.getValue(credentialsJsonFilePath, "password");

        new LoginPage(driver)
                .fillUserNameFiled(username)
                .fillPasswordField(password)
                .clickLoginBtn();
    }

    @Test(priority = 1)
    public void createItemUnit() {
        itemUnitsPage
                .createNewItemUnit(
                        DataReader.getValue(itemUnitsJsonFilePath, "create.nameAR"),
                        DataReader.getValue(itemUnitsJsonFilePath, "create.nameEN"),
                        Boolean.parseBoolean(DataReader.getValue(itemUnitsJsonFilePath, "create.isActivated"))
                );
    }
    @Test(priority = 2)
    public void editItemUnit() {
        itemUnitsPage
                .updateItemUnit(
                        DataReader.getValue(itemUnitsJsonFilePath, "update.nameAR"),
                        DataReader.getValue(itemUnitsJsonFilePath, "update.nameEN"),
                        Boolean.parseBoolean(DataReader.getValue(itemUnitsJsonFilePath, "update.isActivated"))
                );
    }

    @Test(priority = 3)
    public void deleteItemUnit() {
        itemUnitsPage.deleteItemUnit();
    }

    @AfterClass
    public void tearDown() {
        driver.browser().deleteAllCookies();
        driver.quit();
    }
}
