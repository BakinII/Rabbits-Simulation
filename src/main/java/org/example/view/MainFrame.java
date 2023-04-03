package org.example.view;


import org.example.view.listener.ChangeListenerDto;
import org.example.view.listener.Listener;
import org.example.view.panel.ControlPanel;
import org.example.view.panel.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class MainFrame extends JFrame implements Listener {
    private Panel panel;

    private ControlPanel controlPanel;

    public MainFrame() {
        panel = new Panel();
        ImageIcon travaImageIcon = new ImageIcon("src\\main\\resources\\trava.png");
        int iconWidth = travaImageIcon.getIconWidth();
        int iconHeight = travaImageIcon.getIconHeight();

        controlPanel = new ControlPanel(this);

        Container contentPane = this.getContentPane();
        contentPane.removeAll();
        this.setSize(iconWidth + 300, iconHeight);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.8;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        contentPane.add(panel, c);
        c.gridx = 1;
        c.weightx = 0.2;
        contentPane.add(controlPanel, c);


//        this.add(panel);
//        this.add(controlPanel);
//        this.revalidate();
//        controlPanel.revalidate();
//        panel.revalidate();

//        controlPanel.repaint();

        this.setResizable(false);
//        this.revalidate();
        this.repaint();
        this.setVisible(true);

    }


    @Override
    public void notifyListener(ChangeListenerDto changeListenerDto) {

        panel.updateTime(changeListenerDto.getTime());
        panel.updateQuantityRabbits(changeListenerDto.getQuantityRabbitAlbino(), changeListenerDto.getQuantityRabbitClassic());
    }

    public void changeViewTimeVisibility() {
        panel.setShowTime(!panel.isShowTime());
        panel.repaint();
    }

    public void showSimulationInformation() {
        panel.setSimulationInformation(!panel.isSimulationInformation());
        panel.repaint();
    }

    public void hideSimulationInformation() {
        panel.setSimulationInformation(false);
        panel.repaint();
    }

    public void addListenerStartKey(ActionListener actionListener) {
        controlPanel.addListenerStartKey(actionListener);
    }

    public void addListenerStopKey(ActionListener actionListener) {
        controlPanel.addListenerStopKey(actionListener);
    }

    public void addListenerTimeKey(ActionListener actionListener){
        controlPanel.addListenerTimeKey(actionListener);
    }
}
