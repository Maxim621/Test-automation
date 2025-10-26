package com.solvd.api.declarative.methods;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Endpoint(url = "${base_url}/post", methodType = HttpMethodType.POST)
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class PostMethod extends AbstractApiMethodV2 {
    private static final Logger logger = LogManager.getLogger(PostMethod.class);

    public PostMethod() {
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

        if (!responseBody.contains("\"data\"")) {
            throw new RuntimeException("Response should contain 'data' field");
        }

        if (!responseBody.contains("\"json\"")) {
            throw new RuntimeException("Response should contain 'json' field");
        }

        logger.info("POST Response validation passed!");
    }
}