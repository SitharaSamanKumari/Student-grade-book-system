package com.inovaItSys.app;

import com.inovaItSys.app.db.GradeDataAccess;
import com.inovaItSys.app.db.SingleDatabaseConnection;
import com.inovaItSys.app.tm.Grade;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppInitializer extends Application {

    public static void main(String[] args) throws SQLException {
        try(Connection connection = SingleDatabaseConnection.getInstance().getConnection();){
            launch(args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {
        AnchorPane root = FXMLLoader.load(this.getClass().getResource("/view/HomeView.fxml"));
        Scene mainScene = new Scene(root);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Student GradeBook");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
