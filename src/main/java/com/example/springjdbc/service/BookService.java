package com.example.springjdbc.service;

import com.example.springjdbc.model.Book;
import com.example.springjdbc.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class BookService {

    private BookRepository bookRepository;

    public List<Book> get() {
        return bookRepository.get();
    }

    public Book get(int id) {
        return bookRepository.get(id);
    }

    public int create(Book book) {
        return bookRepository.save(book);
    }

    public int update(Book book) {
        return bookRepository.update(book);
    }

    public int delete(int id) {
        return bookRepository.delete(id);
    }
}
