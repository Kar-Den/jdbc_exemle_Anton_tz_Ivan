import by.it_academy.jdbc.app.connector.DataBaseConnector;
import by.it_academy.jdbc.app.constant.ApplicationConstant;
import by.it_academy.jdbc.app.dao.impl.BookDaoJdbcImpl;
import by.it_academy.jdbc.app.dao.impl.TestDaoJdbcImpl;
import by.it_academy.jdbc.app.dao.impl.UserDaoJdbcImpl;
import by.it_academy.jdbc.app.exception.ApplicationBaseException;
import by.it_academy.jdbc.app.model.Role;
import by.it_academy.jdbc.app.model.Test;
import by.it_academy.jdbc.app.model.User;
import org.h2.tools.RunScript;
import org.h2.tools.Server;

import java.io.FileReader;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Arrays;

public class Runner {

    // for connect with UI tool to database use url - jdbc:h2:tcp://localhost/mem:jdbc
    private static final Server SERVER;
    private static final DataBaseConnector DATABASE_CONNECTOR = DataBaseConnector.getInstance();

    static {
        try {
            SERVER = Server.createTcpServer().start();
        } catch (SQLException e) {
            throw new ApplicationBaseException("Failed start tcp H2 server");
        }

        init();
    }

    private static void init() {
        try (Connection connection = DATABASE_CONNECTOR.getConnection()) {

            URL ddlSql = Runner.class.getResource(ApplicationConstant.DDL_INITIALIZATION_SCRIPT_PATH);
            URL dmlSql = Runner.class.getResource(ApplicationConstant.DML_INITIALIZATION_SCRIPT_PATH);

            RunScript.execute(connection, new FileReader(Paths.get(ddlSql.toURI()).toFile()));
            RunScript.execute(connection, new FileReader(Paths.get(dmlSql.toURI()).toFile()));

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error initialization in-memory database: " + e.getMessage());
            throw new ApplicationBaseException("Error initialization in-memory database: " + e.getMessage());
        }
    }


    public static void main(String[] args) {


        UserDaoJdbcImpl userDao = new UserDaoJdbcImpl();

        System.out.println();
        System.out.println("# getAll Users ");
        userDao.getAll().forEach(System.out::println);

        System.out.println();
        System.out.println("# create User and getById without role");
        User user = new User("Jimmy", "White", "jimmy_white@it-academy.com", "it-academy", "password", null);
        user.setRoles(Arrays.asList(new Role("ADMIN"), new Role("DEVELOPER"), new Role("MANAGER")));
        user = userDao.create(user);


        System.out.println();
        System.out.println("# getUserByIdWithRole ");
        System.out.println(userDao.getUserByIdWithRole(user.getId()));


        System.out.println();
        System.out.println("# delete User and getAll");
        userDao.delete(10L);
        userDao.getAll().forEach(System.out::println);


        System.out.println();
        System.out.println("# book dao sample");
        BookDaoJdbcImpl bookDaoJdbc = new BookDaoJdbcImpl();
        bookDaoJdbc.getAll().forEach(System.out::println);
        System.out.println(bookDaoJdbc.getById(2L));

        System.out.println();
        System.out.println("# test dao");
        TestDaoJdbcImpl testDaoJdbc = new TestDaoJdbcImpl();
        System.out.println("# create Test");
        Test test = new Test(Date.valueOf("2020-09-10"), 7, 10, Time.valueOf("02:03:04"), "это было круто");
        testDaoJdbc.create(test);

        System.out.println("# getAll Test ");
        testDaoJdbc.getAll().forEach(System.out::println);


        System.out.println();
        System.out.println("# delete Test ");
        testDaoJdbc.delete(3);
        testDaoJdbc.getAll().forEach(System.out::println);

        System.out.println();
        System.out.println("# Update Test ");
        Test testUpdate = new Test(Date.valueOf("2020-09-10"), 7, 5, Time.valueOf("10:05:06"), "это Update, детка у user_id=7");
        testDaoJdbc.update(testUpdate);

        System.out.println();
        System.out.println("# getById Test ");
        System.out.println(testDaoJdbc.getById(4));

        System.out.println();
        System.out.println("# resultTestByUserID Test ");
        testDaoJdbc.resultTestByUserID(7);


//        SERVER.stop();
    }
}
