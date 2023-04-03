package org.example;

import org.example.controller.Controller;
import org.example.model.Habitat;
import org.example.view.MainFrame;

public class Main {

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        Habitat habitat = new Habitat(1, 0.5, 1, 0.5, mainFrame.getWidth() - 500, mainFrame.getHeight() - 200);
        Controller controller = new Controller(habitat, mainFrame);
    }

}
