package com.example.technicalvisualizer;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class treeActivity extends AppCompatActivity {

    treeVisualizer treeVisualizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tree);

        Button btnInsertNode = findViewById(R.id.btnInsertNode);
        treeVisualizer = findViewById(R.id.treeVisualizer);

        btnInsertNode.setOnClickListener(v -> {
            Random random = new Random();
            treeVisualizer.insertNode(random.nextInt(100)); // Call it on the instance
        });

    }
}