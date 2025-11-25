package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AppointmentPages {
    WebDriver driver;

    // Sample Locators -- Replace with actual IDs/XPaths from your real application
    By newVisitLink = By.id("newVisitLink");
    By newPatientButton = By.id("newPatientBtn");
    By patientInformationText = By.xpath("//h2[text()='Patient Information']");
    By careOfPersonContactTextbox = By.id("careOfPersonContact");
    By careOfPersonTextbox = By.id("careOfPerson");
    By printInvoiceButton = By.id("printInvoice");
    By confirmButton = By.id("confirmBtn");
    By errorMessage = By.cssSelector(".error-message");
    By firstNameTextbox = By.id("firstName");
    By middleNameTextbox = By.id("middleName");
    By lastNameTextbox = By.id("lastName");
    By ageTextbox = By.id("age");
    By phoneTextbox = By.id("phone");
    By haveDOBCheckbox = By.id("haveDOB");
    By datePickerField = By.id("datepicker");
    By externalCheckbox = By.id("externalReferral");
    By addButton = By.id("addReferral");
    By successNotification = By.cssSelector(".notification-success");

    public AppointmentPages(WebDriver driver) {
        this.driver = driver;
    }
    
    public void openNewVisitPage() {
        driver.findElement(newVisitLink).click();
    }
    public void clickNewPatientButton() {
        driver.findElement(newPatientButton).click();
    }
    public boolean isPatientInformationTextVisible() {
        return driver.findElement(patientInformationText).isDisplayed();
    }
    public boolean isCareOfPersonContactTextboxPresent() {
        return driver.findElement(careOfPersonContactTextbox).isDisplayed();
    }
    public String getCareOfPersonTextboxPlaceholder() {
        return driver.findElement(careOfPersonTextbox).getAttribute("placeholder");
    }
    public void clickPrintInvoiceButton() {
        driver.findElement(printInvoiceButton).click();
    }
    public void clickConfirmButton() {
        driver.findElement(confirmButton).click();
    }
    public boolean isErrorMessagePresent(String message) {
        return driver.findElement(errorMessage).getText().equals(message);
    }
    public void enterPatientInformation(Map<String, String> testData) {
        driver.findElement(firstNameTextbox).sendKeys(testData.get("FirstName"));
        driver.findElement(middleNameTextbox).sendKeys(testData.get("MiddleName"));
        driver.findElement(lastNameTextbox).sendKeys(testData.get("LastName"));
        driver.findElement(ageTextbox).sendKeys(testData.get("Age"));
        driver.findElement(phoneTextbox).sendKeys(testData.get("Phone"));
    }
    public boolean verifyPatientInfoValues(Map<String, String> expectedData) {
        String actualFirstName = driver.findElement(firstNameTextbox).getAttribute("value");
        String actualMiddleName = driver.findElement(middleNameTextbox).getAttribute("value");
        String actualLastName = driver.findElement(lastNameTextbox).getAttribute("value");
        String actualAge = driver.findElement(ageTextbox).getAttribute("value");
        return actualFirstName.equals(expectedData.get("FirstName")) &&
               actualMiddleName.equals(expectedData.get("MiddleName")) &&
               actualLastName.equals(expectedData.get("LastName")) &&
               actualAge.equals(expectedData.get("Age"));
    }
    public String getPhoneNumberValue() {
        return driver.findElement(phoneTextbox).getAttribute("value");
    }
    public boolean isHaveDOBCheckboxPresent() {
        return driver.findElement(haveDOBCheckbox).isDisplayed();
    }
    public boolean isDatePickerPresent() {
        return driver.findElement(datePickerField).isDisplayed();
    }
    public void clickExternalCheckbox() {
        driver.findElement(externalCheckbox).click();
    }
    public void enterExternalReferralDetails() {
        // Fill required details for referral here
    }
    public void clickAddButton() {
        driver.findElement(addButton).click();
    }
    public boolean isSuccessNotificationPresent(String text) {
        return driver.findElement(successNotification).getText().contains(text);
    }
}


@Test(priority=3)
public void verifyNewPatientButtonShowsPatientInfo() {
    AppointmentPages page = new AppointmentPages(driver);
    page.openNewVisitPage();
    page.clickNewPatientButton();
    Assert.assertTrue(page.isPatientInformationTextVisible());
}

@Test(priority=4)
public void verifyCareOfPersonContactTextboxPresent() {
    AppointmentPages page = new AppointmentPages(driver);
    page.openNewVisitPage();
    Assert.assertTrue(page.isCareOfPersonContactTextboxPresent());
}

@Test(priority=5)
public void verifyCareOfPersonTextboxPlaceholder() {
    AppointmentPages page = new AppointmentPages(driver);
    page.openNewVisitPage();
    Assert.assertEquals(page.getCareOfPersonTextboxPlaceholder(), "Care Taker Person");
}

@Test(priority=6)
public void verifyErrorMessageForEmptyForm() {
    AppointmentPages page = new AppointmentPages(driver);
    page.openNewVisitPage();
    page.clickPrintInvoiceButton();
    page.clickConfirmButton();
    Assert.assertTrue(page.isErrorMessagePresent("Last Name is required"));
}

@Test(priority=7)
public void validateEnteredPatientInfoValues() {
    AppointmentPages page = new AppointmentPages(driver);
    Map<String, String> testData = new HashMap<>();
    testData.put("FirstName", "John");
    testData.put("MiddleName", "A");
    testData.put("LastName", "Doe");
    testData.put("Age", "30");
    testData.put("Phone", "1234567890");
    page.enterPatientInformation(testData);
    Assert.assertTrue(page.verifyPatientInfoValues(testData));
    Assert.assertEquals(page.getPhoneNumberValue(), "1234567890");
}

@Test(priority=8)
public void verifyHaveDOBCheckboxAndDatePickerPresent() {
    AppointmentPages page = new AppointmentPages(driver);
    page.openNewVisitPage();
    Assert.assertTrue(page.isHaveDOBCheckboxPresent());
    Assert.assertTrue(page.isDatePickerPresent());
}

@Test(priority=9)
public void verifySuccessNotificationOnAddExternalReferral() {
    AppointmentPages page = new AppointmentPages(driver);
    page.openNewVisitPage();
    page.clickExternalCheckbox();
    page.enterExternalReferralDetails();
    page.clickAddButton();
    Assert.assertTrue(page.isSuccessNotificationPresent("Success update"));
}
