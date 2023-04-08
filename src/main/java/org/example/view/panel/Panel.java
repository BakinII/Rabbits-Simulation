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
    private PhotoFactory photoFactory;
    private RabbitImageConverter rabbitImageConverter;

    public Panel() {
        this.photoFactory = new PhotoFactory();
        this.rabbitImageConverter = new RabbitImageConverter(photoFactory);
    }

    public void paint(Graphics g) {
        g = (Graphics2D) g;
        g.drawImage(photoFactory.getPhotoBackground(), 0, 0, null);

        for (Rabbit rabbit : RabbitList.getInstance().getRabbitList()) {
            g.drawImage(rabbitImageConverter.convert(rabbit), rabbit.getX(), rabbit.getY(), null);
        }
    }
}
