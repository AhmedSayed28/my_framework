package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ScreenShotManager {

    static String screenShotDirectoryPath = "./screenshots";


    public static void CaptureScreenShot(WebDriver driver, String screenShotName) {
        try {
            Path screenshotDir = Paths.get(screenShotDirectoryPath);
            if (!Files.exists(screenshotDir)) {
                Files.createDirectories(screenshotDir);
            }

            Path destination = Paths.get(screenShotDirectoryPath, screenShotName + ".jpg");

            byte[] byteArray = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            Files.write(destination, byteArray, StandardOpenOption.CREATE);

            System.out.println("Screenshot saved: " + destination.toAbsolutePath());

        } catch (IOException e) {
            System.out.println("Unable to save screenshot: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unexpected error while taking screenshot: " + e.getMessage());
            e.printStackTrace();
        }
    }








}
