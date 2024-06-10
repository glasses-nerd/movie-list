package com.movie;
import javax.swing.*;

public class MainFrame extends JFrame {
    private static final MainFrame INSTANCE = new MainFrame();

    private MainFrame() {
        setTitle("Movie list App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        getContentPane().add(MainPanel.getInstance());

        setVisible(true);
    }

    public static MainFrame getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::getInstance);
    }
}
