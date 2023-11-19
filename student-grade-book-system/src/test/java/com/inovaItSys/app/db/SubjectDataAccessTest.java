package com.inovaItSys.app.db;

import com.inovaItSys.app.tm.Subject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class SubjectDataAccessTest {

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
    void addNewSubject() throws SQLException {
        assertDoesNotThrow(()->{
            SubjectDataAccess.addNewSubject(new Subject("S001", "Maths", 1.5));
            SubjectDataAccess.addNewSubject(new Subject("S002", "Sinhala", 4));
        });
        assertThrows(SQLException.class, ()-> SubjectDataAccess
                .addNewSubject(new Subject("S001", "Maths", 1.5)));
    }

    @Test
    void deleteSubject() throws SQLException {
        SubjectDataAccess.addNewSubject(new Subject("S001", "Maths", 1.5));
        int size = SubjectDataAccess.getAllSubjects().size();
        assertDoesNotThrow(()-> {
            SubjectDataAccess.deleteSubject("S001");
            assertEquals(size - 1, SubjectDataAccess.getAllSubjects().size());
        });
    }
}