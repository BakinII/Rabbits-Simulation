package org.example.model;

import lombok.Getter;

@Getter
public abstract class Rabbit implements IBehaviour {
    private int x;
    private int y;
    private int bornTime;
    private int id;
    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void setBornTime(int time){
        this.bornTime = time;
    }
    public void setId(int id){
        this.id = id;
    }
}
