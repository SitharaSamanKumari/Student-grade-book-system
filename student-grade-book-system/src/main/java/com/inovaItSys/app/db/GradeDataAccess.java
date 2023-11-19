package com.inovaItSys.app.db;

//import com.inovaItSys.app.to.GradesList;

import com.inovaItSys.app.to.Grade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class GradeDataAccess {
    private static final PreparedStatement STM_ADD_GRADES;
    static {
        try {
            Connection connection = SingleDatabaseConnection.getInstance().getConnection();
            STM_ADD_GRADES = connection
                    .prepareStatement("INSERT INTO grade(grade, upper_mark, lower_mark) VALUES (?, ?, ?)");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public GradeDataAccess(List<Grade> gradeList) {
//        this.GRADE_LIST = gradeList;
//    }

    public static void addGrades( List<Grade> gradeList) throws SQLException {
        for (Grade grade : gradeList) {
            STM_ADD_GRADES.setString(1,grade.getLetter());
            STM_ADD_GRADES.setInt(2,grade.getUpperMark());
            STM_ADD_GRADES.setInt(3,grade.getLowerMark());
            STM_ADD_GRADES.executeUpdate();
        }
    }


}
