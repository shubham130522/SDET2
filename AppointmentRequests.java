package apiRequests;

//src/test/java/apiRequests/AppointmentRequests.java

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.util.Map;

public class AppointmentRequests {
    private String bearerToken;
    private String baseUrl;

    public AppointmentRequests(String bearerToken, String baseUrl) {
        this.bearerToken = bearerToken;
        this.baseUrl = baseUrl;
    }

    public Response createAppointment(Map<String, Object> body) {
        return given()
            .header("Authorization", "Bearer " + bearerToken)
            .contentType("application/json")
            .body(body)
            .post(baseUrl + "/api/Appointment/AddAppointment");
    }

    public Response cancelAppointmentWithAuth(String appointmentId) {
        return given()
            .header("Authorization", "Bearer " + bearerToken)
            .contentType("application/json")
            .put(baseUrl + "/api/Appointment/AppointmentStatus?appointmentId=" + appointmentId + "&status=cancelled");
    }

    public Response searchPatientWithAuth(String searchStr) {
        return given()
            .header("Authorization", "Bearer " + bearerToken)
            .get(baseUrl + "/api/Patient/SearchRegisteredPatient?search=" + searchStr);
    }

    public Response getAppointmentsForPerformer(String performerId, String fromDate, String toDate) {
        return given()
            .header("Authorization", "Bearer " + bearerToken)
            .get(baseUrl + "/api/Appointment/Appointments?FromDate=" + fromDate + "&ToDate=" + toDate + "&performerId=" + performerId + "&status=new");
    }

    public Response getMainStoreDetails() {
        return given()
            .header("Authorization", "Bearer " + bearerToken)
            .get(baseUrl + "/api/PharmacySettings/MainStore");
    }

    public Response getPharmacyStores() {
        return given()
            .header("Authorization", "Bearer " + bearerToken)
            .get(baseUrl + "/api/Dispensary/PharmacyStores");
    }
}
