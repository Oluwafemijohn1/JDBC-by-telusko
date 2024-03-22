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
 * */
public class Main {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/jdbc_student";
        String userName = "root";
        String password = "Emmanuel@1803";

        //printing one user
        String query = "SELECT name FROM student WHERE student_id=3";

        // Printing all
        String query2 = "SELECT * FROM student;";
//        Class.forName("com.mysql.jdbc.Driver"); // Deprecated
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement st = connection.createStatement();
        ResultSet result =    st.executeQuery(query2);
        result.next();
        String name = result.getString("name");
        st.close();
        connection.close();
        System.out.println(name);

    }
}