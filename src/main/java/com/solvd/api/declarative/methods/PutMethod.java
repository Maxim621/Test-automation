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
public class PutMethod extends AbstractApiMethodV2 {
    private static final Logger logger = LogManager.getLogger(PutMethod.class);

    public PutMethod() {
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

        logger.info("PUT Response validation passed!");
    }
}