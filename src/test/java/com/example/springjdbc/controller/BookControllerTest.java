package com.example.springjdbc.controller;

import com.example.springjdbc.model.Book;
import com.example.springjdbc.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Sql(scripts = "/initDB.sql")
class BookControllerTest {


    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MockMvc mvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        for (int i = 0; i < 3; i++) {
            bookRepository.save(new Book(i + 1, "title" + (i + 1), "author" + (i + 1), LocalDate.of(2024, 7, 9 - i)));
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
    void create() throws Exception {
        Book book = bookRepository.get(1);
        mvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(book.getId() + 3))
                .andExpect(jsonPath("$.title").value(book.getTitle()))
                .andExpect(jsonPath("$.author").value(book.getAuthor()))
                .andExpect(jsonPath("$.publicationYear").value(book.getPublicationYear().toString()));
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}