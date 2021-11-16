package com.techproed.day08;

import com.techproed.testBase.DummyTestBase;
import com.techproed.testData.DummyTestData;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class C02_GetRequest extends DummyTestBase {

    //http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
    //Status kodun 200 olduğunu,
    //5. Çalışan isminin "Airi Satou" olduğunu ,  çalışan sayısının 24 olduğunu,
    //Sondan 2. çalışanın maaşının 106450 olduğunu
    //40,21 ve 19 yaslarında çalışanlar olup olmadığını
    //11. Çalışan bilgilerinin
    //  {
    // “id”:”11”
    // "employee_name": "Jena Gaines",
    //"employee_salary": "90560",
    //"employee_age": "30",
    //"profile_image": "" }
    //} gibi olduğunu test edin.

    @Test
    public void test(){

        // url olustur

        spec03.pathParams("first","employees");

        // expected result getir.

        DummyTestData dummyObj = new DummyTestData();
        HashMap<String,Object> expectedData = dummyObj.setUpData();

        // request getir.
        Response response = given().accept("application/json").spec(spec03).when().get("/{first}");
        response.prettyPrint();

        //actual result getir.

        HashMap<String,Object> actualData = response.as(HashMap.class);

        //Assertion

        Assert.assertEquals(expectedData.get("Status Code"),response.getStatusCode());





    }
}
