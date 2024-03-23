package org.example;


import com.mysql.cj.jdbc.Driver;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ForNameMain {
    public static void main(String[] args) throws Exception {

//        Pqr pqr = new Pqr(); // Both Static and the Instance will be printed.
        Class.forName("org.example.Pqr"); // Only static will be printed

//        DriverManager.registerDriver(new Driver()); // We don't have to write this statement because it is already in the Class.forName()


    }
}

class Pqr {

    static {
        System.out.println("Hello from Static block");
    }

    // Instance
    {
        System.out.println("Hello from instance block");
    }
}
