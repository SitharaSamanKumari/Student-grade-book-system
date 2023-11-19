package com.inovaItSys.app;

import com.inovaItSys.app.db.SingleDatabaseConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        /*Get database Connection When App Launch*/
        Connection connection = SingleDatabaseConnection.getInstance().getConnection();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        AnchorPane root = FXMLLoader.load(this.getClass().getResource("/view/SubjectView.fxml"));
        Scene mainScene = new Scene(root);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Student GradeBook");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
