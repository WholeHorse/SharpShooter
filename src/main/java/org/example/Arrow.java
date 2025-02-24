package org.example;

import java.awt.*;

public class Arrow {
    private int x, y;
    private static final int SPEED = 10;
    private static final int MAX_X = 800; // Screen width

    public Arrow(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        x += SPEED;
    }

    public boolean isOutOfBounds() {
        return x > MAX_X;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillPolygon(new int[]{x, x + 10, x}, new int[]{y - 5, y, y + 5}, 3);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}