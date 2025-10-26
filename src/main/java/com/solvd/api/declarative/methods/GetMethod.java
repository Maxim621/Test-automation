package com.solvd.api.declarative.methods;

import com.itextpdf.text.log.LoggerFactory;
import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Endpoint(url = "${base_url}/get", methodType = HttpMethodType.GET)
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetMethod extends AbstractApiMethodV2 {
    private static final Logger logger = LogManager.getLogger(GetMethod.class);

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

        logger.info("GET Response validation passed!");
    }
}