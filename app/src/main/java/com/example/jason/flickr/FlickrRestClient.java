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

    public FlickrRestClient(Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
        setBaseUrl(BASE_URL);
    }

}
