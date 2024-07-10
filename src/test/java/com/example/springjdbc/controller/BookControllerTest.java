package com.example.springjdbc.controller;

import com.example.springjdbc.model.Book;
import com.example.springjdbc.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class BookControllerTest {


    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MockMvc mvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    @Sql(scripts = "/initDB.sql")
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        for (int i = 0; i < 3; i++) {
            bookRepository.save(new Book(i + 1, "title" + i + 1, "author" + i + 1, LocalDate.of(2024, 7, 9 - i)));
        }
    }

    @Test
    void getBooks() throws Exception {
        mvc.perform(get("/books"))
                .andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(bookRepository.get())));
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