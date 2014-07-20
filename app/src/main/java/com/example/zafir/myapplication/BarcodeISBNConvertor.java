package com.example.zafir.myapplication;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by superman on 7/19/14.
 */
public class BarcodeISBNConvertor {
    public BarcodeISBNConvertor() {
        HttpClient httpclient = new DefaultHttpClient();
        String api_key = "ac255830-94e3-4e6d-bb70-5cd62cb483e6";
        String idol_server = "https://api.idolondemand.com/1/api/sync/recognizebarcodes/v1";

    }
    public HttpResponse convert(String image_location) {
        HttpResponse response = null;
        return response;
    }
}
