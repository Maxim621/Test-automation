package com.solvd.api.tests.declarative;

import com.solvd.api.declarative.methods.*;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import org.testng.annotations.Test;

public class DeclarativeApiTests {

    @Test
    @MethodOwner(owner = "Maksym")
    public void testGetMethod() {
        GetMethod getMethod = new GetMethod();
        getMethod.validateResponse();
    }

    @Test
    @MethodOwner(owner = "Maksym")
    public void testPostMethod() {
        PostMethod postMethod = new PostMethod();
        postMethod.validateResponse();
    }

    @Test
    @MethodOwner(owner = "Maksym")
    public void testPutMethod() {
        PutMethod putMethod = new PutMethod();
        putMethod.validateResponse();
    }

    @Test
    @MethodOwner(owner = "Maksym")
    public void testDeleteMethod() {
        DeleteMethod deleteMethod = new DeleteMethod();
        deleteMethod.validateResponse();
    }

    @Test
    @MethodOwner(owner = "Maksym")
    public void testGetWithParametersMethod() {
        GetWithParamsMethod getMethod = new GetWithParamsMethod();
        getMethod.setName("John");
        getMethod.setAge(30);
        getMethod.validateResponse();
    }

    @Test
    @MethodOwner(owner = "Maksym")
    public void testJsonPutMethod() {
        JsonPutMethod jsonPutMethod = new JsonPutMethod();
        String jsonBody = "{\"title\":\"JSON Test\",\"message\":\"This is a JSON body test\",\"status\":true}";
        jsonPutMethod.setJsonBody(jsonBody);
        jsonPutMethod.validateResponse();
    }
}