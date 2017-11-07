/* B1601563
 */
package b1601563;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Registration Form is for use in Lab Test 2 (BIT203)
 *
 * @author ngsm
 * @author celine_yin
 */
public class RegistrationForm extends JFrame implements ActionListener{

    JTextField firstnameTF, lastnameTF;
    JComboBox salutationCB;
    JRadioButton maleRB, femaleRB;
    JButton submitBtn, cancelBtn;
    JCheckBox termsCB;
    ButtonGroup genderBG;

    /**
     * Constructor
     */
    public RegistrationForm() {
        initComponents();
        setTitle("Business Card Registration");
        setSize(200, 200);
        setResizable(false);
    }

    /**
     * Test the class
     *
     * @param args
     */
    public static void main(String[] args) {
        JFrame f = new RegistrationForm();
        f.pack();
        f.setVisible(true);

    }

    /**
     * To initialise the components of the Registration Form
     */
    public void initComponents() {
        // set up the labels
        JPanel labelsPanel = new JPanel(new GridLayout(0, 1, 0, 10));
        labelsPanel.add(new JLabel("Salutation"));
        labelsPanel.add(new JLabel("First Name"));
        labelsPanel.add(new JLabel("Last Name"));

        // set up the text fields
        JPanel dataPanel = new JPanel(new GridLayout(0, 1, 0, 6));
        firstnameTF = new JTextField(10);
        lastnameTF = new JTextField(10);

        String[] salutation = {"Dr", "Ms", "Mr"};
        salutationCB = new JComboBox(salutation);
        dataPanel.add(salutationCB);
        dataPanel.add(firstnameTF);
        dataPanel.add(lastnameTF);

        // infoPanel is in the top part of the JFrame
        JPanel infoPanel = new JPanel(new FlowLayout());
        infoPanel.add(labelsPanel);
        infoPanel.add(dataPanel);

        // Set up the radio buttons
        JPanel optionsPanel = new JPanel(new FlowLayout());
        genderBG = new ButtonGroup();
        femaleRB = new JRadioButton("Female");
        maleRB = new JRadioButton("Male");
        genderBG.add(femaleRB);
        genderBG.add(maleRB);

        optionsPanel.add(femaleRB);
        optionsPanel.add(maleRB);

        cancelBtn = new JButton("Cancel");
        submitBtn = new JButton("Submit");
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(cancelBtn);
        buttonsPanel.add(submitBtn);
        termsCB = new JCheckBox("I have read the terms and conditions");

        // bottomPanel is for the bottom half of the frame
        JPanel bottomPanel = new JPanel(new GridLayout(0, 1));
        bottomPanel.add(optionsPanel);
        bottomPanel.add(termsCB);
        bottomPanel.add(buttonsPanel);
        
        // register components by adding action listeners
        salutationCB.addActionListener(this);
        submitBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        Container cp = this.getContentPane();
        cp.setLayout(new GridLayout(0, 1));
        cp.add(infoPanel);
        cp.add(bottomPanel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String salutation = salutationCB.getSelectedItem().toString();
        String fName = firstnameTF.getText();
        String lName = lastnameTF.getText();
        String gender = "unknown gender";

        if (salutationCB.getSelectedItem().equals("Mr"))
            maleRB.setSelected(true);
        else if (salutationCB.getSelectedItem().equals("Ms"))
            femaleRB.setSelected(true);
  
        if (ae.getSource() == cancelBtn) {
            System.exit(0);
        } else if (ae.getSource() == submitBtn) {
            if (termsCB.isSelected() == false) 
                JOptionPane.showMessageDialog(this, "Please read the terms and conditions.");
            
            if (fName.equals("") || lName.equals(""))
                JOptionPane.showMessageDialog(this, "Please enter both the first name and last name.");
                
            if (!fName.equals("") && !lName.equals("") && termsCB.isSelected() == true) {
                if (maleRB.isSelected() == true)
                    gender = "male";
                else if (femaleRB.isSelected() == true)
                    gender = "female";
                
                Person newP = new Person(salutation, fName, lName, gender);
                
                // Task 7 previous code
                // JOptionPane.showMessageDialog(this, newP.toString());
                
                BusinessCardDialog bcDialog = new BusinessCardDialog(this, true, newP);
                bcDialog.setVisible(true);
                
                firstnameTF.setText("");
                lastnameTF.setText("");
                genderBG.clearSelection();
                termsCB.setSelected(false);
                salutationCB.setSelectedIndex(0);
            }

        }
    }

}
