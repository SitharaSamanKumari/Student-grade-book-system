package com.inovaItSys.app.controller;

import com.inovaItSys.app.AppInitializer;
import com.inovaItSys.app.db.GradeDataAccess;
import com.inovaItSys.app.tm.Grade;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
                Platform.runLater(()-> primaryStage.setResizable(false));
            }
        }
    }
    public static void navigateToHome(Node rootNode) throws IOException {
        Parent root = FXMLLoader.load(AppInitializer.class.getResource("/view/HomeView.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (rootNode.getScene().getWindow());
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.centerOnScreen();
        primaryStage.setOnCloseRequest(null);
    }
}