package by.it_academy.jdbc.app.dao.impl;

import by.it_academy.jdbc.app.connector.DataBaseConnector;
import by.it_academy.jdbc.app.dao.AbstractCrudDao;
import by.it_academy.jdbc.app.mapper.ResultSetMapper;
import by.it_academy.jdbc.app.mapper.impl.TestResultSetMapper;
import by.it_academy.jdbc.app.model.Test;
import by.it_academy.jdbc.app.query.CrudJdbcSqlQueryHolder;
import by.it_academy.jdbc.app.query.impl.TestSqlQueryHolderImpl;
import by.it_academy.jdbc.app.statement.StatementInitializer;
import by.it_academy.jdbc.app.statement.impl.TestStatementInitializer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TestDaoJdbcImpl extends AbstractCrudDao<Test> {
    private final CrudJdbcSqlQueryHolder sqlQueryHolder;
    private final StatementInitializer<Test> statementInitializer;
    private final ResultSetMapper<Test> resultSetMapper;

    public TestDaoJdbcImpl() {
        sqlQueryHolder = new TestSqlQueryHolderImpl();
        statementInitializer = new TestStatementInitializer();
        resultSetMapper = new TestResultSetMapper();
    }

    @Override
    protected CrudJdbcSqlQueryHolder getSqlHolder() {
        return sqlQueryHolder;
    }

    @Override
    protected ResultSetMapper<Test> getResultSetMapper() {
        return resultSetMapper;
    }

    @Override
    protected StatementInitializer<Test> getStatementInitializer() {
        return statementInitializer;
    }

    public void resultTestByUserID (long id){
        String sql = "select name, surname, mark, data, duration, comment from users join test on users.id = test.user_id where users.id=?";

        try(Connection con = getConnector().getConnection();
            PreparedStatement ps = con.prepareStatement(sql))  {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();


            while (rs.next()){
                rs.getString("name");
                rs.getString("surname");
                rs.getInt("mark");
                rs.getDate("data");
                rs.getTime("duration");
                rs.getString("comment");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ArithmeticException("Error in method resultTestByUserID" + e);
        }

    }
}
