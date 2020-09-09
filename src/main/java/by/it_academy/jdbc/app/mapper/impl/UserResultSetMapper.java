package by.it_academy.jdbc.app.mapper.impl;


import by.it_academy.jdbc.app.mapper.ResultSetMapper;
import by.it_academy.jdbc.app.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserResultSetMapper implements ResultSetMapper<User> {

    @Override
    public User processResultSet(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .surname(resultSet.getString("surname"))
                .email(resultSet.getString("email"))
                .login(resultSet.getString("login"))
                .password(resultSet.getString("password"))
                .build();
    }
}
