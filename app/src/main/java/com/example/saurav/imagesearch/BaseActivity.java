package com.example.saurav.imagesearch;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by saura on 15-12-2016.
 */

public class BaseActivity extends AppCompatActivity {
    private Toolbar toolbar;


    public static final String PHOTO_TRANSFER = "PHOTO_TRANSFER";
    public static final String FLICKR_QUERY = "FLICKR_QUERY";
    protected Toolbar activateToolbar(){
        if (toolbar == null) {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            if (toolbar!=null){
                setSupportActionBar(toolbar);
            }
        }
        return toolbar;
    }
    protected Toolbar activateToolbarWithHomeEnabled(){
        activateToolbar();
        if (toolbar!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        return toolbar;
    }
}
