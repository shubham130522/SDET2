package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AppointmentPages {
    private WebDriver driver;

    public AppointmentPages(WebDriver driver) {
        this.driver = driver;
    }

    // --- Login Elements ---
    public WebElement usernameField() { return driver.findElement(By.id("username")); }
    public WebElement passwordField() { return driver.findElement(By.id("password")); }
    public WebElement loginButton() { return driver.findElement(By.id("login-btn")); }

    // --- Home/Dashboard Elements ---
    public WebElement homeTitle() { return driver.findElement(By.tagName("title")); }

    // --- New Visit & Appointment Module Elements ---
    public WebElement newPatientButton() { return driver.findElement(By.id("new-patient-btn")); }
    public WebElement patientInfoText() { return driver.findElement(By.id("patient-info-text")); }
    public WebElement careOfPersonContactTextbox() { return driver.findElement(By.id("care-person-contact")); }
    public WebElement careOfPersonContactPlaceholder() { return driver.findElement(By.id("care-person-contact")); }
    public WebElement lastNameTextbox() { return driver.findElement(By.id("last-name")); }
    public WebElement printInvoiceButton() { return driver.findElement(By.id("print-invoice-btn")); }
    public WebElement confirmPopupButton() { return driver.findElement(By.id("confirm-btn")); }
    public WebElement errorMessage() { return driver.findElement(By.id("error-msg")); }
    public WebElement firstNameTextbox() { return driver.findElement(By.id("first-name")); }
    public WebElement middleNameTextbox() { return driver.findElement(By.id("middle-name")); }
    public WebElement ageTextbox() { return driver.findElement(By.id("age")); }
    public WebElement phoneTextbox() { return driver.findElement(By.id("phone")); }
    public WebElement haveDobCheckbox() { return driver.findElement(By.id("have-dob")); }
    public WebElement datePickerField() { return driver.findElement(By.id("datepicker")); }
    public WebElement externalCheckbox() { return driver.findElement(By.id("external-checkbox")); }
    public WebElement referrerTextbox() { return driver.findElement(By.id("referrer-name")); }
    public WebElement addButton() { return driver.findElement(By.id("add-btn")); }

    // --- Actions (Reusable) ---
    public void login(String username, String password) {
        usernameField().clear();
        usernameField().sendKeys(username);
        passwordField().clear();
        passwordField().sendKeys(password);
        loginButton().click();
    }

    public void clickNewPatientButton() {
        newPatientButton().click();
    }

    public void clearAndSetLastName(String lastName) {
        lastNameTextbox().clear();
        lastNameTextbox().sendKeys(lastName);
    }

    public void clickPrintInvoice() {
        printInvoiceButton().click();
    }

    public void clickConfirmPopup() {
        confirmPopupButton().click();
    }

    public String getErrorMessageText() {
        return errorMessage().getText();
    }

    public void fillPatientInformation(String first, String middle, String last, String age, String phone) {
        firstNameTextbox().clear();
        firstNameTextbox().sendKeys(first);
        middleNameTextbox().clear();
        middleNameTextbox().sendKeys(middle);
        lastNameTextbox().clear();
        lastNameTextbox().sendKeys(last);
        ageTextbox().clear();
        ageTextbox().sendKeys(age);
        phoneTextbox().clear();
        phoneTextbox().sendKeys(phone);
    }

    public void clickHaveDobCheckbox() {
        haveDobCheckbox().click();
    }

    public boolean isDatePickerDisplayed() {
        return datePickerField().isDisplayed();
    }

    public void clickExternalCheckbox() {
        externalCheckbox().click();
    }

    public void setReferrerName(String referrer) {
        referrerTextbox().clear();
        referrerTextbox().sendKeys(referrer);
    }

    public void clickAddButton() {
        addButton().click();
    }

    // --- Getters for Field Values (For Assertion) ---
    public String getFirstNameValue() { return firstNameTextbox().getAttribute("value"); }
    public String getMiddleNameValue() { return middleNameTextbox().getAttribute("value"); }
    public String getLastNameValue() { return lastNameTextbox().getAttribute("value"); }
    public String getAgeValue() { return ageTextbox().getAttribute("value"); }
    public String getPhoneValue() { return phoneTextbox().getAttribute("value"); }
    public String getCareOfPersonPlaceholderText() { return careOfPersonContactPlaceholder().getAttribute("placeholder"); }
}

