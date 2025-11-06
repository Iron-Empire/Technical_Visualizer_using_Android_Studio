package com.example.technicalvisualizer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


public class treeVisualizer extends View {
    private class Node {
        int value;
        Node left, right;
        int x, y;
        Node(int value, int x, int y) {
            this.value = value;
            this.x = x;
            this.y = y;
        }
    }

    private Node root;
    private Paint nodePaint, linePaint;
    private int width, height;

    public treeVisualizer(Context context, AttributeSet attrs) {
        super(context, attrs);
        nodePaint = new Paint();
        linePaint = new Paint();
        root = null;
    }

    public void insertNode(int value) {
        root = insert(root, value, getWidth() / 2, 100, 200);
        postInvalidate();
    }

    private Node insert(Node node, int value, int x, int y, int offset) {
        if (node == null) return new Node(value, x, y);

        if (value < node.value) node.left = insert(node.left, value, x - offset, y + 100, offset / 2);
        else node.right = insert(node.right, value, x + offset, y + 100, offset / 2);

        return node;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getWidth();
        height = getHeight();
        canvas.drawColor(Color.BLACK);
        linePaint.setColor(Color.WHITE);
        nodePaint.setColor(Color.GREEN);
        drawTree(canvas, root);
    }

    private void drawTree(Canvas canvas, Node node) {
        if (node == null) return;

        if (node.left != null)
            canvas.drawLine(node.x, node.y, node.left.x, node.left.y, linePaint);
        if (node.right != null)
            canvas.drawLine(node.x, node.y, node.right.x, node.right.y, linePaint);

        canvas.drawCircle(node.x, node.y, 30, nodePaint);
        nodePaint.setTextSize(30);
        canvas.drawText(String.valueOf(node.value), node.x - 15, node.y + 10, nodePaint);

        drawTree(canvas, node.left);
        drawTree(canvas, node.right);
    }
}
