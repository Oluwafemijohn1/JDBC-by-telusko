package org.example;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class JdbcDao {
    public static void main(String[] args) {
        StudentDao dao = new StudentDao();
        dao.connect();
        Student s1 = dao.getStudent(1);
        System.out.println(s1);

        /**
         * To Add Student
         * */
        Student s2 = new Student();
        s2.sName = "Funmmi";
        s2.rollNo = 6;

        dao.addStudent(s2);

        dao.removeStudent(2);

        dao.closeConnection();
    }
}

class StudentDao {
    private final String url = "jdbc:mysql://localhost:3306/Ephemzy";
    private final String userName = "root";
    private final String password = "Emmanuel@1803";
    private Connection connection;
    public void connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public Student getStudent(int rollNo){
        Student s = new Student();
        s.rollNo = rollNo;
        try {

            String query = "SELECT sname FROM student WHERE rollno = " + rollNo;
            Statement st = connection.createStatement();
            ResultSet result = st.executeQuery(query);
            result.next();
            String name = result.getString(1);
            s.sName = name;
            st.close();
            return s;

        } catch (Exception e) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, e);
        }

        return null;
    }

    public void addStudent(Student s2) {
        try {

            String query = "INSERT INTO student VALUES ("+ s2.rollNo +", '"+ s2.sName +"')";
            Statement st = connection.createStatement();
            int count = st.executeUpdate(query);
            st.close();
            System.out.println(count + " Roll(s) Affected");
        } catch (Exception e) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void removeStudent(int rollNo) {
        try {

            String query = "DELETE FROM student WHERE rollno = " + rollNo;
            Statement st = connection.createStatement();
            int count = st.executeUpdate(query);
            st.close();
            System.out.println(count + " Roll(s) Affected");
        } catch (Exception e) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
class Student{
    int rollNo;
    String sName;
}

