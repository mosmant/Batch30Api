package com.techproed.day06;

import com.techproed.testBase.HerokuAppTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C01_GetRequest extends HerokuAppTestBase {
    // https://restful-booker.herokuapp.com/booking/2 url’ine bir request yolladigimda;
    // HTTP Status Code’unun 200, response content type’inin “application/JSON” oldugunu, response
    // body’sinin asagidaki gibi oldugunu test edin

    // {"firstname": Sally,
    // "lastname": "Smith",
    // "totalprice": 789,
    // "depositpaid": false,
    // "bookingdates": {     "checkin": "2017-12-11","checkout":"2020-02-20" }}

    @Test
    public void test() {

        spec02.pathParams("parametre1","booking","parametre2",2);

        Response response = given().

                accept("application/json").

                spec(spec02).

                when().

                get("/{parametre1}/{parametre2}");

        response.then().

                assertThat().

                statusCode(200).

                contentType(ContentType.JSON);

        // Assert.assertEquals(200,response.getStatusCode());  RESPONSE CLASSINI KULLANMADAN YAPILAN ISLEMLER

        JsonPath jsonPath = response.jsonPath();

        // jsonPath header ve digerlerini dondurmnez sadece BODY de calisir.

        Assert.assertEquals("Sally",jsonPath.getString("firstname"));

        Assert.assertEquals("Wilson",jsonPath.getString("lastname"));

        Assert.assertEquals(753,jsonPath.getInt("totalprice"));

        Assert.assertEquals(true,jsonPath.getBoolean("depositpaid"));

        Assert.assertEquals("2020-10-31",jsonPath.getString("bookingdates.checkin"));

        Assert.assertEquals("2021-06-26",jsonPath.getString("bookingdates.checkout"));

    }

}
