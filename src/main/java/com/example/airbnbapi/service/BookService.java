package com.example.airbnbapi.service;

import com.example.airbnbapi.mapper.FetchObject;
import com.example.airbnbapi.model.Book;
import com.example.airbnbapi.model.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookService implements ServiceInterface<Book>{


    private Book[] cachedBooks;
    private MediaType type = MediaType.BOOK;

    @Autowired
    public BookService(FetchObject<Book> fetchObject) {

        this.cachedBooks = fetchObject.getObjectFromJsonFile("books", Book[].class);

    }


    @Override
    public Book[] getItems() {
        return cachedBooks;    }

    @Override
    public int getAction() {
        return 2;
    }

    @Override
    public Book getMediaById(int id) {
        for (int i = 0; i < cachedBooks.length; i++) {
            if (cachedBooks[i].getId() == (id)) {


                return cachedBooks[id-1];
            }
        }

        return null;
    }

    @Override
    public MediaType getType() {
        return type;
    }
}



