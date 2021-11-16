package com.techproed.testData;

import java.util.HashMap;
import java.util.Map;

public class HerokuappTestData {

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


    public HashMap <String , Object > setUpTestData () {

        HashMap<String, Object> bookingdates = new HashMap<>();
        bookingdates.put("checkin","2016-07-28");
        bookingdates.put("checkout","2020-03-26");

        HashMap<String,Object> expectedData = new HashMap<>();
        expectedData.put("firstname","Sally");
        expectedData.put("lastname","Smith");
        expectedData.put("totalprice",716);
        expectedData.put("depositpaid",false);
        expectedData.put("bookingdates",bookingdates);

        return expectedData;
    }
}
