package com.inovaItSys.app.controller;

import com.inovaItSys.app.db.StudentDataAccess;
import com.inovaItSys.app.db.SubjectDataAccess;
import com.inovaItSys.app.tm.Student;
import com.inovaItSys.app.tm.Subject;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentViewController   {

    public TextField txtID;
    public TextField txtFirstName;
    public TextField txtLastName;
    public TextField txtSearchSubject;
    public ListView<Subject> lvSubjects;
    public ListView<Subject> lvSelectedSubjects;
    public Button btnNext;
    public Button btnBack;
    public Button btnRemove;
    public Button btnSave;
    public Button btnDelete;
    public Button btnNewStudent;

    public void initialize(){
        ObservableList<Subject> subjects = lvSubjects.getItems();
        try{
            subjects.addAll(SubjectDataAccess.getAllSubjects());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Fail to load subjects").show();
            e.printStackTrace();
        }
    }
    public void txtSearchSubjectOnAction(ActionEvent actionEvent) {
    }

    public void btnNextOnAction(ActionEvent actionEvent) {
        ObservableList<Subject> subjectList = lvSubjects.getItems();
        ObservableList<Subject> selectedSubjectList = lvSelectedSubjects.getItems();

        Subject selectedItem = lvSubjects.getSelectionModel().getSelectedItem();
        subjectList.remove(selectedItem);
        selectedSubjectList.add(selectedItem);
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        ObservableList<Subject> subjectList = lvSubjects.getItems();
        ObservableList<Subject> selectedSubjectList = lvSelectedSubjects.getItems();

        Subject selectedItem = lvSelectedSubjects.getSelectionModel().getSelectedItem();
        subjectList.add(selectedItem);
        selectedSubjectList.remove(selectedItem);

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnNewStudentOnAction(ActionEvent actionEvent) {
        for (TextField textField : new TextField[]{txtFirstName, txtLastName, txtID,txtSearchSubject})
            textField.clear();
        lvSubjects.getSelectionModel().clearSelection();
        lvSelectedSubjects.getItems().removeAll();
        txtID.requestFocus();
    }
    public void btnRemoveOnAction(ActionEvent actionEvent) {
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        if(!isStudentDataValid()) return;
        String id = txtID.getText().strip();
        String firstName = txtFirstName.getText().strip();
        String lastName = txtLastName.getText().strip();
        List<Subject> subjectList = new ArrayList<>();
        for (Subject subject : lvSelectedSubjects.getItems()) {
            subjectList.add(subject);
        }
        try {
            StudentDataAccess.addNewStudent(new Student(id,firstName,lastName), subjectList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private boolean isStudentDataValid() {
        String id = txtID.getText().strip();
        String firstName = txtFirstName.getText().strip();
        String lastName = txtLastName.getText().strip();

        if (!id.matches("S\\d{2}")) {
            txtID.requestFocus();
            txtID.selectAll();
            return false;
        }else if (!firstName.matches("[A-Za-z ]{2,}")) {
            txtFirstName.requestFocus();
            txtFirstName.selectAll();
            return false;
        }else if (!lastName.matches("[A-Za-z ]{2,}")) {
            txtLastName.requestFocus();
            txtLastName.selectAll();
            return false;
        }
        return true;
    }


}
