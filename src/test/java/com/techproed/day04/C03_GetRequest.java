package com.techproed.day04;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C03_GetRequest {

    @Test
    public void test2() {

        // BU TEST BIZDE CALISMADI.
        // artik calisti


        String url = "https://restful-booker.herokuapp.com/booking/1001";

        Response response = given().
                accept("application/json").
                when().get(url);


        response.prettyPrint();

        response.then().assertThat().statusCode(404);  // status code un 404 olup olmadigini kontrol ediyoruz.

        //============================== asString() ====================================

        //asString methodu gelen json datayi stringe cevirir.

        Assert.assertTrue(response.asString().contains("Not Found")); //  burada once response dan gelen sonucu string e,
        //  cevirdik ardindan test ettik.

        Assert.assertFalse(response.asString().contains("API"));        // ayni sekilde response datasini stringe cevirdik.
        // ardindan aradigimiz keywordu arattık.

    }
}
