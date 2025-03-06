package tests.systemAdministrationModuleTest;

import driverFactory.Driver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.systemAdministration.AreasPage;
import pages.systemAdministration.BankAccountsPage;
import utilities.DataReader;

public class BankAccountsTest {
    public Driver driver;
    private BankAccountsPage account;
    String credentialsJsonFilePath = "src/test/resources/testData/credentials.json";
    String bankAccountsJsonFilePath = "src/test/resources/testData/systemAdministrationData/bankAccountsData.json";

    @BeforeClass
    public void setUp() {
        driver = new Driver();
        account = new BankAccountsPage(driver);

        DataReader.loadFiles(credentialsJsonFilePath);
        DataReader.loadFiles(bankAccountsJsonFilePath);
        String username = DataReader.getValue(credentialsJsonFilePath, "username");
        String password = DataReader.getValue(credentialsJsonFilePath, "password");

        new LoginPage(driver)
                .fillUserNameFiled(username)
                .fillPasswordField(password)
                .clickLoginBtn();
    }

    // Test cases here...
    @Test(priority = 1)
    public void testAddBankAccount() throws InterruptedException {
        account.createNewBankAccount(
                DataReader.getValue(bankAccountsJsonFilePath, "create.bankName"),
                DataReader.getValue(bankAccountsJsonFilePath, "create.nameAR"),
                DataReader.getValue(bankAccountsJsonFilePath, "create.nameEN"),
                DataReader.getValue(bankAccountsJsonFilePath, "create.IBAN"),
                DataReader.getValue(bankAccountsJsonFilePath, "create.notes")
        );
    }

    @Test(priority = 2)
    public void testEditBankAccount() throws InterruptedException {
        account.editBankAccount(
                DataReader.getValue(bankAccountsJsonFilePath, "update.bankName"),
                DataReader.getValue(bankAccountsJsonFilePath, "update.nameAR"),
                DataReader.getValue(bankAccountsJsonFilePath, "update.nameEN"),
                DataReader.getValue(bankAccountsJsonFilePath, "update.IBAN"),
                DataReader.getValue(bankAccountsJsonFilePath, "update.notes")
        );
    }

    @AfterClass
    public void tearDown() {
        driver.browser().deleteAllCookies();
        driver.quit();
    }
}
