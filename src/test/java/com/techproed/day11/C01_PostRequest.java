package com.techproed.day11;

import com.techproed.testBase.JsonPlaceHolderTestBase;
import com.techproed.testData.JsonPlaceHolderTestData;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.junit.Test;

public class C01_PostRequest extends JsonPlaceHolderTestBase {

//    https://jsonplaceholder.typicode.com/todos URL ine aşağıdaki body gönderildiğinde,
//}
//             "userId": 55,
//             "title": "Tidy your room",
//             "completed": false
//             }
//             Dönen response un Status kodunun 201 ve response body nin aşağıdaki gibi olduğunu test edin
//             {
//             "userId": 55,
//             "title": "Tidy your room",
//             "completed": false,
//             "id": …
//             }

    @Test
    public void test(){

        spec01.pathParams("parametre1","todos");

        JsonPlaceHolderTestData expObje = new JsonPlaceHolderTestData();
        JSONObject expectedData = expObje.setUpPostTestData();





    }




}
