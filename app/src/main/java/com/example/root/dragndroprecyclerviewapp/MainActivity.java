package com.example.root.dragndroprecyclerviewapp;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageButton;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private int coloumn;
    private JsonHandler jsonHandler;
    private ImageButton imageButtonBack;
    private ItemAdapter adapter;
    private MediaPlayer backButtonSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.binding_list_activity);
        int level = 1;
        coloumn = 3;
        backButtonSound = MediaPlayer.create(this, R.raw.buttonclick);
        imageButtonBack = (ImageButton) findViewById(R.id.imagebuttonback);
        imageButtonBack.setVisibility(View.VISIBLE);

        if (SceneTracker.getLevel() == 2) {
            imageButtonBack.setVisibility(View.VISIBLE);
        }

        recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(coloumn, 1));


        jsonHandler = new JsonHandler(this);
        jsonHandler.readJson();

        SceneTracker.setLevel(level);
        adapter = new ItemAdapter(jsonHandler.getSceneData(level - 1), this);
        recyclerView.setAdapter(adapter);


        imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SceneTracker.getLevel() > 1 && SceneTracker.getLevel() <= SceneTracker.getTotalLevel()) {
                    backButtonSound.start();
                    adapter.prevScene();

                }


            }
        });


       /* if(SceneTracker.getLevel()==1){
            imageButtonBack.setVisibility(View.INVISIBLE);
        }*/


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            trimCache(this);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void trimCache(Context context) {
        try {
            File dir = context.getCacheDir();
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        // The directory is now empty so delete it
        return dir.delete();
    }


}