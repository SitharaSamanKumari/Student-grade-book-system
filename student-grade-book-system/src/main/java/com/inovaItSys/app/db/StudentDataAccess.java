package com.inovaItSys.app.db;

import com.inovaItSys.app.to.Student;
import com.inovaItSys.app.to.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDataAccess {
    private static final PreparedStatement STM_STUDENT_INSERT;
    private static final PreparedStatement STM_ENROLL_SUBJECT_INSERT;
    private static final PreparedStatement STM_GET_ALL_STUDENT;
    static {
        try {
            Connection connection = SingleDatabaseConnection.getInstance().getConnection();
            STM_STUDENT_INSERT = connection.prepareStatement("INSERT INTO student(id, first_name, last_name) VALUES (?,?,?)");
            STM_ENROLL_SUBJECT_INSERT = connection.prepareStatement("INSERT INTO subject_enroll(student_id, subject_code) VALUES (?,?)");
            STM_GET_ALL_STUDENT = connection.prepareStatement("SELECT * FROM student ORDER BY id ");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addNewStudent(Student student,List<Subject> subjectList) throws SQLException {

        SingleDatabaseConnection.getInstance().getConnection().setAutoCommit(false);
        try{
            /*Save new student */
            STM_STUDENT_INSERT.setString(1, student.getId());
            STM_STUDENT_INSERT.setString(2, student.getFirstName());
            STM_STUDENT_INSERT.setString(3, student.getLastName());
            STM_STUDENT_INSERT.executeUpdate();

            /*Save new enrollment*/
            for (Subject subject : subjectList) {
                STM_ENROLL_SUBJECT_INSERT.setString(1,student.getId());
                STM_ENROLL_SUBJECT_INSERT.setString(2,subject.getCode());
                STM_STUDENT_INSERT.executeUpdate();
            }
            SingleDatabaseConnection.getInstance().getConnection().commit();
        }catch (Exception e){
            SingleDatabaseConnection.getInstance().getConnection().rollback();
            e.printStackTrace();
        }finally {
            SingleDatabaseConnection.getInstance().getConnection().setAutoCommit(true);
        }

    }

    }

