package com.inovaItSys.app.controller;

import com.inovaItSys.app.to.Subject;

import java.sql.SQLException;

public abstract class GradeBookSystem {
    public void addNewStudent(Subject subject) throws SQLException {
    }
    public void deleteSubject(String code) throws SQLException {
    }

    void addNewSubject() {
    }

    double generateOverallGpa() {
        return 0;
    }

    void generateTranscript() {
    }
}
