package com.inovaItSys.app.controller;

import com.inovaItSys.app.db.GradeDataAccess;
import com.inovaItSys.app.db.StudentDataAccess;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import com.inovaItSys.app.tm.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ResultViewController   {

    public Button btnBack;
    public TextField txtName;
    public TableView<Result> tblResult;
    public Button btnTranscript;
    public ComboBox<Student> cmbId;
    public Label lblOverallGpa;

//    private double cgpa = 0;
//    private double totalPoints = 0;

    public void initialize(){
        lblOverallGpa.setText("CGPA: ");

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
            tblResult.getItems().clear();
//            this.gpa = 0;
//            lblOverallGpa.setText(String.valueOf(gpa));
            txtName.setText(selectedStudent.getFirstName()+" " + selectedStudent.getLastName());
            ObservableList<Result> table = tblResult.getItems();
            List<Subject> enrolledSubjectList = StudentDataAccess.getEnrolledSubjects(selectedStudent.getId());
            for (Subject enrollSubject : enrolledSubjectList) {
                TextField mark = new TextField();
                Label displayGrade = new Label();
                Result result = new Result(enrollSubject.getCode(), enrollSubject.getSubjectName(), enrollSubject.getGpa(),mark,displayGrade);
                table.add(result);
                mark.textProperty().addListener((e)->{
                    try {
                        String subjectMark = mark.getText().strip();
                        if(subjectMark.isBlank()
                                || !subjectMark.matches("^\\d{1,3}([.]\\d{1,2})?$")
                                ||(Double.valueOf(subjectMark)>100.0)){
                            displayGrade.setText("-");
                        }else{
                            Grade resultGrade = GradeDataAccess.getResultGrade(Double.valueOf(subjectMark));
                            displayGrade.setText(resultGrade.getGradeLetter());
                            double cgpa = getGpa();
//                            double points = resultGrade.getPoints();
//                            double gpa = result.getGpa();
//                            double total = points*gpa;
//                            cgpa+=total;
                            lblOverallGpa.setText(String.format("CGPA: %.2f",cgpa));
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                });
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            lblOverallGpa.setText(String.valueOf(getGpa()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public double getGpa() throws SQLException {
        double totalCredit = 0;
        double totalSubjectCredit = 0;
        double cgpa = 0;
        ObservableList<Result> results = tblResult.getItems();
        for (Result result : results) {
            double subjectCredit = result.getGpa() * GradeDataAccess.getGradePoint(result.getGrade().getText().strip());
            if(result.getMarks().getText()!=null){
                totalSubjectCredit+=subjectCredit;
                totalCredit+=result.getGpa();
            }
        }
        return (totalSubjectCredit/totalCredit);
    }


    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        HomeViewController.navigateToHome(btnBack);
    }

    public void btnTranscriptOnAction(ActionEvent actionEvent) {

    }
}
