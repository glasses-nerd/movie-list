package com.movie;
import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private static final MainPanel INSTANCE = new MainPanel();
    private CardLayout cardLayout;

    private MainPanel() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        add(new DataDisplayPanel(), "dataDisplayPanel");
        add(new LoginPanel(), "loginPanel");
        add(new DataEntryPanel(), "dataEntryPanel");
    }

    public static MainPanel getInstance() {
        return INSTANCE;
    }
}

