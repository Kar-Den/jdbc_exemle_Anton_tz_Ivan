package by.it_academy.jdbc.app.test_Nemchinskiy;

import java.sql.*;

public class DaoLayer {
    public static void main(String[] args)  {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error in load driver");
        }

        try (Connection connection = DriverManager.
                getConnection("jdbc:h2:tcp://localhost/mem:jdbc", "root", "root");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement
                     .executeQuery("select name, surname, email, login, id, password " +
                             "from users where name = 'владимир'");) {


            while (resultSet.next()) {
                String name = resultSet.getString(1);
                String surname = resultSet.getString(2);
                String email = resultSet.getString(3);
                String login = resultSet.getString(4);
                int id = resultSet.getInt(5);
                String password = resultSet.getString(6);
                System.out.println(resultSet);
                System.out.println(id + " " + name + " " + surname + " " + email + " " + login + " пароль:" + password);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error in DriverManager.getConnection()");
        }
    }
}
