package testcase;

//src/test/java/testcase/appointment_testcase.java

import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.restassured.response.Response;
import apiRequests.AppointmentRequests;
import pages.AppointmentPages;
import java.util.*;

public class appointment_testcase {
    WebDriver driver;
    AppointmentPages ap;
    AppointmentRequests api;
    String baseURL = "https://healthapp.yaksha.com";
    String bearerToken = "YOUR_BEARER_TOKEN_HERE"; // Replace with actual

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        ap = new AppointmentPages(driver);
        api = new AppointmentRequests(bearerToken, baseURL);
    }

    @Test(priority=1)
    public void TC01_validateHomePageTitleUrl() {
        driver.get(baseURL + "/Home/Index");
        Assert.assertEquals(driver.getTitle(), "DaphneHealth");
        Assert.assertEquals(driver.getCurrentUrl(), baseURL + "/Home/Index");
    }

    @Test(priority=2)
    public void TC02_loginWithValidCredentials() {
        driver.get(baseURL + "/Account/Login");
        ap.getLoginUsername().sendKeys("admin");
        ap.getLoginPassword().sendKeys("pass123");
        ap.getLoginButton().click();
        Assert.assertTrue(driver.getCurrentUrl().contains("/Dashboard"));
    }

    @Test(priority=3)
    public void TC03_newPatientButtonPresent() {
        ap.getNewPatientButton().click();
        Assert.assertTrue(ap.getPatientInformationText().isDisplayed());
    }

    @Test(priority=4)
    public void TC04_careOfPersonTextboxPresent() {
        Assert.assertTrue(ap.getCareOfPersonContactTextbox().isDisplayed());
    }

    @Test(priority=5)
    public void TC05_careOfPersonTextboxPlaceholder() {
        String placeholder = ap.getCareOfPersonPlaceholder().getAttribute("placeholder");
        Assert.assertEquals(placeholder, "Care Taker Person");
    }

    @Test(priority=6)
    public void TC06_lastNameErrorOnPrintInvoice() {
        ap.getLastNameTextbox().clear();
        ap.getPrintInvoiceButton().click();
        ap.getConfirmPopupButton().click();
        String errText = ap.getErrorMessage().getText();
        Assert.assertEquals(errText, "Last Name is required.");
    }

    @Test(priority=7)
    public void TC07_patientInfoFormDataValidation() {
        // Enter data (replace with data-driven loop if using Excel for more robustness)
        ap.getFirstNameTextbox().sendKeys("John");
        ap.getMiddleNameTextbox().sendKeys("M");
        ap.getLastNameTextbox().sendKeys("Doe");
        ap.getAgeTextbox().sendKeys("34");
        ap.getPhoneTextbox().sendKeys("9876543210");
        // Fetch and assert values
        Assert.assertEquals(ap.getFirstNameTextbox().getAttribute("value"), "John");
        Assert.assertEquals(ap.getLastNameTextbox().getAttribute("value"), "Doe");
        // Validate phone as string
        String phone = ap.getPhoneTextbox().getAttribute("value");
        Assert.assertEquals(phone, "9876543210");
    }

    @Test(priority=8)
    public void TC08_haveDobAndDatepickerFieldsPresent() {
        ap.getHaveDOBCheckbox().click();
        Assert.assertTrue(ap.getDatepickerField().isDisplayed());
    }

    @Test(priority=9)
    public void TC09_successNotificationExternalReferral() {
        ap.getExternalCheckbox().click();
        ap.getReferrerTextbox().sendKeys("Dr. Smith");
        ap.getAddButton().click();
        // Assuming notification element appears:
        Assert.assertTrue(driver.getPageSource().contains("Success update"));
    }

    // ============================= API TEST CASES =============================

    @Test(priority=10)
    public void TC10_createAppointmentWithAuthorization() {
        Map<String, Object> body = new HashMap<>();
        body.put("patientName", "John Appointment");
        body.put("doctorId", "1234");
        Response res = api.createAppointment(body);
        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(res.jsonPath().getString("Status"), "OK");
        Assert.assertNotNull(res.jsonPath().get("AppointmentId"));
    }

    @Test(priority=11)
    public void TC11_cancelAppointmentWithAuthorization() {
        String appointmentId = "test-apt-id"; // Get from previous test ideally
        Response res = api.cancelAppointmentWithAuth(appointmentId);
        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(res.jsonPath().getString("Status"), "OK");
    }

    @Test(priority=12)
    public void TC12_searchPatientWithAuthorization() {
        Response res = api.searchPatientWithAuth("Test");
        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(res.jsonPath().getString("Status"), "OK");
    }

    @Test(priority=13)
    public void TC13_getAppointmentsForPerformer() {
        Response res = api.getAppointmentsForPerformer("performerIdValue", "2025-11-01", "2025-11-21");
        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(res.jsonPath().getString("Status"), "OK");
    }

    @Test(priority=14)
    public void TC14_getMainStoreDetails() {
        Response res = api.getMainStoreDetails();
        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(res.jsonPath().getString("Status"), "OK");
        Assert.assertNotNull(res.jsonPath().get("Name"));
        Assert.assertNotNull(res.jsonPath().get("storeDesc"));
        Assert.assertNotNull(res.jsonPath().get("storeId"));
    }

    @Test(priority=15)
    public void TC15_getPharmacyStoresList() {
        Response res = api.getPharmacyStores();
        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(res.jsonPath().getString("Status"), "OK");
        Assert.assertNotNull(res.jsonPath().get("Results"));
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
