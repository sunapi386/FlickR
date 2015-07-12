package com.example.jason.flickr;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by jason on 15-07-11.
 * Popular tags are listed here in a view. User clicks on tag
 * and is shown thumbnails of it.
 */

public class PopularActivity extends ActionBarActivity {
    private FlickrRestClient client;
    private ListView listView;
    private ArrayList<String> popularTags;
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular);
        final Context context = this.getApplicationContext();
        client = FlickrClientApp.getRestClient();
        popularTags = new ArrayList<>();
        listView = (ListView) findViewById(R.id.popular_list);
        listAdapter = new ArrayAdapter<>(this, R.layout.pop_row, popularTags);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                String item = (String) adapter.getItemAtPosition(position);
                Log.d("DEBUG", "click: " + item);
                // tag is selected, show list of thumbnails matching those tags.
                Intent intent = new Intent(context, ThumbnailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("userClickedTag", item);
                intent.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent);
            }
        });
        loadPopularTags();
    }

    private void loadPopularTags() {
        client.flickrGetHotList(new JsonHttpResponseHandler() {
            public void onSuccess(JSONObject json) {
                Log.d("DEBUG", "result: " + json.toString());
                try {
                    JSONArray tags = json.getJSONObject("hottags").getJSONArray("tag");
                    for (int t = 0; t < tags.length(); t++) {
                        String tag_name = tags.getJSONObject(t).getString("_content");
                        listAdapter.add(tag_name);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("DEBUG", e.toString());
                }
                Log.d("DEBUG", "Total items: " + popularTags.size());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_popular, menu);
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
