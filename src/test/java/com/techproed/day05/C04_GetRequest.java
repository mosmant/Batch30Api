package com.techproed.day05;

import com.techproed.testBase.JsonPlaceHolderTestBase;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class C04_GetRequest extends JsonPlaceHolderTestBase {

    // https://jsonplaceholder.typicode.com/todos/123 url'ine
    //   accept type'i "application/json" olan GET request'i yolladigimda
    //   gelen response’un
    //  status kodunun 200
    //    ve content type'inin "application/json"
    //  ve Headers'daki "Server" in "cloudflare"
    //  ve response body'deki "userId"'nin 7
    //  ve "title" in "esse et quis iste est earum aut impedit"
    //  ve "completed" bolumunun false oldugunu test edin

    @Test
    public void test(){

        // String url = "https://jsonplaceholder.typicode.com/todos/123";
        spec01.pathParams("parameter1","todos",
                     "parameter2",123);

        // parametreleri adlandirma yaptik yukarida, bizim 2 adet parametremiz vardı todos ve id.

        Response response = given().accept("application/json").spec(spec01).get("/{parameter1}/{parameter2}");

        response.prettyPrint();

        response.then().assertThat().
                statusCode(200).
                contentType("application/json").
                headers("Server", equalTo("cloudflare")).
                body("userId", equalTo(7),"title",
                        equalTo("esse et quis iste est earum aut impedit"),
                        "completed",equalTo(false));
    }
}
