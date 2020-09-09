package by.it_academy.jdbc.app.query;

public class BookSqlQueryHolderImpl implements CrudJdbcSqlQueryHolder {
    @Override
    public String getByIdSql() {
        return "select * from books where id = ?";
    }

    @Override
    public String getAllSql() {
        return "select * from books";
    }

    @Override
    public String updateSql() {
        return null;
    }

    @Override
    public String createSql() {
        return null;
    }

    @Override
    public String deleteSql() {
        return null;
    }
}
