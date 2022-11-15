package com.example.lab10;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Leaf extends RectF {
    private int dx,dy,color,bottree;
    public Leaf(float left, float top, float right, float bottom, int dx, int dy, int color,int bottree) {
        super(left, top, right, bottom);
        this.dx = dx;
        this.dy = dy;
        this.color = color;
        this.bottree = bottree;
    }

    public Leaf(float left, float top, float right, float bottom) {
        this(left, top, right, bottom,0,5, Color.MAGENTA,-1);
    }

    public Leaf(int dx, int dy, int color) {
        this(1,1,111,111,dx,dy,color,-1);
    }

    public Leaf() {
        this(0,5,Color.GREEN);
    }

    public void update(boolean intersect) {
        if (intersect) {
            dy = 0;
        }
        if (centerY() + width()/2 + dy <=bottree) {
            offset(dx,dy);
            dx = 0;
            dy = 5;
        }
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawCircle(centerX(),centerY(),width()/2,paint);
    }
    public boolean onTop(Leaf leaf) {
        double dis = Math.sqrt(Math.pow(centerX()-leaf.centerX(),2) + Math.pow(centerY()-leaf.centerY(),2)*4);
        if (centerY() < leaf.centerY() && (dis < Math.abs(width()/2 -leaf.width()/2) || dis < width()/2 + leaf.width()/2)) {
            if (centerX() >= leaf.centerX()) {
                if (dx == -3) {
                    dx = 0;
                }else {
                    dx = 3;
                }
            }else {
                if (dx == 3) {
                    dx = 0;
                }else {
                    dx = -3;
                }
            }
            return true;
        }
        return false;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
