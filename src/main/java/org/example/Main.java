package org.example;

import java.sql.*;


/**
 * JDBC requires 7 Steps:
 * 1. Import the package.
 * 2. Load and Register the Driver.
 * 3. Create Connection.
 * 4. Create Statement.
 * 5. Execute Statement.
 * 6. Process the statement.
 * 7. Close statement and connections.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/jdbc_student";
        String userName = "root";
        String password = "Emmanuel@1803";
        //        Class.forName("com.mysql.jdbc.Driver"); // Deprecated
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement st = connection.createStatement();
//        printSingleCellFromDb(url, userName, password, connection, st);
        insertingIntoTableWithPrepareStatement(url, userName, password, connection);
    }

    public static void printSingleCellFromDb(
            String url,
            String userName,
            String password,
            Connection connection,
            Statement st
    ) throws Exception {
        String query = "SELECT name FROM student WHERE student_id=3";
        ResultSet result = st.executeQuery(query);
        result.next();
        String name = result.getString("name");
        st.close();
        connection.close();
        System.out.println(name);
    }

    public static void printTableFromDb(
            String url,
            String userName,
            String password,
            Connection connection,
            Statement st
    ) throws Exception {
        String query2 = "SELECT * FROM student;";
        ResultSet result = st.executeQuery(query2);
        while (result.next()){
            String name = result.getString("student_id") + " " + result.getString("name") + " " + result.getString("class");
            System.out.println(name);
        }
        st.close();
        connection.close();

    }

    public static void insertingIntoTable(
            String url,
            String userName,
            String password,
            Connection connection,
            Statement st
    ) throws Exception {
//        String query2 = "INSERT INTO student VALUES (4, 'Kunle', '.Net' );";

        // Using veriables
        int studentId =  5;
        String studentName = "Tolani";
        String studentClass = "Python";

        String query2 = "INSERT INTO student VALUES ("+ studentId + ", '"+ studentName +"', '"+ studentClass+"');";
        /**
         * We have three types on languages in SQL:
         * 1. DDL -> Data Definition Language: Used when changing the structure of your database
         *          (like creating table, altering table)
         * 2. DML -> Data Manipulation Language: Use when changing the value of your table (Like inserting value, updating value in the table)
         * 3. DQL -> Data Query Language: Use when retrieving data from the DB
         *
         * */
        int count = st.executeUpdate(query2);
        System.out.println(count + " Row effected");
        st.close();
        connection.close();

    }

    public static void insertingIntoTableWithPrepareStatement(
            String url,
            String userName,
            String password,
            Connection connection
    ) throws Exception {
//        String query2 = "INSERT INTO student VALUES (4, 'Kunle', '.Net' );";

        // Using veriables
        int studentId =  5;
        String studentName = "Tolani";
        String studentClass = "Python";

        String query = "INSERT INTO student VALUES (?,?,?);";
        /**
         * We have three types on languages in SQL:
         * 1. DDL -> Data Definition Language: Used when changing the structure of your database
         *          (like creating table, altering table)
         * 2. DML -> Data Manipulation Language: Use when changing the value of your table (Like inserting value, updating value in the table)
         * 3. DQL -> Data Query Language: Use when retrieving data from the DB
         *
         * */
        PreparedStatement st = connection.prepareStatement(query);
        st.setInt(1, 6);
        st.setString(2, "Bola");
        st.setString(3, "Shell");
        int count = st.executeUpdate();
        System.out.println(count + " Row effected");
        st.close();
        connection.close();

    }


}