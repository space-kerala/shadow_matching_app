package com.example.root.dragndroprecyclerviewapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LastActivity extends AppCompatActivity {

    public static TextView right,wrong,levelText,accuracy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);
        right = (TextView) findViewById(R.id.rightNo_id);
        wrong = (TextView) findViewById(R.id.wrongNo_id);
        levelText = (TextView) findViewById(R.id.levelNo_id) ;
        accuracy = (TextView)findViewById(R.id.accuracyNo_id) ;

        wrong.setText(String.valueOf(SceneTracker.getWrongItem()));
        right.setText(String.valueOf(SceneTracker.getCorrectedItem()));
        int a = SceneTracker.getWrongItem();
        int b = SceneTracker.getCorrectedItem();
        int c = a+b;
        int x = (int) (((double) b / (double) c) * 100);
        accuracy.setText(String.valueOf(x));


    }
    public void restartActivity(View view)
    {
        SceneTracker.setCorrectedItem(0);
        SceneTracker.setWrongItem(0);
        SceneTracker.setCount(0);
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
