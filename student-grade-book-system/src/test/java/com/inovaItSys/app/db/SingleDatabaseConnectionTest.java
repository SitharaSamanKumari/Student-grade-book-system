package com.inovaItSys.app.db;

import com.inovaItSys.app.db.SingleDatabaseConnection;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class SingleDatabaseConnectionTest {

    @Test
    void getConnection() {
        assertDoesNotThrow(SingleDatabaseConnection.getInstance()::getConnection);
    }

    @Test
    void getInstance() {
        var instance1 = SingleDatabaseConnection.getInstance();
        var instance2 = SingleDatabaseConnection.getInstance();
        var instance3 = SingleDatabaseConnection.getInstance();
        assertEquals(instance1, instance2);
        assertEquals(instance2, instance3);
    }

//    @Test
//    void generateSchema() {
//        assertDoesNotThrow(()->{
//            SingleDatabaseConnection.getInstance().getConnection().createStatement()
//                    .executeQuery("SELECT * FROM subject,  grade") ;
//        });
//    }
}