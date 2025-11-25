package testcases;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.*;

public class appointment_testcase extends AppTestBase {
    // Test data and page objects
    Map<String, String> configData;
    Map<String, String> loginCredentials;
    String EXCEL_FILE_PATH = "src/main/resources/config.xlsx";
    appointment_Pages appointmentPagesInstance;

    @BeforeClass
    public void setup() throws Exception {
        configData = // Load config
        loginCredentials = // Load login credentials
        appointmentPagesInstance = new appointment_Pages(driver);
    }

    @Test(priority = 1, groups = {"sanity"}, description = "Navigate to the URL and verify the homepage title and URL")
    public void verifyTitleAndURLOfHomePage() throws Exception {
        Map<String, String> loginData = // Read from Excel
        Assert.assertTrue(appointmentPagesInstance.loginHealthAppByGivenValidCredential(loginData));
        Assert.assertEquals(appointmentPagesInstance.verifyTitleAndURLOfHomePage(), loginData.get("expectedTitle"));
    }

    @Test(priority = 2, groups = {"sanity"}, description = "Verify that Appointment module is present")
    public void verifyAppointmentModuleIsPresent() throws Exception {
        Map<String, String> expectedData = // Read from Excel
        Assert.assertTrue(appointmentPagesInstance.verifyAppointmentModuleIsPresent(), "Module is not present");
    }

    @Test(priority = 3, groups = {"sanity"}, description = "Verify New Patient button is present")
    public void verifyNewPatientButtonAndTestIsPresent() throws Exception {
        Map<String, String> expectedData = // Read from Excel
        Assert.assertTrue(appointmentPagesInstance.verifyNewPatientButtonIsPresent(), "New Patient button not present");
    }

    @Test(priority = 4, groups = {"sanity"}, description = "Scroll to bottom and check for fields")
    public void scrollToBottomAndVerifyFieldAndHighlight() throws Exception {
        Assert.assertTrue(appointmentPagesInstance.scrollToBottomAndVerifyFieldAndHighlight(), "Fields not present");
    }

    @Test(priority = 5, groups = {"sanity"}, description = "Verify the placeholder name of textboxes")
    public void verifyPlaceholderNameOfTextboxIfTextboxIsEnabled() throws Exception {
        Map<String, String> expectedData = // Read from Excel
        Assert.assertTrue(appointmentPagesInstance.verifyPlaceholderNameOfTextbox(expectedData), "Placeholder name validation failed");
    }

    @Test(priority = 6, groups = {"sanity"}, description = "Validate error message when no input provided")
    public void verifyErrorMessage() throws Exception {
        Map<String, String> expectedData = // Read from Excel
        Assert.assertTrue(appointmentPagesInstance.verifyErrorMessage(expectedData), "Error message validation failed");
    }

    @Test(priority = 7, groups = {"sanity"}, description = "Validate entered patient information fields")
    public void verifyEnteredDetailsPresentInTextbox() throws Exception {
        Map<String, String> expectedData = // Read from Excel
        Assert.assertTrue(appointmentPagesInstance.verifyEnteredDetailsPresentInTextbox(expectedData), "Entered values are missing");
    }

    @Test(priority = 8, groups = {"sanity"}, description = "Check DOB checkbox and datepicker")
    public void verifyCheckboxIsSelectedAndDatePickerIsPresent() throws Exception {
        Assert.assertTrue(appointmentPagesInstance.verifyCheckboxIsSelectedAndDatePickerIsPresent(), "Checkbox or datepicker validation failed");
    }

    @Test(priority = 9, groups = {"sanity"}, description = "Add external referral and verify success notification")
    public void verifySuccessNotificationPopupMessage() throws Exception {
        Map<String, String> expectedData = // Read from Excel
        Assert.assertTrue(appointmentPagesInstance.verifySuccessNotificationPopupMessage(expectedData), "Success notification is missing");
    }

