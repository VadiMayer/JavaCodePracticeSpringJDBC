package com.example.springjdbc.controller;

import com.example.springjdbc.model.Book;
import com.example.springjdbc.repository.BookRepository;
import com.example.springjdbc.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private BookService bookService;

    @Autowired
    private MockMvc mvc;

    private List<Book> books;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        books = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            books.add(new Book(i, "title" + i + 1, "author" + i + 1, LocalDate.of(2024, 7, 9 - i)));
        }
    }

    @Test
    void getBooks() throws Exception {
        mvc.perform(get("/books"))
                .andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(books)));
    }

    @Test
    void getBook() throws Exception {
        mvc.perform(get("/books/" + 1))
                .andExpect(status().isOk());
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}