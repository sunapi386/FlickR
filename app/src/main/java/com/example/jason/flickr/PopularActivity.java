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
import java.util.List;


public class PopularActivity extends ActionBarActivity {
    private FlickrRestClient client;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular);
        client = FlickrClientApp.getRestClient();
        listView = (ListView) findViewById(R.id.popular_list);
        loadPopularTags();

    }

    /* Popular Tags Example:
   {
      "hottags":{
         "period":"day",
         "count":1,
         "tag":[
            {
               "score":"100",
               "_content":"feb23"
            }
            {
               "score":"97",
               "_content":"whitenight"
            },
         ]
      },
      "stat":"ok"
    }*/
    private void loadPopularTags() {
        client.flickrGetHotList(new JsonHttpResponseHandler() {
            public void onSuccess(JSONObject json) {
                Log.d("DEBUG", "result: " + json.toString());

//                try {
//                    JSONArray tags = json.getJSONObject("hottags").getJSONArray("tag");
//                    for (int t = 0; t < tags.length(); t++) {
//                        String tag_name = tags.getJSONObject(t).getString("_content");
//                        popularTags.add(tag_name);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    Log.e("DEBUG", e.toString());
//                }

//                Log.d("DEBUG", "Total items: " + popularTags.size());
            }
        });
//        listAdapter = new ArrayAdapter<String>(context, R.layout.simplerow, popularTags);
//        listView.setAdapter(listAdapter);

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
