package com.inovaItSys.app.db;

//import com.inovaItSys.app.to.GradesList;

import com.inovaItSys.app.tm.Grade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GradeDataAccess {
    private static final PreparedStatement STM_ADD_GRADES;
    private static final PreparedStatement STM_GET_ALL_GRADES;
    static {
        try {
            Connection connection = SingleDatabaseConnection.getInstance().getConnection();
            STM_ADD_GRADES = connection.prepareStatement("INSERT INTO grade (grade_letter, upper_mark, lower_mark, points)  VALUE (?,?,?,?)");
            STM_GET_ALL_GRADES = connection.prepareStatement("SELECT * FROM grade;");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public GradeDataAccess(List<Grade> gradeList) {
//        this.GRADE_LIST = gradeList;
//    }

//    public static void addGrades( Grade grade) throws SQLException {

//            STM_ADD_GRADES.setString(1,grade.getGradeLetter());
//            STM_ADD_GRADES.setInt(2,grade.getUpperMark());
//            STM_ADD_GRADES.setInt(3,grade.getLowerMark());
//            STM_ADD_GRADES.setDouble(4,grade.getPoints());
//            STM_ADD_GRADES.executeUpdate();

//    }
//    public static List<Grade> getAllGrades( Grade grade) throws SQLException {
//
//        ResultSet rst = STM_GET_ALL_GRADES.executeQuery();
//        while (rst.next()){
//            String gradeLetter = rst.getString("grade_letter");
//
//        }
//        return  null;
//
//    }


}
