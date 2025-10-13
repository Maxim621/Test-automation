package com.solvd.api.methods.declarative;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@Endpoint(url = "${base_url}/put", methodType = HttpMethodType.PUT)
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class JsonPutMethod extends AbstractApiMethodV2 {
    public JsonPutMethod() {
        replaceUrlPlaceholder("base_url", "https://postman-echo.com");
        setHeaders("Content-Type=application/json", "Accept=application/json");
    }

    public void setJsonBody(String jsonBody) {
        setRequestBody(jsonBody);
    }
}