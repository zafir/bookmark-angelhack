package com.example.zafir.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

public class ParseIsbnJson extends AsyncTask<String, Void, HashMap<String, String>> {

    @Override
    protected HashMap<String, String> doInBackground(String... params) {

        HashMap<String, String> data = new HashMap<String, String>();

        try {

            JSONObject mainObject = new JSONObject(params[0]);

            Iterator keys = mainObject.keys();
            int i = 0;
            while(keys.hasNext()) {
                String key = String.valueOf(keys.next());
                JSONObject bookObject = mainObject.getJSONObject(key);

                if(bookObject.has("thumbnail_url")) {
                    String thumbUrl = bookObject.getString("thumbnail_url");
                    data.put("thumbUrl_" + i, thumbUrl);

                    Log.i("ParseIsbnJson.class", "thumbUrl_" + i + ": " + thumbUrl);
                }
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}
