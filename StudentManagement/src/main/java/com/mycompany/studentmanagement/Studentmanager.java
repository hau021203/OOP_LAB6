package com.mycompany.studentmanagement;

import java.util.ArrayList;
import java.util.List;

public class  Studentmanager extends Student {

    private ArrayList<Student> listStudent = new ArrayList<>();
    Studentmanager a;
    
    public  Studentmanager() {
    }

    public Student findStudentById(String studentID) {
        for (int i = 0; i < getListStudent().size(); i++) {
            if (getListStudent().get(i).getStudentID().equalsIgnoreCase(studentID)) {
                return getListStudent().get(i);
            }
        }
        return null;
    }

    public  boolean addStudent(Student stu) {
        if (findStudentById(stu.getStudentID()) == null) {
            getListStudent().add(stu);
            return true;
        }
        return false;

    }

    public ArrayList<Student> getListStudent() {
        return listStudent;
    }

}
