package com.example.jason.flickr;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.codepath.oauth.OAuthLoginActivity;

/**
 * Created by jason on 15-07-11.
 * To query photos; we need to create our own REST client.
 */


public class LoginActivity extends OAuthLoginActivity<FlickrRestClient> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public void onLoginSuccess() {
        Intent i = new Intent(this, PopularActivity.class);
        startActivity(i);
    }

    @Override
    public void onLoginFailure(Exception e) {
        e.printStackTrace();
    }

    public void loginToRest(View view) {
        getClient().connect();
    }

}
