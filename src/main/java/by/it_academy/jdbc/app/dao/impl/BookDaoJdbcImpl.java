package by.it_academy.jdbc.app.dao.impl;

import by.it_academy.jdbc.app.dao.AbstractCrudDao;
import by.it_academy.jdbc.app.mapper.ResultSetMapper;
import by.it_academy.jdbc.app.mapper.impl.BookResultSetMapper;
import by.it_academy.jdbc.app.model.Book;
import by.it_academy.jdbc.app.query.impl.BookSqlQueryHolderImpl;
import by.it_academy.jdbc.app.query.CrudJdbcSqlQueryHolder;
import by.it_academy.jdbc.app.statement.StatementInitializer;
import by.it_academy.jdbc.app.statement.impl.BookStatementInitializer;

public class BookDaoJdbcImpl extends AbstractCrudDao<Book> {


    @Override
    protected CrudJdbcSqlQueryHolder getSqlHolder() {
        return new BookSqlQueryHolderImpl();
    }

    @Override
    protected ResultSetMapper<Book> getResultSetMapper() {
        return new BookResultSetMapper();
    }

    @Override
    protected StatementInitializer<Book> getStatementInitializer() {
        return new BookStatementInitializer();
    }
}
