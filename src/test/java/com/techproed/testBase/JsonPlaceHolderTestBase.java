package com.techproed.testBase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Before;

public class JsonPlaceHolderTestBase {

    protected RequestSpecification spec01;

    // requestspecification bir interfacedir. bu yuzden her zaman cagirmam gerekir.

    @Before
    public void setUp(){
        spec01= new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").build();

    }




}
