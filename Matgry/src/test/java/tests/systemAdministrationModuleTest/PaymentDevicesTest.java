package tests.systemAdministrationModuleTest;

import driverFactory.Driver;
import listeners.testng.TestNGListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.systemAdministration.PaymentDevicesPage;
import utilities.DataReader;

@Listeners(TestNGListener.class)
public class PaymentDevicesTest {

    public Driver driver;
    public PaymentDevicesPage paymentDevicesPage;
    String credentialsJsonFilePath = "src/test/resources/testData/credentials.json";
    public String PaymentDeviceJsonFilePath = "src/test/resources/testData/systemAdministrationData/paymentDevicesData.json";

    @BeforeClass
    public void setUp() {
        driver = new Driver();
        paymentDevicesPage = new PaymentDevicesPage(driver);
        DataReader.loadFiles(credentialsJsonFilePath);
        DataReader.loadFiles(PaymentDeviceJsonFilePath);
        String username = DataReader.getValue(credentialsJsonFilePath, "username");
        String password = DataReader.getValue(credentialsJsonFilePath, "password");

        new LoginPage(driver)
                .fillUserNameFiled(username)
                .fillPasswordField(password)
                .clickLoginBtn();
    }


    @Test(priority = 1)
    public void createPaymentDevice() {
        paymentDevicesPage
                .createNewPaymentDevice(
                        DataReader.getValue(PaymentDeviceJsonFilePath, "create.bankAccountName"),
                        DataReader.getValue(PaymentDeviceJsonFilePath, "create.nameAR"),
                        DataReader.getValue(PaymentDeviceJsonFilePath, "create.nameEN"),
                        DataReader.getValue(PaymentDeviceJsonFilePath, "create.deviceNumber"),
                        Boolean.parseBoolean(DataReader.getValue(PaymentDeviceJsonFilePath, "create.isActivated")),
                        DataReader.getValue(PaymentDeviceJsonFilePath, "create.notes")
                );
    }

    @Test(priority = 2)
    public void editPaymentDevice() {
        paymentDevicesPage
                .updatePaymentDevice(
                        DataReader.getValue(PaymentDeviceJsonFilePath, "update.bankAccountName"),
                        DataReader.getValue(PaymentDeviceJsonFilePath, "update.nameAR"),
                        DataReader.getValue(PaymentDeviceJsonFilePath, "update.nameEN"),
                        DataReader.getValue(PaymentDeviceJsonFilePath, "update.deviceNumber"),
                        Boolean.parseBoolean(DataReader.getValue(PaymentDeviceJsonFilePath, "update.isActivated")),
                        DataReader.getValue(PaymentDeviceJsonFilePath, "update.notes")
                );
    }

    @Test(priority = 3)
    public void deletePaymentDevice() {
        paymentDevicesPage.deletePaymentDevice();
    }


    @AfterClass
    public void tearDown() {
        driver.browser().deleteAllCookies();
        driver.quit();
    }

}
