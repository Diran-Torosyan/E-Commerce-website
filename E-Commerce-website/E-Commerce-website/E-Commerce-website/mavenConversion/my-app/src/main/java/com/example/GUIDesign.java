package com.example;

import javax.swing.SwingUtilities;

public class GUIDesign {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CreatePages var0 = new CreatePages();
            var0.setVisible(true); // Ensure the frame is visible
        });
    }
}