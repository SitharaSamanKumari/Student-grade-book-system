package com.inovaItSys.app.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Subject {
    private String code;
    private String subjectName;
    private double  gpa;
}
