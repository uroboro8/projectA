package com.example.recyclerview_try;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.SlideInDownAnimator;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Horizontal Recycler View
        RecyclerView myRw = findViewById(R.id.recyclerView);
        ArrayList<String> data = initData(500);

        ItemAdapter adapter = new ItemAdapter(data);
        adapter.setLayout(R.layout.item_row_horizontal);
        myRw.setAdapter(adapter);

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        myRw.setLayoutManager(horizontalLayoutManager);

        /*
        ////Vertical Recycler View
        RecyclerView myRw2 = findViewById(R.id.recyclerView2);
        ArrayList<String> data1 = initData(200);
        ItemAdapter adapter1 = new ItemAdapter(data1);
        adapter1.setLayout(R.layout.item_row_vertical);
        myRw2.setAdapter(adapter1);
        myRw2.setLayoutManager(new LinearLayoutManager(this));
        */

        ///Grid Recycler View
        RecyclerView myRw2 = findViewById(R.id.recyclerView2);
        ArrayList<String> data1 = initData(200);
        ItemAdapter adapter1 = new ItemAdapter(data1);
        adapter1.setLayout(R.layout.grid_row);
        myRw2.setAdapter(adapter1);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false);
        myRw2.setLayoutManager(gridLayoutManager);


    }

    private ArrayList<String> initData(int max) {
        ArrayList<String> list = new ArrayList<>();
        for(int i=0;i<max;i++)
            list.add("item"+i);
        return list;
    }


}