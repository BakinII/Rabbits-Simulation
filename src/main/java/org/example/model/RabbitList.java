package org.example.model;

import java.util.*;

public class RabbitList {
    private static volatile RabbitList instance;
    //private List<Rabbit> rabbitList;
    private Vector<Rabbit> rabbitList;
    private TreeSet<Integer> rabbitID;
    //private TreeSet<Rabbit> rabbitTree;
    private HashMap<Integer, Integer> rabbitBornTime;

    private RabbitList() {
        rabbitList = new Vector<>();
        rabbitID = new TreeSet<>();
        rabbitBornTime = new HashMap<>();
        //rabbitTree = new TreeSet<>();
    }

    public static RabbitList getInstance() {
        if (instance == null) {
            instance = new RabbitList();
        }

        return instance;
    }

    public void addRabbit(Rabbit rabbit) {
        rabbitList.add(rabbit);
        rabbitID.add(rabbit.getId());
        rabbitBornTime.put(rabbit.getId(), rabbit.getBornTime());
        //rabbitTree.add(rabbit);
    }

    public Vector<Rabbit> getRabbitList() {
        return rabbitList;
    }

    //public List<Rabbit> getRabbitList(){
    //    return rabbitList;
    //}
    public boolean checkId(int id) {
        return !rabbitID.contains(id);
    }

    public void clearRabbits() {
        rabbitList.clear();
        rabbitID.clear();
        rabbitBornTime.clear();
    }
    public void checkRabbitLifeTime(int rabbitAlbinoLifeTime,int rabbitClassicLifeTime, int time){
        Vector<Integer> rabbitToDelete = new Vector<>();
        for(Map.Entry<Integer,Integer> entry : rabbitBornTime.entrySet()){
            Integer key = entry.getKey();
            Integer value = entry.getValue();

            boolean rabbitType =true;

            for (Iterator<Rabbit> it = rabbitList.iterator();it.hasNext();){
                Rabbit rabbit = it.next();
                if (rabbit.getId() == key) {
                    if(rabbit.getClass() == RabbitAlbino.class){
                        rabbitType = false;
                    }
                    if(rabbit.getClass() == RabbitClassic.class){
                        rabbitType = true;
                    }
                    break;
                }
            }

            if(value + rabbitAlbinoLifeTime <= time && !rabbitType){
                rabbitToDelete.add(key);
            }

            if(value + rabbitClassicLifeTime <= time && rabbitType){
                rabbitToDelete.add(key);
            }
        }
        rabbitToDelete.forEach(this::deleteRabbit);
        rabbitToDelete.clear();
    }
    
    private void deleteRabbit(int rabbitRemoveId){
        rabbitBornTime.remove(rabbitRemoveId);
        rabbitID.remove(rabbitRemoveId);
        for (Iterator<Rabbit> it = rabbitList.iterator();it.hasNext();){
            Rabbit rabbit = it.next();
            if (rabbit.getId() == rabbitRemoveId) {
                it.remove();
                break;
            }
        }
    }
    public HashMap<Integer,Integer> getHashMapRabbit(){
        return rabbitBornTime;
    }
}
