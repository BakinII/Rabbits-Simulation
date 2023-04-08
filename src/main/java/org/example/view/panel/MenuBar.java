package org.example.view.panel;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar {
    JMenu menu = new JMenu("Simulation");
    JMenuItem startMenuItem = new JMenuItem("Start");
    JMenuItem stopMenuItem = new JMenuItem("Stop");

    public MenuBar (){
        menu.add(startMenuItem);
        menu.add(stopMenuItem);
        this.add(menu);
    }
    public void addListenerStartMenu(ActionListener actionListener) {
        startMenuItem.addActionListener(actionListener);
    }
    public void addListenerStopMenu(ActionListener actionListener) {
        stopMenuItem.addActionListener(actionListener);
    }
}
