/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.studentmanagement;

import java.awt.FlowLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author pc
 */
public class NewStudentDialog extends JFrame {

    JLabel Id, Year, Name;
    JTextField IdField, Last, Mid, First, YearField;
    JComboBox Stage;
    JRadioButton Male, FeMale;
    JButton Save, Clear, Cancel;
    ButtonGroup gr;
    Studentmanager a;

    public NewStudentDialog(Studentmanager a) {
        setTitle("New Student");
        setSize(370, 200);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        Id = new JLabel("Student ID");
        Name = new JLabel("Last-Mid-First Name");
        Year = new JLabel("Year of Birth");
        IdField = new JTextField(23);
        Last = new JTextField(5);
        Mid = new JTextField(5);
        First = new JTextField(5);
        YearField = new JTextField(12);
        Male = new JRadioButton("Male");
        FeMale = new JRadioButton("FeMale");
        gr = new ButtonGroup();
        gr.add(Male);
        gr.add(FeMale);
        this.a = a;
        String SchoolStage[] = {"Elementary School", "Middle School", "High School"};
        Stage = new JComboBox(SchoolStage);
        Save = new JButton("Save");
        Clear = new JButton("Clear");
        Cancel = new JButton("Cancel");

        //
        add(Id);
        add(IdField);
        add(Name);
        add(Last);
        add(Mid);
        add(First);
        add(Year);
        add(YearField);
        add(Male);
        add(FeMale);
        add(Stage);
        add(Save);
        add(Clear);
        add(Cancel);
        
        Save.addActionListener(e -> Save());
        Clear.addActionListener(e -> Clear());
        Cancel.addActionListener(e -> Cancel());
    }

    public void Save() {
        String studentID = IdField.getText();
        String lastName = Last.getText();
        String midName = Mid.getText();
        String firstName = First.getText();

        int yearOfBirth = Integer.parseInt(YearField.getText());
        String gender = "Female";
        if (Male.isSelected()) {
            gender = "Male";
        }
        String schoolStage = Stage.getSelectedItem().toString();
        Student stu = new Student(studentID, lastName, midName, firstName, yearOfBirth, gender, schoolStage);
        if (a.addStudent(stu)) {

            JOptionPane.showMessageDialog(this, "Adding Successful");
        } else {
            JOptionPane.showMessageDialog(this, "Student ID is already exist");
        }
    }

    public void Clear() {
        IdField.setText("");
        Last.setText("");
        Mid.setText("");
        First.setText("");
        YearField.setText("");
        gr.clearSelection();
    }

    public void Cancel() {
        dispose();
    }

}
