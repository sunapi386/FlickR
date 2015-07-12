package com.example.jason.flickr;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by jason on 15-07-11.
 */
public class PhotoAdapter extends ArrayAdapter<FlickrPhoto> {
    public PhotoAdapter(Context context, List<FlickrPhoto> flickrPhotoList) {
        super(context, R.layout.photo_object, flickrPhotoList);

    }
}