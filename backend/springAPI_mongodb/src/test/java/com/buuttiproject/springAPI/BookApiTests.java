package com.buuttiproject.springAPI;

import com.buuttiproject.springAPI.model.Book;
import com.buuttiproject.springAPI.repository.BookRepository;
import com.buuttiproject.springAPI.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class BookApiTests {
    private static final String END_POINT_PATH = "/books";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private BookService service;

    @MockBean
    private BookRepository repository;

    @Test
    public void testAddShouldReturn201() throws Exception {
        Book newBook = new Book("J.P Morris", "Harry Potter", "This is a description");
        Mockito.when(this.repository.save(newBook)).thenReturn(newBook);

        String requestBody = objectMapper.writeValueAsString(newBook);

        mockMvc.perform(post(END_POINT_PATH).contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andDo(print())
        ;
    }

    @Test
    public void testAddShouldReturn500() throws Exception {
        Book newBook = new Book("J.P Morris", "", "This is a description");
        Mockito.when(this.repository.save(newBook)).thenReturn(newBook);

        String requestBody = objectMapper.writeValueAsString(newBook);

        mockMvc.perform(post(END_POINT_PATH).contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isInternalServerError())
                .andDo(print())
        ;
    }

    @Test
    public void testGetBooksReturn200() throws Exception {

    }
}
