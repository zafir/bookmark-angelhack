package com.example.zafir.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class ParseBarcodeJson extends AsyncTask<String, Void, HashMap<String, String>> {

    @Override
    protected HashMap<String, String> doInBackground(String... params) {

        HashMap<String, String> data = new HashMap<String, String>();

        try {

            JSONObject mainObject = new JSONObject(params[0]);

            JSONArray barcodes = mainObject.getJSONArray("barcode");

            for(int i = 0; i < barcodes.length(); i++) {
                String isbn = barcodes.getJSONObject(i).getString("text");
                String barcodeType = barcodes.getJSONObject(i).getString("barcode_type");
                Log.i(ParseBarcodeJson.class.getName(), "isbn" + i + ": " + isbn);
                Log.i(ParseBarcodeJson.class.getName(), "barcode_type" + i + ": " + barcodeType);

                data.put("isbn_" + i, isbn);
                data.put("barcode_type_" + i, barcodeType);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

}