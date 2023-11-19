package com.inovaItSys.app.tm;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grade implements Serializable {
    private String letter;
    private int upperMark;
    private int lowerMark;
}
//enum GradesList {
//    A_PLUS(100,80),A(79,75),A_MIN(74,65);
//
//
//    GradesList(int upperMark, int lowerMark){
//        this.lowerMark=lowerMark;
//        this.upperMark=upperMark;
//    }
//
//
//}
