package com.example.technicalvisualizer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import java.util.Arrays;
import java.util.Random;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;


public class searchingVisualizer extends View {
    private int[] array;
    private Paint paint;
    private int width, height, delay = 100;
    private int searchingIndex = -1;

    public searchingVisualizer(Context context, AttributeSet attrs) {
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

    public void startSearching(String algorithm, int key) {
        new Thread(() -> {
            if (algorithm.equals("Binary Search")) {
                sortArray(); // Ensure the array is sorted for Binary Search
            }
            postInvalidate(); // Refresh UI before search starts
            sleep(); // Small delay before searching

            if (algorithm.equals("Binary Search")) {
                binarySearch(key);
            } else {
                linearSearch(key);
            }
        }).start();
    }

    private void sortArray() {
        Arrays.sort(array); // Sort array before Binary Search
    }


    private void linearSearch(int key) {
        boolean found = false;

        for (int i = 0; i < array.length; i++) {
            searchingIndex = i;
            postInvalidate();
            sleep();
            if (array[i] == key) {
                found = true;
                break;
            }
        }

        if (!found) {
            new Handler(Looper.getMainLooper()).post(() ->
                    Toast.makeText(getContext(), "Number not found!", Toast.LENGTH_SHORT).show()
            );
        }
    }


    private void binarySearch(int key) {
        int left = 0, right = array.length - 1;
        boolean found = false;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            searchingIndex = mid;
            postInvalidate();
            sleep();

            if (array[mid] == key) {
                found = true;
                break;
            } else if (array[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (!found) {
            new Handler(Looper.getMainLooper()).post(() ->
                    Toast.makeText(getContext(), "Number not found!", Toast.LENGTH_SHORT).show()
            );
        }
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
            paint.setColor(i == searchingIndex ? Color.RED : Color.GREEN);

            int barLeft = i * barWidth;
            int barRight = (i + 1) * barWidth;
            int barTop = height - array[i];
            int barBottom = height;

            canvas.drawRect(barLeft, barTop, barRight, barBottom, paint);
            paint.setColor(Color.WHITE);
            canvas.drawText(String.valueOf(array[i]), (barLeft + barRight) / 2, barTop - 10, paint);
        }
    }

    private void sleep() {
        try { Thread.sleep(delay); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    public void resetArray() {
        generateRandomArray();
        searchingIndex = -1;
        postInvalidate();
    }
}
