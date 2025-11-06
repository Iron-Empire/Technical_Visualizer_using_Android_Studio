package com.example.technicalvisualizer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        Button btnSorting = findViewById(R.id.btnSorting);
        Button btnSearching = findViewById(R.id.btnSearching);
        Button btnGraph = findViewById(R.id.btnGraph);
        Button btnTree = findViewById(R.id.btnTree);

        btnSorting.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, sortingActivity.class);
            startActivity(intent);
        });

        btnSearching.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, searchingActivity.class);
            startActivity(intent);
        });

        btnGraph.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, graphActivity.class);
            startActivity(intent);
        });

        btnTree.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, treeActivity.class);
            startActivity(intent);
        });
    }
}