package com.example.jason.flickr;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        client = FlickrClientApp.getRestClient();
        flickrPhotos = new ArrayList<>();
        gvPhotos = (GridView) findViewById(R.id.thumbnail_photos_gv);
        title = (TextView) findViewById(R.id.thumbnail_title);
        photosAdapter = new PhotoAdapter(this, flickrPhotos);

        Intent intent = getIntent();
        String userClickedTag = intent.getStringExtra("userClickedTag");
        Log.d("DEBUG", "ThumbnailActivity userClickedTag: " + userClickedTag);

        title.setText("Tag: " + userClickedTag);
        gvPhotos.setAdapter(photosAdapter);
        loadPhotos(userClickedTag);
    }

    private void loadPhotos(String userClickedTag) {
        client.setTag(userClickedTag);
        client.getPhotosByTag(new JsonHttpResponseHandler() {
            public void onSuccess(JSONObject json) {
                Log.d("DEBUG", "getPhotosByTag result: " + json.toString());
                try {
                    JSONArray photos = json.getJSONObject("photos").getJSONArray("photo");
                    for (int i = 0; i < photos.length(); i++) {
                        Log.d("DEBUG", "photo: " + photos.getJSONObject(i).toString());
                        String uid = photos.getJSONObject(i).getString("id");
                        FlickrPhoto photo = FlickrPhoto.byPhotoUid(uid);
                        if (photo == null) {
                            photo = new FlickrPhoto(photos.getJSONObject(i));
                        }
                        photo.save();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (FlickrPhoto p : FlickrPhoto.recentPhotos()) {
                    photosAdapter.add(p);
                }
            }
        });
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
