package lv.ak07178.testapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:kirill.afanasjev@odnoklassniki.ru">Kirill Afanasjev</a>
 */
@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int getUserCount() {
        return this.jdbcTemplate.queryForObject(
                "select count(*) from users", Integer.class);

    }

    public void addUser(String name, String password) {
        this.jdbcTemplate.update("insert into users (name, password) values(?, ?)", new String[]{name, password});
    }

}
