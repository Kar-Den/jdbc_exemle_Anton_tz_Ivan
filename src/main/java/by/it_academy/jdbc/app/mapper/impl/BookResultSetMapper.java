package by.it_academy.jdbc.app.mapper.impl;


import by.it_academy.jdbc.app.mapper.ResultSetMapper;
import by.it_academy.jdbc.app.model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookResultSetMapper implements ResultSetMapper<Book> {

    @Override
    public Book processResultSet(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getLong("id"));
        book.setTitle(resultSet.getString("title"));
        book.setGenre(resultSet.getString("genre"));
        book.setAuthor(resultSet.getString("author"));

        return book;
    }
}
