package com.inovaItSys.app.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrintStudent {
    private String subCode;
    private String subName;
    private String gradeLetter;
    private String subGpa;
}
