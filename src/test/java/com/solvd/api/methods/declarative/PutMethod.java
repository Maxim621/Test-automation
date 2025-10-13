package com.solvd.api.methods.declarative;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@Endpoint(url = "${base_url}/put", methodType = HttpMethodType.PUT)
@RequestTemplatePath(path = "api/put/rq.json")
@ResponseTemplatePath(path = "api/put/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class PutMethod extends AbstractApiMethodV2 {
    public PutMethod() {
        replaceUrlPlaceholder("base_url", "https://postman-echo.com");
    }
}