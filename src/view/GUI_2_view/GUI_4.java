/*
package view.GUI_2_view;

import model.Station;
import model.StationListModel;
import ui_controller.GUI_1_ui_controller.GUI_1ListChangeListener;
import ui_controller.GUI_2_ui_controller.GUI_2KeyListener;
import ui_controller.GUI_2_ui_controller.GUI_2SelectionListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import business_controller.DataPersistenceFile;
import business_controller.StationCreator;

// view extends java class JFrame
public class GUI_2 extends JFrame {
    // create objects which contents will be changed during runtime as class variables
    private JList<Station> stationList;
    private JTextField stationIdTextField;
    private JTextField dateTextField;
    private JTextField targetTextField;
    private JTextField actualTextField;
    private JTextField varianceTextField;
    private JLabel messageLabel;
    private JLabel varianceWarning;
    
    public GUI_2() {
        super(); // call constructor of super class
        init();
    }
    
    private void init() {
        // create empty list
        this.stationList = new JList<Station>();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(this.stationList);  // add scrollpane to list
        setStationList();
        // add Listener for station list changes
        GUI_1ListChangeListener stationListChangeListener = new GUI_1ListChangeListener(this.stationList);
        DataPersistenceFile.getInstance().addStationListChangeListener(stationListChangeListener);
        // create labels
        JLabel stationIdLabel = new JLabel("Stations-ID");
        JLabel dateLabel = new JLabel("Messungsdatum");
        JLabel targetLabel = new JLabel("Zielwert");
        JLabel actualLabel = new JLabel("Tatsächlicher Wert");
        JLabel varianceLabel = new JLabel("Abweichung");
        messageLabel = new JLabel(" ");
        // set color
        Color labelcolor = new Color(100, 30, 250);
        stationIdLabel.setForeground(labelcolor); 
        dateLabel.setForeground(labelcolor); 
        targetLabel.setForeground(labelcolor); 
        actualLabel.setForeground(labelcolor); 
        varianceLabel.setForeground(labelcolor); 
        // create extra variance output
        JPanel variancePanel = new JPanel();
        varianceWarning = new JLabel("");
        //variancePanel.setLayout(new FlowLayout());
        variancePanel.add(varianceWarning);
        // create text fields
        stationIdTextField = new JTextField();
        dateTextField = new JTextField();
        targetTextField = new JTextField();
        actualTextField = new JTextField();
        varianceTextField = new JTextField();
        disableAllTextFields();

        // add elements to ui/frame      
        this.setTitle("Eis Messungsanwendung GUI 2");
        this.setLayout(new BorderLayout(20, 0)); // horizontal and vertical gap
        this.add(scrollPane, BorderLayout.WEST);
        JPanel valuePanel = new JPanel();
        valuePanel.setLayout(new GridLayout(5, 2)); // 5 rows, 2 columns
        valuePanel.add(stationIdLabel);
        valuePanel.add(stationIdTextField);
        valuePanel.add(dateLabel);
        valuePanel.add(dateTextField);
        valuePanel.add(targetLabel);
        valuePanel.add(targetTextField);
        valuePanel.add(actualLabel);
        valuePanel.add(actualTextField);
        valuePanel.add(varianceLabel);
        valuePanel.add(varianceTextField);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(valuePanel, BorderLayout.CENTER);
        mainPanel.add(variancePanel, BorderLayout.SOUTH);
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(messageLabel, BorderLayout.SOUTH);

        // set some min size
        scrollPane.setPreferredSize(new Dimension(150, 300));
        valuePanel.setPreferredSize(new Dimension(250, 300));

        // add listener to objects
        stationList.addListSelectionListener(new GUI_2SelectionListener(this));
        actualTextField.addKeyListener(new GUI_2KeyListener(this));
        dateTextField.addKeyListener(new GUI_2KeyListener(this));

        // pack, visualize
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        // remove station list change listener on close
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing (WindowEvent e){
                DataPersistenceFile.getInstance().removeStationListChangeListener(stationListChangeListener);
                e.getWindow().dispose();
            }
        });
    }

    public void disableAllTextFields() {
        stationIdTextField.setEditable(false);
        dateTextField.setEditable(false);
        targetTextField.setEditable(false);
        actualTextField.setEditable(false);
        varianceTextField.setEditable(false);
    }

    public void enablePossibleTextFields() {
        dateTextField.setEditable(true);
        actualTextField.setEditable(true);
    }

    public void setStationList() {
        List <Station> valueList = StationListModel.getInstance().getStationList();
        DefaultListModel listModel = new DefaultListModel();
        for (int i=0; i<valueList.size(); i++) {
            listModel.addElement(valueList.get(i));
        }
        stationList.setModel(listModel);
    }

    public List getStationList() {
        List list = new ArrayList<Station>();
        DefaultListModel listModel = ((DefaultListModel)stationList.getModel());
        for(int i=0; i<listModel.size(); i++) {
            list.add(listModel.get(i));
        }
        return list;
    }

    public Station getSelectedStationListObject() {
        return (Station) stationList.getModel().getElementAt(stationList.getSelectedIndex());
    }

    public int getSelectedStationListIndex() {
        return stationList.getSelectedIndex();
    }

    /*
        please note that we use model of JList to add and change objects
        in our case it does not make so much difference, 
        but sometimes it brings some benefits
    */
    /*

    public void changeSelectedStationListObject(Object o) {
        ((DefaultListModel)stationList.getModel()).set(stationList.getSelectedIndex(), o);
    }


    public void setVarianceFontColor(Color color) {
        varianceTextField.setForeground(color);
        Border border = BorderFactory.createLineBorder(color);
        varianceWarning.setBorder(border);
        varianceWarning.setText(" Die Abweichung liegt bei " + varianceTextField.getText() + ". ");
    }

    //
    // Set text of TextFields
    //
    public void setStationIdText(String s) {
        stationIdTextField.setText(s);
    }

    public void setDateText(String s) {
        dateTextField.setText(s);
    }

    public void setTargetText(String s) {
        targetTextField.setText(s);
    }

    public void setActualText(String s) {
        actualTextField.setText(s);
    }

    public void setVarianceText(String s) {
        varianceTextField.setText(s);
    }

    public void setMessageLabelText(String s) {
        messageLabel.setText(s);
    }

    //
    // Get text of TextFields
    //
    public String getStationIdText() {
        return stationIdTextField.getText();
    }

    public String getDateText() {
        return dateTextField.getText();
    }

    public String getTargetText() {
        return targetTextField.getText();
    }

    public String getActualText() {
        return actualTextField.getText();
    }

    public String getVarianceText() {
        return varianceTextField.getText();
    }
}
*/
