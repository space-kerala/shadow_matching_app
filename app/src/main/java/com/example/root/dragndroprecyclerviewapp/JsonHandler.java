package com.example.root.dragndroprecyclerviewapp;

/**
 * Created by root on 4/11/17.
 */

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * Created by root on 17/10/17.
 */

public class JsonHandler {
    private  static JSONArray scenes;


   public JsonHandler(){
       // System.out.print("hello");
    }

    public void readJson() {
        try {
            //Load File
           // BufferedReader jsonReader = new BufferedReader(new InputStreamReader(ctx.getResources().openRawResource(R.raw.shadow_matching_scene_data)));
            File myFile = new File((Environment.getExternalStorageDirectory().getAbsolutePath()+ "/shadow/shadow_matching_scene_data.json"));
            System.out.println("file.exists() = " + myFile.exists());
           // myFile.setReadable(true);
           // myFile.setWritable(true);

            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader jsonReader = new BufferedReader(
                    new InputStreamReader(fIn));

            StringBuilder builder = new StringBuilder();
            for (String line = null; (line = jsonReader.readLine()) != null; ) {
                builder.append(line).append("\n");
            }


            JSONObject root = new JSONObject(builder.toString());
            Log.d("TTag", root.toString());
            //Toast.makeText(this,root.getString("name").toString(),Toast.LENGTH_SHORT).show();
            //root.getJSONArray("scenes").getJSONObject(0).getString("src");

            scenes = root.getJSONArray("scenes");
            Log.d("scenes length",String.valueOf(scenes.length()));
           /* JSONArray scene = scenes.getJSONArray(0);
            JSONObject sceneItem = scene.getJSONObject(0);

            Toast.makeText(this, sceneItem.getString("src").toString(), Toast.LENGTH_SHORT).show();*/




        } catch (FileNotFoundException e) {
            Log.e("jsonFile", "file not found");
        } catch (IOException e) {
            Log.e("jsonFile", "ioerror");
        } catch (JSONException e) {
            Log.e("jsonFile", "error while parsing json");
        }



    }

    public ArrayList<Item> getSceneData(int index){
        JSONObject sceneItem;
        ArrayList<Item> itemList = new ArrayList<>();

            try {

                    JSONArray scene = scenes.getJSONArray(index);
                SceneTracker.setTotalLevel(scenes.length());
                   // Log.d("scene length",String.valueOf(scene.length()));

                    for (int i = 0; i < scene.length(); i++) {

                        sceneItem = scene.getJSONObject(i);
                        Item item = new Item(sceneItem.getString("answer"), sceneItem.getString("src"));
                        itemList.add(item);
                    }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        return itemList;
    }


}