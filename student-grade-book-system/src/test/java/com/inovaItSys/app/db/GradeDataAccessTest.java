package com.inovaItSys.app.db;

import com.inovaItSys.app.to.Grade;
import com.inovaItSys.app.to.Subject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GradeDataAccessTest {
    @BeforeEach
    void setUp() throws SQLException {
        SingleDatabaseConnection.getInstance().getConnection().setAutoCommit(false);
    }

    @AfterEach
    void tearDown() throws SQLException {
        SingleDatabaseConnection.getInstance().getConnection().rollback();
        SingleDatabaseConnection.getInstance().getConnection().setAutoCommit(true);
    }

    @Test
    void addGrades() {
        List<Grade> gradelist = new ArrayList<>();
        gradelist.add(new Grade("A", 80, 100));
        gradelist.add(new Grade("A+", 80, 75));
        assertDoesNotThrow(()->{
            GradeDataAccess.addGrades(gradelist);
        });
        assertThrows(SQLException.class, ()-> GradeDataAccess.addGrades(gradelist));
    }
}