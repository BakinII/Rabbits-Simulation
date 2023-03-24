package org.example.controller;

import lombok.Getter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@Getter
public class KeyboardInput implements KeyListener {

    private Controller controller;

    public KeyboardInput(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char keyChar = e.getKeyChar();
        if (keyChar == 'b') {
            controller.setSimulationRunning(true);
            controller.hideSimulationInformation();
        }
        if (keyChar == 't') {
            controller.setShowTime(!controller.isShowTime());
            controller.changeViewTimeVisibility();
        }
        if (keyChar == 'e') {
            controller.showSimulationInformation();
            controller.setSimulationRunning(false);
            controller.resetSimulation();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
