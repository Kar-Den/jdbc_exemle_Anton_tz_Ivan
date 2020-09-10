package by.it_academy.jdbc.app.statement.impl;

import by.it_academy.jdbc.app.model.Test;
import by.it_academy.jdbc.app.statement.StatementInitializer;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestStatementInitializer implements StatementInitializer<Test> {

    @Override
    public void getByIdQueryStatement(PreparedStatement stmt, long id) throws SQLException {
        stmt.setLong(1, id);
    }

    @Override
    public void deleteQueryStatement(PreparedStatement stmt, long id) throws SQLException {
        stmt.setLong(1, id);
    }

    @Override
    public void createQueryStatement(PreparedStatement ps, Test test) throws SQLException {
        ps.setDate(1, (Date) test.getData());
        ps.setInt(2, test.getMark());
        ps.setInt(3, test.getUser_id());
        ps.setTime(4, test.getDuration());
        ps.setString(5, test.getComment());

    }

    @Override
    public void updateQueryStatement(PreparedStatement ps, Test test) throws SQLException {
//        data = ?, mark = ?, duration = ?, comment = ? where user_id = ?"
        ps.setDate(1, (Date) test.getData());
        ps.setInt(2, test.getMark());
        ps.setTime(3, test.getDuration());
        ps.setString(4, test.getComment());
        ps.setInt(5, test.getUser_id());


    }
}
