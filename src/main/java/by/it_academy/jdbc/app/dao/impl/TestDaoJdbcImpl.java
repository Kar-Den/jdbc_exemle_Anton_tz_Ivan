package by.it_academy.jdbc.app.dao.impl;

import by.it_academy.jdbc.app.connector.DataBaseConnector;
import by.it_academy.jdbc.app.dao.AbstractCrudDao;
import by.it_academy.jdbc.app.exception.ApplicationBaseException;
import by.it_academy.jdbc.app.mapper.ResultSetMapper;
import by.it_academy.jdbc.app.mapper.impl.TestResultSetMapper;
import by.it_academy.jdbc.app.model.Test;
import by.it_academy.jdbc.app.model.User;
import by.it_academy.jdbc.app.query.CrudJdbcSqlQueryHolder;
import by.it_academy.jdbc.app.query.impl.TestSqlQueryHolderImpl;
import by.it_academy.jdbc.app.statement.StatementInitializer;
import by.it_academy.jdbc.app.statement.impl.TestStatementInitializer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class TestDaoJdbcImpl extends AbstractCrudDao<Test> {
    private final CrudJdbcSqlQueryHolder sqlQueryHolder;
    private final StatementInitializer<Test> statementInitializer;
    private final ResultSetMapper<Test> resultSetMapper;
    private final UserDaoJdbcImpl userDao;
    private String name;

    {
        sqlQueryHolder = new TestSqlQueryHolderImpl();
        statementInitializer = new TestStatementInitializer();
        resultSetMapper = new TestResultSetMapper();
        userDao = new UserDaoJdbcImpl();
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

    public Test getTestWithUserById(long id) {
        Test test = getById(id);

        if (test != null) {
            test.setUser(userDao.getById(test.getUser_id()));
        }
        return test;
    }

    public List<Test> getAllTestWithUser() {
        List<Test> tests = getAll();

        for (Test test : tests) {
            test.setUser(userDao.getById(test.getUser_id()));
        }
        return tests;
    }

    public Test getTestUserById(long id) {
        Test test;

        TestSqlQueryHolderImpl sqlHolder = (TestSqlQueryHolderImpl) getSqlHolder();
        TestStatementInitializer initializer = (TestStatementInitializer) getStatementInitializer();
        TestResultSetMapper mapper = (TestResultSetMapper) getResultSetMapper();

        try (Connection con = getConnector().getConnection();
             PreparedStatement ps = con.prepareStatement(sqlHolder.getTestUserByIdSql())) {

            initializer.getByIdQueryStatement(ps, id);


            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    test = mapper.processResultSet(rs);
                    test.setUser(
                            User.builder()
                                    .name(rs.getString("NAME"))
                                    .surname(rs.getString("SURNAME"))
                                    .build()
                    );

                } else throw new ApplicationBaseException("Invalid entity id: " + id);

            } catch (SQLException e) {
                e.printStackTrace();
                throw new ApplicationBaseException("Error process getById entity method: " + e.getMessage());
            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new ArithmeticException("Error in method resultTestByUserID" + e);
        }

        return test;

    }
}
