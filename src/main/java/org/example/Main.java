package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow();
        gameWindow.setResizable(false);
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setIconImage(new ImageIcon("bow-and-arrow.png").getImage());
        gameWindow.setVisible(true);
    }
}