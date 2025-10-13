package com.solvd.api.methods.declarative;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@Endpoint(url = "${base_url}/delete", methodType = HttpMethodType.DELETE)
@ResponseTemplatePath(path = "api/delete/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class DeleteMethod extends AbstractApiMethodV2 {
    public DeleteMethod() {
        replaceUrlPlaceholder("base_url", "https://postman-echo.com");
    }
}