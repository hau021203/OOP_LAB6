package com.mycompany.studentmanagement;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends JFrame {

    private JButton TotalStudent, NewStudent, Refresh;
    private Studentmanager a;
    private JTable table;
    private DefaultTableModel model;
    private JPanel layout;

    MainFrame(Studentmanager a) {
        setTitle("Student Management");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.a = a;

        TotalStudent = new JButton("Check total Student");
        NewStudent = new JButton("New Student");
        Refresh = new JButton("Refresh");
        model = new DefaultTableModel();
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        layout = new JPanel();
        model.addColumn("Student ID");
        model.addColumn("First Name");
        model.addColumn("Last Name");
        model.addColumn("Gender");
        model.addColumn("School Stage");
        layout.add(TotalStudent);
        layout.add(NewStudent);
        layout.add(Refresh);
        add(layout, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        TotalStudent.addActionListener(e -> TotalStudent());
        NewStudent.addActionListener(e -> {
            new NewStudentDialog(a).setVisible(true);
        });
        table.addKeyListener(new CustomKeyListener());
        Refresh.addActionListener(e -> FillInStudentTable());
        table.getSelectedRow();
        table.getSelectedRow();
        pack();
        setLocationRelativeTo(null);
        load();
    }

    class CustomKeyListener implements KeyListener {

        public void keyTyped(KeyEvent e) {
        }

        public void keyReleased(KeyEvent e) {
        }

        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                model.removeRow(table.getSelectedRow());
            }
            FillInStudentTable();
            save();
        }
    }

    public void TotalStudent() {
        JOptionPane.showMessageDialog(this, "There are " + a.getListStudent().size() + " students in the database");

    }
    ArrayList<String[]> dataList = new ArrayList<>();

    public void FillInStudentTable() {

        for (int i = model.getRowCount(); i < a.getListStudent().size(); i++) {
            dataList.add(new String[]{a.getListStudent().get(i).getStudentID(), a.getListStudent().get(i).getFirstName(), a.getListStudent().get(i).getLastName(), a.getListStudent().get(i).getGender(), a.getListStudent().get(i).getSchoolStage()});
        }
        for (int i = model.getRowCount(); i < dataList.size(); i++) {

            model.addRow(dataList.get(i));

        }
        save();

    }

    private void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Student.dat"))) {
            oos.writeObject(dataList);
            oos.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving employees to file.");
        }
    }

    private void load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Student.dat"))) {
            dataList = (ArrayList<String[]>) ois.readObject();

            for (String[] row : dataList) {
                a.getListStudent().add(new Student("", "", "", "", 1, "", ""));
                model.addRow(row);

            }
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error loading employees from file.");
        }
    }

}
