package com.inovaItSys.app.controller;

import com.inovaItSys.app.db.GradeDataAccess;
import com.inovaItSys.app.tm.Grade;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HomeViewController {
    @FXML
    public ImageView imvAddStudent;
    @FXML
    public ImageView imvAddSubject;
    @FXML
    public ImageView imvTranscript;
    @FXML
    public AnchorPane root;
    public Label lblAddSubject;
    public Label lblAddStudent;
    public Label lblTranscript;
    public ImageView imvRemove;
    public Label lblRemoveStudent;


    public void initialize(){
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), root);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
        List<Grade> gradelist = new ArrayList<>(){};
        gradelist.add(new Grade("A+", 100, 85, 4.2));
        gradelist.add(new Grade("A", 84, 75, 4.0));
        gradelist.add(new Grade("A-", 74, 70, 3.7));
        gradelist.add(new Grade("B+", 69, 65, 3.3));
        gradelist.add(new Grade("B", 64, 60, 3.0));
        gradelist.add(new Grade("B-", 59, 55, 2.7));
        gradelist.add(new Grade("C+", 54, 50, 2.3));
        gradelist.add(new Grade("C", 49, 45, 2.0));
        gradelist.add(new Grade("C-", 44, 40, 1.5));
        gradelist.add(new Grade("D",39 , 35, 1));
        gradelist.add(new Grade("F", 34, 0, 0));

        try {
            if(!GradeDataAccess.doGradesExist()){
                for (Grade grade : gradelist) {
                    GradeDataAccess.addGrades(grade);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    @FXML
    private void navigate(MouseEvent event) throws IOException {

        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            AnchorPane view = null;
            switch (icon.getId()) {
                case "imvAddStudent":
                    view = FXMLLoader.load(this.getClass().getResource("/view/StudentView.fxml"));
                    break;
                case "imvAddSubject":
                    view = FXMLLoader.load(this.getClass().getResource("/view/SubjectView.fxml"));
                    break;
                case "imvTranscript":
                    view = FXMLLoader.load(this.getClass().getResource("/view/ResultView.fxml"));
                    break;
                case "imvRemove":
                    view = FXMLLoader.load(this.getClass().getResource("/view/RemoveStudentView.fxml"));
                    break;
            }

            if (view != null) {
                Scene subScene = new Scene(view);
                Stage primaryStage = (Stage) root.getScene().getWindow();
                primaryStage.setResizable(true);

                primaryStage.setScene(subScene);
                primaryStage.sizeToScene();
                primaryStage.centerOnScreen();
                TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();
                Platform.runLater(()-> primaryStage.setResizable(false));
            }
        }
    }
}