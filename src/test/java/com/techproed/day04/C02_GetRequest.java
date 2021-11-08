package com.techproed.day04;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C02_GetRequest {

    //   https://restful-booker.herokuapp.com/booking url'ine
    //   accept type'i "application/json" olan GET request'i yolladigimda
    //   gelen response'un
    //   status kodunun 200
    //   content type'inin "application/json" oldugunu test edin

    @Test
    public void test(){

        String url = "https://restful-booker.herokuapp.com/booking";

        //=======================================================================

        // BURASI ASLINDA REQUEST GONDERME ISINI YAPIYOR.

        Response response = given().accept("application/json").when().get(url);

        System.out.println(response.getStatusCode());
        System.out.println(response.getContentType());

        response.prettyPrint();

        //=======================================================================

        // BURASIDA GELEN RESPONSE U DOGRULAMA ISINI YAPIYOR.

        response.then().assertThat().
                statusCode(200).                 // status code 200 mu?
                contentType("application/json"); // content type json mi?

        //2. yol

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals("application/json; charset=utf-8",response.getContentType());
    }


}
