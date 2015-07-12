package com.example.jason.flickr;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;


public class ThumbnailActivity extends ActionBarActivity {
    private FlickrRestClient client;
    private ArrayList<FlickrPhoto> flickrPhotos;
    private PhotoAdapter photosAdapter;
    private GridView gvPhotos;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thumbnail);
        Intent intent = getIntent();
//        client = FlickrClientApp.getRestClient();
//        flickrPhotos = new ArrayList<>();
//        photosAdapter = new PhotoAdapter(this, flickrPhotos);
        title = (TextView) findViewById(R.id.thumbnail_title);
//        gvPhotos = (GridView) findViewById(R.id.thumbnail_photos_gv);

        String userClickedTag = intent.getStringExtra("userClickedTag");
        Log.d("DEBUG", "ThumbnailActivity userClickedTag: " + userClickedTag);

        title.setText("Tag: " + userClickedTag);
//        gvPhotos.setAdapter(photosAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_thumbnail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
