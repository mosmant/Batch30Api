package com.techproed.day07;

import com.techproed.testBase.DummyTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class C01_GetRequest extends DummyTestBase {

    //http://dummy.restapiexample.com/api/v1/employees
    //url ine bir istek gönderildiğinde
    //Dönen response un
    // Status kodunun 200,
    // 1)10’dan büyük tüm id’leri ekrana yazdırın ve
    //10’dan büyük 14 id olduğunu,
    // 2)30’dan küçük tüm yaşları ekrana yazdırın ve
    //  bu yaşların içerisinde en büyük yaşın 23 olduğunu
    // 3)Maası 350000 den büyük olan tüm employee name’leri ekrana yazdırın ve
    //  bunların içerisinde “Charde Marshall” olduğunu test edin

    @Test
    public void test(){

        spec03.pathParams("parameter1","employees");

        Response response = given().

                accept("application/json").

                spec(spec03).

                when().

                get("/{parameter1}");

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(200,response.getStatusCode());

        List<Integer> idList = jsonPath.getList("data.findAll{it.id>10}.id");

        System.out.println(idList);

        Assert.assertEquals(14,idList.size());

        List<Integer> ageList = jsonPath.getList("data.findAll{it.employee_age<30}.employee_age");

        System.out.println(ageList);
        /*
        int maxAge = ageList.stream().reduce(Integer.MIN_VALUE,Math::max);

        Assert.assertEquals(23,maxAge);
        */

        // 2)30’dan küçük tüm yaşları ekrana yazdırın ve bu yaşların içerisinde en büyük yaşın 23 olduğunu


        Collections.sort(ageList);
        Assert.assertEquals(23,(int)ageList.get(ageList.size()-1));

        // actual result Integer oldugundan ve expected result ise int oldugundan dolayi casting yapiyoruz.


        // 3)Maası 350000 den büyük olan tüm employee name'leri ekrana yazdırın ve bunların içerisinde "Charde Marshall" olduğunu test edin

        List<String> isimListesi=jsonPath.getList("data.findAll{it.employee_salary>350000}.employee_name");
        System.out.println(isimListesi);

        Assert.assertTrue(isimListesi.contains("Charde Marshall"));


        // groovy dili
        //     data. findAll   {it.employee_salary>350000}.employee_name
        // datalardan hepsinin sec ve liste ekle ->
        // {filterleme yap ===> it.employee salary ise this. kullanimi gibi} ==>
        // .employee_name ise filtreleme yapildiktan sonra istedigimiz datayi getirebiliriz.

    }
}
