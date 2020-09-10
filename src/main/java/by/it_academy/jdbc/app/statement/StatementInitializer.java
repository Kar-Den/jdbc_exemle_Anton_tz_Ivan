package by.it_academy.jdbc.app.statement;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementInitializer<T> {
    void createQueryStatement(PreparedStatement stmt, T t) throws SQLException;
    void updateQueryStatement(PreparedStatement stmt, T t) throws SQLException;
    void getByIdQueryStatement(PreparedStatement stmt, long id) throws SQLException;
    void deleteQueryStatement(PreparedStatement stmt, long id) throws SQLException;
}
