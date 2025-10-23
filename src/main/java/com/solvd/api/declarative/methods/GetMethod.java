package com.solvd.api.declarative.methods;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import io.restassured.response.Response;

@Endpoint(url = "${base_url}/get", methodType = HttpMethodType.GET)
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetMethod extends AbstractApiMethodV2 {

    public GetMethod() {
        replaceUrlPlaceholder("base_url", "https://postman-echo.com");
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

        if (!responseBody.contains("\"url\"")) {
            throw new RuntimeException("Response should contain 'url' field");
        }

        if (!responseBody.contains("\"args\"")) {
            throw new RuntimeException("Response should contain 'args' field");
        }

        System.out.println("GET Response validation passed!");
    }
}