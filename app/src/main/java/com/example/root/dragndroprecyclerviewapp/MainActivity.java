package com.example.root.dragndroprecyclerviewapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private int coloumn;
    private JsonHandler jsonHandler;

    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.binding_list_activity);
        int level=1;
        coloumn = 3;

        recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(coloumn, 1));


        jsonHandler = new JsonHandler(this);
        jsonHandler.readJson();

       SceneTracker.setLevel(level);
        adapter = new UserAdapter(jsonHandler.getSceneData(level-1),this);
        recyclerView.setAdapter(adapter);


    }
}