package com.example.root.dragndroprecyclerviewapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }
    public void  startGame(View view){

        SceneTracker.setCorrectedItem(0);
        SceneTracker.setWrongItem(0);
        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void exitActivity(View view){
        this.finishAffinity();
    }

    @Override
    public void onBackPressed() {
        this.finishAffinity();
    }
}
