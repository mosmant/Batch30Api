package com.techproed.day06;

import com.techproed.testBase.DummyTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class C03_GetRequest extends DummyTestBase {

    // http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde,
    // status kodun 200,
    // gelen body de,
    // 5. çalışanın isminin "Airi Satou" olduğunu,
    // 6. çalışanın maaşının "372000" olduğunu,
    // Toplam 24 tane çalışan olduğunu,
    // "Rhona Davidson" ın employee lerden biri olduğunu,
    // "21", "23", "61" yaşlarında employeeler olduğunu test edin

    @Test
    public void test()  {

        spec03.pathParams("parametre1","employees");

        Response response = given().

                accept("application/json").

                spec(spec03).

                when().

                get("/{parametre1}");

        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(200,response.getStatusCode());

        // Assert.assertTrue(response.getStatusCode() == 200);

        System.out.println(jsonPath.getList("data.id").size());

        Assert.assertEquals(24,jsonPath.getList("data.id").size());  // output : 24

        Assert.assertEquals("Airi Satou",jsonPath.getString("data.employee_name[4]"));

        Assert.assertEquals(372000,jsonPath.getInt("data.employee_salary[5]"));

        Assert.assertTrue(jsonPath.getList("data.employee_name").contains("Rhona Davidson"));

        Assert.assertTrue(jsonPath.getList("data.employee_age").stream().anyMatch(t-> t.equals(21) || t.equals(61) || t.equals(23)));

        List<Integer> arananyaslar = Arrays.asList(21,23,61);

        /*
        List<Integer> arananyaslar1 = new ArrayList<>();

        arananyaslar1.add(21);
        arananyaslar1.add(23);
        arananyaslar1.add(61);
        */

        Assert.assertTrue(jsonPath.getList("data.emplyee_age").containsAll(arananyaslar));


       // Assert.assertTrue(jsonPath.getList("data.employee_age").stream().anyMatch(t-> t.equals(21) && t.equals(61) && t.equals(23)));


    }



}
