package lv.ak07178.testapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import lv.ak07178.testapp.dto.UserWithName;

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
        this.jdbcTemplate.update(
                "insert into users (name, password) values(?, ?)",
                new String[]{name, password}
        );
    }

    public List<UserWithName> getAllUsers() {
        return this.jdbcTemplate.query("select id, name from users", new RowMapper<UserWithName>() {
            @Override
            public UserWithName mapRow(ResultSet resultSet, int i) throws SQLException {
                UserWithName result = new UserWithName(
                        resultSet.getLong(1),
                        resultSet.getString(2)
                        );
                return result;
            }
        });
    }

}
