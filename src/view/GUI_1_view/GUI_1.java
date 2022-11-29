package view.GUI_1_view;

import model.Station;
import model.StationListModel;
import ui_controller.HelperFunctions;
import ui_controller.GUI_1_ui_controller.GUI_1KeyListener;
import ui_controller.GUI_1_ui_controller.GUI_1SelectionListener;
import ui_controller.GUI_1_ui_controller.GUI_1ListChangeListener;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.Color;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import business_controller.DataPersistenceFile;

// view extends java class JFrame
public class GUI_1 extends JFrame {
    // create objects which contents will be changed during runtime as class variables
    private JList<Station> stationList;
    private JTextField stationIdTextField;
    private JTextField dateTextField;
    private JTextField targetTextField;
    private JTextField actualTextField;
    private JTextField varianceTextField;
    private JLabel messageLabel;
    
    public GUI_1() {
        super(); // call constructor of super class
        init();
    }
    
    private void init() {
        // create empty list
        this.stationList = new JList<Station>();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(this.stationList);  // add scrollpane to list
        setStationList(StationListModel.getInstance().getStationList());
        // add Listener for station list changes
        GUI_1ListChangeListener stationListChangeListener = new GUI_1ListChangeListener(this);
        DataPersistenceFile.getInstance().addPropertyChangeListener(stationListChangeListener);
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

        // add elements to ui/frame      
        this.setTitle("Ice Cream Particulate GUI 1");
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
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(messageLabel, BorderLayout.SOUTH);

        // set some min size
        scrollPane.setPreferredSize(new Dimension(150, 300));
        valuePanel.setPreferredSize(new Dimension(250, 300));

        // add listener to objects
        stationList.addListSelectionListener(new GUI_1SelectionListener(this));
        actualTextField.addKeyListener(new GUI_1KeyListener(this));
        dateTextField.addKeyListener(new GUI_1KeyListener(this));

        // pack, visualize
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        // remove station list change listener on close
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing (WindowEvent e){
                DataPersistenceFile.getInstance().removePropertyChangeListener(stationListChangeListener);
                e.getWindow().dispose();
            }
        });
    }

    private void disableAllTextFields() {
        stationIdTextField.setEditable(false);
        dateTextField.setEditable(false);
        targetTextField.setEditable(false);
        actualTextField.setEditable(false);
        varianceTextField.setEditable(false);
    }

    // functions which are used by ui_controller package

    public void setStationList(List <Station> valueList) {
        int currentIndex = stationList.getSelectedIndex();
        DefaultListModel listModel = new DefaultListModel();
        for (int i=0; i<valueList.size(); i++) {
            listModel.addElement(valueList.get(i));
        }
        stationList.setModel(listModel);
        // make sure to have right object selected after list update
        stationList.setSelectedIndex(currentIndex);
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

    public void updateCurrentStation (){
        if(getSelectedStationListIndex() >= 0) {
            Station s = (Station) getSelectedStationListObject();
            stationIdTextField.setText(s.getStationId());
            dateTextField.setText(s.getDate());
            targetTextField.setText(s.getTarget() + "");
            // check if actual value was set by user
            if (s.getActual() >= 0) {
                actualTextField.setText(s.getActual() + "");
            } else {
                // inizial actual value is -1, so if actual value is < 0, no value was set at all
                actualTextField.setText("");
            }
            varianceTextField.setText(s.getVariance() + "");
            // reset message label due to new action
            messageLabel.setText(" "); 
            // change font color according to variance
            setVarianceFontColor(HelperFunctions.getCalculatedFontColor(s.getVariance(), s.getTarget()));
            //  make possible input fields editable
            dateTextField.setEditable(true);
            actualTextField.setEditable(true);
        }           
    }

    public void clearCurrentStation(){
        // nothing selected -> clear textfields
        stationIdTextField.setText("");
        dateTextField.setText("");
        targetTextField.setText("");
        actualTextField.setText("");
        varianceTextField.setText("");
        // change font color according to variance
        varianceTextField.setForeground(HelperFunctions.getCalculatedFontColor(0, 0));
        // if nothing is selected the textfields should not be enabled
        disableAllTextFields();
    }

    //
    // Getter for TextFields
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

    //
    // Setter for TextFields which need to be modified from outside the class
    //

    public void setActualText(String s) {
        actualTextField.setText(s);
    }

    public void setVarianceText(String s) {
        varianceTextField.setText(s);
    }

    public void setMessageLabelText(String s) {
        messageLabel.setText(s);
    }

    public void setVarianceFontColor(Color color) {
        varianceTextField.setForeground(color);
    }
}
