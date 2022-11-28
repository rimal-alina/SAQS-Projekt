package view;

import controller.AssessmentRecordController;
import controller.StationController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

// view extends java class JDialog (which is modal)
public class StationView extends JDialog {
    // add parent which called this view as class variable so we can hand over content to parent
    private AssessmentRecordController parent;
    // create objects which contents will be changed during runtime as class variables
    private JTextField stationIdTextField;
    private JTextField targetTextField;
    private JLabel messageLabel;

    public StationView(AssessmentRecordController parent) {
        super(); // call constructor of super class
        this.parent = parent;
        init();
    }
    
    private void init() {
        // create labels
        JLabel stationIdLabel = new JLabel("Station ID");
        JLabel targetLabel = new JLabel("Target");
        messageLabel = new JLabel(" ");
        // create text fields
        stationIdTextField = new JTextField();
        stationIdTextField.setEditable(false);
        targetTextField = new JTextField();
        // create buttons
        JButton saveButton = new JButton("Speichern");
        JButton cancelButton = new JButton("Abbrechen");

        // add elements to ui/frame      
        this.setTitle("Station - Stammdaten");
        this.setLayout(new BorderLayout(20, 0)); // horizontal and vertical gap
        JPanel valuePanel = new JPanel();
        valuePanel.setLayout(new GridLayout(2, 2)); // 2 rows, 2 columns
        valuePanel.add(stationIdLabel);
        valuePanel.add(stationIdTextField);
        valuePanel.add(targetLabel);
        valuePanel.add(targetTextField);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(valuePanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(messageLabel, BorderLayout.SOUTH);

        // set some min size
        valuePanel.setPreferredSize(new Dimension(400, 200));

        // add listener to objects
        StationController statListener = new StationController(this);
        saveButton.addActionListener(statListener);
        cancelButton.addActionListener(statListener);

        // pack, visualize
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }


    //
    // Set text of TextFields
    //
    public void setMessageLabelText(String s) {
        messageLabel.setText(s);
    }

    public void setStationIdText(String s) {
        stationIdTextField.setText(s);
    }


    //
    // Get text of TextFields
    //
    public String getStationIdText() {
        return stationIdTextField.getText();
    }

    public String getTargetText() {
        return targetTextField.getText();
    }


    public AssessmentRecordController getParentController() {
        return parent;
    }

}
