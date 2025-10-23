package com.solvd.api.declarative.methods;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import io.restassured.response.Response;

@Endpoint(url = "${base_url}/get?name=${name}&age=${age}", methodType = HttpMethodType.GET)
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetWithParamsMethod extends AbstractApiMethodV2 {

    public GetWithParamsMethod() {
        replaceUrlPlaceholder("base_url", "https://postman-echo.com");
    }

    public void setName(String name) {
        replaceUrlPlaceholder("name", name);
    }

    public void setAge(int age) {
        replaceUrlPlaceholder("age", String.valueOf(age));
    }

    public Response callAPIAndGetResponse() {
        return callAPIExpectSuccess();
    }

    public void validateResponse() {
        Response response = callAPIAndGetResponse();
        String responseBody = response.getBody().asString();

        if (responseBody == null || responseBody.isEmpty()) {
            throw new RuntimeException("Response body is null or empty");
        }

        if (!responseBody.contains("\"name\"")) {
            throw new RuntimeException("Response should contain 'name' parameter");
        }

        if (!responseBody.contains("\"age\"")) {
            throw new RuntimeException("Response should contain 'age' parameter");
        }

        if (!responseBody.contains("John")) {
            throw new RuntimeException("Response should contain the name 'John'");
        }

        if (!responseBody.contains("30")) {
            throw new RuntimeException("Response should contain the age '30'");
        }

        System.out.println("GET with params validation passed!");
    }
}