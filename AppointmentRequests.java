package apiRequests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class AppointmentRequests {
    private String bearerToken;
    private String baseUrl;

    public AppointmentRequests(String bearerToken, String baseUrl) {
        this.bearerToken = bearerToken;
        this.baseUrl = baseUrl;
    }

    // 1. Create Appointment (POST)
    public Response createAppointment(Map<String, Object> body) {
        return given()
            .header("Authorization", "Bearer " + bearerToken)
            .contentType("application/json")
            .body(body)
            .post(baseUrl + "/api/Appointment/AddAppointment");
    }

    // 2. Cancel Appointment with Authorization (PUT)
    public Response cancelAppointmentWithAuth(String appointmentId) {
        return given()
            .header("Authorization", "Bearer " + bearerToken)
            .contentType("application/json")
            .put(baseUrl + "/api/Appointment/AppointmentStatus?appointmentId=" + appointmentId + "&status=cancelled");
    }

    // 3. Search Patient with Authorization (GET)
    public Response searchPatientWithAuth(String searchStr) {
        return given()
            .header("Authorization", "Bearer " + bearerToken)
            .get(baseUrl + "/api/Patient/SearchRegisteredPatient?search=" + searchStr);
    }

    // 4. Get Appointments for a Performer in a Date Range (GET)
    public Response getAppointmentsForPerformer(String performerId, String fromDate, String toDate) {
        String endpoint = String.format(
            "%s/api/Appointment/Appointments?FromDate=%s&ToDate=%s&performerId=%s&status=new",
            baseUrl, fromDate, toDate, performerId
        );
        return given()
            .header("Authorization", "Bearer " + bearerToken)
            .get(endpoint);
    }

    // 5. Get Main Store Details (GET)
    public Response getMainStoreDetails() {
        return given()
            .header("Authorization", "Bearer " + bearerToken)
            .get(baseUrl + "/api/PharmacySettings/MainStore");
    }

    // 6. Get Pharmacy Stores List (GET)
    public Response getPharmacyStores() {
        return given()
            .header("Authorization", "Bearer " + bearerToken)
            .get(baseUrl + "/api/Dispensary/PharmacyStores");
    }

    // 7. Get All Appointments in the Appointment List API (GET) -- Example
    public Response getAllAppointments() {
        return given()
            .header("Authorization", "Bearer " + bearerToken)
            .get(baseUrl + "/api/Appointment/Appointments");
    }

    // ADD MORE methods as needed for any other API endpoints directly used by your tests
}
