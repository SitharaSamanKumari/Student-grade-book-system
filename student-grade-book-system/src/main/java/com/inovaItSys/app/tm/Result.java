package com.inovaItSys.app.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {
    private String code;
    private String subject;
    private double gpa;
    private double marks;
    private String grade;

}
