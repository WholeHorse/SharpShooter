package org.example;

import java.awt.*;

public class Target {
    private int x, y;
    private int size;
    private int speed;
    private String type;
    private int direction = 1; // 1 for down, -1 for up

    public Target(int x, int y, int size, int speed, String type) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.speed = speed;
        this.type = type;
    }

    public void move() {
        y += speed * direction;
        if (y > 500 - size / 2 || y < 50 + size / 2) { // Bounce at top (50) and bottom (500) of the screen
            direction *= -1; // Reverse direction
        }
    }

    public boolean contains(int px, int py) {
        return px >= x - size / 2 && px <= x + size / 2 &&
                py >= y - size / 2 && py <= y + size / 2;
    }

    public void draw(Graphics g) {
        g.setColor(type.equals("Big") ? Color.RED : Color.PINK);
        g.fillOval(x - size / 2, y - size / 2, size, size);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}