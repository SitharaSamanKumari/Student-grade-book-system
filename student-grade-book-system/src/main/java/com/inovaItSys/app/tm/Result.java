package com.inovaItSys.app.tm;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {
    private String code;
    private String subjectName;
    private double gpa;
    private TextField marks;
    private Label grade;

}
