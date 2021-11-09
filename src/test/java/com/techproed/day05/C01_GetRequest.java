package com.techproed.day05;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*; // burada methodlarin onune static ekledik ve equal.to yu sildik onun yerine * koyduk.
                                        // matcher class tan sadece equalsTo u almayacagiz. diger methodlarida kullanacagimizdan dolayi * kullandık.

import org.junit.Test;

import static io.restassured.RestAssured.given;


public class C01_GetRequest {

    // https://restful-booker.herokuapp.com/booking/7 url'ine
    // accept type'i "application/json" olan GET request'i yolladigimda
    // gelen response'un
    // status kodunun 200
    // ve content type'inin "application/json"
    // ve firstname'in "Mary"
    // ve lastname'in "Jones"
    // ve checkin date'in 2021-06-25"
    // ve checkout date'in "2021-07-08" oldugunu test edin
    // ========== MATCHER CLASS ================
    @Test
    public void test(){

        String url = "https://restful-booker.herokuapp.com/booking/7";

        Response response = given().
                accept("application/json").
                when().
                get(url);

        response.prettyPrint();

/*        response.
                then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("firstname", Matchers.equalTo("Susan")).
                body("lastname",Matchers.equalTo("Jackson")).
                body("totalprice",Matchers.equalTo(811)).
                body("depositpaid",Matchers.equalTo(true)).
                body("bookingdates.checkin",Matchers.equalTo("2021-08-11")).
                body("bookingdates.checkout",Matchers.equalTo("2021-10-18"));*/

        // ==== DAHA DINAMIK BIR YAPI ========

        // Yukaridaki yontem daha amatorce bir test yapisidir.
        // Ayrıca import Classlarin methodlarinda Matcher class i static yaptik. ver equalto yu sildik onun yerine * yazdik
        // ILERLEYEN ZAMANLARDA BU TEST CALISMAYACAK. DATALAR SUREKLI DEGİSİYOR.


        response.then().assertThat().
                statusCode(200).
                contentType("application/json").
                body("firstname",equalTo("Susan"),
                        "lastname", equalTo("Wilson"),
                        "totalprice",equalTo(903),
                        "depositpaid",equalTo(true),
                        "bookingdates.checkin",equalTo("2021-01-30"),
                        "bookingdates.checkout",equalTo("2021-07-30"));
    }
}
