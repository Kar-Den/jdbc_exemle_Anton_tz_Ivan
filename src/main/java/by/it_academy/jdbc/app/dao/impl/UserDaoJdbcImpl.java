package by.it_academy.jdbc.app.dao.impl;

import by.it_academy.jdbc.app.dao.AbstractCrudDao;
import by.it_academy.jdbc.app.mapper.ResultSetMapper;
import by.it_academy.jdbc.app.mapper.impl.UserResultSetMapper;
import by.it_academy.jdbc.app.model.Role;
import by.it_academy.jdbc.app.model.User;
import by.it_academy.jdbc.app.query.CrudJdbcSqlQueryHolder;
import by.it_academy.jdbc.app.query.impl.UserSqlQueryHolderImpl;
import by.it_academy.jdbc.app.statement.StatementInitializer;
import by.it_academy.jdbc.app.statement.impl.UserStatementInitializer;

import java.util.List;

public class UserDaoJdbcImpl extends AbstractCrudDao<User> {

    private final CrudJdbcSqlQueryHolder sqlQueryHolder;
    private final StatementInitializer<User> statementInitializer;
    private final ResultSetMapper<User> resultSetMapper;

    private final RoleDaoJdbcImpl roleDao;

    public UserDaoJdbcImpl() {
        sqlQueryHolder = new UserSqlQueryHolderImpl();
        statementInitializer = new UserStatementInitializer();
        resultSetMapper = new UserResultSetMapper();

        roleDao = new RoleDaoJdbcImpl();
    }

    public User getUserByIdWithRole(long id) {
        User user = getById(id);
        List<Role> roles = roleDao.getRolesByUserId(id);
        user.setRoles(roles);
        return user;
    }

    @Override
    public User create(User user) {
        List<Role> roles = user.getRoles();

        user = super.create(user);

        if (roles != null) {
            roleDao.addAllUserRoles(roles, user.getId());
        }

        return user;
    }

    @Override
    public void delete(long id) {
        super.delete(id);
        roleDao.deleteAllUserRoles(id);
    }

    @Override
    protected CrudJdbcSqlQueryHolder getSqlHolder() {
        return sqlQueryHolder;
    }

    @Override
    protected StatementInitializer<User> getStatementInitializer() {
        return statementInitializer;
    }

    @Override
    protected ResultSetMapper<User> getResultSetMapper() {
        return resultSetMapper;
    }
}
