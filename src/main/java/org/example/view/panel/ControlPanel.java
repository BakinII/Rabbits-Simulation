package org.example.view.panel;

import lombok.Getter;
import lombok.Setter;
import org.example.view.MainFrame;
import org.example.view.PhotoFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {

    private int quantityRabbitClassic;
    private int quantityRabbitAlbino;
    private double time;
    @Setter
    @Getter
    private boolean simulationRunning;
    @Setter
    @Getter
    private boolean timeShow;
    PhotoFactory photoFactory;
    JButton startButton = new JButton("Старт");
    JButton stopButton = new JButton("Стоп");
    JButton currentObjectsButton = new JButton("Текущие объекты");

    JRadioButton showTimeButton = new JRadioButton("Показывать время");
    JRadioButton hideTimeButton = new JRadioButton("Скрывать время");
    ButtonGroup groupTimeButton = new ButtonGroup();

    JRadioButton showDialogMessage = new JRadioButton("Показывать информацию");


    JTextField rabbitClassicBornPeriodText = new JTextField();
    JTextField rabbitAlbinoBornPeriodText = new JTextField();
    JTextField rabbitClassicLifeTimeText = new JTextField();
    JTextField rabbitAlbinoLifeTimeText = new JTextField();


    JComboBox<Integer> rabbitClassicBornProbabilityInput = new JComboBox<>();
    JComboBox<Integer> rabbitAlbinoBornProbabilityInput = new JComboBox<>();


    public ControlPanel(MainFrame mainFrame) {
        this.quantityRabbitAlbino = 0;
        this.quantityRabbitClassic = 0;
        this.time = 0;
        this.simulationRunning = false;
        this.timeShow = false;

        this.photoFactory = new PhotoFactory();

        startButton.setFocusable(false);
        stopButton.setFocusable(false);
        currentObjectsButton.setFocusable(false);

        showTimeButton.setFocusable(false);
        hideTimeButton.setFocusable(false);

        rabbitAlbinoBornProbabilityInput.setFocusable(false);
        rabbitClassicBornProbabilityInput.setFocusable(false);

        groupTimeButton.add(showTimeButton);
        groupTimeButton.add(hideTimeButton);

        showDialogMessage.setFocusable(false);

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        startButton.setBackground(Color.green);
        this.add(startButton, gbc);

        gbc.gridx = 0;
        //gbc.gridx = 1;
        gbc.gridy = 0;
        stopButton.setBackground(Color.red);
        this.add(stopButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        Font font = new Font("Times New Roman", Font.BOLD, 20);
        showTimeButton.setFont(font);
        this.add(showTimeButton, gbc);

        hideTimeButton.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        this.add(hideTimeButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        Font font1 = new Font("Times New Roman", Font.BOLD, 20);
        showDialogMessage.setFont(font1);
        this.add(showDialogMessage, gbc);


        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel rabbitAlbinoLabel = new JLabel("Период появления кроликов альбиносов(сек)");
        this.add(rabbitAlbinoLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        rabbitAlbinoBornPeriodText.setPreferredSize(new Dimension(100, 25));
        rabbitAlbinoBornPeriodText.setText("1");
        this.add(rabbitAlbinoBornPeriodText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        JLabel rabbitClassicLabel = new JLabel("Период появления обычных кроликов(сек)");
        this.add(rabbitClassicLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        rabbitClassicBornPeriodText.setPreferredSize(new Dimension(100, 25));
        rabbitClassicBornPeriodText.setText("1");
        this.add(rabbitClassicBornPeriodText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        JLabel RabbitAlbinoSpawnChanceLabel = new JLabel("Условие рождения кроликов альбиносов (%)");
        this.add(RabbitAlbinoSpawnChanceLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        for (int i = 0; i <= 100; i += 10) {
            rabbitAlbinoBornProbabilityInput.addItem(i);
        }
        this.add(rabbitAlbinoBornProbabilityInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        JLabel RabbitClassicSpawnChanceLabel = new JLabel("Вероятность появления обычного кролика (%)");
        rabbitAlbinoBornProbabilityInput.setSelectedIndex(5);
        this.add(RabbitClassicSpawnChanceLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 11;
        for (int i = 0; i <= 100; i += 10) {
            rabbitClassicBornProbabilityInput.addItem(i);
        }
        rabbitClassicBornProbabilityInput.setSelectedIndex(5);
        this.add(rabbitClassicBornProbabilityInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 12;
        JLabel rabbitAlbinoLifeTimeLabel = new JLabel("Время жизни кроликов альбиносов(сек)");
        this.add(rabbitAlbinoLifeTimeLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 13;
        rabbitAlbinoLifeTimeText.setPreferredSize(new Dimension(100, 25));
        rabbitAlbinoLifeTimeText.setText("5");
        this.add(rabbitAlbinoLifeTimeText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 14;
        JLabel rabbitClassicLifeTimeLabel = new JLabel("Время жизни обычных кроликов (сек)");
        this.add(rabbitClassicLifeTimeLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 15;
        rabbitClassicLifeTimeText.setPreferredSize(new Dimension(100, 25));
        rabbitClassicLifeTimeText.setText("5");
        this.add(rabbitClassicLifeTimeText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 16;
        this.add(currentObjectsButton, gbc);

        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.drawImage(photoFactory.getPhotoMisata(), 0, 0, null);

        if (simulationRunning) {
            stopButton.setVisible(true);
            startButton.setVisible(false);
        } else {
            stopButton.setVisible(false);
            startButton.setVisible(true);
        }

        if (timeShow) {
            Font font = new Font("Arial", Font.BOLD, 20);
            g.setFont(font);
            g.drawString("Прошедшее время " + Integer.toString((int) time), 150, 100);
        }
    }


    public void addListenerStartKey(ActionListener actionListener) {
        startButton.addActionListener(actionListener);
    }

    public void addListenerStopKey(ActionListener actionListener) {
        stopButton.addActionListener(actionListener);
    }


    public void addListenerShowTimeKey(ActionListener actionListener) {
        showTimeButton.addActionListener(actionListener);
    }

    public void addListenerHideTimeKey(ActionListener actionListener) {
        hideTimeButton.addActionListener(actionListener);
    }

    public void addListenerShowKey(ActionListener actionListener) {
        showDialogMessage.addActionListener(actionListener);
    }

    public void addListenerRabbitAlbinoText(ActionListener actionListener) {
        rabbitAlbinoBornPeriodText.addActionListener(actionListener);
    }

    public void addListenerRabbitClassicText(ActionListener actionListener) {
        rabbitClassicBornPeriodText.addActionListener(actionListener);
    }

    public void addListenerRabbitClassicLifeTimeText(ActionListener actionListener) {
        rabbitClassicLifeTimeText.addActionListener(actionListener);
    }
    public void addListenerRabbitAlbinoLifeTimeText(ActionListener actionListener) {
        rabbitAlbinoLifeTimeText.addActionListener(actionListener);
    }

    public void addListenerCurrentObjectButton(ActionListener actionListener) {
        currentObjectsButton.addActionListener(actionListener);
    }

    public void updateData(int quantityRabbitAlbino, int quantityRabbitClassic, double time) {
        this.quantityRabbitAlbino = quantityRabbitAlbino;
        this.quantityRabbitClassic = quantityRabbitClassic;
        this.time = time;
        repaint();
    }

    public int getRabbitAlbinoText() {
        int spawnRabbitAlbinoPeriod;
        int defaultValue = 1;
        try {
            spawnRabbitAlbinoPeriod = Integer.parseInt(rabbitAlbinoBornPeriodText.getText());
            if (spawnRabbitAlbinoPeriod <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            spawnRabbitAlbinoPeriod = defaultValue;
            rabbitAlbinoBornPeriodText.setText(String.valueOf(spawnRabbitAlbinoPeriod));
            JOptionPane.showMessageDialog(this, "Неправильное время рождения, значение по умолчанию: " + defaultValue, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return spawnRabbitAlbinoPeriod;
    }

    public int getRabbitClassicText() {
        int spawnRabbitClassicPeriod;
        int defaultValue = 1;
        try {
            spawnRabbitClassicPeriod = Integer.parseInt(rabbitClassicBornPeriodText.getText());
            if (spawnRabbitClassicPeriod <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            spawnRabbitClassicPeriod = defaultValue;
            rabbitClassicBornPeriodText.setText(String.valueOf(spawnRabbitClassicPeriod));
            JOptionPane.showMessageDialog(this, "Неправильное время рождения, значение по умолчанию: " + defaultValue, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return spawnRabbitClassicPeriod;
    }

    public int getRabbitClassicRabbitClassicLifeTimeText(){
        int RabbitClassicLifeTime;
        int defaultValue = 5;
        try {
            RabbitClassicLifeTime = Integer.parseInt(rabbitClassicLifeTimeText.getText());
            if (RabbitClassicLifeTime <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            RabbitClassicLifeTime = defaultValue;
            rabbitClassicLifeTimeText.setText(String.valueOf(RabbitClassicLifeTime));
            JOptionPane.showMessageDialog(this, "Неправильное время жизни, значение по умолчанию: " + defaultValue, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return RabbitClassicLifeTime;
    }
    public int getRabbitAlbinoLifeTimeText(){
        int RabbitAlbinoLifeTime;
        int defaultValue = 5;
        try {
            RabbitAlbinoLifeTime = Integer.parseInt(rabbitAlbinoLifeTimeText.getText());
            if (RabbitAlbinoLifeTime <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            RabbitAlbinoLifeTime = defaultValue;
            rabbitAlbinoLifeTimeText.setText(String.valueOf(RabbitAlbinoLifeTime));
            JOptionPane.showMessageDialog(this, "Неправильное время жизни, значение по умолчанию: " + defaultValue, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return RabbitAlbinoLifeTime;
    }

    public void setFocusTextRabbitAlbino() {
        rabbitAlbinoBornPeriodText.setFocusable(false);
    }

    public void setFocusTextRabbitClassic() {
        rabbitClassicBornPeriodText.setFocusable(false);
    }

    public void setFocusTextRabbitClassicLifeTime() {
        rabbitClassicLifeTimeText.setFocusable(false);
    }
    public void setFocusTextRabbitAlbinoLifeTime() {
        rabbitAlbinoLifeTimeText.setFocusable(false);
    }
    public double getRabbitClassicProbability() {
        return Double.parseDouble(String.valueOf(rabbitClassicBornProbabilityInput.getSelectedItem())) / 100;
    }

    public double getRabbitAlbinoBornProportionCondition() {
        return Double.parseDouble(String.valueOf(rabbitAlbinoBornProbabilityInput.getSelectedItem())) / 100;
    }
}
