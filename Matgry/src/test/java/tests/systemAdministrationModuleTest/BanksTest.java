package tests.systemAdministrationModuleTest;

import driverFactory.Driver;
import listeners.testng.TestNGListener;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.systemAdministration.BanksPage;
import utilities.DataReader;

@Listeners(TestNGListener.class)
public class BanksTest {
    public Driver driver;
    private BanksPage bank;
    String credentialsJsonFilePath = "src/test/resources/testData/credentials.json";
    String banksJsonFilePath = "src/test/resources/testData/systemAdministrationData/banksData.json";

    @BeforeClass
    public void setUp() {
        driver = new Driver();
        bank = new BanksPage(driver);
        DataReader.loadFiles(credentialsJsonFilePath);
        DataReader.loadFiles(banksJsonFilePath);
        String username = DataReader.getValue(credentialsJsonFilePath, "username");
        String password = DataReader.getValue(credentialsJsonFilePath, "password");

        new LoginPage(driver)
                .fillUserNameFiled(username)
                .fillPasswordField(password)
                .clickLoginBtn();
    }

    @Test
    public void testCreateBank()  {
        bank
                .createBank(
                        DataReader.getValue(banksJsonFilePath, "create.nameAR"),
                        DataReader.getValue(banksJsonFilePath, "create.nameEN")
                );
    }

    @Test
    public void testUpdateBank()  {
        bank
                .updateBank(
                        DataReader.getValue(banksJsonFilePath, "update.nameAR"),
                        DataReader.getValue(banksJsonFilePath, "update.nameEN")
                );
    }

    @Test
    public void testDeleteBank()  {
        bank
                .deleteBank();
    }

    @Test
    public void testValidateLanguageBankName()  {
        bank
                .validateLanguageBankName(
                        DataReader.getValue(banksJsonFilePath, "create.nameEN"),
                        DataReader.getValue(banksJsonFilePath, "create.nameAR")
                );
    }


    @AfterClass
    public void tearDown() {
        driver.browser().deleteAllCookies();
        driver.quit();
    }


}
