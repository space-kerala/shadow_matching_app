package com.example.root.dragndroprecyclerviewapp;

/**
 * Created by root on 1/11/17.
 */

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import jp.wasabeef.picasso.transformations.ColorFilterTransformation;

public final class ImageBinder {

    private static String tagPlant;
    private static int count = 0;


    private ImageBinder() {

    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        // Picasso.with(context).load(url).into(imageView);
        //  Picasso.with(context).load(url).config(Bitmap.Config.ARGB_4444).into(imageView);

        tagPlant = (String) imageView.getTag();
        // Log.d("Tagg",Integer.toString(SceneTracker.getPosition()));

        if (true) {
            RequestCreator requestCreator = Picasso.with(context)
                    .load(url)
                    .config(Bitmap.Config.RGB_565);

            requestCreator.into(imageView);


        } else {

            Picasso.with(context).load(url).transform(new ColorFilterTransformation(Color.BLACK)).into(imageView);


        }


    }


}