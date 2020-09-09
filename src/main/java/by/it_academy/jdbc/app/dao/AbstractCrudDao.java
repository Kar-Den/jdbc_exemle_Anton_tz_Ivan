package by.it_academy.jdbc.app.dao;

import by.it_academy.jdbc.app.connector.DataBaseConnector;
import by.it_academy.jdbc.app.exception.ApplicationBaseException;
import by.it_academy.jdbc.app.mapper.ResultSetMapper;
import by.it_academy.jdbc.app.query.CrudJdbcSqlQueryHolder;
import by.it_academy.jdbc.app.statement.StatementInitializer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public abstract class AbstractCrudDao<T> extends BaseAbstractJdbcDao implements CrudDao<T> {

    protected abstract CrudJdbcSqlQueryHolder getSqlHolder();

    protected abstract ResultSetMapper<T> getResultSetMapper();

    protected abstract StatementInitializer<T> getStatementInitializer();

    @Override
    public T getById(long id) {
        try (Connection conn = getConnector().getConnection();
             PreparedStatement prSt = conn.prepareStatement(getSqlHolder().getByIdSql())) {
            prSt.setLong(1, id);

            try (ResultSet rs = prSt.executeQuery()) {
                if (rs.next()) {
                    return getResultSetMapper().processResultSet(rs);
                }
                throw new ApplicationBaseException("Invalid entity id: " + id);

            } catch (SQLException e) {
                e.printStackTrace();
                throw new ApplicationBaseException("Error process getById entity method: " + e.getMessage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationBaseException("Error receive database connection: " + e.getMessage());
        }
    }

    @Override
    public List<T> getAll() {

        try (Connection conn = getConnector().getConnection()) {

            try (PreparedStatement prSt = conn.prepareStatement(getSqlHolder().getAllSql());
                 ResultSet rs = prSt.executeQuery()) {

                List<T> t = new ArrayList<>();
                while (rs.next()) {
                    t.add(getResultSetMapper().processResultSet(rs));
                }
                return t;

            } catch (SQLException e) {
                e.printStackTrace();
                throw new ApplicationBaseException("Error process getAll entities method: " + e.getMessage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationBaseException("Error receive database connection: " + e.getMessage());
        }

    }

    public T update(T t) {

        try (Connection conn = getConnector().getConnection();
             PreparedStatement prSt = conn.prepareStatement(getSqlHolder().updateSql())) {

            getStatementInitializer().updateQueryStatement(prSt, t);
            prSt.executeQuery();

            return t;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationBaseException("Error process update entity method: " + e.getMessage());
        }
    }

    public T create(T t) {
        try (Connection connection = DataBaseConnector.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(getSqlHolder().createSql(), Statement.RETURN_GENERATED_KEYS)) {

            getStatementInitializer().createQueryStatement(stmt, t);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return getById(rs.getLong(1));
            }

            throw new ApplicationBaseException("Error generate ID for create entity: " + t);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationBaseException("Error process create entity: " + e.getMessage());
        }
    }

    public void delete(long id) {
        try (Connection connection = DataBaseConnector.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(getSqlHolder().deleteSql())) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationBaseException("Error process delete entity method: " + e.getMessage());
        }
    }
}