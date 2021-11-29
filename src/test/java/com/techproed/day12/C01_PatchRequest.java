package com.techproed.day12;


import com.techproed.testBase.JsonPlaceHolderTestBase;
import com.techproed.testData.JsonPlaceHolderTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class C01_PatchRequest extends JsonPlaceHolderTestBase {


    @Test
    public void test(){

        spec01.pathParam("parametre1","todos");

        JsonPlaceHolderTestData testObje=new JsonPlaceHolderTestData();
        JSONObject expectedRequest= testObje.setUpPatchRequestData();
        System.out.println(expectedRequest);

        Response response= given().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().basic("admin","password123").
                body(expectedRequest.toString()).
                when().
                post("/{parametre1}");

        //  response.prettyPrint();

        //Matchers class

        response.then().
                assertThat().statusCode(expectedRequest.getInt("statusCode"))
                .body("completed", equalTo(expectedRequest.getBoolean("completed")),
                        "title",equalTo(expectedRequest.getString("title")),
                        "userId",equalTo(expectedRequest.getInt("userId")));


        //JsonPath ile

        JsonPath json=response.jsonPath();

        Assert.assertEquals(expectedRequest.getInt("statusCode"),response.getStatusCode());
        Assert.assertEquals(expectedRequest.getInt("userId"),json.getInt("userId"));
        Assert.assertEquals(expectedRequest.getString("title"),json.getString("title"));
        Assert.assertEquals(expectedRequest.getBoolean("completed"),json.getBoolean("completed"));

        //De Serialization

        HashMap<String,Object> actualDataMap=response.as(HashMap.class);

        Assert.assertEquals(expectedRequest.getString("title"),actualDataMap.get("title"));
        Assert.assertEquals(expectedRequest.getInt("userId"),actualDataMap.get("userId"));
        Assert.assertEquals(expectedRequest.getBoolean("completed"),actualDataMap.get("completed"));












    }





}
