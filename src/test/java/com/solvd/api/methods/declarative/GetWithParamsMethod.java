package com.solvd.api.methods.declarative;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@Endpoint(url = "${base_url}/get?name=${name}&age=${age}", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/get/with-params-rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetWithParamsMethod extends AbstractApiMethodV2 {
    public GetWithParamsMethod() {
        replaceUrlPlaceholder("base_url", "https://postman-echo.com");
    }

    public void setName(String name) {
        replaceUrlPlaceholder("name", name);
    }

    public void setAge(int age) {
        replaceUrlPlaceholder("age", String.valueOf(age));
    }
}