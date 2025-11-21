package pages;

//src/main/java/pages/AppointmentPages.java

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AppointmentPages {
    WebDriver driver;
    public AppointmentPages(WebDriver driver) { this.driver = driver; }

    public WebElement getLoginUsername() { return driver.findElement(By.id("username")); }
    public WebElement getLoginPassword() { return driver.findElement(By.id("password")); }
    public WebElement getLoginButton() { return driver.findElement(By.id("login-btn")); }

    public WebElement getNewPatientButton() { return driver.findElement(By.id("new-patient-btn")); }
    public WebElement getPatientInformationText() { return driver.findElement(By.id("patient-info-text")); }
    public WebElement getCareOfPersonContactTextbox() { return driver.findElement(By.id("care-person-contact")); }
    public WebElement getCareOfPersonPlaceholder() { return driver.findElement(By.id("care-person-contact")); }
    public WebElement getLastNameTextbox() { return driver.findElement(By.id("last-name")); }
    public WebElement getPrintInvoiceButton() { return driver.findElement(By.id("print-invoice-btn")); }
    public WebElement getConfirmPopupButton() { return driver.findElement(By.id("confirm-btn")); }
    public WebElement getErrorMessage() { return driver.findElement(By.id("error-msg")); }
    public WebElement getFirstNameTextbox() { return driver.findElement(By.id("first-name")); }
    public WebElement getMiddleNameTextbox() { return driver.findElement(By.id("middle-name")); }
    public WebElement getAgeTextbox() { return driver.findElement(By.id("age")); }
    public WebElement getPhoneTextbox() { return driver.findElement(By.id("phone")); }
    public WebElement getHaveDOBCheckbox() { return driver.findElement(By.id("have-dob")); }
    public WebElement getDatepickerField() { return driver.findElement(By.id("datepicker")); }
    public WebElement getExternalCheckbox() { return driver.findElement(By.id("external-checkbox")); }
    public WebElement getReferrerTextbox() { return driver.findElement(By.id("referrer-name")); }
    public WebElement getAddButton() { return driver.findElement(By.id("add-btn")); }
}
