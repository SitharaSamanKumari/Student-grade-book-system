package com.inovaItSys.app.db;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class SingleDatabaseConnection {
    private static SingleDatabaseConnection instance;
    private final Connection connection;

    private SingleDatabaseConnection() {
        try {
            Properties properties = new Properties();
            properties.load(this.getClass().getResourceAsStream("/application.properties"));
            String username = properties.getProperty("app.datasource.username");
            String password = properties.getProperty("app.datasource.password");
            String url = properties.getProperty("app.datasource.url");
            connection = DriverManager.getConnection(url, username, password);
//            generateSchema();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static SingleDatabaseConnection getInstance() {
        return (instance == null) ? (instance = new SingleDatabaseConnection()) : instance;
    }

    private void generateSchema() throws Exception {
        URL url = getClass().getResource("/schema.sql");
        Path path = Paths.get(url.toURI());
        String dbScript = Files.readAllLines(path).stream()
                .reduce((prevLine, currentLine) -> prevLine + currentLine).get();
        connection.createStatement().execute(dbScript);
    }

    public Connection getConnection() {
        return connection;
    }
}

