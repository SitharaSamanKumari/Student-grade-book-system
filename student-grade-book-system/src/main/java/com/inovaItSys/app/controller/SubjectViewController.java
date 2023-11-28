package com.inovaItSys.app.controller;

import com.inovaItSys.app.db.SubjectDataAccess;
import com.inovaItSys.app.tm.Subject;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.sql.SQLException;

public class SubjectViewController   {
    public TextField txtCode;
    public TextField txtSubjectName;
    public TextField txtGpa;
    public Button btnSave;
    public Button btnDelete;
    public Button btnNewSubject;
    public TableView<Subject> tblSubject;
    public Button btnBack;
    public void initialize() {
        for (TextField textField : new TextField[]{txtGpa, txtSubjectName, txtCode}){
            textField.setDisable(true);
        }

        tblSubject.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblSubject.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        tblSubject.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("gpa"));

        tblSubject.setEditable(false);
        btnSave.setDefaultButton(true);
        try {
            tblSubject.getItems().addAll(SubjectDataAccess.getAllSubjects());
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load Subjects, try later!").show();
        }
        tblSubject.getSelectionModel().selectedItemProperty().addListener((ov, prev, cur) ->{
            if (cur != null){
                txtCode.setText(cur.getCode());
                txtSubjectName.setText(cur.getSubjectName());
                txtGpa.setText(String.format("%s",cur.getGpa()));
            }else{
                btnDelete.setDisable(true);
            }
        });
        Platform.runLater(txtCode::requestFocus);
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (!isDataValid()) return;

        try {
            Subject subject = new Subject(txtCode.getText(),
                txtSubjectName.getText().strip(), Double.valueOf(txtGpa.getText().strip()));
            SubjectDataAccess.addNewSubject(subject);
            tblSubject.getItems().add(subject);
            btnNewSubject.fire();

        } catch (SQLException ex) {
            new Alert(Alert.AlertType.ERROR, "Failed to save the subject, try again").show();
            ex.printStackTrace();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Subject selectedSubject = tblSubject.getSelectionModel().getSelectedItem();

        try{
           if(SubjectDataAccess.isEnrolledSubject(selectedSubject.getCode())){
               new Alert(Alert.AlertType.ERROR,"Some students has enrolled this subject.").show();
           }else{
               SubjectDataAccess.deleteSubject(selectedSubject.getCode());
               tblSubject.getItems().remove(selectedSubject);
               if(tblSubject.getItems().isEmpty()) btnNewSubject.fire();
           }
       } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnNewSubjectOnAction(ActionEvent actionEvent) {
        for (TextField textField : new TextField[]{txtGpa, txtSubjectName, txtCode}){
            textField.setDisable(false);
            textField.clear();
        }
        tblSubject.getSelectionModel().clearSelection();
        txtCode.requestFocus();
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        HomeViewController.navigateToHome(btnBack);
    }
    private boolean isDataValid() {
        String code = txtCode.getText().strip();
        String subjectName = txtSubjectName.getText().strip();
        String gpa = txtGpa.getText().strip();

        if (!code.matches("^([a-zA-Z1-9]{1,})$")) {
            txtCode.requestFocus();
            txtCode.selectAll();
            return false;
        }else if (!subjectName.matches("^[A-Za-z ]{2,}$")) {
            txtSubjectName.requestFocus();
            txtSubjectName.selectAll();
            return false;
        }else if (!gpa.matches("^[1-5]([.][0-9]{1,2})?$")){
            txtGpa.requestFocus();
            txtGpa.selectAll();
            return false;
        }
        return true;
    }
}
