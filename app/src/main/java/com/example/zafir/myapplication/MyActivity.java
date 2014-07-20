package com.example.zafir.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;


public class MyActivity extends Activity {


    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        // *** test code for testing HTTP and JSON request
        String responseBarcode = getBarcode("/storage/emulated/0/DCIM/Camera/IMG_20140720_115402.jpg");
        Log.i("Barcode response", responseBarcode);

        HashMap hashBarcode = getBarcodeHashtable(responseBarcode);
        String isbn = hashBarcode.get("isbn_0").toString();
        Log.i("ISBN", isbn);

        HashMap hashBookInfo = getBookInfo(isbn);

        Log.i("book info", hashBookInfo.toString());
        // *** end test code. Remove/move to other parts of code later.

        String[] listItems = new String[3];
        listItems[0] = "Item 1";
        listItems[1] = "Item 2";
        listItems[2] = "Item 3";

        //ArrayAdapter<String> listItemAdapter = new CustomAdapter(this, android.R.layout.simple_list_item_1,listItems);
        ArrayAdapter<String> listItemAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        ListView lv = (ListView)this.findViewById(R.id.mainList);
        lv.setAdapter(listItemAdapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void onClickAddBook(View view) {
        //Inform the user the button has been clicked
        Toast.makeText(this, "Add book clicked.", Toast.LENGTH_SHORT).show();
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri mMediaUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
        if (mMediaUri == null) {
            Toast.makeText(MyActivity.this, "error",
                    Toast.LENGTH_LONG).show();
        } else {
            takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, mMediaUri);
            startActivityForResult(takePhotoIntent, 0);
        }
    }

    /** Create a file Uri for saving an image or video */
    private static Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /** Create a File for saving an image or video */
    private static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

    public String getBarcode(String s) {
        BarcodeISBNConvertor b = new BarcodeISBNConvertor();
        String response = null;

        try {
            response = b.execute(s).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return response;
    }

    public HashMap getBarcodeHashtable(String s) {
        HashMap data = null;

        try {
            data = new ParseBarcodeJson().execute(s).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return data;
    }

    public HashMap getBookInfo(String s) {
        HttpBooksRequest g = new HttpBooksRequest();
        String response = null;
        String url = "http://openlibrary.org/api/books?bibkeys=ISBN:" + s + "&format=json";
        Log.i("books api url", url);

        try {
            response = g.execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        HashMap data = null;

        try {
            data = new ParseIsbnJson().execute(response).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return data;
    }

}
