package com.solvd.api.methods.declarative;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@Endpoint(url = "${base_url}/get", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/get/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetMethod extends AbstractApiMethodV2 {
    public GetMethod() {
        replaceUrlPlaceholder("base_url", "https://postman-echo.com");
    }
}