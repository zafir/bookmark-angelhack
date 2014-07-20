package com.example.zafir.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by superman on 7/19/14.
 */
public class BarcodeISBNConvertor extends AsyncTask<String, Integer, String> {

    HttpClient httpclient = new DefaultHttpClient();
    String api_key = "ac255830-94e3-4e6d-bb70-5cd62cb483e6";
    String idol_server = "http://api.idolondemand.com/1/api/sync/recognizebarcodes/v1?apikey="+api_key;

    HttpContext localContext = new BasicHttpContext();
    String json_result_string = "";
    @Override
    protected String doInBackground(String... image_location) {
        HttpResponse response = null;
        String result = "";
        HttpPost httppost = new HttpPost(this.idol_server);
        try {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

            File file = new File(image_location[0]);
            builder.addPart("file", new FileBody(file));
            builder.addPart("apikey", new StringBody(this.api_key));
            HttpEntity entity = builder.build();
            httppost.setEntity((org.apache.http.HttpEntity) entity);

            response = httpclient.execute(httppost, localContext);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
            StringBuilder string_builder = new StringBuilder();
            for (String line = null; (line = reader.readLine()) != null;) {
                //string_builder.append(line).append("\n");
                string_builder.append(line);
            }

            result = string_builder.toString();

//            JSONTokener tokener = new JSONTokener(string_builder.toString());
//            JSONObject finalResult = new JSONObject(tokener);
//            this.json_result_string = finalResult.toString();
//            Log.i("successful", finalResult.toString(3));

        } catch (ClientProtocolException e) {
            Log.i("failed", Log.getStackTraceString(e));
        } catch (IOException e) {
            Log.getStackTraceString(e);
        } catch (Exception e) {
            Log.i("failed", Log.getStackTraceString(e));
        }

        return result;
    }
    @Override
    protected void onPostExecute(String result){
        Log.i("successful", result);
    }
}