    @Test(priority = 10, groups = {"sanity"}, description = "Create appointment via API")
    public void createAppointmentTest() throws Exception {
        Map<String, String> postData = // Read from Excel
        CustomResponse response = appointmentPagesInstance.createAppointmentWithAuth("/Appointment/AddAppointment", postData);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getStatus(), "OK");
        Assert.assertNotNull(response.getAppointmentId());
        System.out.println("Create Appointment Response: " + response.getResponse().toString());
    }

    @Test(priority = 11, groups = {"sanity"}, dependsOnMethods = {"createAppointmentTest"}, description = "Cancel appointment test")
    public void cancelAppointmentTest() throws Exception {
        Integer appointmentId = // get from createAppointmentTest
        CustomResponse response = appointmentPagesInstance.cancelAppointmentWithAuth(appointmentId);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getStatus(), "OK");
        Assert.assertEquals(response.getResultMessage(), "Appointment information updated successfully.");
        System.out.println("Cancelled Appointment Response: " + response.getResponse().toString());
    }

    @Test(priority = 12, groups = {"sanity"}, description = "Search patient test")
    public void searchPatientTest() throws Exception {
        CustomResponse response = appointmentPagesInstance.searchPatientWithAuth("/Patient/SearchRegister?searchText=test");
        Assert.assertEquals(response.getStatusCode(), 200);
        String firstName = response.getResult("Results[0].FirstName");
        String shortName = response.getResult("Results[0].ShortName");
        String lastName = response.getResult("Results[0].LastName");
        System.out.println("FirstName: " + firstName);
        System.out.println("ShortName: " + shortName);
        System.out.println("LastName: " + lastName);
        Assert.assertTrue(firstName.contains("test"));
        Assert.assertTrue(shortName.contains("test"));
        Assert.assertTrue(lastName.contains("test"));
    }

    @Test(priority = 13, groups = {"sanity"}, description = "Booking list test")
    public void BookingListTest() throws Exception {
        LocalDate currentDate = LocalDate.now();
        LocalDate earlierDate = currentDate.minusDays(5);
        String startDate = earlierDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String endDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        // Add performerId logic
        CustomResponse response = appointmentPagesInstance.bookingListWithAuthInRange("/Appointment/AppointmentItems?FromDate=" + startDate + "&ToDate=" + endDate);
        Assert.assertEquals(response.getStatusCode(), 200);
        List<Map<String, Object>> results = response.getListResults();
        for (Map<String, Object> result : results) {
            String appointmentDate = (String) result.get("AppointmentDate");
            Assert.assertNotNull(appointmentDate, "Appointment Date should not be null");
            // Additional date logic...
        }
    }

    @Test(priority = 14, groups = {"sanity"}, description = "Main store test")
    public void MainStoreTest() throws Exception {
        CustomResponse response = appointmentPagesInstance.MainStoreDetailsWithAuth("/PharmacySettings/MainStore");
        Assert.assertEquals(response.getStatusCode(), 200);
        Map<String, Object> results = response.getNpResults();
        String name = (String) results.get("Name");
        String storeDescription = (String) results.get("StoreDescription");
        Integer storeId = (Integer) results.get("StoreId");
        Assert.assertNotNull(name);
        Assert.assertNotNull(storeDescription);
        Assert.assertNotNull(storeId);
        Assert.assertEquals(response.getStatus(), "OK");
    }

    @Test(priority = 15, groups = {"sanity"}, description = "Pharmacy store test")
    public void PharmacyStoreTest() throws Exception {
        CustomResponse response = appointmentPagesInstance.PharmacyStoresWithAuth("/Dispensary/PharmacyStores");
        Assert.assertEquals(response.getStatusCode(), 200);
        List<Map<String, Object>> results = response.getListResults();
        for (Map<String, Object> result : results) {
            Integer storeId = (Integer) result.get("StoreId");
            String name = (String) result.get("Name");
            System.out.println("StoreId: " + storeId);
            System.out.println("Name: " + name);
            Assert.assertNotNull(storeId, "StoreId is null");
            Assert.assertNotNull(name, "Name is null");
        }
        Assert.assertEquals(response.getStatus(), "OK");
        System.out.println("Pharmacy store response:");
        System.out.println(response.getResponse().toString());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
