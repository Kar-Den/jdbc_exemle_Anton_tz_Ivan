package by.it_academy.jdbc.app.statement.impl;


import by.it_academy.jdbc.app.model.User;
import by.it_academy.jdbc.app.statement.StatementInitializer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserStatementInitializer implements StatementInitializer<User> {

    @Override
    public void getByIdQueryStatement(PreparedStatement stmt, long id) throws SQLException {
        stmt.setLong(1, id);
    }

    @Override
    public void deleteQueryStatement(PreparedStatement stmt, long id) throws SQLException {
        stmt.setLong(1, id);
    }

    @Override
    public void updateQueryStatement(PreparedStatement stmt, User user) throws SQLException {
        stmt.setString(1, user.getName());
        stmt.setString(2, user.getSurname());
        stmt.setString(3, user.getEmail());
        stmt.setString(4, user.getLogin());
        stmt.setString(5, user.getPassword());
    }

    @Override
    public void createQueryStatement(PreparedStatement stmt, User user) throws SQLException {
        stmt.setString(1, user.getName());
        stmt.setString(2, user.getSurname());
        stmt.setString(3, user.getEmail());
        stmt.setString(4, user.getLogin());
        stmt.setString(5, user.getPassword());
    }
}
