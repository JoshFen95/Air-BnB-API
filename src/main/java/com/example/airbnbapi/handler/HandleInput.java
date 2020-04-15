package com.example.airbnbapi.handler;

import com.example.airbnbapi.model.Book;
import com.example.airbnbapi.model.Game;
import com.example.airbnbapi.service.BookService;
import com.example.airbnbapi.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class HandleInput<T> implements CommandLineRunner {
    private Scanner input = new Scanner(System.in);

    private final GameService gameService;
    private final BookService bookService;

    @Autowired
    public HandleInput(GameService gameService, BookService bookService) {

        this.gameService = gameService;
        this.bookService = bookService;

    }


    @Override
    public void run(String... args) throws Exception {

        boolean quit = false;
        int id, action;
        boolean match;

        while (!quit) {

            System.out.println("What item do you want to locate?\n" +
                    "0)Quit\n" +
                    "1)Game\n" +
                    "2)Book");
            action = input.nextInt();

            switch (action) {

                case 0:
                    System.out.println("Quitting Application");
                    quit = true;
                    break;


                //Locating Game
                case 1:
                    Game[] cachedGames = gameService.getGames();

                    System.out.println("What is the ID number of the game you would like to locate?: \n--------");
                    id = input.nextInt();
                    System.out.println("--------");


                    match = false;

                    while (!match) {
                        for (int i = 0; i < cachedGames.length; i++) {
                            if (cachedGames[i].getId() == (id)) {

                                System.out.println(cachedGames[id - 1].toString() + "\n--------");
                                match = true;
                                break;
                            }
                        }
                        if (!match) {
                            System.out.println("Could not locate game in file");
                            break;
                        }
                    }
                    break;


                //Locating Book
                case 2:
                    Book[] cachedBooks = bookService.getBooks();
                    System.out.println("What is the ID number of the book you would like to locate?: \n--------");
                    id = input.nextInt();
                    System.out.println("--------");


                    match = false;

                    while (!match) {
                        for (int i = 0; i < cachedBooks.length; i++) {
                            if (cachedBooks[i].getId() == (id)) {

                                System.out.println(cachedBooks[id - 1].toString() + "\n--------");
                                match = true;
                                break;
                            }
                        }
                        if (!match) {
                            System.out.println("Could not locate book in file");
                            break;
                        }
                        break;
                    }
            }
        }


    }

}


