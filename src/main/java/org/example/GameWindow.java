package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {
    private final GameLogic gameLogic;
    private JLabel scoreLabel;
    private JLabel shotsLabel;
    private JButton startButton;
    private JButton stopButton;
    private JButton pauseButton;
    private JButton shootButton;

    public GameWindow() {
        gameLogic = new GameLogic(this);
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Sharp Shooter");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Game panel for drawing
        JPanel gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                gameLogic.draw(g);
            }
        };
        gamePanel.setBackground(Color.WHITE);
        add(gamePanel, BorderLayout.CENTER);

        // Control panel with buttons and labels
        JPanel controlPanel = new JPanel();
        scoreLabel = new JLabel("Счет: 0");
        shotsLabel = new JLabel("Выстрелов: 0");
        startButton = new JButton("Старт");
        stopButton = new JButton("Стоп");
        pauseButton = new JButton("Пауза");
        shootButton = new JButton("Выстрел");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameLogic.startGame();
                updateButtonState();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameLogic.stopGame();
                updateButtonState();
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameLogic.togglePause();
                updateButtonState();
            }
        });

        shootButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameLogic.shoot();
            }
        });

        controlPanel.add(scoreLabel);
        controlPanel.add(shotsLabel);
        controlPanel.add(startButton);
        controlPanel.add(stopButton);
        controlPanel.add(pauseButton);
        controlPanel.add(shootButton);
        add(controlPanel, BorderLayout.SOUTH);

        updateButtonState();
    }

    public void updateScore(int score) {
        scoreLabel.setText("Счет: " + score);
    }

    public void updateShots(int shots) {
        shotsLabel.setText("Выстрелов: " + shots);
    }

    public void updateButtonState() {
        boolean isRunning = gameLogic.isRunning();
        boolean isPaused = gameLogic.isPaused();
        startButton.setEnabled(!isRunning);
        stopButton.setEnabled(isRunning);
        pauseButton.setEnabled(isRunning);
        shootButton.setEnabled(isRunning && !isPaused);
    }

    public void repaintGame() {
        repaint();
    }
}