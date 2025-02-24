package org.example;

import java.awt.*;

public class GameLogic {
    private final GameWindow gameWindow;
    private Thread animationThread;
    private boolean running;
    private boolean paused;
    private int score;
    private int shots;
    private Target bigTarget;
    private Target smallTarget;
    private Arrow arrow;

    public GameLogic(GameWindow window) {
        this.gameWindow = window;
        initializeGame();
    }

    private void initializeGame() {
        running = false;
        paused = false;
        score = 0;
        shots = 0;
        // Start targets from the right side, moving vertically
        bigTarget = new Target(500, 250, 50, 2, "Big");
        smallTarget = new Target(600, 200, 30, 4, "Small");
        arrow = null;
    }

    public void startGame() {
        if (!running) {
            running = true;
            paused = false;
            score = 0;
            shots = 0;
            updateUI();
            animationThread = new Thread(() -> {
                while (running) {
                    if (!paused) {
                        updateGame();
                        gameWindow.repaintGame();
                    }
                    try {
                        Thread.sleep(16); // ~60 FPS
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            });
            animationThread.start();
        }
    }

    public void stopGame() {
        running = false;
        if (animationThread != null) {
            animationThread.interrupt();
        }
        initializeGame();
        updateUI();
    }

    public void togglePause() {
        if (running) {
            paused = !paused;
            updateUI();
        }
    }

    public void shoot() {
        if (running && !paused && arrow == null) {
            shots++;
            arrow = new Arrow(50, 250); // Start from left side, middle height
            updateUI();
        }
    }

    private void updateGame() {
        bigTarget.move();
        smallTarget.move();
        if (arrow != null) {
            arrow.move();
            checkCollision();
            if (arrow != null && arrow.isOutOfBounds()) {
                arrow = null;
            }
        }
    }

    private void checkCollision() {
        if (arrow != null) {
            if (bigTarget.contains(arrow.getX(), arrow.getY())) {
                score += 1;
                arrow = null;
                updateUI();
            } else if (smallTarget.contains(arrow.getX(), arrow.getY())) {
                score += 2;
                arrow = null;
                updateUI();
            }
        }
    }

    public void draw(Graphics g) {
        // Draw the yellow background strip for the player area
        g.setColor(Color.YELLOW);
        g.fillRect(0, 0, 100, 600);

        // Draw the player as a blue triangle on the left side
        int playerX = 50; // Left side
        int playerY = 250; // Middle height
        int[] xPoints = {playerX, playerX + 40, playerX};
        int[] yPoints = {playerY - 20, playerY, playerY + 20};
        g.setColor(Color.BLUE);
        g.fillPolygon(xPoints, yPoints, 3);

        bigTarget.draw(g);
        smallTarget.draw(g);

        if (arrow != null) {
            arrow.draw(g);
        }
    }

    private void updateUI() {
        gameWindow.updateScore(score);
        gameWindow.updateShots(shots);
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isPaused() {
        return paused;
    }
}