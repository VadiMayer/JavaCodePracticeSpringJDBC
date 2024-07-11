package com.example.springjdbc.repository;

import com.example.springjdbc.model.Book;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Repository
@AllArgsConstructor
public class BookRepository {
    private JdbcTemplate jdbcTemplate;

    public List<Book> get() {
        return jdbcTemplate.query("SELECT * FROM Books", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book get(int id) {
        return jdbcTemplate.query("SELECT * FROM Books WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public Book save(Book book) {
        String sql = "INSERT INTO Books (title, author, publicationYear) VALUES(?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setDate(3, Date.valueOf(book.getPublicationYear()));
            return ps;
        }, keyHolder);

        int newBookId = Objects.requireNonNull(keyHolder.getKey()).intValue();

        return get(newBookId);
    }

    public int update(Book book) {
        return jdbcTemplate.update("UPDATE Books SET title=?, author=?, publicationYear=? WHERE id=?",
                book.getTitle(), book.getAuthor(), book.getPublicationYear(), book.getId());
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM Books WHERE id=?", id);
    }
}
