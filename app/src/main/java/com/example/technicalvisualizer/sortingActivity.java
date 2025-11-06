package com.example.technicalvisualizer;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class sortingActivity extends AppCompatActivity {
    private sortingVisualizer visualizer;
    private TextView complexityView;
    private String selectedAlgorithm = "Bubble Sort";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorting);

        visualizer = findViewById(R.id.sortingVisualizer);
        complexityView = findViewById(R.id.complexityView);
        Button startBtn = findViewById(R.id.startSortingBtn);
        Spinner algorithmSpinner = findViewById(R.id.algorithmSpinner);

        // Dropdown menu with sorting options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.sorting_algorithms, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        algorithmSpinner.setAdapter(adapter);

        algorithmSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedAlgorithm = parent.getItemAtPosition(position).toString();
                updateComplexityText();
                visualizer.resetArray();  // Reset array whenever a new algorithm is selected
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        startBtn.setOnClickListener(v -> visualizer.startSorting(selectedAlgorithm));
    }

    private void updateComplexityText() {
        String complexity = sortingAlgorithms.getTimeComplexity(selectedAlgorithm);
        complexityView.setText(complexity);
    }
}
