package com.example.airbnbapi.handler;

import com.example.airbnbapi.model.Game;
import com.example.airbnbapi.mapper.FetchObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class HandleInput implements CommandLineRunner {
   private Scanner input = new Scanner(System.in);

    private final FetchObject fetchObject;

    @Autowired
    public HandleInput(FetchObject fetchObject) {
        this.fetchObject = fetchObject;
    }

    @Override
    public void run(String... args) throws Exception {

        boolean quit = false;

        while (!quit) {


            System.out.println("What is the ID number of the game you would like to locate?: ");
            int id = input.nextInt();
            System.out.println("--------");

            Game[] cachedGames = fetchObject.getCachedGames();

            boolean match = false;

            while (!match) {
                for (int i = 0; i < cachedGames.length; i++) {
                    if (cachedGames[i].getId() == (id)) {

                        System.out.println(cachedGames[id - 1].toString());
                        match = true;
                        break;
                    }
                }
                if (!match) {
                    System.out.println("Could not locate game in file");
                    break;
                }
                System.out.println("\nWould you like to search for another ID\n0 for NO\n" +
                        "1 for YES");
                int action = input.nextInt();


                switch (action) {
                    case 0:
                        System.out.println("Quitting Application");
                        quit = true;
                        break;
                    case 1:
                        quit = false;
                        break;
                }

            }


        }
    }
}
