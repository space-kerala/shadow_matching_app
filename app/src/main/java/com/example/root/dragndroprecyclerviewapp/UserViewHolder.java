package com.example.root.dragndroprecyclerviewapp;

/**
 * Created by root on 1/11/17.
 */

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.example.root.dragndroprecyclerviewapp.databinding.UserItemBinding;



public class UserViewHolder extends RecyclerView.ViewHolder {
    private UserItemBinding binding;
    public ImageButton imageButton;
   

    public UserViewHolder(View layoutView) {
        super(layoutView);
        binding = DataBindingUtil.bind(layoutView);
        imageButton = (ImageButton) layoutView.findViewById(R.id.circleView);


    }

    public void bind(User user) {
        binding.setUser(user);
    }
}
