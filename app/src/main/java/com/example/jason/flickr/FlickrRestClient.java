package com.example.jason.flickr;

import android.content.Context;
import android.util.Log;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.FlickrApi;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * Created by jason on 15-07-11.
 */

public class FlickrRestClient extends OAuthBaseClient {
    public static final Class<? extends Api> REST_API_CLASS = FlickrApi.class;
    public static final String REST_URL = "http://www.flickr.com/services";
    public static final String REST_CONSUMER_KEY = "d9da98bad53c44ec3774a802ba4e908c";
    public static final String REST_CONSUMER_SECRET = "081187a655e3cd72";
    public static final String REST_CALLBACK_URL = "oauth://flickrRestCallback";
    public static final String BASE_URL = "https://api.flickr.com/services/rest";
    private String tag;

    public FlickrRestClient(Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
        setBaseUrl(BASE_URL);
    }

    public void getPhotosByTag(AsyncHttpResponseHandler handler) {
        // Return a list of photos matching some criteria.
        // https://api.flickr.com/services/rest?&format=json&nojsoncallback=1&api_key=d9da98bad53c44ec3774a802ba4e908c&method=flickr.photos.search&tags=happy
        String apiUrl = getApiUrl("?&format=json&nojsoncallback=1&api_key=" + REST_CONSUMER_KEY +
                "&method=flickr.photos.search&tags=" + tag);
        Log.d("DEBUG", "Sending API call to " + apiUrl);
        client.get(apiUrl, null, handler);
    }

    public void flickrGetHotList(AsyncHttpResponseHandler handler) {
        // https://www.flickr.com/services/api/flickr.tags.getHotList.html
        // ex: https://api.flickr.com/services/rest?&format=json&nojsoncallback=1&api_key=d9da98bad53c44ec3774a802ba4e908c&method=flickr.tags.getHotList
        String apiUrl = getApiUrl("?&format=json&nojsoncallback=1&api_key=" + REST_CONSUMER_KEY +
                "&method=flickr.tags.getHotList");

        Log.d("DEBUG", "Sending API call to " + apiUrl);
        client.get(apiUrl, null, handler);
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
