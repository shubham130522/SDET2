package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class appointment_Pages {
    private WebDriver driver;

    public appointment_Pages(WebDriver driver) {
        this.driver = driver;
    }

    // --- Element Getters ---
    public WebElement newPatientButton() {
        return driver.findElement(By.id("new-patient-btn"));
    }
    public WebElement patientInfoText() {
        return driver.findElement(By.id("patient-info-text"));
    }
    public WebElement careOfPersonContactTextbox() {
        return driver.findElement(By.id("care-person-contact"));
    }
    public WebElement lastNameTextbox() {
        return driver.findElement(By.id("last-name"));
    }
    public WebElement printInvoiceButton() {
        return driver.findElement(By.id("print-invoice-btn"));
    }
    public WebElement confirmPopupButton() {
        return driver.findElement(By.id("confirm-btn"));
    }
    public WebElement errorMessage() {
        return driver.findElement(By.id("error-msg"));
    }
    public WebElement firstNameTextbox() {
        return driver.findElement(By.id("first-name"));
    }
    public WebElement middleNameTextbox() {
        return driver.findElement(By.id("middle-name"));
    }
    public WebElement ageTextbox() {
        return driver.findElement(By.id("age"));
    }
    public WebElement phoneTextbox() {
        return driver.findElement(By.id("phone"));
    }
    public WebElement haveDobCheckbox() {
        return driver.findElement(By.id("have-dob"));
    }
    public WebElement datepickerField() {
        return driver.findElement(By.id("datepicker"));
    }
    public WebElement externalCheckbox() {
        return driver.findElement(By.id("external-checkbox"));
    }
    public WebElement referrerTextbox() {
        return driver.findElement(By.id("referrer-name"));
    }
    public WebElement addButton() {
        return driver.findElement(By.id("add-btn"));
    }
    public WebElement appointmentModuleElement() {
        return driver.findElement(By.id("appointment-module")); // ADJUST if your real id is different
    }

    // --- Getters for Field Values (For Assertion) ---
    public String getFirstNameValue() { return firstNameTextbox().getAttribute("value"); }
    public String getMiddleNameValue() { return middleNameTextbox().getAttribute("value"); }
    public String getLastNameValue() { return lastNameTextbox().getAttribute("value"); }
    public String getAgeValue() { return ageTextbox().getAttribute("value"); }
    public String getPhoneValue() { return phoneTextbox().getAttribute("value"); }
    public String getCareOfPersonPlaceholderText() { return careOfPersonContactTextbox().getAttribute("placeholder"); }

    // --- Method Implementations for Testing ---

    // Returns the current title of the loaded page
    public String verifyTitleOfThePage() {
        return driver.getTitle();
    }

    // Returns true if Appointment module (main container) is visible
    public boolean verifyAppointmentModuleIsPresent() {
        try {
            return appointmentModuleElement().isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Verifies 'New Patient' button and 'Patient Information' text are both visible
    public boolean verifyButtonAndTextIsPresent() {
        try {
            return newPatientButton().isDisplayed() && patientInfoText().isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Scrolls to Care Person Contact textbox and checks if it's enabled and visible
    public boolean scrollToBottomOrVerifyFieldAndHighlight() {
        try {
            WebElement el = careOfPersonContactTextbox();
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
            // Optional: highlight via JavaScript if your test requires
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='2px solid blue'", el);
            return el.isDisplayed() && el.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    // Returns the placeholder value of the Care of Person textbox
    public String verifyPlaceholderNameOfTextbox() {
        return getCareOfPersonPlaceholderText();
    }

    // Returns the error message text for assertions
    public String verifyErrorMessage() {
        try {
            return errorMessage().getText();
        } catch (Exception e) {
            return "";
        }
    }

    // Returns the entered value in Phone text box (for assertion)
    public String verifyEnteredDataIsPresentInTextbox() {
        try {
            return phoneTextbox().getAttribute("value");
        } catch (Exception e) {
            return "";
        }
    }

    // Checkbox selected and datepicker field appears
    public boolean verifyCheckboxIsSelectedAndDatePickerIsPresent() {
        try {
            haveDobCheckbox().click();
            return haveDobCheckbox().isSelected() && datepickerField().isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Verifies success notification popup/message after adding referral
    public boolean verifySuccessNotificationPopupMessage() {
        try {
            externalCheckbox().click();
            referrerTextbox().sendKeys("Dr. Smith");
            addButton().click();
            // Replace with actual locator for your notification
            WebElement notification = driver.findElement(By.className("notification-success")); // Adjust if needed
            return notification.isDisplayed() && notification.getText().contains("Success update");
        } catch (Exception e) {
            return false;
        }
    }
}
