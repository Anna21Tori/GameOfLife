package panels;

import elements.Slider;
import tools.DataManager;
import tools.Utils;
import layout.GBC;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class ToolsPanel extends JPanel {

    private Utils utils;
    private DataManager dm;
    private JButton btnStart;
    private JButton btnStop;
    private JButton btnReset;
    private JButton btnOK;
    private JButton btnOKPattern;

    private JComboBox jComboBoxPattern;
   // private JComboBox jComboBoxAction;
    private Slider sliderNumCells;
    private Slider sliderSpeed;
    private Timer timer;
    private int delay;
    private ButtonGroup groupActions;
    private JRadioButton jRadioButtonRandom;
    private JRadioButton jRadioButtonClickable;
    private JRadioButton jRadioButtonPatterns;
    private JLabel jLabelRandom;
    private JLabel jLabelPattern;
    private JLabel jLabelSpeed;
    private JTextField jTextFieldNumOfCells;
    private String [] pattern = {"block", "beehive", "loaf", "boat", "blinker", "toad", "beacon", "glider", "lightweight spaceship"};


    public ToolsPanel(Utils utils, DataManager dm) {
        this.setPreferredSize(new Dimension(dm.getWidth(), 321));
        this.setLayout(new GridBagLayout());
        this.utils = utils;
        this.delay = 800;
        this.dm = dm;
    }

    public void addTools() {

        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");
        btnReset = new JButton("Reset");
        btnOK = new JButton("OK");
        btnOKPattern = new JButton("OK");
        jComboBoxPattern = new JComboBox(pattern);
        groupActions = new ButtonGroup();
        jRadioButtonClickable = new JRadioButton("Clickable");
        jRadioButtonPatterns = new JRadioButton("Pattern");
        jRadioButtonRandom = new JRadioButton("Random");
        groupActions.add(jRadioButtonRandom);
        groupActions.add(jRadioButtonClickable);
        groupActions.add(jRadioButtonPatterns);
        jLabelRandom = new JLabel("Num of cells:");
        jLabelPattern = new JLabel("Pattern type:");
        jLabelSpeed = new JLabel("Speed:");
        sliderSpeed = new Slider(100, 1300, this.delay, 300);
        sliderNumCells = new Slider(1, dm.getNumCellsHeight()*dm.getNumCellsWidth(), 100, 200);
        jTextFieldNumOfCells = new JTextField("100");

        timer = new Timer(this.delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                action();
            }
        });

        //set grid
        this.add(jRadioButtonRandom, new GBC(0, 0, 1, 1).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));
        this.add(jLabelRandom, new GBC(1, 0, 1, 1).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));
        this.add(sliderNumCells, new GBC(2, 0, 1, 1).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));
        this.add(jTextFieldNumOfCells, new GBC(3, 0, 1, 1).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));
        this.add(btnOK, new GBC(4, 0, 1, 1).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));

        this.add(jRadioButtonClickable, new GBC(0, 1, 1, 1).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));

        this.add(jRadioButtonPatterns, new GBC(0, 2, 1, 1).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));
        this.add(jLabelPattern, new GBC(1, 2, 1, 1).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));
        this.add(jComboBoxPattern, new GBC(2, 2, 1, 1).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));
        this.add(btnOKPattern, new GBC(4, 2, 1, 1).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));

       this.add(jLabelSpeed, new GBC(1, 3, 1, 1).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));
       this.add(sliderSpeed, new GBC(2, 3, 1, 1).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));

        this.add(btnStart, new GBC(0, 4, 1, 1).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));
        this.add(btnStop, new GBC(1, 4, 1, 1).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));
        this.add(btnReset, new GBC(2, 4, 1, 1).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));

        setEnabledPattern(false);
        btnStop.setEnabled(false);
        jRadioButtonRandom.setSelected(true);

        btnStart.addActionListener(e -> {
            btnStop.setEnabled(true);
            btnReset.setEnabled(false);
            setEnabledAll(false);
            timer.setDelay(this.delay);
            timer.start();

        });

        btnStop.addActionListener(e -> {
            timer.stop();
            btnStop.setEnabled(false);
            btnReset.setEnabled(true);
            setEnabledAll(true);
        });

        btnReset.addActionListener(e -> {
            utils.reset();
        });

        btnOKPattern.addActionListener( e ->{
            int id = jComboBoxPattern.getSelectedIndex();
            utils.reset();
            if(pattern[id].equals("block")){
                utils.generatePattern(dm.getBlock());
            }else if(pattern[id].equals("beehive")){
                utils.generatePattern(dm.getBeehive());
            }else if(pattern[id].equals("loaf")){
                utils.generatePattern(dm.getLoaf());
            }else if(pattern[id].equals("boat")){
                utils.generatePattern(dm.getBoat());
            }else if(pattern[id].equals("blinker")){
                utils.generatePattern(dm.getBlinker());
            }else if(pattern[id].equals("toad")){
                utils.generatePattern(dm.getToad());
            }else if(pattern[id].equals("beacon")){
                utils.generatePattern(dm.getBeacon());
            }else if(pattern[id].equals("glider")){
                utils.generatePattern(dm.getGlider());
            }else if(pattern[id].equals("lightweight spaceship")){
                utils.generatePattern(dm.getLightweightSpaceship());
            }
        });


        jRadioButtonRandom.addActionListener( e->{
            setEnabledPattern(false);
            setEnabledRandom(true);
            dm.setClickable(false);
        });

        jRadioButtonPatterns.addActionListener( e->{
            utils.reset();
            setEnabledPattern(true);
            setEnabledRandom(false);
            dm.setClickable(false);
        });
        jRadioButtonClickable.addActionListener( e->{
            setEnabledPattern(false);
            setEnabledRandom(false);
            dm.setClickable(true);
        });

        sliderNumCells.addChangeListener(e -> {
            jTextFieldNumOfCells.setText(""+sliderNumCells.getValue());
        });

        sliderSpeed.addChangeListener(e -> {
            this.delay = sliderSpeed.getValue();
        });

        jTextFieldNumOfCells.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                try {
                    int val = Integer.parseInt(jTextFieldNumOfCells.getText());
                    if (val >= 0 && val <= dm.getNumCellsHeight()*dm.getNumCellsWidth()) {
                        sliderNumCells.setValue(val);
                    } else {
                        sliderNumCells.setValue(0);
                    }
                }catch(Exception err){
                    sliderNumCells.setValue(0);
                }
            }
        });

        btnOK.addActionListener(e->{
            random();
        });

    }
    public void action(){
        utils.CA();

    }

    public void random(){
        utils.setRandomCells(sliderNumCells.getValue());
    }

    private void setEnabledPattern(boolean state){
        jComboBoxPattern.setEnabled(state);
        jLabelPattern.setEnabled(state);
        btnOKPattern.setEnabled(state);
    }

    private void setEnabledRandom(boolean state){
        sliderNumCells.setEnabled(state);
        jLabelRandom.setEnabled(state);
        jTextFieldNumOfCells.setEnabled(state);
        btnOK.setEnabled(state);
    }

    private void setEnabledAll(boolean state){
        jLabelSpeed.setEnabled(state);
        sliderSpeed.setEnabled(state);

        if(jRadioButtonRandom.isSelected())
            setEnabledRandom(state);

        if(jRadioButtonPatterns.isSelected())
            setEnabledPattern(state);

        jRadioButtonClickable.setEnabled(state);
        jRadioButtonRandom.setEnabled(state);
        jRadioButtonPatterns.setEnabled(state);

        if(state && jRadioButtonClickable.isSelected())
            utils.setClickable(state);
        else
            utils.setClickable(state);
    }

}
