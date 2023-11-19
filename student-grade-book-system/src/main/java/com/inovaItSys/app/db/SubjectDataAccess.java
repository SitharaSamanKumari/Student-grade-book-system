package com.inovaItSys.app.db;

import com.inovaItSys.app.controller.GradeBookSystem;
import com.inovaItSys.app.to.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDataAccess {
    private static final PreparedStatement STM_SUBJECT_INSERT;
    private static final PreparedStatement STM_SUBJECT_DELETE;
    private static final PreparedStatement STM_GET_ALL_SUBJECTS;
    static {
        try {
            Connection connection = SingleDatabaseConnection.getInstance().getConnection();
            STM_SUBJECT_INSERT = connection
                    .prepareStatement("INSERT INTO subject (code, subject_name, gpa) VALUES (?, ?, ?)");
            STM_SUBJECT_DELETE = connection.prepareStatement("DELETE FROM subject WHERE code=?");
            STM_GET_ALL_SUBJECTS = connection.prepareStatement("SELECT * FROM subject ORDER BY code");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addNewSubject(Subject subject) throws SQLException {
        STM_SUBJECT_INSERT.setString(1, subject.getCode());
        STM_SUBJECT_INSERT.setString(2, subject.getSubjectName());
        STM_SUBJECT_INSERT.setDouble(3, subject.getGpa());
        STM_SUBJECT_INSERT.executeUpdate();
    }

    public static  void deleteSubject(String code) throws SQLException {
        STM_SUBJECT_DELETE.setString(1, code);
        STM_SUBJECT_DELETE.executeUpdate();
    }

    public static List<Subject> getAllSubjects() throws SQLException {
        ResultSet rst = STM_GET_ALL_SUBJECTS.executeQuery();
        List<Subject> SubjectList = new ArrayList<>();
        while (rst.next()) {
            String code = rst.getString("code");
            String name = rst.getString("subject_name");
            double gpa = rst.getDouble("gpa");
            SubjectList.add(new Subject(code, name, gpa));
        }
        return SubjectList;
    }
}
