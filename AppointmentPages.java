package pages;


// restConfig/ConfigManager.java
public class ConfigManager {
    public static String getBaseUrl() {
        // read from config.properties
        return PropertiesFileReader.get("baseUrl");
    }

    public static String getBearerToken() {
        return PropertiesFileReader.get("bearerToken");
    }
}








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





package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class appointment_Pages {

    WebDriver driver;

    // Sample locators (update with your actual element identifiers)
    By buttonAtBottom = By.id("bottomButton");
    By appointmentModule = By.id("appointmentModule");
    By newPatientButton = By.id("newPatientBtn");
    By patientInfoText = By.xpath("//h2[text()='Patient Information']");
    By loginUsername = By.id("username");
    By loginPassword = By.id("password");
    By loginButton = By.id("loginBtn");
    By homePageTitle = By.tagName("title");

    public appointment_Pages(WebDriver driver) {
        this.driver = driver;
    }

    // 1. Scroll and highlight field at bottom
    public boolean scrollToBottomOrVerifyFieldAndHighlight() {
        try {
            driver.findElement(buttonAtBottom).click();
            // Use JavaScript to highlight if needed
            // JavascriptExecutor js = (JavascriptExecutor) driver;
            // js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(buttonAtBottom));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 2. Verify button and Patient Information text present
    public String verifyButtonAndTextIsPresent() {
        try {
            driver.findElement(newPatientButton).click();
            return driver.findElement(patientInfoText).getText();
        } catch (Exception e) {
            return null;
        }
    }

    // 3. Verify appointment module present
    public String verifyAppointmentModuleIsPresent() {
        try {
            if (driver.findElement(appointmentModule).isDisplayed()) {
                return driver.findElement(appointmentModule).getText();
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    // 4. Login to health app with valid credentials
    public boolean loginToHealthAppByGivenValidCredential(Map<String, String> loginData) {
        try {
            driver.findElement(loginUsername).sendKeys(loginData.get("username"));
            driver.findElement(loginPassword).sendKeys(loginData.get("password"));
            driver.findElement(loginButton).click();
            // Wait for login to complete (add explicit wait as needed)
            return true; // Return false if login fails
        } catch (Exception e) {
            return false;
        }
    }

    // 5. Get homepage title after login
    public String verifyTitleAndURLOfTheHomePage() {
        try {
            return driver.getTitle();
        } catch (Exception e) {
            return null;
        }
    }

    // 6. Get homepage URL after login
    public String verifyURLOfThePage() {
        try {
            return driver.getCurrentUrl();
        } catch (Exception e) {
            return null;
        }
    }
}




// In appointment_Pages.java

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

import restConfig.ConfigManager;

public class appointment_Pages {

    // ... your existing UI code and getCurrentUrl() etc.

    private String getBaseUrl() {
        return ConfigManager.getBaseUrl();    // implement in ConfigManager
    }

    private String getBearerToken() {
        return ConfigManager.getBearerToken(); // implement in ConfigManager
    }

    private CustomResponse buildCustomResponse(Response resp) {
        CustomResponse cr = new CustomResponse();
        cr.setResponse(resp);
        cr.setStatusCode(resp.getStatusCode());
        cr.setStatus(resp.jsonPath().getString("status"));   // change key if your JSON uses different field
        // assuming result is a list of maps under key "result"
        List<Map<String, Object>> list = resp.jsonPath().getList("result");
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", list);
        cr.setResults(resultMap);
        return cr;
    }

    // 10. createAppointmentWithAuth(String endpoint, String requestBody)
    public CustomResponse createAppointmentWithAuth(String endpoint, String requestBody) {
        String url = getBaseUrl() + endpoint;        // endpoint like "/Appointment/AddAppointment"

        Response resp = RestAssured
                .given()
                    .baseUri(url)
                    .header("Authorization", "Bearer " + getBearerToken())
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                .when()
                    .post()
                .then()
                    .extract().response();

        // Optionally basic assertion
        if (resp.getStatusCode() != 200) {
            throw new RuntimeException("createAppointmentWithAuth failed, status: " + resp.getStatusCode());
        }

        return buildCustomResponse(resp);
    }

    // 11. cancelAppointmentWithAuth(String endpoint, Object requestObj)
    //   endpoint example from doc:
    //   "/Appointment/AppointmentStatus?appointmentId=" + apptId + "&status=cancelled"
    public CustomResponse cancelAppointmentWithAuth(String endpoint, Object requestObj) {
        String url = getBaseUrl() + endpoint;

        Response resp = RestAssured
                .given()
                    .baseUri(url)
                    .header("Authorization", "Bearer " + getBearerToken())
                    .contentType(ContentType.JSON)
                    .body(requestObj)           // if body not required, remove this
                .when()
                    .put()
                .then()
                    .extract().response();

        if (resp.getStatusCode() != 200) {
            throw new RuntimeException("cancelAppointmentWithAuth failed, status: " + resp.getStatusCode());
        }

        return buildCustomResponse(resp);
    }

    // 12. searchPatientWithAuth(String endpoint, Object requestObj)
    // endpoint like "/Patient/SearchRegisteredPatient?searchText=<text>"
    public CustomResponse searchPatientWithAuth(String endpoint, Object requestObj) {
        String url = getBaseUrl() + endpoint;

        Response resp = RestAssured
                .given()
                    .baseUri(url)
                    .header("Authorization", "Bearer " + getBearerToken())
                    .contentType(ContentType.JSON)
                    .body(requestObj)   // if GET has no body, remove this and change to .get()
                .when()
                    .get()
                .then()
                    .extract().response();

        if (resp.getStatusCode() != 200) {
            throw new RuntimeException("searchPatientWithAuth failed, status: " + resp.getStatusCode());
        }

        return buildCustomResponse(resp);
    }

    // 13. bookingListWithAuthInRange(String endpoint, Object requestObj)
    // endpoint sample: "/Appointment/Appointments?FromDate=...&ToDate=...&performerId=...&status=new"
    public CustomResponse bookingListWithAuthInRange(String endpoint, Object requestObj) {
        String url = getBaseUrl() + endpoint;

        Response resp = RestAssured
                .given()
                    .baseUri(url)
                    .header("Authorization", "Bearer " + getBearerToken())
                    .contentType(ContentType.JSON)
                    .body(requestObj)   // remove if GET has no body
                .when()
                    .get()
                .then()
                    .extract().response();

        if (resp.getStatusCode() != 200) {
            throw new RuntimeException("bookingListWithAuthInRange failed, status: " + resp.getStatusCode());
        }

        return buildCustomResponse(resp);
    }

    // 14. MainStoreDetailsWithAuth(String endpoint, Object requestObj)
    // endpoint: "/PharmacySettings/MainStore"
    public CustomResponse MainStoreDetailsWithAuth(String endpoint, Object requestObj) {
        String url = getBaseUrl() + endpoint;

        Response resp = RestAssured
                .given()
                    .baseUri(url)
                    .header("Authorization", "Bearer " + getBearerToken())
                    .contentType(ContentType.JSON)
                    .body(requestObj)  // likely no body needed; remove if not
                .when()
                    .get()
                .then()
                    .extract().response();

        if (resp.getStatusCode() != 200) {
            throw new RuntimeException("MainStoreDetailsWithAuth failed, status: " + resp.getStatusCode());
        }

        CustomResponse cr = buildCustomResponse(resp);
        // additional checks as per doc: Name, storeDesc, storeId should not be null
        // you can assert fields here based on your JSON structure
        return cr;
    }

    // 15. PharmacyStoresWithAuth(String endpoint, Object requestObj)
    // endpoint: "/Dispensary/PharmacyStores"
    public CustomResponse PharmacyStoresWithAuth(String endpoint, Object requestObj) {
        String url = getBaseUrl() + endpoint;

        Response resp = RestAssured
                .given()
                    .baseUri(url)
                    .header("Authorization", "Bearer " + getBearerToken())
                    .contentType(ContentType.JSON)
                    .body(requestObj)  // remove if GET without body
                .when()
                    .get()
                .then()
                    .extract().response();

        if (resp.getStatusCode() != 200) {
            throw new RuntimeException("PharmacyStoresWithAuth failed, status: " + resp.getStatusCode());
        }

        CustomResponse cr = buildCustomResponse(resp);
        // as per doc, StoreId and Name should not be null; add checks from JSON
        return cr;
    }
}




