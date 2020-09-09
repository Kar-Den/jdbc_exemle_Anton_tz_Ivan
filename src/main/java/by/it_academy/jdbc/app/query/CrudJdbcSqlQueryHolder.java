package by.it_academy.jdbc.app.query;

public interface CrudJdbcSqlQueryHolder {
    String getByIdSql();
    String getAllSql();
    String updateSql();
    String createSql();
    String deleteSql();
}
