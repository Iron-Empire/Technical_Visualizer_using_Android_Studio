package com.example.technicalvisualizer;

public class sortingAlgorithms {
    public static String getTimeComplexity(String algorithm) {
        switch (algorithm) {
            case "Bubble Sort": return "Best: O(n) | Average: O(n²) | Worst: O(n²)";
            case "Selection Sort": return "Best: O(n²) | Average: O(n²) | Worst: O(n²)";
            case "Insertion Sort": return "Best: O(n) | Average: O(n²) | Worst: O(n²)";
            case "Quick Sort": return "Best: O(n log n) | Average: O(n log n) | Worst: O(n²)";
            case "Merge Sort": return "Best: O(n log n) | Average: O(n log n) | Worst: O(n log n)";
            case "Heap Sort": return "Best: O(n log n) | Average: O(n log n) | Worst: O(n log n)";
            default: return "N/A";
        }
    }
}
