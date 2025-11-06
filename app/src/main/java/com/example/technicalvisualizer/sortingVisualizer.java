package com.example.technicalvisualizer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import java.util.Random;

public class sortingVisualizer extends View {
    private int[] array;
    private Paint paint;
    private int width, height, delay = 100;
    private boolean isPaused = false;
    private int swappingIndex1 = -1, swappingIndex2 = -1;

    public sortingVisualizer(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        generateRandomArray();
    }

    private void generateRandomArray() {
        Random random = new Random();
        array = new int[10];
        for (int i = 0; i < 10; i++) {
            array[i] = random.nextInt(500);
        }
        postInvalidate();
    }

    public void resetArray() {
        generateRandomArray(); // Generate a new random array
        isPaused = false; // Resume sorting if paused
        postInvalidate(); // Redraw the view
    }


    public void startSorting(String algorithm) {
        new Thread(() -> {
            switch (algorithm) {
                case "Bubble Sort": bubbleSort(); break;
                case "Selection Sort": selectionSort(); break;
                case "Insertion Sort": insertionSort(); break;
                case "Quick Sort": quickSort(0, array.length - 1); break;
                case "Merge Sort": mergeSort(0, array.length - 1); break;
                case "Heap Sort": heapSort(); break;
            }
        }).start();
    }

    private void bubbleSort() {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                swappingIndex1 = j;
                swappingIndex2 = j + 1;
                if (array[j] > array[j + 1]) {
                    swap(j, j + 1);
                }
                postInvalidate();
                sleep();
            }
        }
    }

    private void selectionSort() {
        for (int i = 0; i < array.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIdx]) {
                    minIdx = j;
                }
            }
            swap(i, minIdx);
            postInvalidate();
            sleep();
        }
    }

    private void insertionSort() {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
            postInvalidate();
            sleep();
        }
    }

    private void quickSort(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);
            quickSort(low, pi - 1);
            quickSort(pi + 1, high);
        }
        postInvalidate();
        sleep();
    }

    private int partition(int low, int high) {
        int pivot = array[high], i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    private void mergeSort(int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(left, mid);
            mergeSort(mid + 1, right);

            merge(left, mid, right);
            postInvalidate();
            sleep();
        }
    }

    // Merge two halves of the array
    private void merge(int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(array, left, L, 0, n1);
        System.arraycopy(array, mid + 1, R, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k++] = L[i++];
            } else {
                array[k++] = R[j++];
            }
            postInvalidate();
            sleep();
        }

        while (i < n1) {
            array[k++] = L[i++];
            postInvalidate();
            sleep();
        }

        while (j < n2) {
            array[k++] = R[j++];
            postInvalidate();
            sleep();
        }
    }


    private void heapSort() {
        int n = array.length;

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(n, i);
        }

        // Extract elements from heap
        for (int i = n - 1; i > 0; i--) {
            swap(0, i);
            heapify(i, 0);
            postInvalidate();
            sleep();
        }
    }

    // Heapify subtree rooted at index i
    private void heapify(int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && array[left] > array[largest]) {
            largest = left;
        }

        if (right < n && array[right] > array[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(i, largest);
            postInvalidate();
            sleep();
            heapify(n, largest);
        }
    }


    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void sleep() {
        try { Thread.sleep(delay); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getWidth();
        height = getHeight();
        canvas.drawColor(Color.BLACK);
        int barWidth = width / array.length;

        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);

        for (int i = 0; i < array.length; i++) {
            // Change color if the element is being swapped
            if (i == swappingIndex1 || i == swappingIndex2) {
                paint.setColor(Color.RED);
            } else {
                paint.setColor(Color.GREEN);
            }

            // Draw bar
            int barLeft = i * barWidth;
            int barRight = (i + 1) * barWidth;
            int barTop = height - array[i];
            int barBottom = height;

            canvas.drawRect(barLeft, barTop, barRight, barBottom, paint);

            // Draw number (array value) on the bar
            paint.setColor(Color.WHITE);
            canvas.drawText(String.valueOf(array[i]), (barLeft + barRight) / 2, barTop - 10, paint);
        }
    }

}
