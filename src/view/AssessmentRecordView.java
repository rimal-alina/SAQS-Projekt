package view;

import controller.AssessmentRecordController;
import controller.AssessmentRecordKeyListener;
import controller.AssessmentRecordListSelectionListener;
import model.StationModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

// view extends java class JFrame
public class AssessmentRecordView extends JFrame {
    // create objects which contents will be changed during runtime as class variables
    private JList stationList;
    private JTextField stationIdTextField;
    private JTextField dateTextField;
    private JTextField targetTextField;
    private JTextField actualTextField;
    private JTextField varianceTextField;
    private JLabel messageLabel;
    private AssessmentRecordController controller;
    
    public AssessmentRecordView() {
        super(); // call constructor of super class
        init();
    }
    
    private void init() {
        // create empty list
        stationList = new JList();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(stationList);  // add scrollpane to list
        //stationList.setLayoutOrientation(JList.VERTICAL);
        setStationList();
        // create labels
        JLabel stationIdLabel = new JLabel("Station ID");
        JLabel dateLabel = new JLabel("Date");
        JLabel targetLabel = new JLabel("Target");
        JLabel actualLabel = new JLabel("Actual");
        JLabel varianceLabel = new JLabel("Variance");
        messageLabel = new JLabel(" ");
        // create text fields
        stationIdTextField = new JTextField();
        dateTextField = new JTextField();
        targetTextField = new JTextField();
        actualTextField = new JTextField();
        varianceTextField = new JTextField();
        disableAllTextFields();
        // create buttons
        JButton saveButton = new JButton("Speichern");
        JButton newButton = new JButton("Neue Station");
        JButton deleteButton = new JButton("Station entfernen");

        // add elements to ui/frame      
        this.setTitle("Assessment Record");
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
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(saveButton);
        buttonPanel.add(newButton);
        buttonPanel.add(deleteButton);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(valuePanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(messageLabel, BorderLayout.SOUTH);

        // set some min size
        scrollPane.setPreferredSize(new Dimension(150, 300));
        valuePanel.setPreferredSize(new Dimension(250, 300));

        // add listener to objects
        stationList.addListSelectionListener(new AssessmentRecordListSelectionListener(this));
        controller = new AssessmentRecordController(this);
        saveButton.addActionListener(controller);
        newButton.addActionListener(controller);
        deleteButton.addActionListener(controller);
        actualTextField.addKeyListener(new AssessmentRecordKeyListener(this));

        // pack, visualize
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
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
        List valueList = StationModel.getInstance().getStationList();
        DefaultListModel listModel = new DefaultListModel();
        for(int i=0; i<valueList.size(); i++) {
            listModel.addElement(valueList.get(i));
        }
        stationList.setModel(listModel);
    }

    public List getStationList() {
        List list = new ArrayList();
        DefaultListModel listModel = ((DefaultListModel)stationList.getModel());
        for(int i=0; i<listModel.size(); i++) {
            list.add(listModel.get(i));
        }
        return list;
    }

    public Object getSelectedStationListObject() {
        return stationList.getModel().getElementAt(stationList.getSelectedIndex());
    }

    public int getSelectedStationListIndex() {
        return stationList.getSelectedIndex();
    }

    /*
        please note that we use model of JList to add/change/remove objects
        in our case it does not make so much difference, 
        but sometimes it brings some benefits.
    */
    public void addStationToList(Object o) {
        ((DefaultListModel)stationList.getModel()).addElement(o);
    }

    public void changeSelectedStationListObject(Object o) {
        ((DefaultListModel)stationList.getModel()).set(stationList.getSelectedIndex(), o);
    }

    public void removeStationFromListAt(int index) {
        ((DefaultListModel)stationList.getModel()).remove(index);
    }


    public void setVarianceFontColor(Color color) {
        varianceTextField.setForeground(color);
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


    public AssessmentRecordController getController() {
        return controller;
    }
}
