package tests.warehouseModuleTest;

import driverFactory.Driver;
import listeners.testng.TestNGListener;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.warehouse.MainCategoriesPage;
import utilities.DataReader;

@Listeners(TestNGListener.class)
public class MainCategoriesTest {

    public Driver driver;
    String credentialsJsonFilePath = "src/test/resources/testData/credentials.json";
    String warehousesJsonFilePath = "src/test/resources/testData/warehouseData/mainCategoriesData.json";

    @BeforeClass
    public void setUp() {
        driver = new Driver();
        DataReader.loadFiles(credentialsJsonFilePath);
        DataReader.loadFiles(warehousesJsonFilePath);

        String username = DataReader.getValue(credentialsJsonFilePath, "username");
        String password = DataReader.getValue(credentialsJsonFilePath, "password");

        new LoginPage(driver)
                .fillUserNameFiled(username)
                .fillPasswordField(password)
                .clickLoginBtn();
    }

    @Test(priority = 1)
    public void testAddMainCategory() {
        new MainCategoriesPage(driver)
                .navigateToMainCategoriesPage()
                .clickOnAddMainCategory()
                .fillNameFields(
                        DataReader.getValue(warehousesJsonFilePath, "create.nameAR"),
                        DataReader.getValue(warehousesJsonFilePath, "create.nameEN")
                )
                .clickOnSaveMainCategory();

    }
    @Test(priority = 2)
    public void testUpdateMainCategory() {
        new MainCategoriesPage(driver)
                .navigateToMainCategoriesPage()
                .clickOnUpdateMainCategory()
                .fillNameFields(
                        DataReader.getValue(warehousesJsonFilePath, "update.nameAR"),
                        DataReader.getValue(warehousesJsonFilePath, "update.nameEN")
                )
                .clickOnSaveMainCategory();
    }
    @Test(priority = 3)
    public void testDeleteMainCategory() {
        new MainCategoriesPage(driver)
                .navigateToMainCategoriesPage()
                .deleteMainCategory();
    }

    @Test(priority = 4)
    public void testArabicValidation() {
        new MainCategoriesPage(driver)
                .checkArabicValidation(DataReader.getValue(warehousesJsonFilePath, "create.nameEN"));
    }
    @Test(priority = 5)
    public void testEnglishValidation() {
        new MainCategoriesPage(driver)
                .checkEnglishValidation(DataReader.getValue(warehousesJsonFilePath, "create.nameAR"));
    }

    @AfterClass
    public void tearDown() {
        driver.browser().deleteAllCookies();
        driver.quit();
    }
}
