package pages;



import apiRequests.AppointmentRequests;
import io.restassured.response.Response;
import pages.CustomResponse;

public CustomResponse PharmacyStoresWithAuth(String endpoint, Object requestBody) {
    // You must specify how to get the token and base URL, depending on your config.
    String BEARER_TOKEN = "your_actual_bearer_token";   // Replace with config if needed
    String BASE_URL = "https://healthapp.yaksha.com";   // Replace with config if needed

    AppointmentRequests api = new AppointmentRequests(BEARER_TOKEN, BASE_URL);
    Response response = api.pharmacyStoresWithAuth(endpoint, requestBody);
    CustomResponse cr = new CustomResponse();

    cr.setResponse(response);
    cr.setStatusCode(response.getStatusCode());
    // If you want to extract fields, assuming CustomResponse has these setters:
    cr.setStatus(response.jsonPath().getString("Status"));
    cr.setResults(response.jsonPath().getList("Results"));
    cr.setCompleteResponseObject(response.asString());

    return cr;
}






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









// 1. Login to HealthApp by valid credential (Example Only)
public boolean loginToHealthAppByGivenValidCredential(Map<String, String> loginData) {
    try {
        driver.findElement(By.id("username")).sendKeys(loginData.get("username"));
        driver.findElement(By.id("password")).sendKeys(loginData.get("password"));
        driver.findElement(By.id("login-btn")).click();
        // Optional: Check if landed on dashboard/home
        return true;
    } catch(Exception e) {
        e.printStackTrace();
        return false;
    }
}

// 2. Verify Home Page Title and/or URL
public String verifyURLOFThePage() {
    return driver.getCurrentUrl();
}

// 3. Scroll to bottom and verify field (e.g., Care of Person Contact)
public boolean scrollToBottomOrVerifyFieldAndHighlight() {
    try {
        WebElement el = driver.findElement(By.id("care-person-contact"));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
        // highlight
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].style.border='2px solid orange'", el);
        return el.isDisplayed() && el.isEnabled();
    } catch(Exception e) {
        return false;
    }
}

// 4. Placeholder name for textbox
public String verifyPlaceholderNameOfTextbox() {
    try {
        return driver.findElement(By.id("care-person-contact")).getAttribute("placeholder");
    } catch(Exception e) {
        return null;
    }
}

// 5. Verify Textbox Present and Validate Entered Value
public String verifyTextboxIsPresentAndValidateEnteredValue(Map<String, String> expectedData) {
    try {
        String fieldVal = driver.findElement(By.id(expectedData.get("textboxId"))).getAttribute("value");
        return fieldVal;
    } catch(Exception e) {
        return null;
    }
}

// 6. Verify Success Notification Popup Message (after adding referral etc.)
public String verifySuccessNotificationPopupMessage(Map<String, String> expectedData) {
    try {
        // Click if flow suggests
        driver.findElement(By.id("add-btn")).click();
        WebElement popup = driver.findElement(By.className("notification-success"));
        if(popup.isDisplayed()) {
            return popup.getText();
        }
    } catch(Exception e) {
        e.printStackTrace();
    }
    return null;
}

// 7. Create Appointment with Authorization (API)
public CustomResponse createAppointmentWithAuth(String endpoint, String requestBody) {
    AppointmentRequests api = new AppointmentRequests(YOUR_BEARER_TOKEN, YOUR_BASE_URL);
    io.restassured.response.Response response = api.createAppointmentWithAuth(endpoint, requestBody);
    CustomResponse cr = new CustomResponse();
    cr.setResponse(response);
    cr.setStatusCode(response.getStatusCode());
    // Set other fields as per your CustomResponse class definition
    return cr;
}

// 8. Cancel Appointment with Authorization (API)
public CustomResponse cancelAppointmentWithAuth(String endpoint, Object requestBody) {
    AppointmentRequests api = new AppointmentRequests(YOUR_BEARER_TOKEN, YOUR_BASE_URL);
    io.restassured.response.Response response = api.cancelAppointmentWithAuth(endpoint, requestBody);
    CustomResponse cr = new CustomResponse();
    cr.setResponse(response);
    cr.setStatusCode(response.getStatusCode());
    return cr;
}

// 9. Search Patient with Authorization
public CustomResponse searchPatientWithAuth(String endpoint, Object requestBody) {
    AppointmentRequests api = new AppointmentRequests(YOUR_BEARER_TOKEN, YOUR_BASE_URL);
    io.restassured.response.Response response = api.searchPatientWithAuth(endpoint, requestBody);
    CustomResponse cr = new CustomResponse();
    cr.setResponse(response);
    cr.setStatusCode(response.getStatusCode());
    return cr;
}

// 10. Booking List with Auth in Range
public CustomResponse bookingListWithAuthInRange(String endpoint, Object requestBody) {
    AppointmentRequests api = new AppointmentRequests(YOUR_BEARER_TOKEN, YOUR_BASE_URL);
    io.restassured.response.Response response = api.bookingListWithAuthInRange(endpoint, requestBody);
    CustomResponse cr = new CustomResponse();
    cr.setResponse(response);
    cr.setStatusCode(response.getStatusCode());
    return cr;
}

// 11. Main Store Details with Auth
public CustomResponse mainStoreDetailsWithAuth(String endpoint, Object requestBody) {
    AppointmentRequests api = new AppointmentRequests(YOUR_BEARER_TOKEN, YOUR_BASE_URL);
    io.restassured.response.Response response = api.mainStoreDetailsWithAuth(endpoint, requestBody);
    CustomResponse cr = new CustomResponse();
    cr.setResponse(response);
    cr.setStatusCode(response.getStatusCode());
    return cr;
}

