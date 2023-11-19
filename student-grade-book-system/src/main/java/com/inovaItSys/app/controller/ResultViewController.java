package com.inovaItSys.app.controller;

import com.inovaItSys.app.db.StudentDataAccess;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import com.inovaItSys.app.tm.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ResultViewController   {

    public Button btnBack;
    public TextField txtName;
    public TableView<Result> tblResult;
    public Button btnTranscript;
    public ComboBox<Student> cmbId;

    public void initialize(){
        txtName.setDisable(true);
        String[] columns = {"code","subjectName","gpa","marks","grade"};
        /*Result table mapping*/
        for (int i = 0; i < columns.length ; i++) {
            tblResult.getColumns().get(i).setCellValueFactory(new PropertyValueFactory<>(columns[i]));
        }


    }
    public void cmbIdOnAction(ActionEvent actionEvent) {

    }
}
