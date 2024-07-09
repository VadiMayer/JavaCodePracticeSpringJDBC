package com.example.springjdbc.controller;

import com.example.springjdbc.model.Book;
import com.example.springjdbc.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {
    private BookService bookService;

    @GetMapping
    public List<Book> get() {
        return bookService.get();
    }

    @GetMapping("/{id}")
    public Book get(@PathVariable int id) {
        return bookService.get(id);
    }

    @PostMapping
    public int create(@RequestBody Book book) {
        return bookService.create(book);
    }

    @PutMapping
    public int update(@RequestBody Book book) {
        return bookService.update(book);
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable int id) {
        return bookService.delete(id);
    }
}
