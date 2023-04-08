package org.example.view;


import lombok.Getter;
import lombok.Setter;
import org.example.view.listener.ChangeListenerDto;
import org.example.view.listener.Listener;
import org.example.view.panel.ControlPanel;
import org.example.view.panel.Dialog;
import org.example.view.panel.MenuBar;
import org.example.view.panel.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class MainFrame extends JFrame implements Listener {

    private int quantityRabbitClassic;
    private int quantityRabbitAlbino;
    private double time;
    @Getter
    @Setter
    private boolean showDialogWindow;
    @Getter
    @Setter
    private boolean simulationStop;
    private Panel panel;
    private ControlPanel controlPanel;
    private MenuBar menuBar;
    @Getter
    @Setter
    private boolean isResetSimulation;


    public MainFrame() {
        this.quantityRabbitAlbino = 0;
        this.quantityRabbitClassic = 0;
        this.time = 0;
        this.showDialogWindow = false;
        this.simulationStop = false;

        this.isResetSimulation = true;

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


        // JOptionPane.showMessageDialog(null, "Прошедшее время " );
        this.menuBar = new MenuBar();
        this.setJMenuBar(menuBar);

        this.setResizable(false);
        this.repaint();
        this.setVisible(true);


    }

    @Override
    public void repaint() {
        if (showDialogWindow && simulationStop) {
            JTextArea infoTextArea = new JTextArea();
            infoTextArea.setEditable(false);
            infoTextArea.setText("Прошедшее время " + Integer.toString((int) time) +
                    "\n" + " Обычных кроликов появилось " + Integer.toString(quantityRabbitClassic) +
                    "\n" + " Кроликов альбиносов появилось " + Integer.toString(quantityRabbitAlbino));
            JScrollPane scrollPane = new JScrollPane(infoTextArea);

            int dialogResult = JOptionPane.showOptionDialog(null, scrollPane, "Simulation Information",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
            if (dialogResult == JOptionPane.OK_OPTION) {
                isResetSimulation = true;
            }
            if (dialogResult == JOptionPane.CANCEL_OPTION) {
                isResetSimulation = false;
            }
        }
    }

    @Override
    public void notifyListener(ChangeListenerDto changeListenerDto) {
        controlPanel.updateData(changeListenerDto.getQuantityRabbitAlbino(), changeListenerDto.getQuantityRabbitClassic(), changeListenerDto.getTime());
        this.updateData(changeListenerDto.getQuantityRabbitAlbino(), changeListenerDto.getQuantityRabbitClassic(), changeListenerDto.getTime());
        panel.repaint();
    }

    public void updateData(int quantityRabbitAlbino, int quantityRabbitClassic, double time) {
        this.quantityRabbitAlbino = quantityRabbitAlbino;
        this.quantityRabbitClassic = quantityRabbitClassic;
        this.time = time;
        //repaint();
    }

    public void changeViewTimeVisibility() {
        controlPanel.setTimeShow(!controlPanel.isTimeShow());
        controlPanel.repaint();
    }

    public void showSimulationInformation() {
        controlPanel.setSimulationRunning(!controlPanel.isSimulationRunning());
        this.setSimulationStop(true);
        controlPanel.repaint();
        this.repaint();
    }

    public void showDialog() {
        this.setShowDialogWindow(!this.isShowDialogWindow());
        controlPanel.repaint();
        this.repaint();
    }

    public void hideSimulationInformation() {
        controlPanel.setSimulationRunning(true);
        this.setSimulationStop(false);
        controlPanel.repaint();
        this.repaint();
    }

    public void addListenerStartKey(ActionListener actionListener) {
        controlPanel.addListenerStartKey(actionListener);
        menuBar.addListenerStartMenu(actionListener);
    }

    public void addListenerStopKey(ActionListener actionListener) {
        controlPanel.addListenerStopKey(actionListener);
        menuBar.addListenerStopMenu(actionListener);
    }


    public void addListenerShowTimeKey(ActionListener actionListener) {
        controlPanel.addListenerShowTimeKey(actionListener);
    }

    public void addListenerHideTimeKey(ActionListener actionListener) {
        controlPanel.addListenerHideTimeKey(actionListener);
    }

    public void addListenerShowKey(ActionListener actionListener) {
        controlPanel.addListenerShowKey(actionListener);
    }

    public void addListenerRabbitAlbinoText(ActionListener actionListener) {
        controlPanel.addListenerRabbitAlbinoText(actionListener);
    }

    public void addListenerRabbitClassicText(ActionListener actionListener) {
        controlPanel.addListenerRabbitClassicText(actionListener);
    }


    public int getRabbitAlbinoText() {
        return controlPanel.getRabbitAlbinoText();
    }

    public int getRabbitClassicText() {
        return controlPanel.getRabbitClassicText();
    }

    public void setFocusTextRabbitAlbino() {
        controlPanel.setFocusTextRabbitAlbino();
    }

    public void setFocusTextRabbitClassic() {
        controlPanel.setFocusTextRabbitClassic();
    }

    public double getRabbitClassicProbability() {
        return controlPanel.getRabbitClassicProbability();
    }

    public double getRabbitAlbinoBornProportionCondition() {
        return controlPanel.getRabbitAlbinoBornProportionCondition();
    }

}
