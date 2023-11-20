package com.inovaItSys.app.db;
import com.inovaItSys.app.tm.Grade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GradeDataAccess {
    private static final PreparedStatement STM_ADD_GRADES;
    private static final PreparedStatement STM_GET_ALL_GRADES;
    private static final PreparedStatement STM_GET_RESULT_GRADE;
    static {
        try {
            Connection connection = SingleDatabaseConnection.getInstance().getConnection();
            STM_ADD_GRADES = connection.prepareStatement("INSERT INTO grade (grade_letter, upper_mark, lower_mark, points)  VALUE (?,?,?,?)");
            STM_GET_ALL_GRADES = connection.prepareStatement("SELECT * FROM grade;");
            STM_GET_RESULT_GRADE = connection.prepareStatement("SELECT * FROM grade WHERE lower_mark<=? AND upper_mark>=?;");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addGrades( Grade grade) throws SQLException {
            STM_ADD_GRADES.setString(1,grade.getGradeLetter());
            STM_ADD_GRADES.setInt(2,grade.getUpperMark());
            STM_ADD_GRADES.setInt(3,grade.getLowerMark());
            STM_ADD_GRADES.setDouble(4,grade.getPoints());
            STM_ADD_GRADES.executeUpdate();
    }
    public static boolean doGradesExist() throws SQLException {
        ResultSet rst = STM_GET_ALL_GRADES.executeQuery();
        return rst.next();
    }

    public static String getResultGrade(double mark) throws SQLException {
        STM_GET_RESULT_GRADE.setDouble(1,mark);
        STM_GET_RESULT_GRADE.setDouble(2,mark);
        ResultSet rst = STM_GET_RESULT_GRADE.executeQuery();
        String result ="";
        if(rst.next()){
            result = rst.getString(1);
        };
        return result;
    }
}
