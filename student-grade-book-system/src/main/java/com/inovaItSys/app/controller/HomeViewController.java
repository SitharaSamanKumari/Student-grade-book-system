package com.inovaItSys.app.controller;

import com.inovaItSys.app.db.GradeDataAccess;
import com.inovaItSys.app.tm.Grade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HomeViewController {
    public void initialize(){
        List<Grade> gradelist = new ArrayList<>(){};
        gradelist.add(new Grade("A+", 100, 85, 4.2));
        gradelist.add(new Grade("A", 84, 75, 4.0));
        gradelist.add(new Grade("A-", 74, 70, 3.7));
        gradelist.add(new Grade("B+", 69, 65, 3.3));
        gradelist.add(new Grade("B", 64, 60, 3.0));
        gradelist.add(new Grade("B-", 59, 55, 2.7));
        gradelist.add(new Grade("C+", 54, 50, 2.3));
        gradelist.add(new Grade("C", 49, 45, 2.0));
        gradelist.add(new Grade("C-", 44, 40, 1.5));
        gradelist.add(new Grade("D",39 , 35, 1));
        gradelist.add(new Grade("F", 34, 0, 0));

        try {
            if(!GradeDataAccess.doGradesExist()){
                for (Grade grade : gradelist) {
                    GradeDataAccess.addGrades(grade);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}