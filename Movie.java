package com.movie;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Movie implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private transient Image img;
    private int releaseDate;

    public Movie(String title, Image img, int releaseDate) {
        this.title = title;
        this.img = img;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public Image getImg() {
        return img;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        
        if (img instanceof BufferedImage) {
            ImageIO.write((BufferedImage) img, "png", out);
        } else {
            BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            Graphics g = bufferedImage.createGraphics();
            g.drawImage(img, 0, 0, null);
            g.dispose();
            ImageIO.write(bufferedImage, "png", out);
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        img = ImageIO.read(in);
    }
}
