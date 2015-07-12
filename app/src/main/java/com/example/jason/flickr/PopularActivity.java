package com.example.jason.flickr;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class PopularActivity extends ActionBarActivity {
    private FlickrRestClient client;
    private ListView listView;
    private ArrayList<String> popularTags;
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular);
        client = FlickrClientApp.getRestClient();
        popularTags = new ArrayList<>();
        listView = (ListView) findViewById(R.id.popular_list);
        listAdapter = new ArrayAdapter<>(this, R.layout.pop_row, popularTags);
        listView.setAdapter(listAdapter);
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
