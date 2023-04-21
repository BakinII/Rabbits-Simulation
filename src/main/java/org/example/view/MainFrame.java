package org.example.view;


import lombok.Getter;
import lombok.Setter;
import org.example.model.Rabbit;
import org.example.model.RabbitList;
import org.example.view.listener.ChangeListenerDto;
import org.example.view.listener.Listener;
import org.example.view.panel.ControlPanel;
import org.example.view.panel.MenuBar;
import org.example.view.panel.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Vector;


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
    @Getter
    @Setter
    private boolean showCollectionDialog;


    public MainFrame() {
        this.quantityRabbitAlbino = 0;
        this.quantityRabbitClassic = 0;
        this.time = 0;
        this.showDialogWindow = false;
        this.simulationStop = false;
        this.showCollectionDialog = false;
        this.isResetSimulation = true;

        panel = new Panel();
        ImageIcon travaImageIcon = new ImageIcon("src\\main\\resources\\trava.png");
        //ImageIcon travaImageIcon = new ImageIcon("D:\\TMP\\3lab\\Rabbits-Simulation-main\\src\\main\\resources\\trava.png");


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
                controlPanel.setSimulationRunning(false);
            }
            if (dialogResult == JOptionPane.CANCEL_OPTION) {
                isResetSimulation = false;
                controlPanel.setSimulationRunning(true);

            }
        }
        //if(showCollectionDialog){

//            HashMap<Integer,Integer> rabbits = RabbitList.getInstance().getHashMapRabbit();
//
//            String message = "Id Кролика : Время Рождения"+ "\n";
//            for (Integer key : rabbits.keySet()) {
//                message += key + " : " + rabbits.get(key) + "\n";
//            }
//
//            JOptionPane.showMessageDialog(null,message,"HashMap", JOptionPane.INFORMATION_MESSAGE);
//        }
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
    public void addListenerRabbitClassicLifeTimeText(ActionListener actionListener) {
        controlPanel.addListenerRabbitClassicLifeTimeText(actionListener);
    }
    public void addListenerRabbitAlbinoLifeTimeText(ActionListener actionListener) {
        controlPanel.addListenerRabbitAlbinoLifeTimeText(actionListener);
    }
    public void addListenerCurrentObjectButton(ActionListener actionListener) {
        controlPanel.addListenerCurrentObjectButton(actionListener);
    }
    public int getRabbitAlbinoText() {
        return controlPanel.getRabbitAlbinoText();
    }

    public int getRabbitClassicText() {
        return controlPanel.getRabbitClassicText();
    }

    public int getRabbitClassicRabbitClassicLifeTimeText(){
        return controlPanel.getRabbitClassicRabbitClassicLifeTimeText();
    }

    public int getRabbitAlbinoLifeTimeText(){
        return controlPanel.getRabbitAlbinoLifeTimeText();
    }

    public void setFocusTextRabbitAlbino() {
        controlPanel.setFocusTextRabbitAlbino();
    }

    public void setFocusTextRabbitClassic() {
        controlPanel.setFocusTextRabbitClassic();
    }

    public void setFocusTextRabbitClassicLifeTime() {
        controlPanel.setFocusTextRabbitClassicLifeTime();
    }
    public void setFocusTextRabbitAlbinoLifeTime() {
        controlPanel.setFocusTextRabbitAlbinoLifeTime();
    }
    public double getRabbitClassicProbability() {
        return controlPanel.getRabbitClassicProbability();
    }

    public double getRabbitAlbinoBornProportionCondition() {
        return controlPanel.getRabbitAlbinoBornProportionCondition();
    }

}
