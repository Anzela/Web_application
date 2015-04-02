package lv.ak07178.testapp.dao;

import lv.ak07178.testapp.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CommentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addComment(Comment comment) {
        this.jdbcTemplate.update(
                "insert into comments (text, authorId, postId) values(?, ?, ?)",
                comment.getText(),
                comment.getAuthorId(),
                comment.getPostId()
        );
    }

    public List<Comment> getAllComments() {
        List<Comment> result = this.jdbcTemplate.query(
                "select id, text, postId, authorId from comments",
                rowMapper
        );
        return result;
    }

    RowMapper<Comment> rowMapper = new RowMapper<Comment>() {
        @Override
        public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
            Comment result = new Comment();
            result.setId(resultSet.getLong(1));
            result.setText(resultSet.getString(2));
            result.setPostId(resultSet.getLong(3));
            result.setAuthorId(resultSet.getLong(4));
            return result;
        }
    };

    public Comment getById(long commentId) {
        List<Comment> result = this.jdbcTemplate.query(
                "select id, text, postId, authorId from comments where id = ?",
                rowMapper,
                commentId
        );
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    public List<Comment> getCommentsByPostId(long postId) {
        List<Comment> result = this.jdbcTemplate.query(
                "select id, text, postId, authorId from comments where postId = ?",
                rowMapper,
                postId
        );
        return result;
    }

    public void deleteUserComments(long userId) {
        jdbcTemplate.update("DELETE FROM comments WHERE authorId = ?", userId);
    }

    public void deleteCommentById(long commentId) {
        jdbcTemplate.update("DELETE FROM comments WHERE id = ?", commentId);
    }

    public void deleteCommentsByPostId(long postId) {
        jdbcTemplate.update("DELETE FROM comments WHERE postId = ?", postId);
    }
}
