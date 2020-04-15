package com.example.airbnbapi.handler;

import com.example.airbnbapi.model.Book;
import com.example.airbnbapi.model.Game;
import com.example.airbnbapi.model.Media;
import com.example.airbnbapi.service.BookService;
import com.example.airbnbapi.service.GameService;
import com.example.airbnbapi.service.ServiceFactory;
import com.example.airbnbapi.service.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class HandleInput<T> implements CommandLineRunner {
    private Scanner input = new Scanner(System.in);

    private final ServiceFactory serviceFactory;


    @Autowired
    public HandleInput( ServiceFactory serviceFactory) {

       this.serviceFactory = serviceFactory;

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
                    "2)Book\n" +
                    "3)Film");
            action = input.nextInt();

            switch (action) {

                case 0:
                    System.out.println("Quitting Application");
                    quit = true;
                    break;


                //Locating Game
                default:
                    Media[] cachedItems = serviceFactory.getService(action).getItems();

                    System.out.println("What is the ID number of the item you would like to locate?: \n--------");
                    id = input.nextInt();
                    System.out.println("--------");


                    match = false;

                    while (!match) {
                        for (int i = 0; i < cachedItems.length; i++) {
                            if (cachedItems[i].getId() == (id)) {

                                System.out.println(cachedItems[id - 1].toString() + "\n--------");
                                match = true;
                                break;
                            }
                        }
                        if (!match) {
                            System.out.println("Could not locate item in file\n--------");
                            break;
                        }
                    }
                    break;

            }
        }


    }

}


