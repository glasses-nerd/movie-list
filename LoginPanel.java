package com.movie;
import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginPanel() {
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton loginButton = new JButton("Login");
        add(loginButton);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.equals("someone") && password.equals("12345")) {
                CardLayout cl = (CardLayout) (MainPanel.getInstance().getLayout());
                cl.show(MainPanel.getInstance(), "dataEntryPanel");
            } else {
                JOptionPane.showMessageDialog(MainFrame.getInstance(), "Invalid login. Please try again.");
            }
        });
    }
}
