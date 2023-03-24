package org.example.view.util;

import org.example.model.Rabbit;
import org.example.model.RabbitAlbino;
import org.example.model.RabbitClassic;
import org.example.view.PhotoFactory;

import java.awt.*;

public class RabbitImageConverter {
    private final PhotoFactory photoFactory;

    public RabbitImageConverter(PhotoFactory photoFactory) {
        this.photoFactory = photoFactory;
    }

    public Image convert(Rabbit rabbit) {
        if (RabbitClassic.class == rabbit.getClass()) {
            return photoFactory.getPhotoRabbitClassic();
        }
        if (RabbitAlbino.class == rabbit.getClass()) {
            return photoFactory.getPhotoRabbitAlbino();
        }
        return null;
    }
}
