package com.inovaItSys.app.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Subject implements Serializable {
    private String code;
    private String subjectName;
    private double  gpa;
    @Override
    public String toString() {
        return subjectName;
    }
}
