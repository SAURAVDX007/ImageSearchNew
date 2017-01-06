package com.example.saurav.imagesearch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by saura on 16-12-2016.
 */

public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

    public static interface OnItemClickListener{
       public   void onItemClick(View view, int position);
       public   void onItemLongClick(View view, int position);
    }
    private OnItemClickListener listener;
    private GestureDetector gestureDetector;

    public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, final OnItemClickListener listener){
        this.listener = listener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
          public boolean onSingleTapUp(MotionEvent event){
              return true;
          }

          public boolean onDown(MotionEvent event){
              return true;
          }
          public void onLongPress(MotionEvent event){
              View childView = recyclerView.findChildViewUnder(event.getX(),event.getY());
              if (childView != null&&listener!=null) {
                  listener.onItemLongClick(childView,recyclerView.getChildLayoutPosition(childView));
              }
          }
        });
    }
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(),e.getY());
        if (childView != null&&listener!=null&gestureDetector.onTouchEvent(e)) {
            listener.onItemClick(childView,rv.getChildLayoutPosition(childView));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
