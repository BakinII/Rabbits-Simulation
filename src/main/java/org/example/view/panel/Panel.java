package org.example.view.panel;

import lombok.Getter;
import lombok.Setter;
import org.example.model.Rabbit;
import org.example.model.RabbitList;
import org.example.view.PhotoFactory;
import org.example.view.util.RabbitImageConverter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Panel extends JPanel {
    //private List<Rabbit> rabbitList;

    private RabbitList rabbitList;

    private PhotoFactory photoFactory;
    private RabbitImageConverter rabbitImageConverter;
    private int quantityRabbitClassic;
    private int quantityRabbitAlbino;

    private double time;
    @Getter
    @Setter
    private boolean showTime;
    @Getter
    @Setter
    private boolean simulationInformation;

    public Panel() {

        this.photoFactory = new PhotoFactory();
        this.rabbitImageConverter = new RabbitImageConverter(photoFactory);

        this.time = 0.0;
        this.showTime = false;
        this.simulationInformation = false;

        this.quantityRabbitClassic = 0;
        this.quantityRabbitAlbino = 0;


    }

    public void paint(Graphics g) {
        g = (Graphics2D) g;
        g.drawImage(photoFactory.getPhotoBackground(), 0, 0, null);

        for (Rabbit rabbit : RabbitList.getInstance().getRabbitList()) {
            g.drawImage(rabbitImageConverter.convert(rabbit), rabbit.getX(), rabbit.getY(), null);
        }

        Font font = new Font("Arial", Font.BOLD, 20);
        g.setFont(font);
        if (showTime) {
            g.drawString("Прошедшее время " + Integer.toString((int) time), 500, 500);
        }

        if (simulationInformation) {
            Font font1 = new Font("Arial", Font.BOLD, 30);
            g.setFont(font1);
            g.drawString("Прошедшее время " + Integer.toString((int) time), 100, 50);
            g.drawString("Обычных кроликов появилось " + Integer.toString(quantityRabbitClassic), 100, 150);
            Color color = Color.RED;
            g.setColor(color);
            g.drawString("Кроликов альбиносов появилось " + Integer.toString(quantityRabbitAlbino), 100, 250);
        }

    }


    public void updateTime(double time) {
        this.time = time;
        repaint();
    }

    public void updateQuantityRabbits(int quantityRabbitAlbino, int quantityRabbitClassic) {
        this.quantityRabbitAlbino = quantityRabbitAlbino;
        this.quantityRabbitClassic = quantityRabbitClassic;
        repaint();
    }
}
