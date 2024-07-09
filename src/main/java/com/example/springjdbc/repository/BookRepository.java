package com.example.springjdbc.repository;

import com.example.springjdbc.model.Book;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public int save(Book book) {
        return jdbcTemplate.update("INSERT INTO Books VALUES(1, ?, ?, ?)", book.getTitle(), book.getAuthor(), book.getPublicationYear());
    }

    public int update(Book book) {
        return jdbcTemplate.update("UPDATE Books SET title=?, author=?, publicationYear=? WHERE id=?", book.getTitle(), book.getAuthor(), book.getPublicationYear(), book.getId());
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM Books WHERE id=?", id);
    }
}
