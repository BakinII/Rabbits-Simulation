package org.example.model;

import lombok.Getter;

@Getter
public abstract class Rabbit implements IBehaviour {
    private int x;
    private int y;

    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
