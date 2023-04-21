package org.example.view;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class PhotoFactory {
    private static final String RABBIT_CLASSIC_PHOTO_PATH = "src\\main\\resources\\rabbit-classic.png";
    private static final String RABBIT_ALBINO_PHOTO_PATH = "src\\main\\resources\\rabbit-albino.png";
    private static final String BACKGROUND_PHOTO_PATH = "src\\main\\resources\\trava.png";
    // private static final String BACKGROUND_PHOTO_PATH = "D:\\TMP\\3lab\\Rabbits-Simulation-main\\src\\main\\resources\\trava.png";
    //private static final String RABBIT_CLASSIC_PHOTO_PATH = "D:\\TMP\\3lab\\Rabbits-Simulation-main\\src\\main\\resources\\rabbit-classic.png";
    // private static final String RABBIT_ALBINO_PHOTO_PATH = "D:\\TMP\\3lab\\Rabbits-Simulation-main\\src\\main\\resources\\rabbit-albino.png";

    private static final String MISATA_PHOTO_PATH = "src\\main\\resources\\misata.jpg";

    private final Image photoRabbitClassic;
    private final Image photoRabbitAlbino;
    private final Image photoBackground;

    private final Image photoMisata;

    public PhotoFactory() {
        this.photoRabbitClassic = new ImageIcon(RABBIT_CLASSIC_PHOTO_PATH).getImage();
        this.photoRabbitAlbino = new ImageIcon(RABBIT_ALBINO_PHOTO_PATH).getImage();
        this.photoBackground = new ImageIcon(BACKGROUND_PHOTO_PATH).getImage();
        this.photoMisata = new ImageIcon(MISATA_PHOTO_PATH).getImage();
    }
}
