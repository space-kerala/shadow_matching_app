package com.example.root.dragndroprecyclerviewapp;

/**
 * Created by root on 3/3/18.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;



/**
 * Created by root on 2/2/18.
 */

public class RecyclerViewEmptySupport extends RecyclerView {

    private View emptyView,buttonView,scoreView,rightView,wrongView,rightTextView,wrongTextView,buttonExitView;

    private AdapterDataObserver emptyObserver = new AdapterDataObserver() {


        @Override
        public void onChanged() {
            Adapter<?> adapter =  getAdapter();
            if(adapter != null && emptyView != null) {
                if(adapter.getItemCount() == 0) {

                    emptyView.setVisibility(View.VISIBLE);
                    buttonView.setVisibility(View.VISIBLE);
                    buttonExitView.setVisibility(View.VISIBLE);
                   // scoreView.setVisibility(View.VISIBLE);

                    // setAdapter(adapter);
                    RecyclerViewEmptySupport.this.setVisibility(View.GONE);
                }
                else {
                    emptyView.setVisibility(View.GONE);
                    buttonView.setVisibility(View.GONE);
                    buttonExitView.setVisibility(View.GONE);
                   // scoreView.setVisibility(View.VISIBLE);
                    RecyclerViewEmptySupport.this.setVisibility(View.VISIBLE);
                }
            }

        }
    };

    public RecyclerViewEmptySupport(Context context) {
        super(context);
    }

    public RecyclerViewEmptySupport(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerViewEmptySupport(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);

        if(adapter != null) {
            adapter.registerAdapterDataObserver(emptyObserver);
        }

        emptyObserver.onChanged();
    }

    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;



    }
    public void setButtonView(View buttonView){
        this.buttonView = buttonView;
    }
    public  void setScore(View scoreView){
        this.scoreView=scoreView;


    }

    public  void setRight(View rightView){
        this.rightView=rightView;


    }
    public  void setWrong(View wrongView){
        this.wrongView=wrongView;


    }

    public  void setRightText(View rightTextView){
        this.rightTextView=rightTextView;


    }
    public  void setWrongText(View wrongTextView){
        this.wrongTextView=wrongTextView;


    }

    public void setButtonExitView(View buttonExitView){
        this.buttonExitView = buttonExitView;
    }



}
