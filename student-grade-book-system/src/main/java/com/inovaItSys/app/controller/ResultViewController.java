package com.inovaItSys.app.controller;

import com.inovaItSys.app.db.StudentDataAccess;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import com.inovaItSys.app.tm.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

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

        try {
            List<Student> allStudent = StudentDataAccess.getAllStudent();
            for (Student student : allStudent) {
                cmbId.getItems().add(student);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void cmbIdOnAction(ActionEvent actionEvent) {

        try {
            Student selectedStudent = cmbId.getSelectionModel().getSelectedItem();
            txtName.setText(selectedStudent.getFirstName()+" " + selectedStudent.getLastName());
            ObservableList<Result> table = tblResult.getItems();
            List<Subject> enrolledSubjectList = StudentDataAccess.getEnrolledSubjects(selectedStudent.getId());
            for (Subject enrollSubject : enrolledSubjectList) {
                TextField mark = new TextField();
                Label displayGrade = new Label();
                Result result = new Result(enrollSubject.getCode(), enrollSubject.getSubjectName(), enrollSubject.getGpa(),mark,displayGrade);
                mark.textProperty().addListener((e)->{
                    if (mark.getText()!=null){
                        double subjectMark = Double.valueOf(mark.getText().strip());

                    }
                });
                table.add(result);


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
