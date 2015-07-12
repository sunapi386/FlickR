package com.example.jason.flickr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by jason on 15-07-11.
 * Takes a FlickrPhoto and renders it.
 */
public class PhotoAdapter extends ArrayAdapter<FlickrPhoto> {
    public PhotoAdapter(Context context, List<FlickrPhoto> flickrPhotoList) {
        super(context, R.layout.photo_object, flickrPhotoList);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FlickrPhoto photo = this.getItem(position);
        LinearLayout itemView;
        ImageView ivImage;
        ImageLoader imageLoader = ImageLoader.getInstance();
        if (convertView == null) {
            LayoutInflater inflator = LayoutInflater.from(getContext());
            itemView = (LinearLayout) inflator.inflate(R.layout.photo_object, parent, false);
        } else {
            itemView = (LinearLayout) convertView;
        }
        ivImage = (ImageView) itemView.findViewById(R.id.ivPhoto);
        ivImage.setImageResource(android.R.color.transparent);
        imageLoader.displayImage(photo.getUrl(), ivImage);
        return itemView;


    }
}