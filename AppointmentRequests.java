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

    // 1. Retrieve List of Pharmacy Stores
    public Response pharmacyStoresWithAuth(String endpoint, Object body) {
        return given()
            .header("Authorization", "Bearer " + bearerToken)
            .contentType("application/json")
            .body(body)
            .get(baseUrl + endpoint);
    }

    // 2. Retrieve Main Store Details
    public Response mainStoreDetailsWithAuth(String endpoint, Object body) {
        return given()
            .header("Authorization", "Bearer " + bearerToken)
            .contentType("application/json")
            .body(body)
            .get(baseUrl + endpoint);
    }

    // 3. Retrieve a List of Appointments for a Specified Performer in Range
    public Response bookingListWithAuthInRange(String endpoint, Object body) {
        return given()
            .header("Authorization", "Bearer " + bearerToken)
            .contentType("application/json")
            .body(body)
            .get(baseUrl + endpoint);
    }

    // 4. Create Appointment With Authorization
    public Response createAppointmentWithAuth(String endpoint, String jsonBody) {
        return given()
            .header("Authorization", "Bearer " + bearerToken)
            .contentType("application/json")
            .body(jsonBody)
            .post(baseUrl + endpoint);
    }

    // 5. Cancel Appointment With Authorization
    public Response cancelAppointmentWithAuth(String endpoint, Object body) {
        return given()
            .header("Authorization", "Bearer " + bearerToken)
            .contentType("application/json")
            .body(body)
            .put(baseUrl + endpoint);
    }

    // 6. Search for a Patient With Authorization
    public Response searchPatientWithAuth(String endpoint, Object body) {
        return given()
            .header("Authorization", "Bearer " + bearerToken)
            .contentType("application/json")
            .body(body)
            .get(baseUrl + endpoint);
    }

    // --- Helper Method for Generic Authorized GET (Optional) ---
    public Response genericGetRequest(String endpoint, Object body) {
        return given()
            .header("Authorization", "Bearer " + bearerToken)
            .contentType("application/json")
            .body(body)
            .get(baseUrl + endpoint);
    }

    // --- Helper Method for Generic Authorized POST (Optional) ---
    public Response genericPostRequest(String endpoint, String jsonBody) {
        return given()
            .header("Authorization", "Bearer " + bearerToken)
            .contentType("application/json")
            .body(jsonBody)
            .post(baseUrl + endpoint);
    }

    // --- Helper Method for Generic Authorized PUT (Optional) ---
    public Response genericPutRequest(String endpoint, String jsonBody) {
        return given()
            .header("Authorization", "Bearer " + bearerToken)
            .contentType("application/json")
            .body(jsonBody)
            .put(baseUrl + endpoint);
    }
}
