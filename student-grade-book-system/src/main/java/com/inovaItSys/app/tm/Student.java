package com.inovaItSys.app.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/*Student memory model*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {
    private String id;
    private String firstName;
    private String lastName;
    @Override
    public String toString() {
        return id;
    }
}
