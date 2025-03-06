package elementActions;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementActions {

    private WebDriver driver;
    private WebDriverWait wait;

    public ElementActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public ElementActions click(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            System.out.println("Click on " + locator.toString());
            element.click();
        } catch (Exception e) {
            System.err.println("❌ Error clicking on: " + locator.toString() + " | " + e.getMessage());
        }
        return this;
    }

    public ElementActions fillField(By locator, String text) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.clear();
            element.sendKeys(text);
            System.out.println("Fill field " + locator.toString() + " with " + text);
        } catch (Exception e) {
            System.err.println("❌ Error filling field: " + locator.toString() + " | " + e.getMessage());
        }
        return this;
    }

    public ElementActions searchBar(By locator, String text) {
        System.out.println("Type \"" + text + "\" into \"Search Product\".");
        return fillField(locator, text);
    }

    public ElementActions clearField(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.clear();
            System.out.println("Clear field with locator " + locator.toString());
        } catch (Exception e) {
            System.err.println("❌ Error clearing field: " + locator.toString() + " | " + e.getMessage());
        }
        return this;
    }

    public String getTextOf(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.getText();
        } catch (Exception e) {
            System.err.println("❌ Error getting text from: " + locator.toString() + " | " + e.getMessage());
            return "";
        }
    }

    public Boolean isDisplayed(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean isSelected(By locator) {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(locator)).isSelected();
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean isClickable(By locator) {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(locator)).isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public ElementActions selectByValue(By locator, String text) {
        try {
            new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(locator))).selectByValue(text);
        } catch (Exception e) {
            System.err.println("❌ Error selecting by value: " + text + " | " + e.getMessage());
        }
        return this;
    }

    public ElementActions selectByIndex(By locator, int index) {
        try {
            new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(locator))).selectByIndex(index);
        } catch (Exception e) {
            System.err.println("❌ Error selecting by index: " + index + " | " + e.getMessage());
        }
        return this;
    }

    public ElementActions selectByVisibilityOfText(By locator, String text) {
        try {
            new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(locator))).selectByVisibleText(text);
        } catch (Exception e) {
            System.err.println("❌ Error selecting by text: " + text + " | " + e.getMessage());
        }
        return this;
    }

    public ElementActions scrollToElement(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            new Actions(driver).scrollToElement(element).perform();
        } catch (Exception e) {
            System.err.println("❌ Error scrolling to element: " + locator.toString() + " | " + e.getMessage());
        }
        return this;
    }

    public ElementActions hoverOnItem(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            new Actions(driver).moveToElement(element).perform();
        } catch (Exception e) {
            System.err.println("❌ Error hovering on: " + locator.toString() + " | " + e.getMessage());
        }
        return this;
    }

    public ElementActions acceptAlert() {
        try {
            wait.until(ExpectedConditions.alertIsPresent()).accept();
            System.out.println("✅ Alert accepted successfully.");
        } catch (Exception e) {
            System.err.println("❌ No alert found or error accepting alert: " + e.getMessage());
        }
        return this;
    }

    public ElementActions dismissAlert() {
        try {
            wait.until(ExpectedConditions.alertIsPresent()).dismiss();
            System.out.println("✅ Alert dismissed successfully.");
        } catch (Exception e) {
            System.err.println("❌ No alert found or error dismissing alert: " + e.getMessage());
        }
        return this;
    }
}