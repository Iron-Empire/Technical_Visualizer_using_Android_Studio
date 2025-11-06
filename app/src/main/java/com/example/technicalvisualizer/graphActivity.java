package com.example.technicalvisualizer;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class graphActivity extends AppCompatActivity {

    graphVisualizer graphVisualizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_graph);

        graphVisualizer = findViewById(R.id.graphVisualizer);

        Button btnStartDFS = findViewById(R.id.btnStartDFS);
        btnStartDFS.setOnClickListener(v -> graphVisualizer.startDFS(0)); // Call it on the instance
    }
}