package by.it_academy.jdbc.app.query.impl;

import by.it_academy.jdbc.app.query.CrudJdbcSqlQueryHolder;

public class TestSqlQueryHolderImpl implements CrudJdbcSqlQueryHolder {
    @Override
    public String getByIdSql() {
        return "select * from test where id = ?";
    }

    @Override
    public String getAllSql() {
        return "select * from test";
    }

    @Override
    public String updateSql() {
        return "update test set data = ?, mark = ?, user_id = ?, duration = ?, comment = ? where id = ?";
    }

    @Override
    public String createSql() {
        return "insert into test (data, mark, user_id, duration, comment) values (?,?,?,?,?)";
    }

    @Override
    public String deleteSql() {
        return "delete from test where id = ?";
    }
}
