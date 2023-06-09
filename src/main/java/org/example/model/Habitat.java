package org.example.model;

import lombok.Setter;
import org.example.view.listener.ChangeListenerDto;
import org.example.view.listener.Listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Habitat {
    @Setter
    private int rabbitClassicBornPeriod;
    @Setter
    private double rabbitClassicBornProbability;
    @Setter
    private int rabbitAlbinoBornPeriod;
    @Setter
    private double rabbitAlbinoBornProportionCondition;
    @Setter
    private int rabbitClassicLifeTime;
    @Setter
    private int rabbitAlbinoLifeTime;
    private int width;
    private int height;
    private double lastUpdateTime;
    private double lastSpawnRabbitClassic;
    private double lastSpawnRabbitAlbino;

    private double quantityRabbitClassic;
    private double quantityRabbitAlbino;
    private boolean rabbitSpawned;
    private List<Listener> listeners;

    public Habitat(int rabbitClassicBornPeriod, double rabbitClassicBornProbability, int rabbitAlbinoBornPeriod,
                   double rabbitAlbinoBornProportionCondition, int width, int height) {
        this.rabbitClassicBornPeriod = rabbitClassicBornPeriod;
        this.rabbitClassicBornProbability = rabbitClassicBornProbability;
        this.rabbitAlbinoBornPeriod = rabbitAlbinoBornPeriod;
        this.rabbitAlbinoBornProportionCondition = rabbitAlbinoBornProportionCondition;
        this.rabbitClassicLifeTime = 5;
        this.rabbitAlbinoLifeTime = 5;
        this.width = width;
        this.height = height;
        this.lastUpdateTime = 0;
        this.lastSpawnRabbitClassic = 0;
        this.lastSpawnRabbitAlbino = 0;
        this.quantityRabbitClassic = 0;
        this.quantityRabbitAlbino = 0;
        this.rabbitSpawned = false;
        this.listeners = new ArrayList<>();

    }

    public void update(double time) {
        double dt = time - lastUpdateTime;
        for (int i = 0; i < (int) dt; i++) {
            simulateStep(lastUpdateTime + i);
        }
        lastUpdateTime = time;

        notifyListeners(createChangeListenerDto(time));

    }

    private void simulateStep(double time) {
        checkLifeTimeRabbit();
        if (time - lastSpawnRabbitAlbino >= rabbitAlbinoBornPeriod &&
                quantityRabbitAlbino / quantityRabbitClassic < rabbitAlbinoBornProportionCondition) {
            spawnRabbitAlbino();
            lastSpawnRabbitAlbino = time;
        }
        if (time - lastSpawnRabbitClassic >= rabbitClassicBornPeriod && isProbable(rabbitClassicBornProbability)) {
            spawnRabbitClassic();
            lastSpawnRabbitClassic = time;
        }
    }

    private boolean isProbable(double probability) {
        return (Math.random() < probability);
    }

    private RabbitAlbino spawnRabbitAlbino() {
        RabbitAlbino rabbitAlbino = new RabbitAlbino();
        setRabbitCoordinates(rabbitAlbino);
        setRabbitBornTime(rabbitAlbino);
        setRabbitID(rabbitAlbino);
        RabbitList.getInstance().addRabbit(rabbitAlbino);


        quantityRabbitAlbino++;
        rabbitSpawned = true;
        return rabbitAlbino;
    }

    private RabbitClassic spawnRabbitClassic() {
        RabbitClassic rabbitClassic = new RabbitClassic();
        setRabbitCoordinates(rabbitClassic);
        setRabbitBornTime(rabbitClassic);
        setRabbitID(rabbitClassic);
        RabbitList.getInstance().addRabbit(rabbitClassic);


        quantityRabbitClassic++;
        rabbitSpawned = true;
        return rabbitClassic;
    }

    private void setRabbitCoordinates(Rabbit rabbit) {
        Random rand = new Random();
        int x = rand.nextInt(width);
        int y = rand.nextInt(height);
        rabbit.setCoordinates(x, y);
    }
    private void setRabbitBornTime(Rabbit rabbit){
        rabbit.setBornTime((int) this.lastUpdateTime);
    }
    private void setRabbitID(Rabbit rabbit){
        Random rand = new Random();
        int id = rand.nextInt(1000);
        while (!RabbitList.getInstance().checkId(id)){
            id= rand.nextInt(1000);}
        rabbit.setId(id);
    }
    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    private void notifyListeners(ChangeListenerDto changeListenerDto) {
        listeners.forEach(listener -> listener.notifyListener(changeListenerDto));
    }

    public void resetSimulation() {
        RabbitList.getInstance().clearRabbits();

        notifyListeners(createChangeListenerDto(lastUpdateTime));
        //lastUpdateTime = 0.0;
        lastUpdateTime = 0.0;
        lastSpawnRabbitClassic = 0;
        lastSpawnRabbitAlbino = 0;
        quantityRabbitClassic = 0;
        quantityRabbitAlbino = 0;
        rabbitSpawned = false;
    }

    private void checkLifeTimeRabbit(){
        RabbitList.getInstance().checkRabbitLifeTime(rabbitAlbinoLifeTime,rabbitClassicLifeTime, (int)lastUpdateTime);
    }
    private ChangeListenerDto createChangeListenerDto(double time) {
        return new ChangeListenerDto(time, (int) quantityRabbitClassic, (int) quantityRabbitAlbino);
    }
}
