package com.example.root.dragndroprecyclerviewapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private int coloumn;

    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.binding_list_activity);

        coloumn = 3;
        recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(coloumn, 1));

        User user1 = new User("apple", "file:///android_asset/apple.png");
        User user2 = new User("bear", "file:///android_asset/bear.png");
        User user3 = new User("plant", "file:///android_asset/plant.png");

        User user4 = new User("apple", "file:///android_asset/apple_shadow.png");
        User user5 = new User("bear", "file:///android_asset/bear_shadow.png");
        User user6 = new User("plant", "file:///android_asset/plant_shadow.png");


        ArrayList<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

       userList.add(user4);
        userList.add(user5);
        userList.add(user6);

        adapter = new UserAdapter(userList);
        recyclerView.setAdapter(adapter);
    }
}