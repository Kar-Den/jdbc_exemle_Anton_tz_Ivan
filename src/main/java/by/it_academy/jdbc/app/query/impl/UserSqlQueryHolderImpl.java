package by.it_academy.jdbc.app.query.impl;


import by.it_academy.jdbc.app.query.CrudJdbcSqlQueryHolder;

public class UserSqlQueryHolderImpl implements CrudJdbcSqlQueryHolder {

    private static final String SELECT_USER_BY_ID_SQL_QUERY = "select id, name, surname, email, login, password from users where id = ?";
    private static final String SELECT_ALL_USERS_SQL_QUERY = "select id, name, surname, email, login, password from users";
    private static final String UPDATE_USER_SQL_QUERY = "update users set name = ?, surname = ?, email = ?, login = ?, password = ? where id = ?";
    private static final String CREATE_USER_SQL_QUERY = "insert into users (name, surname, email, login, password) values (?,?,?,?, ?)";
    private static final String DELETE_USER_BY_ID_SQL_QUERY = "delete from users where id = ?";

    @Override
    public String getByIdSql() {
        return SELECT_USER_BY_ID_SQL_QUERY;
    }

    @Override
    public String getAllSql() {
        return SELECT_ALL_USERS_SQL_QUERY;
    }

    @Override
    public String createSql() {
        return CREATE_USER_SQL_QUERY;
    }

    @Override
    public String updateSql() {
        return UPDATE_USER_SQL_QUERY;
    }

    @Override
    public String deleteSql() {
        return DELETE_USER_BY_ID_SQL_QUERY;
    }
}
