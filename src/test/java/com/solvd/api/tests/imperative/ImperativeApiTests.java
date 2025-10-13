package com.solvd.api.tests.imperative;

import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.carina.utils.config.Configuration;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class ImperativeApiTests {

    private static final String BASE_URL = Configuration.getRequired("base_url");

    @Test
    @MethodOwner(owner = "Maksym")
    public void testGetRequest() {
        io.restassured.RestAssured
                .given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .when()
                .get("/get")
                .then()
                .statusCode(200);
    }

    @Test
    @MethodOwner(owner = "Maksym")
    public void testPostRequest() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "John");
        requestBody.put("email", "john@test.com");

        io.restassured.RestAssured
                .given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/post")
                .then()
                .statusCode(200);
    }

    @Test
    @MethodOwner(owner = "Maksym")
    public void testPutRequest() {
        Map<String, Boolean> requestBody = new HashMap<>();
        requestBody.put("updated", true);

        io.restassured.RestAssured
                .given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .header("Custom-Header", "test-value")
                .body(requestBody)
                .when()
                .put("/put")
                .then()
                .statusCode(200);
    }

    @Test
    @MethodOwner(owner = "Maksym")
    public void testDeleteRequest() {
        io.restassured.RestAssured
                .given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .when()
                .delete("/delete")
                .then()
                .statusCode(200);
    }

    @Test
    @MethodOwner(owner = "Maksym")
    public void testGetWithQueryParams() {
        io.restassured.RestAssured
                .given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .queryParam("page", 2)
                .queryParam("limit", 10)
                .when()
                .get("/get")
                .then()
                .statusCode(200);
    }
}