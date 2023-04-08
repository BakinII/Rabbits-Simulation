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
    @Setter
    @Getter
    private MainFrame mainFrame;
    private double time;
    private Timer timer;
    private KeyboardInput keyboardInput;
    @Setter
    private boolean isSimulationRunning;
    @Setter
    @Getter
    private boolean showTime;
    @Getter
    private boolean isResetSimulation;

    public Controller(Habitat habitat, MainFrame mainFrame) {
        this.habitat = habitat;
        this.mainFrame = mainFrame;
        this.time = 0;
        this.timer = new Timer();
        this.isSimulationRunning = false;

        isResetSimulation = true;

        this.keyboardInput = new KeyboardInput(this);

        mainFrame.addListenerStartKey(new ButtonStartListener());
        mainFrame.addListenerStopKey(new ButtonStopListener());
        mainFrame.addListenerShowTimeKey(new ButtonShowTimeListener());
        mainFrame.addListenerHideTimeKey(new ButtonHideTimeListener());
        mainFrame.addListenerShowKey(new ButtonShowInformationListener());
        mainFrame.addListenerRabbitAlbinoText(new RabbitAlbinoTextListener());
        mainFrame.addListenerRabbitClassicText(new RabbitClassicTextListener());
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
            setSimulationRunning(false);
            showSimulationInformation();
            if (mainFrame.isResetSimulation()) {
                resetSimulation();
            } else setSimulationRunning(true);

        }
    }


    private class ButtonShowTimeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            changeViewTimeVisibility();
        }
    }

    private class ButtonHideTimeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            changeViewTimeVisibility();
        }
    }

    private class ButtonShowInformationListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.showDialog();
        }
    }

    private class RabbitAlbinoTextListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //int rabbitAlbinoBornPeriod = 1;

            int newRabbitAlbinoBornPeriod = mainFrame.getRabbitAlbinoText();
            habitat.setRabbitAlbinoBornPeriod(newRabbitAlbinoBornPeriod);
            mainFrame.setFocusTextRabbitAlbino();
        }
    }

    private class RabbitClassicTextListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //int rabbitAlbinoBornPeriod = 1;

            int newRabbitClassicBornPeriod = mainFrame.getRabbitClassicText();
            habitat.setRabbitClassicBornPeriod(newRabbitClassicBornPeriod);
            mainFrame.setFocusTextRabbitClassic();
        }
    }

    private class OkButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            isResetSimulation = false;

        }
    }

    private class CancelButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            isResetSimulation = true;


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
                    habitat.setRabbitClassicBornProbability(mainFrame.getRabbitClassicProbability());
                    habitat.setRabbitAlbinoBornProportionCondition(mainFrame.getRabbitAlbinoBornProportionCondition());
                    habitat.update(time);
                }
            }
        }, TIMER_DELAY_VALUE, TIMER_TICK_VALUE);
    }

    public void resetSimulation() {

        habitat.resetSimulation();
        time = 0.0;
        mainFrame.repaint();
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
