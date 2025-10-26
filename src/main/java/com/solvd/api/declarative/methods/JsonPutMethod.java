package com.solvd.api.declarative.methods;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Endpoint(url = "${base_url}/put", methodType = HttpMethodType.PUT)
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class JsonPutMethod extends AbstractApiMethodV2 {
    private static final Logger logger = LogManager.getLogger(JsonPutMethod.class);

    public JsonPutMethod() {
        replaceUrlPlaceholder("base_url", "https://postman-echo.com");
        setHeaders("Content-Type=application/json", "Accept=application/json");
    }

    public void setJsonBody(String jsonBody) {
        setRequestBody(jsonBody);
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

        if (!responseBody.contains("\"data\"")) {
            throw new RuntimeException("Response should contain 'data' field");
        }

        if (!responseBody.contains("JSON Test")) {
            throw new RuntimeException("Response should contain the sent JSON title");
        }

        if (!responseBody.contains("This is a JSON body test")) {
            throw new RuntimeException("Response should contain the sent JSON message");
        }

        logger.info("JSON PUT validation passed!");
    }
}