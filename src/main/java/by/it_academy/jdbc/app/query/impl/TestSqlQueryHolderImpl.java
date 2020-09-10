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
        return "update test set data = ?, mark = ?, duration = ?, comment = ? where user_id = ?";
    }

    @Override
    public String createSql() {
        return "insert into test (data, mark, user_id, duration, comment) values (?,?,?,?,?)";
    }

    @Override
    public String deleteSql() {
        return "delete from test where id = ?";
    }



    public String getTestUserByIdSql() {
        return "select t.ID, t.USER_ID, NAME, SURNAME, MARK, DATA, DURATION, COMMENT from TEST t join USERS u on u.ID = t.user_id where t.ID=?;";
    }

}
