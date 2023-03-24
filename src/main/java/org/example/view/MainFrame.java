package org.example.view;


import org.example.view.listener.ChangeListenerDto;
import org.example.view.listener.Listener;
import org.example.view.panel.Panel;

import javax.swing.*;


public class MainFrame extends JFrame implements Listener {
    private Panel panel;

    public MainFrame() {
        panel = new Panel();
        ImageIcon travaImageIcon = new ImageIcon("src\\main\\resources\\trava.png");
        int iconWidth = travaImageIcon.getIconWidth();
        int iconHeight = travaImageIcon.getIconHeight();

        this.getContentPane().removeAll();
        this.add(panel);
        this.setSize(iconWidth, iconHeight);
        this.setResizable(false);
        this.repaint();
        this.setVisible(true);
    }


    @Override
    public void notifyListener(ChangeListenerDto changeListenerDto) {
        panel.updateRabbits(changeListenerDto.getRabbitList());
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
}
