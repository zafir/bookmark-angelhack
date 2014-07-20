package com.example.zafir.myapplication;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * Created by superman on 7/19/14.
 */
public class BarcodeISBNConvertor {

    HttpClient httpclient = new DefaultHttpClient();
    String api_key = "ac255830-94e3-4e6d-bb70-5cd62cb483e6";
    String idol_server = "https://api.idolondemand.com/1/api/sync/recognizebarcodes/v1";

    public HttpResponse convert(String image_location) {
        HttpResponse response = null;
        HttpPost httppost = new HttpPost(this.idol_server);
        try {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

            builder.addPart("api_key", new StringBody(this.api_key, ContentType.TEXT_PLAIN));
            File file = new File(image_location);
            builder.addPart("file", new FileBody(file));
            HttpEntity entity = builder.build();
            httppost.setEntity((org.apache.http.HttpEntity) entity);
            response = httpclient.execute(httppost);
            Log.i("successful", response.toString());
        } catch (ClientProtocolException e) {
            Log.getStackTraceString(e);
        } catch (IOException e) {
            Log.getStackTraceString(e);
        }

        return response;
    }
}
