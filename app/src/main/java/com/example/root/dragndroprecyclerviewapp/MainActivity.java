package com.example.root.dragndroprecyclerviewapp;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.renderscript.Sampler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //private RecyclerView recyclerView;
    private RecyclerViewEmptySupport recyclerViewEmptySupport;
    private int coloumn = 3;
    private JsonHandler jsonHandler;
    private ImageButton imageButtonBack;
    private ItemAdapter adapter;
    private MediaPlayer backButtonSound;
    private File f1;
    private static int permission,permission1;
    private int level = 1;
    public static TextView score,right,wrong,levelText;
    public static Button nextButton;

    private String folder_main = "shadow";
    private ArrayList<Item> itemTemp ;
    private ProgressDialog progressDoalog;
    private  Handler handle;



    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        permission1 = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.binding_list_activity);
        jsonHandler = new JsonHandler();
       // score = (TextView)findViewById(R.id.score);
        right = (TextView) findViewById(R.id.rightNo_id);
        wrong = (TextView) findViewById(R.id.wrongNo_id);
        levelText = (TextView) findViewById(R.id.levelNo_id) ;
        nextButton = (Button)findViewById(R.id.next_button);
        nextButton.setVisibility(View.GONE);
        SceneTracker.setLevel(level);
        verifyStoragePermissions(MainActivity.this);

        recyclerViewEmptySupport = (RecyclerViewEmptySupport) findViewById(R.id.list);
        recyclerViewEmptySupport.setLayoutManager(new StaggeredGridLayoutManager(coloumn, 1));
        recyclerViewEmptySupport.setEmptyView(findViewById(R.id.list_empty));
        recyclerViewEmptySupport.setButtonView(findViewById(R.id.reload));
      //  recyclerViewEmptySupport.setScore(findViewById(R.id.score));
        recyclerViewEmptySupport.setRight(findViewById(R.id.rightNo_id));
        recyclerViewEmptySupport.setWrong(findViewById(R.id.wrongNo_id));
        recyclerViewEmptySupport.setRightText(findViewById(R.id.right_id));
        recyclerViewEmptySupport.setWrongText(findViewById(R.id.wrong_id));
        recyclerViewEmptySupport.setButtonExitView(findViewById(R.id.exit_id));






      // itemTemp = new ArrayList<>();



        backButtonSound = MediaPlayer.create(this, R.raw.buttonclick);
        imageButtonBack = (ImageButton) findViewById(R.id.imagebuttonback);
        imageButtonBack.setVisibility(View.VISIBLE);

        if (SceneTracker.getLevel() == 2) {
            imageButtonBack.setVisibility(View.VISIBLE);
        }

        /*recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(coloumn, 1));
        recyclerView.setAdapter(adapter)*/;
      ////

        if((permission == PackageManager.PERMISSION_GRANTED)&&(permission1==PackageManager.PERMISSION_GRANTED)) {


            SceneTracker.setFlag(1);
            makeDir();
           // CopyAssets();
            loadJson();
           // Log.d("Right",String.valueOf(SceneTracker.getCorrectedItem()));

            imageButtonBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (SceneTracker.getLevel() > 1 && SceneTracker.getLevel() <= SceneTracker.getTotalLevel()) {


                        backButtonSound.start();
                        adapter.prevScene();

                    }


                }
            });

        }
        else
        {
            verifyStoragePermissions(this);
           // loading();


        }

    }




  public void loadJson()  {

      jsonHandler = new JsonHandler();
      jsonHandler.readJson();
     // itemTemp=jsonHandler.getSceneData(level - 1);
      adapter = new ItemAdapter(jsonHandler.getSceneData(level - 1), MainActivity.this);
//      score.setText(String.valueOf(SceneTracker.getScore()));
     // right.setText(String.valueOf(SceneTracker.getCorrectedItem()));
     // wrong.setText(String.valueOf(SceneTracker.getWrongItem()));
      recyclerViewEmptySupport.setAdapter(adapter);





    }



    private void makeDir()
    {

        f1 = new File(Environment.getExternalStorageDirectory(), folder_main);
        if (!f1.exists()) {
            f1.mkdirs();
        }

    }
    public void restartActivity(View view)
    {
        SceneTracker.setWrongItem(0);
        SceneTracker.setCorrectedItem(0);
        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void exitActivity(View view){
        this.finishAffinity();
    }


    public void goHome(View view){
        Intent intent= new Intent(this, StartActivity.class);
        startActivity(intent);

    }



/*    private void CopyAssets() {
        AssetManager assetManager = getAssets();
        String[] files = null;
        try {
            files = assetManager.list("");
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }

        for(String filename : files) {
            System.out.println("File name => "+filename);
            InputStream in = null;
            OutputStream out = null;
            try {
                in = assetManager.open("" +filename);   // if files resides inside the "Files" directory itself
                out = new FileOutputStream(Environment.getExternalStorageDirectory().toString() +"/shadow/"+ filename);
                Log.d("TagPath",Environment.getExternalStorageDirectory().toString());
                copyFile(in, out);
                in.close();
                in = null;
                out.flush();
                out.close();
                out = null;
            } catch(Exception e) {
                Log.e("tag", e.getMessage());
            }
        }
    }*/

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }







    @Override
    @NonNull
    public void onStart()
    {
        super.onStart();
    }


    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public void onResume(){
        super.onResume();
    }



}