package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class RabbitList {
    private static volatile RabbitList instance;
    private List<Rabbit> rabbitList;

    private RabbitList(){
        rabbitList = new ArrayList<>();
    }

    public static RabbitList getInstance() {
        if (instance == null) {
            instance = new RabbitList();
        }

        return instance;
    }

    public void addRabbit(Rabbit rabbit){
        rabbitList.add(rabbit);
    }

    public List<Rabbit> getRabbitList(){
        return rabbitList;
    }
    public void clearRabbits(){
        rabbitList.clear();
    }
}
