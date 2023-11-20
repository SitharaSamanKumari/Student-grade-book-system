package com.inovaItSys.app.tm;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grade implements Serializable {
    private String gradeLetter;
    private int upperMark;
    private int lowerMark;
    private double points;
}

