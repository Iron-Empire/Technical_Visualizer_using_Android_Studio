package com.example.technicalvisualizer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class graphVisualizer extends View {
    private List<int[]> edges;
    private int[] nodes;
    private Paint nodePaint, edgePaint;
    private boolean[] visited;

    public graphVisualizer(Context context, AttributeSet attrs) {
        super(context, attrs);
        nodePaint = new Paint();
        edgePaint = new Paint();
        edges = new ArrayList<>();
        nodes = new int[6]; // Example graph with 6 nodes
        visited = new boolean[nodes.length];
        initializeGraph();
    }

    private void initializeGraph() {
        edges.add(new int[]{0, 1});
        edges.add(new int[]{1, 2});
        edges.add(new int[]{2, 3});
        edges.add(new int[]{3, 4});
        edges.add(new int[]{4, 5});
    }

    public void startDFS(int startNode) {
        new Thread(() -> {
            dfs(startNode);
            postInvalidate();
        }).start();
    }

    private void dfs(int node) {
        visited[node] = true;
        postInvalidate();
        try { Thread.sleep(500); } catch (InterruptedException e) {}

        for (int[] edge : edges) {
            int neighbor = edge[1];
            if (edge[0] == node && !visited[neighbor]) {
                dfs(neighbor);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        edgePaint.setColor(Color.WHITE);
        nodePaint.setColor(Color.GREEN);

        for (int[] edge : edges) {
            canvas.drawLine(100 + edge[0] * 100, 200, 100 + edge[1] * 100, 200, edgePaint);
        }

        for (int i = 0; i < nodes.length; i++) {
            if (visited[i]) nodePaint.setColor(Color.RED);
            else nodePaint.setColor(Color.GREEN);
            canvas.drawCircle(100 + i * 100, 200, 30, nodePaint);
        }
    }
}
