package com.techproed.day10;

import com.techproed.testBase.HerokuAppTestBase;
import com.techproed.testData.HerokuappTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class C02_PostRequest extends HerokuAppTestBase {
//    https://restful-booker.herokuapp.com/booking url ine, Request Body olarak
//    { "firstname": "Selim",
//            "lastname": "Ak",
//            "totalprice": 11111,
//            "depositpaid": true,
//            "bookingdates": {
//        "checkin": "2020-09-09",
//                "checkout": "2020-09-21"
//    }
//    }gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
// "booking": {
//        "firstname": " Selim ",
//                "lastname": " Ak",
//                "totalprice":  11111,
//                "depositpaid": true,
//                "bookingdates": {
//            "checkin": "2020-09-01",
//                    "checkout": " 2020-09-21”
//        },
//    }
//    olduğunu test edin

    @Test
    public void test(){

        // url
        spec02.pathParams("parametre1","booking");

        // request body ve expected data ayni oldugu icin tekbir JSONObject kullanilmasi yeterli

        HerokuappTestData testData = new HerokuappTestData();
        JSONObject expectedDataRequest =testData.SetupTestandRequestData();

        System.out.println(expectedDataRequest);

        // request gonder
        Response response=given().
                contentType(ContentType.JSON).
                spec(spec02).
                auth().basic("admin","password123").
                body(expectedDataRequest.toString()).
                when().
                post("/{parametre1}");


        //response.prettyPrint();

        //DE SERIALIZATION

        HashMap<String,Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);

        Assert.assertEquals(expectedDataRequest.getString("firstname"),
                           ((Map)actualData.get("booking")).get("firstname")  );

        Assert.assertEquals(expectedDataRequest.getString("lastname"),
                ((Map)actualData.get("booking")).get("lastname")  );

        Assert.assertEquals(expectedDataRequest.getInt("totalprice"),
                ((Map)actualData.get("booking")).get("totalprice")  );

        Assert.assertEquals(expectedDataRequest.getBoolean("depositpaid"),
                ((Map)actualData.get("booking")).get("depositpaid")  );


        // inner map icinde olan bookingdates
        Assert.assertEquals(expectedDataRequest.getJSONObject("bookingdates").getString("checkin"),
                ((Map)((Map<?, ?>) actualData.get("booking")).get("bookingdates")).get("checkin"));

        Assert.assertEquals(expectedDataRequest.getJSONObject("bookingdates").getString("checkout"),
                ((Map)((Map<?, ?>) actualData.get("booking")).get("bookingdates")).get("checkout"));


        // JSON PATH

        JsonPath json = response.jsonPath();

        Assert.assertEquals(expectedDataRequest.getString("firstname"),json.getString("booking.firstname"));

        Assert.assertEquals(expectedDataRequest.getString("lastname"),json.getString("booking.lastname"));

        Assert.assertEquals(expectedDataRequest.getString("totalprice"),json.getInt("booking.totalprice"));

        Assert.assertEquals(expectedDataRequest.getString("depositpaid"),json.getBoolean("booking.depositpaid"));

        Assert.assertEquals(expectedDataRequest.getJSONObject("bookingdates").getString("checkin"),
                json.getString("booking.bookingdates.checkin"));

        Assert.assertEquals(expectedDataRequest.getJSONObject("bookingdates").getString("checkout"),
                json.getString("booking.bookingdates.checkout"));




    }





}
