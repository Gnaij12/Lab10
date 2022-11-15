package com.example.lab10;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

public class Tree {
    private float[] radii;
    private int[] colorSelection;
    private Paint bark = new Paint();
    private int left,top,width,height;
    private int[] x;
    private int[] y;
    private int[] colors;
    private Paint[] paints;
    private ArrayList<Integer> notChanged;
    private int size = 75;
    public Tree(int l, int t, int w, int h, int minLeafRadius, int treeRadius) {
        radii = new float[size];
        colorSelection = new int[size];
        paints = new Paint[size];
        x = new int[size];
        y = new int[size];
        notChanged = new ArrayList<Integer>();
        colors = new int[]{Color.rgb(255, 183, 197),Color.GREEN,Color.rgb(145,15,0),Color.TRANSPARENT};
        left = l;
        top = t;
        width = w;
        height = h;
        for (int i = 0;i<radii.length;i++) {
            notChanged.add(i);
            radii[i] = (float) (Math.random()*minLeafRadius+minLeafRadius);
            colorSelection[i] = 0;
            paints[i] = new Paint();
            paints[i].setColor(colors[0]);
            double r = treeRadius * Math.sqrt(Math.random());
            double theta = Math.random() * 2 * Math.PI;
            x[i] = (int) (left + width/2 + r * Math.cos(theta));
            y[i] = (int) (top - treeRadius*4/5 + r * Math.sin(theta));

        }
        bark.setColor(Color.rgb(97, 59, 22));
        Collections.shuffle(notChanged);
    }

    public Leaf update() {
        if (notChanged.isEmpty()) {
            for (int i = 0;i<radii.length;i++) {
                notChanged.add(i);
            }
            Collections.shuffle(notChanged);
        }
        int iChange = notChanged.remove(0);
        colorSelection[iChange] = (colorSelection[iChange]+1)%colors.length;

        if (colorSelection[iChange] == 3 || colorSelection[iChange] == 1) { //winter or spring, make a leaf that will fall
            Leaf leaf = new Leaf(x[iChange]-radii[iChange],y[iChange]-radii[iChange],x[iChange]+radii[iChange],
                    y[iChange]+radii[iChange], 0,5,paints[iChange].getColor(),top+height);
            paints[iChange].setColor(colors[colorSelection[iChange]]);
            return leaf;
        } else if (colorSelection[iChange] == 2) { //Fall, make different colors
            double ran = Math.random();
            if (ran <= 0.33) {
                paints[iChange].setColor(Color.rgb(208, 122, 4));
            }else if (ran <= 0.66) {
                paints[iChange].setColor(Color.rgb(148, 91, 10));
            }else {
                paints[iChange].setColor(colors[colorSelection[iChange]]);
            }
        }else {
            paints[iChange].setColor(colors[colorSelection[iChange]]);
        }
        return null;


    }
    public void drawTree(Canvas canvas) {
        canvas.drawRect(left,top,left+width,top+height,bark);
        for (int i = 0;i<radii.length;i++) {
            canvas.drawCircle(x[i],y[i],radii[i],paints[i]);
        }
    }
}
