package com.example.airbnbapi.service;

import com.example.airbnbapi.api.BookRepository;
import com.example.airbnbapi.model.Book;
import com.example.airbnbapi.model.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BookService implements ServiceInterface<Book>{


    private MediaType type = MediaType.BOOK;

    @Autowired
    private BookRepository bookRepository;


    @Override
    public List<Book> getItems() {

        return bookRepository.findAll();    }


    @Override
    public Optional<Book> getItemById(String id) {

        return bookRepository.findById(id);

    }

    @Override
    public MediaType getType() {
        return type;
    }

    @Override
    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }
}



