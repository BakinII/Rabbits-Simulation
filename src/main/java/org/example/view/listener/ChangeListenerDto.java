package org.example.view.listener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.model.Rabbit;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ChangeListenerDto {

    private double time;
    private int quantityRabbitClassic;
    private int quantityRabbitAlbino;

}
