package com.techproed.day06;

import com.techproed.testBase.JsonPlaceHolderTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C99_Practise extends JsonPlaceHolderTestBase {

    // 1) Create a class and name it as you wish :)
    // 2) When I send a GET Request to https://jsonplaceholder.typicode.com/todos Then;
    // HTTP Status code should be "200", Content type should be in "JSON" format, there should be
    // 200 "title", "dignissimos quo nobis earum saepe" should be one of the "title"s, 111, 121, and 131
    // should be among the "id"s, 4th title is "et porro tempora" and last title is "ipsam aperiam
    // voluptates qui"

    @Test
    public void test() {

        spec01.pathParam("param","todos");

        Response response = given().

                accept("application/json").

                spec(spec01).

                when().

                get("/{param}");

        response.then().

                assertThat().

                statusCode(200).

                contentType("application/json");

        JsonPath jsonPath=response.jsonPath();

        Assert.assertEquals(200,jsonPath.getList("title").size());

        Assert.assertTrue(jsonPath.getList("title").contains("dignissimos quo nobis earum saepe"));

        Assert.assertTrue(jsonPath.getList("id").contains(111) ||

                jsonPath.getList("id").contains(121) ||

                jsonPath.getList("id").contains(131));

        Assert.assertEquals("et porro tempora",jsonPath.getString("title[3]"));

        Assert.assertEquals("ipsam aperiam voluptates qui",jsonPath.getString("title[-1]"));

    }



}
