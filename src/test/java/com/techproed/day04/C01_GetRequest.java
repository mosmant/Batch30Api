package com.techproed.day04;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C01_GetRequest {

    // 1- API TESTI YAPARKEN URL(END POINT) BELIRLENMELI
    // 2- BEKLENEN SONUC(EXPECTED RESULT) OLUSTURULUR.
    // DIYELIMKI BU CASE DE BODY DOGRULAMASI ISTENMEDIGI ICIN SIMDILIK BEKLENEN SONUC OLUSTURMUYORUZ.
    // 3- REQUEST GONDERIMI YAPIYORUZ
    // 4- ACTUAL RESULY OLUSTUR
    // 5- DOGRULAMA YAP(ASSERTION).


    @Test
    public void test01() {

        //  https://restful-booker.herokuapp.com/booking/3  <== (endpoint)   adresine bir request gonderildiginde donecek cevap(response) icin
        //  HTTP status kodunun 200
        //  Content Type'in Json
        //  Ve Status Line'in HTTP/1.1 200 OK
        //  Oldugunu test edin
        String url = "https://restful-booker.herokuapp.com/booking/3";

        Response response = given().   // gerekliligi ifade eder.
                accept("application/json").
                when().  // kullanıcı action ı
                get(url);

        // gonderilen requestin response unu assign ettik. Response classini rest assured library den aldik.

        response.prettyPrint();

        // sout yerine response methodunda tanimli olan prettyPrint methodu bize gelen response u yazdirir.

        // response body ile ilgili islem yapmayacagimiz icin dogrulama yapmayacagiz.

        System.out.println("Status code : "+response.getStatusCode());

        // status kodunu getirdik. biz getirdigimiz status kod 200 du.

        System.out.println("Content Type : "+response.getContentType());

        // content type i getirdik. bizim content typimiz json di.

        System.out.println("Status Line : "+response.statusLine());

        // statusLine i getirdik.

        System.out.println("Response Headers : "+response.getHeaders());

        // tum response headers i bize getirecek.

        Assert.assertEquals("Beklenen status code actual result'a esit degildir. TEST FAILED ",200,response.getStatusCode());

        // status code bize int olarak geldigi icin 200 yazdik. expected kismi bize task olarak verildi.
        // Actual kismibi response objesinden elde ettik.

        Assert.assertEquals("Beklenen content type actual result'a esit degildir. TEST FAILED","application/json; charset=utf-8",response.getContentType());

        // content typr bize string olarak geldi.

        Assert.assertEquals("Beklenen status code result'a esit degildir. TEST FAILED","HTTP/1.1 200 OK",response.statusLine());

        //burada statusline bize string olarak geldi.

        // ================= response dan method halinde gelen assertion kullanimi MATCHER CLASS yontemi ===================

        response.then().                        // gherkin language de assertion then keywordunden sonra calisir.
                assertThat().                   // assertion yapmamizi saglayan method.
                statusCode(200).                // burada status codu int olarak 200 mu?
                contentType(ContentType.JSON).  // content type i JSON mi?
                statusLine("HTTP/1.1 200 OK");  // status line i "HTTP/1.1 200 OK" mi?



    }

}
