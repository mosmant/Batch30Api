package com.techproed.testData;

import org.json.JSONObject;

import java.util.HashMap;

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

    public JSONObject SetupTestandRequestData(){

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin","2020-09-09");
        bookingdates.put("checkout","2020-09-21");

        JSONObject expectedRequest = new JSONObject();
        expectedRequest.put("firstname","Batch30");
        expectedRequest.put("lastname","bitti");
        expectedRequest.put("totalprice",500);
        expectedRequest.put("depositpaid",false);
        expectedRequest.put("bookingdates",bookingdates);


        return expectedRequest;



    }
}
