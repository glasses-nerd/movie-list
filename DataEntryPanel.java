package com.movie;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DataEntryPanel extends JPanel {
    private JTextField titleField;
    private JTextField imagePathField;
    private JTextField releaseDateField;

    public DataEntryPanel() {
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Title:"));
        titleField = new JTextField();
        add(titleField);

        add(new JLabel("Image Path:"));
        imagePathField = new JTextField();
        add(imagePathField);

        add(new JLabel("Release Date:"));
        releaseDateField = new JTextField();
        add(releaseDateField);

        JButton submitButton = new JButton("Submit");
        add(submitButton);

        submitButton.addActionListener(e -> {
            try {
                String title = titleField.getText();
                String imagePath = imagePathField.getText();
                int releaseDate = Integer.parseInt(releaseDateField.getText());

                Image img = loadImage(imagePath);

                Movie movie = new Movie(title, img, releaseDate);

                DataDisplayPanel.addMovieToGrid(movie);

                CardLayout cl = (CardLayout) (MainPanel.getInstance().getLayout());
                cl.show(MainPanel.getInstance(), "dataDisplayPanel");
            } catch (NumberFormatException | IOException ex) {
                JOptionPane.showMessageDialog(MainFrame.getInstance(), "An error occurred. Please check your input.");
                ex.printStackTrace();
            }
        });
    }

    private Image loadImage(String imagePath) throws IOException {
        BufferedImage img = ImageIO.read(new File(imagePath));
        return img;
    }
}
