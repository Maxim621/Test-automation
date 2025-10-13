package com.solvd.api.methods.declarative;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@Endpoint(url = "${base_url}/post", methodType = HttpMethodType.POST)
@RequestTemplatePath(path = "api/post/rq.json")
@ResponseTemplatePath(path = "api/post/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class PostMethod extends AbstractApiMethodV2 {
    public PostMethod() {
        replaceUrlPlaceholder("base_url", "https://postman-echo.com");
    }
}