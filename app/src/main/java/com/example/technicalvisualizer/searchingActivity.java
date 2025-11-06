package com.example.technicalvisualizer;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class searchingActivity extends AppCompatActivity {
    private searchingVisualizer visualizer;
    private String selectedAlgorithm = "Linear Search";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);

        visualizer = findViewById(R.id.searchingVisualizer);
        Button startBtn = findViewById(R.id.startSearchingBtn);
        EditText searchInput = findViewById(R.id.searchKey);
        Spinner searchSpinner = findViewById(R.id.searchingAlgorithmSpinner);

        // Dropdown menu for selecting search algorithms
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.searching_algorithms, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchSpinner.setAdapter(adapter);

        searchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedAlgorithm = parent.getItemAtPosition(position).toString();
                visualizer.resetArray();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        startBtn.setOnClickListener(v -> {
            int key = Integer.parseInt(searchInput.getText().toString());
            visualizer.startSearching(selectedAlgorithm, key);
        });
    }
}
