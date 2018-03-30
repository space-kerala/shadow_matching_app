package com.example.root.dragndroprecyclerviewapp;

/**
 * Created by root on 1/11/17.
 */

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import jp.wasabeef.picasso.transformations.ColorFilterTransformation;

public final class ImageBinder {

    private static String tagPlant;
    private static int count = 0;
    private static int flag = 0;



    private ImageBinder() {

    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        // Picasso.with(context).load(url).into(imageView);
        //  Picasso.with(context).load(url).config(Bitmap.Config.ARGB_4444).into(imageView);

        tagPlant = (String) imageView.getTag();
        // Log.d("Tagg",Integer.toString(SceneTracker.getPosition()));
        Log.d("Tagg",Integer.toString(SceneTracker.getPicassoCount()));
        Log.d("Taggflag",Integer.toString(flag));


        if (SceneTracker.getPicassoCount() < 3) {
            RequestCreator requestCreator = Picasso.with(context)
                    .load(url)
                    .config(Bitmap.Config.RGB_565);


            requestCreator.into(imageView);
            flag=0;
            count=0;

        } else {

            //  Picasso.with(context).load(url).transform(new ColorFilterTransformation(Color.BLACK)).into(imageView).memoryPolicy(MemoryPolicy.NO_CACHE );;
            ArrayList<String> listImage = new ArrayList<>();
            listImage.add(url);

                Log.d("Tlistimage", String.valueOf(listImage));
            Collections.shuffle(listImage);


            RequestCreator requestCreator = Picasso.with(context)
                    .load(url)
                    .transform(new ColorFilterTransformation(Color.BLACK))
                    .memoryPolicy(MemoryPolicy.NO_CACHE);

            requestCreator.into(imageView);



            count++;
            flag++;
            countFlag(flag);


        }
        SceneTracker.setPicassoCount(SceneTracker.getPicassoCount()+1);

    }

    public static void countFlag(int value){
       if(value==3){
       SceneTracker.setPicassoCount(-1);
       }
    }


}