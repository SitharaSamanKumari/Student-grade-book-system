package com.inovaItSys.app.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*Student memory model*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String id;
    private String firstName;
    private String lastName;
//    private List<Subject> subjectList;


}
