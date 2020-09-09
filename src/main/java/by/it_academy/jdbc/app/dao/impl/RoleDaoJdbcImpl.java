package by.it_academy.jdbc.app.dao.impl;

import by.it_academy.jdbc.app.connector.DataBaseConnector;
import by.it_academy.jdbc.app.dao.BaseAbstractJdbcDao;
import by.it_academy.jdbc.app.exception.ApplicationBaseException;
import by.it_academy.jdbc.app.model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoJdbcImpl extends BaseAbstractJdbcDao {

    private static final String SELECT_USER_ROLES_SQL_QUERY = "select id, role from roles where user_id = ?";
    private static final String CREATE_USER_ROLE_SQL_QUERY = "insert into roles (role, user_id) values (?,?)";
    private static final String DELETE_ALL_ROLES_BY_USER_ID_SQL_QUERY = "delete from roles where user_id = ?";

    public List<Role> getRolesByUserId(long userId) {
        try (Connection connection = DataBaseConnector.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(SELECT_USER_ROLES_SQL_QUERY)) {
            stmt.setLong(1, userId);

            try (ResultSet resultSet = stmt.executeQuery()) {
                List<Role> roles = new ArrayList<>();

                while (resultSet.next()) {
                    roles.add(new Role(resultSet.getInt("id"), resultSet.getString("role")));
                }
                return roles;

            } catch (SQLException e) {
                e.printStackTrace();
                throw new ApplicationBaseException("Error process get roles by user-id entity method: " + e.getMessage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationBaseException("Error receive database connection: " + e.getMessage());
        }
    }

    public void addUserRole(Role role, long userId) {
        try (Connection connection = DataBaseConnector.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(CREATE_USER_ROLE_SQL_QUERY)) {

            stmt.setString(1, role.getRole());
            stmt.setLong(2, userId);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationBaseException("Error process create entity: " + e.getMessage());
        }
    }


    public void addAllUserRoles(List<Role> roles, long userId) {
        roles.forEach(role -> addUserRole(role, userId));
    }

    public void deleteAllUserRoles(long userId) {
        try (Connection connection = DataBaseConnector.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(DELETE_ALL_ROLES_BY_USER_ID_SQL_QUERY)) {

            stmt.setLong(1, userId);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationBaseException("Error process delete entity: " + e.getMessage());
        }
    }

}
