package com.inovaItSys.app.controller;

import com.inovaItSys.app.db.StudentDataAccess;
import com.inovaItSys.app.tm.Student;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RemoveStudentViewController {
    public TextField txtSearch;
    public TableView<Student> tblStudentData;
    public Button btnDelete;
    public Button btnBack;
    public void initialize(){
        tblStudentData.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblStudentData.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tblStudentData.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("lastName"));

        try {
            tblStudentData.getItems().addAll(StudentDataAccess.getAllStudent());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        txtSearch.textProperty().addListener(e->{
            tblStudentData.getItems().clear();
            try {
                tblStudentData.getItems().addAll(StudentDataAccess.findStudents(txtSearch.getText()));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void btnDeleteStudentOnAction(ActionEvent actionEvent) {
        Student selectedStudent = tblStudentData.getSelectionModel().getSelectedItem();
        try {
            StudentDataAccess.deleteStudent(selectedStudent.getId());
            tblStudentData.getItems().remove(selectedStudent);

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete student").show();

        }


    }


    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        HomeViewController.navigateToHome(btnBack);
    }

}
