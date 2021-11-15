package com.techproed.day06;

import com.techproed.testBase.DummyTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

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

        Assert.assertTrue(jsonPath.getList("data.employee_age").contains(21) ||

                jsonPath.getList("data.employee_age").contains(23) ||

                jsonPath.getList("data.employee_age").contains(61));

//        List<Integer> list=new ArrayList<Integer>();
//
//        list.add(21);
//
//        list.add(23);
//
//        list.add(61);
//
//        Assert.assertTrue(jsonPath.getList("data.employee_age").containsAll(list));

    }



}
