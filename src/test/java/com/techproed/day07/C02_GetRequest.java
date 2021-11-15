package com.techproed.day07;

import com.techproed.testBase.JsonPlaceHolderTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C02_GetRequest extends JsonPlaceHolderTestBase {

    //https://jsonplaceholder.typicode.com/todos/2 url ‘ine istek gönderildiğinde,
    // Dönen response un
    // Status kodunun 200, dönen body de,
    //       "completed": değerinin false
    //       "title”: değerinin “quis ut nam facilis et officia qui”
    //       "userId" sinin 1 ve header değerlerinden
    //       "Via" değerinin “1.1 vegur” ve
    //       "Server" değerinin “cloudflare” olduğunu test edin…

    @Test
    public void test(){

        spec01.pathParams("parameter1","todos",
                     "parameter2",2);

        HashMap<String,Object> expectedData = new HashMap<>();
        expectedData.put("statusCode",200);
        expectedData.put("via","1.1 vegur");
        expectedData.put("Server","cloudflare");
        expectedData.put("userId",1);
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("completed",false);

        System.out.println(expectedData);

        Response response = given().accept("application/json").
                spec(spec01).
                when().
                get("/{parameter1}/{parameter2}");

        response.prettyPrint();

        // 2 yol ile assert yapacagiz.

        // 1.yol
        response.then().assertThat().statusCode((Integer)expectedData.get("statusCode")).
                headers("via", equalTo(expectedData.get("via")),
                        "Server",equalTo(expectedData.get("Server"))).
                body("userId",equalTo(expectedData.get("userId")),
                        "title",equalTo(expectedData.get("title")),
                        "completed",equalTo(expectedData.get("completed")));

        // burada value Object oldugu icin ve status code 200 yani int oldugundan dolayi casting yaptik.


        // 2.yol

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(expectedData.get("statusCode"),response.statusCode());  // header
        Assert.assertEquals(expectedData.get("via"),response.getHeader("via"));  // header
        Assert.assertEquals(expectedData.get("Server"),response.getHeader("Server"));  // header
        Assert.assertEquals(expectedData.get("userId"),jsonPath.getInt("userId"));  // body icin jsonpath kullandik.
        Assert.assertEquals(expectedData.get("title"),jsonPath.getString("title"));
        Assert.assertEquals(expectedData.get("completed"),jsonPath.getBoolean("completed"));


        // NEW NEW NEW NEW NEW NEW NEW NEW
        // 3. yol deserilization

        // --object mapper
        // --pojo class ile birlikte map kullanacagiz.







    }
}
