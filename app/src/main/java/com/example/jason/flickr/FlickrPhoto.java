package com.example.jason.flickr;

import android.util.Log;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by jason on 15-07-11.
 * You can construct the source URL to a photo once you know its
 * ID, server ID, farm ID and secret, as returned by many API methods.
 * https://www.flickr.com/services/api/misc.urls.html
 * This object is put into a SQLite database, in FlickerClientApp.
 */

@Table(name = "photos")
public class FlickrPhoto extends Model {
    @Column(name = "uid")
    private String uid;
    @Column(name = "name")
    private String name;
    @Column(name = "url")
    private String url;

    public FlickrPhoto() { super(); }

    public FlickrPhoto(JSONObject jsonObject) {
        // https://api.flickr.com/services/rest?&format=json&nojsoncallback=1&api_key=d9da98bad53c44ec3774a802ba4e908c&method=flickr.photos.search&tags=happy
        // {"id":"18995294153","owner":"93502496@N08","secret":"c0f66d5fc6","server":"557","farm":1,"title":"DSC_0839","ispublic":1,"isfriend":0,"isfamily":0}
        super();
        try {
            uid = jsonObject.getString("id");
            String secret = jsonObject.getString("secret");
            int server = jsonObject.getInt("server");
            int farm = jsonObject.getInt("farm");
            name = jsonObject.getString("title");
            url = "http://farm" + farm + ".staticflickr.com/" + server +
                    "/" + uid + "_" + secret + ".jpg";

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static FlickrPhoto byPhotoUid(String uid) {
        Log.d("DEBUG", "photo: " + uid);
        return new Select().from(FlickrPhoto.class).where("uid = ?", uid).executeSingle();
    }

    public static ArrayList<FlickrPhoto> recentPhotos() {
        return new Select().from(FlickrPhoto.class).orderBy("id DESC").limit("300").execute();
    }

    public String getUrl() {
        return url;
    }
}
