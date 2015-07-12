package com.example.jason.flickr;

import android.content.Context;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by jason on 15-07-11.
 * Extends an https://github.com/pardom/ActiveAndroid class,
 * photos are stored in here.
 * Image loader loads photos as needed.
 */
public class FlickrClientApp extends com.activeandroid.app.Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        FlickrClientApp.context = this;
        // initialize ImageLoader with this configuration
        DisplayImageOptions defaultOptions =
                new DisplayImageOptions.Builder().
                cacheInMemory().cacheOnDisc().build();
        ImageLoaderConfiguration config =
                new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config);
    }

    public static FlickrRestClient getRestClient() {
        return (FlickrRestClient) FlickrRestClient.getInstance(FlickrRestClient.class, FlickrClientApp.context);
    }
}
