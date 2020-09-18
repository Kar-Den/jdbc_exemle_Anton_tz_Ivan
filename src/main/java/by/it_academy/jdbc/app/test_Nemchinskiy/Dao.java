package by.it_academy.jdbc.app.test_Nemchinskiy;

import java.sql.*;

import static by.it_academy.jdbc.app.test_Nemchinskiy.ConstantConnection.*;

public class Dao {
    public static void getSQL(String sql) {
        try {
            Class.forName(BASE_NAME);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error in load driver");
        }

        try (Connection connection = DriverManager.
                getConnection(BASE_URL, BASE_LOGIN, BASE_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement
                     .executeQuery(sql);)
        {


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
