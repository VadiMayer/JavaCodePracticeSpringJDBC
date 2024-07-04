package com.example.springjdbc.repository;

import com.example.springjdbc.model.Book;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class BookRepository {
    private JdbcTemplate jdbcTemplate;

    public List<Book> get() {
        return jdbcTemplate.query("SELECT * FROM Books");
    }

    public Book get(int id) {
        return jdbcTemplate.query("SELECT * FROM Books WHERE id=" + id);
    }

    public Book create(Book book) {
        return jdbcTemplate.query("INSERT INTO VALUES");
    }

    public boolean delete(int id) {
        return jdbcTemplate.query("DELETE FROM Books WHERE id" + id);
    }
}
