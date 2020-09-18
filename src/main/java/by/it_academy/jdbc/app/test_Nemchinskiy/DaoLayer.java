package by.it_academy.jdbc.app.test_Nemchinskiy;

public class DaoLayer {

    public static void main(String[] args) {
        String sql = "select name, surname, email, login, id, password from users where " +
                "id = 3";

        Dao.getSQL(sql);

    }
}
