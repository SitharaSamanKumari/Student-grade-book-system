package com.inovaItSys.app.db;

import com.inovaItSys.app.tm.Student;
import com.inovaItSys.app.tm.Subject;

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
    private static final PreparedStatement STM_GET_ENROLL_SUBJECTS;
    static {
        try {
            Connection connection = SingleDatabaseConnection.getInstance().getConnection();
            STM_STUDENT_INSERT = connection.prepareStatement("INSERT INTO student(id, first_name, last_name) VALUES (?,?,?)");
            STM_ENROLL_SUBJECT_INSERT = connection.prepareStatement("INSERT INTO subject_enroll(student_id, subject_code) VALUES (?,?)");
            STM_GET_ALL_STUDENT = connection.prepareStatement("SELECT * FROM student");
            STM_GET_ENROLL_SUBJECTS = connection.prepareStatement("SELECT code,subject_name,gpa FROM\n" +
                    "                                 (SELECT * FROM subject INNER JOIN subject_enroll ON code = subject_code) as es \n" +
                    "                             WHERE student_id=?;\n");
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
                STM_ENROLL_SUBJECT_INSERT.executeUpdate();
            }


            SingleDatabaseConnection.getInstance().getConnection().commit();
        }catch (Exception e){
            SingleDatabaseConnection.getInstance().getConnection().rollback();
            e.printStackTrace();
        }finally {
            SingleDatabaseConnection.getInstance().getConnection().setAutoCommit(true);
        }

    }
    /*Get all enrolled data for a student*/
    public static List<Subject> getEnrolledSubjects(String id) throws SQLException {
        STM_GET_ENROLL_SUBJECTS.setString(1,id);
        ResultSet rst = STM_GET_ENROLL_SUBJECTS.executeQuery();
        List<Subject> enrollSubject = new ArrayList<>();
        while (rst.next()){
            String code = rst.getString("code");
            String subjectName = rst.getString("subject_name");
            double gpa = rst.getDouble("gpa");
            enrollSubject.add(new Subject(code,subjectName,gpa));
        }
        return enrollSubject;
    }

    public static List<Student> getAllStudent() throws SQLException {
        List<Student> studentList = new ArrayList<>();
        ResultSet rst = STM_GET_ALL_STUDENT.executeQuery();
        while (rst.next()){
            String id = rst.getString("id");
            String firstName = rst.getString("first_name");
            String lastName = rst.getString("last_name");
            studentList.add(new Student(id,firstName,lastName));
        }
    return studentList;
    }

    }

