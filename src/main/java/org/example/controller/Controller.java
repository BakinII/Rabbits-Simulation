package org.example.controller;

import lombok.Getter;
import lombok.Setter;
import org.example.model.Habitat;
import org.example.view.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {
    private static final int TIMER_TICK_VALUE = 1000;
    private static final int TIMER_DELAY_VALUE = 0;
    private Habitat habitat;
    private MainFrame mainFrame;
    private double time;
    private Timer timer;
    private KeyboardInput keyboardInput;
    @Setter
    private boolean isSimulationRunning;
    @Setter
    @Getter
    private boolean showTime;

    public Controller(Habitat habitat, MainFrame mainFrame) {
        this.habitat = habitat;
        this.mainFrame = mainFrame;
        this.time = 0;
        this.timer = new Timer();
        this.isSimulationRunning = false;
        this.keyboardInput = new KeyboardInput(this);

        mainFrame.addListenerStartKey(new ButtonStartListener());
        mainFrame.addListenerStopKey(new ButtonStopListener());
        mainFrame.addListenerTimeKey(new ButtonInformationListener());
        init();
    }

    private class ButtonStartListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           setSimulationRunning(true);
           hideSimulationInformation();
        }
    }

    private class ButtonStopListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            showSimulationInformation();
            setSimulationRunning(false);
            resetSimulation();
        }
    }

    private class ButtonInformationListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            changeViewTimeVisibility();
        }
    }

    private void init() {
        habitat.addListener(mainFrame);
        mainFrame.addKeyListener(keyboardInput);
        startTimer();
    }

    private void startTimer() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (isSimulationRunning) {
                    time += 1.0;
                    habitat.update(time);
                }
            }
        }, TIMER_DELAY_VALUE, TIMER_TICK_VALUE);
    }

    public void resetSimulation() {
        habitat.resetSimulation();
        time = 0.0;
    }

    public void changeViewTimeVisibility() {
        mainFrame.changeViewTimeVisibility();
    }

    public void showSimulationInformation() {
        mainFrame.showSimulationInformation();
    }

    public void hideSimulationInformation() {
        mainFrame.hideSimulationInformation();
    }
}
