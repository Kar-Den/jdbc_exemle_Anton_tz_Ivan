package by.it_academy.jdbc.app.dao;

import by.it_academy.jdbc.app.connector.DataBaseConnector;

public abstract class BaseAbstractJdbcDao {

    private static final DataBaseConnector CONNECTOR = DataBaseConnector.getInstance();

    public DataBaseConnector getConnector() {
        return CONNECTOR;
    }
}