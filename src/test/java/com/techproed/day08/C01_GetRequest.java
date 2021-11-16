package com.techproed.day08;

import com.techproed.testBase.HerokuAppTestBase;
import com.techproed.testData.HerokuappTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class C01_GetRequest extends HerokuAppTestBase {
    // https://restful-booker.herokuapp.com/booking/1 url ine bir istek gönderildiğinde
    // dönen response body nin
    // {
    //    "firstname": "Susan",
    //    "lastname": "Ericsson",
    //    "totalprice": 521,
    //    "depositpaid": true,
    //    "bookingdates": {
    //        "checkin": "2015-03-05",
    //        "checkout": "2019-08-18"
    //    }
    //}
    //} gibi olduğunu test edin

    @Test
    public void test(){


        // url olustur.
        spec02.pathParams("parameter1","booking",
                     "paramater2",1);

        // expected result

        HerokuappTestData expectedObj = new HerokuappTestData();
        HashMap<String,Object> expectedData = expectedObj.setUpTestData();
        System.out.println(expectedData);

        // request gonder
        Response response = given().accept("application/json").spec(spec02).when().get("/{parameter1}/{paramater2}");
        response.prettyPrint();
        /*
        // JSONPATH ILE

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(expectedData.get("firstname"),jsonPath.getString("firstname"));
        Assert.assertEquals(expectedData.get("lastname"),jsonPath.getString("lastname"));
        Assert.assertEquals(expectedData.get("totalprice"),jsonPath.getInt("totalprice"));
        Assert.assertEquals(expectedData.get("depositpaid"),jsonPath.getBoolean("depositpaid"));

        Assert.assertEquals(((Map)expectedData.get("bookingdates")).get("checkin"),
                            (jsonPath.getString("bookingdates.checkin")));

        Assert.assertEquals(((Map)expectedData.get("bookingdates")).get("checkout"),
                            (jsonPath.getString("bookingdates.checkout")));

        */

        // serilization yolu ile

        // actual result
        HashMap <String , Object > actualData = response.as(HashMap.class);
        System.out.println(actualData);

        // assert

        Assert.assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        Assert.assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        Assert.assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        Assert.assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));

        // NESTED MAP ASSERT

        Assert.assertEquals(((Map)expectedData.get("bookingdates")).get("checkin"),
                            ((Map)actualData.get("bookingdates")).get("checkin"));

        Assert.assertEquals(((Map) expectedData.get("bookingdates")).get("checkout"),
                            ((Map) actualData.get("bookingdates")).get("checkout"));

        //** NESTED MAP TE TYPE CASTING ISLEMINI bookingdates in ICINE GIREBILMEK ICIN YAPTIK.
        //** GET() methodari sadece bir defa cikti.



        /*
        KOCAMAN NOT HEROKUAPP SITESINDE SUREKLI OLARAK JSON DATALAR DEGISIYOR.

        comparisonfailure exception almamiz normal...
         */




    }

}
