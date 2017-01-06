package com.example.saurav.imagesearch;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by saura on 12-12-2016.
 */

public class FlickrImageViewHolder extends RecyclerView.ViewHolder{

    protected ImageView thumbnail;
    protected TextView title;
    public FlickrImageViewHolder(View view) {
        super(view);
        thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        title = (TextView) view.findViewById(R.id.title);
    }
}
