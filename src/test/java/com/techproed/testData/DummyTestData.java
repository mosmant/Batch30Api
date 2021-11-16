package com.techproed.testData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DummyTestData {

    //Status kodun 200 olduğunu,
    //    //5. Çalışan isminin "Airi Satou" olduğunu ,  çalışan sayısının 24 olduğunu,
    //    //Sondan 2. çalışanın maaşının 106450 olduğunu
    //    //40,21 ve 19 yaslarında çalışanlar olup olmadığını
    //    //11. Çalışan bilgilerinin
    //    //  {
    //    // “id”:”11”
    //    // "employee_name": "Jena Gaines",
    //    //"employee_salary": "90560",
    //    //"employee_age": "30",
    //    //"profile_image": "" }

    public HashMap<String, Object> setUpData(){

        HashMap<String,Object> eleventhEmployee = new HashMap<>();
        eleventhEmployee.put("id",11);
        eleventhEmployee.put("employee_name","Jena Gaines");
        eleventhEmployee.put("employee_salary",90560);
        eleventhEmployee.put("employee_age",30);
        eleventhEmployee.put("profile_image","");


        List<Integer> ageList=new ArrayList<Integer>();
        ageList.add(40);
        ageList.add(21);
        ageList.add(19);

        HashMap<String,Object> expectedData = new HashMap<>();
        expectedData.put("agelist",ageList);
        expectedData.put("eleventh_employee",eleventhEmployee);
        expectedData.put("Status Code",200);
        expectedData.put("fifth_employee","Airi Satou");
        expectedData.put("num_of_employee",24);
        expectedData.put("sec_but_last_salary",106450);

        return expectedData;

    }
}
