package peaksoft;

import peaksoft.dao.impl.AnnouncementDaoImpl;
import peaksoft.dao.impl.UserDaoImpl;
import peaksoft.databse.Database;
import peaksoft.enums.HouseType;
import peaksoft.models.Announcement;
import peaksoft.models.User;
import peaksoft.services.AnnouncementService;
import peaksoft.services.UserService;
import peaksoft.services.impl.AnnouncementServiceImpl;
import peaksoft.services.impl.UserServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Database database = new Database();
        UserDaoImpl userDao = new UserDaoImpl(database);
        UserService userService = new UserServiceImpl(userDao);
        AnnouncementDaoImpl announcementDao = new AnnouncementDaoImpl(database);
        AnnouncementService announcementService = new AnnouncementServiceImpl(announcementDao, userDao);

        User currentUser = null;

        while (true) {
            System.out.println("""
                    Press to 0 -> get all users
                    Press to 1 -  save user
                    Press to 2 -  find user by id
                    Press to 3 -  sign in
                    Press to 4 -  save announcement
                    """);

            switch (new Scanner(System.in).nextInt()) {

                case 0 -> {
                  List<User> userList = userService.findAll();
                    for (User user : userList) {
                        System.out.println(user);
                    }
                }
                case 1 -> {
                    String message1 = userService.save(
                            new User(
                                    "Baisalbek",
                                    "baisalbek@gmail.com",
                                    "Baisalbek18!",
                                    "https:lasfjalkij2k3nflkjf",
                                    "+996700600500"
                            )
                    );

                    String message2 = userService.save(
                            new User(
                                    "Atai",
                                    "atai@gmail.com",
                                    "Atai!",
                                    "https:lasfjalkij2k3nflkjf",
                                    "+996700600555"
                            )
                    );

                    String message3 = userService.save(
                            new User(
                                    "Babakhan",
                                    "babakhan@gmail.com",
                                    "Babakhan!",
                                    "https:lasfjalkij2k3nflkjf",
                                    "+996700600777"
                            )
                    );
                    System.out.println(message1);
                    System.out.println(message2);
                    System.out.println(message3);
                }
                case 2 -> {
                    System.out.print("Write user id: ");
                    User byId = userService.findById(new Scanner(System.in).nextLong());
                    System.out.println(byId);
                }
                case 3 -> {
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Write email:  ");
                    String email = scanner.nextLine();
                    System.out.print("Write password: ");
                    String password = scanner.nextLine();

                    try {
                       currentUser =  userService.signIn(email, password);
                    } catch (IllegalArgumentException e) {
                        System.err.println(e.getMessage());
                    }
                }

                case 4 -> {
                    if (currentUser != null){
                        String save = announcementService.save(currentUser.getId(),
                                new Announcement(
                                        "Ihlas",
                                        "Address Bishkek",
                                        BigDecimal.valueOf(200),
                                        new ArrayList<>(List.of("https://sdlfjsljf")),
                                        HouseType.APARTMENT
                                )
                        );
                        System.out.println(save);
                    }
                }
            }

        }


    }
}