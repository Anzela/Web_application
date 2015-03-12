package lv.ak07178.testapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import lv.ak07178.testapp.domain.User;

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

    public void addUser(User user) {
        this.jdbcTemplate.update(
                "insert into users (name, password) values(?, ?)",
                new String[]{user.getName(), user.getPassword()}
        );
    }

    public List<User> getAllUsers() {
        RowMapper<User> rowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User result = new User();
                result.setId(resultSet.getLong(1));
                result.setName(resultSet.getString(2));
                result.setPassword(resultSet.getString(3));
                return result;
            }
        };
        return this.jdbcTemplate.query("select id, name, password from users", rowMapper);
    }

    public User getUserById(long userId) {
        RowMapper<User> rowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User result = new User();
                result.setId(resultSet.getLong(1));
                result.setName(resultSet.getString(2));
                result.setPassword(resultSet.getString(3));
                return result;
            }
        };
        List<User> result = this.jdbcTemplate.query("select id, name, password from users where id = ?", rowMapper, userId);
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    public void deleteUser(long userId) {
        jdbcTemplate.update("DELETE FROM hosts WHERE user_id=?", userId);

    }

    public User getUserByName(String name) {
        RowMapper<User> rowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User result = new User();
                result.setId(resultSet.getLong(1));
                result.setName(resultSet.getString(2));
                result.setPassword(resultSet.getString(3));
                return result;
            }
        };
        List<User> result = this.jdbcTemplate.query("select id, name, password from users where name = ?", rowMapper, name);
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }
}
