package org.example.view.panel;

import org.example.view.MainFrame;
import org.example.view.PhotoFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {
    PhotoFactory photoFactory;

    JButton startButton = new JButton("Старт");
    JButton stopButton = new JButton("Стоп");
    JRadioButton showTimeButton = new JRadioButton("Показывать время");



    public ControlPanel(MainFrame mainFrame) {
        this.photoFactory = new PhotoFactory();

        startButton.setFocusable(false);
        stopButton.setFocusable(false);
        showTimeButton.setFocusable(false);
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(startButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(stopButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        this.add(showTimeButton, gbc);

        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(photoFactory.getPhotoMisata(), 0, 0, null);
    }


    public void addListenerStartKey(ActionListener actionListener) {
        startButton.addActionListener(actionListener);
    }

    public void addListenerStopKey(ActionListener actionListener) {
        stopButton.addActionListener(actionListener);
    }

    public void addListenerTimeKey(ActionListener actionListener) {
        showTimeButton.addActionListener(actionListener);
    }
}
