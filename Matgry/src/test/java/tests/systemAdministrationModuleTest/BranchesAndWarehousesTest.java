package tests.systemAdministrationModuleTest;

import driverFactory.Driver;
import listeners.testng.TestNGListener;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.systemAdministration.BranchesAndWarehousesPage;
import utilities.DataReader;

@Listeners(TestNGListener.class)
public class BranchesAndWarehousesTest {
    private Driver driver;
    String credentialsJsonFilePath = "src/test/resources/testData/credentials.json";
    String branchesAndWarehousesJsonFilePath = "src/test/resources/testData/systemAdministrationData/branchesAndWarehouseData.json";

    @BeforeClass
    public void setUp() {
        driver = new Driver();
        DataReader.loadFiles(credentialsJsonFilePath);
        DataReader.loadFiles(branchesAndWarehousesJsonFilePath);
        String username = DataReader.getValue(credentialsJsonFilePath, "username");
        String password = DataReader.getValue(credentialsJsonFilePath, "password");

        new LoginPage(driver)
                .fillUserNameFiled(username)
                .fillPasswordField(password)
                .clickLoginBtn();
    }
    // Test cases for branches and warehouses module
    @Test(priority = 1)
    public void testAddBranch() throws InterruptedException {
        new BranchesAndWarehousesPage(driver)
                .navigateToBranchesAndWarehousesPage()
                .clickOnAddBranch()
                .fillBranchForm(
                        DataReader.getValue(branchesAndWarehousesJsonFilePath, "create.nameAR"),
                        DataReader.getValue(branchesAndWarehousesJsonFilePath, "create.nameEN"),
                        DataReader.getValue(branchesAndWarehousesJsonFilePath, "create.addressAR"),
                        DataReader.getValue(branchesAndWarehousesJsonFilePath, "create.addressEN"),
                        DataReader.getValue(branchesAndWarehousesJsonFilePath, "create.commercialRegistrationNo"),
                        DataReader.getValue(branchesAndWarehousesJsonFilePath, "create.phone"),
                        DataReader.getValue(branchesAndWarehousesJsonFilePath, "create.mobile1"),
                        DataReader.getValue(branchesAndWarehousesJsonFilePath, "create.mobile2"),
                        DataReader.getValue(branchesAndWarehousesJsonFilePath, "create.responsibleUser"),
                        Boolean.parseBoolean(DataReader.getValue(branchesAndWarehousesJsonFilePath, "create.active")),
                        Boolean.parseBoolean(DataReader.getValue(branchesAndWarehousesJsonFilePath, "create.isBranch"))
                        );
    }
    @Test(priority =2)
    public void testUpdateBranch() throws InterruptedException {
        new BranchesAndWarehousesPage(driver)
                .navigateToBranchesAndWarehousesPage()
                .clickOnUpdateBranch()
                .fillBranchForm(
                        DataReader.getValue(branchesAndWarehousesJsonFilePath, "update.nameAR"),
                        DataReader.getValue(branchesAndWarehousesJsonFilePath, "update.nameEN"),
                        DataReader.getValue(branchesAndWarehousesJsonFilePath, "update.addressAR"),
                        DataReader.getValue(branchesAndWarehousesJsonFilePath, "update.addressEN"),
                        DataReader.getValue(branchesAndWarehousesJsonFilePath, "update.commercialRegistrationNo"),
                        DataReader.getValue(branchesAndWarehousesJsonFilePath, "update.phone"),
                        DataReader.getValue(branchesAndWarehousesJsonFilePath, "update.mobile1"),
                        DataReader.getValue(branchesAndWarehousesJsonFilePath, "update.mobile2"),
                        DataReader.getValue(branchesAndWarehousesJsonFilePath, "update.responsibleUser"),
                        Boolean.parseBoolean(DataReader.getValue(branchesAndWarehousesJsonFilePath, "update.active")),
                        Boolean.parseBoolean(DataReader.getValue(branchesAndWarehousesJsonFilePath, "update.isBranch"))
                );
    }
    @Test(priority = 3)
    public void testDeleteBranch() throws InterruptedException {
        new BranchesAndWarehousesPage(driver)
                .navigateToBranchesAndWarehousesPage()
                .deleteBranch();
    }
    @Test(priority = 4)
    public void testArabicValidation() {
        new BranchesAndWarehousesPage(driver)
                .checkArabicValidation(DataReader.getValue(branchesAndWarehousesJsonFilePath, "create.nameEN"));
    }
    @Test(priority = 5)
    public void testEnglishValidation() {
        new BranchesAndWarehousesPage(driver)
                .checkEnglishValidation(DataReader.getValue(branchesAndWarehousesJsonFilePath, "create.nameAR"));
    }



    @AfterClass
    public void tearDown() {
        driver.browser().deleteAllCookies();
        driver.quit();
    }

}

