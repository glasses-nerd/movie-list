package com.movie;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataDisplayPanel extends JPanel {
    private static final String movieDataFile = "movies.dat";
    private static JPanel gridPanel;
    private static List<Movie> movieList = new ArrayList<>();

    public DataDisplayPanel() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton loginButton = new JButton("Login");

        topPanel.add(new JLabel("Login to add"));
        topPanel.add(loginButton);

        gridPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        JScrollPane scrollPane = new JScrollPane(gridPanel);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        loginButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) (MainPanel.getInstance().getLayout());
            cl.show(MainPanel.getInstance(), "loginPanel");
        });

        loadMovies();
        displayMovies();
    }

    public static void addMovieToGrid(Movie movie) {
        try {
            movieList.add(movie);
            saveMovies();

            JLabel picLabel = new JLabel(new ImageIcon(movie.getImg()));
            JLabel titleLabel = new JLabel(movie.getTitle());
            JLabel releaseDateLabel = new JLabel(String.valueOf(movie.getReleaseDate()));

            gridPanel.add(picLabel);
            gridPanel.add(titleLabel);
            gridPanel.add(releaseDateLabel);

            gridPanel.revalidate();
            gridPanel.repaint();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occurred while adding movie to the grid: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void saveMovies() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(movieDataFile))) {
            oos.writeObject(movieList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private static void loadMovies() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(movieDataFile))) {
            movieList = (List<Movie>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void displayMovies() {
        for (Movie movie : movieList) {
            JLabel picLabel = new JLabel(new ImageIcon(movie.getImg()));
            JLabel titleLabel = new JLabel(movie.getTitle());
            JLabel releaseDateLabel = new JLabel(String.valueOf(movie.getReleaseDate()));

            gridPanel.add(picLabel);
            gridPanel.add(titleLabel);
            gridPanel.add(releaseDateLabel);
        }
    }
}
