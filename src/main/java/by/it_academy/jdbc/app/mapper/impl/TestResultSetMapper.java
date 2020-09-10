package by.it_academy.jdbc.app.mapper.impl;

import by.it_academy.jdbc.app.mapper.ResultSetMapper;
import by.it_academy.jdbc.app.model.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestResultSetMapper implements ResultSetMapper<Test> {
    @Override
    public Test processResultSet(ResultSet rs) throws SQLException {
        return Test.builder()
                .id(rs.getInt("id"))
                .data(rs.getDate("data"))
                .mark(rs.getInt("mark"))
                .user_id(rs.getInt("user_id"))
                .duration(rs.getTime("duration"))
                .comment(rs.getString("comment"))
                .build();
    }
}
