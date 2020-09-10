package by.it_academy.jdbc.app.statement.impl;

import by.it_academy.jdbc.app.model.Book;
import by.it_academy.jdbc.app.statement.StatementInitializer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookStatementInitializer implements StatementInitializer<Book> {
    @Override
    public void createQueryStatement(PreparedStatement stmt, Book book) throws SQLException {

    }

    @Override
    public void updateQueryStatement(PreparedStatement stmt, Book book) throws SQLException {

    }

    @Override
    public void getByIdQueryStatement(PreparedStatement stmt, long id) throws SQLException {
        stmt.setLong(1, id);
    }

    @Override
    public void deleteQueryStatement(PreparedStatement stmt, long id) throws SQLException {
        stmt.setLong(1, id);
    }
}
