package com.techproed.day04;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C99_Practise {

    //  https://restful-booker.herokuapp.com/booking/10 url'ine bir GET request gonderdigimizde donen Response'un,
    //  status code'unun 200,
    //  ve content type'inin application/json; charset=utf-8,
    //  ve Server isimli Header'in degerinin Cowboy,
    //  ve status Line'in HTTP/1.1 200 OK
    //  ve response suresinin 5 sn'den kisa oldugunu manuel olarak test ediniz.

    @Test
    public void test(){

        String url="https://restful-booker.herokuapp.com/booking/10";
        Response response=given().
                accept("application/json").
                when().
                get(url);
        response.prettyPrint();

        //  status code'unun 200,

        response.then().assertThat().statusCode(200);

        //  ve content type'inin application/json; charset=utf-8,

        response.then().assertThat().contentType("application/json; charset=utf-8");

        //  ve Server isimli Header'in degerinin Cowboy,

        response.then().assertThat().headers("Server","Cowboy");

        //  ve status Line'in HTTP/1.1 200 OK

        response.then().assertThat().statusLine("HTTP/1.1 200 OK");

        //  ve response suresinin 5 sn'den kisa oldugunu manuel olarak test ediniz

        Assert.assertTrue(response.time()<5000);









    }
}
