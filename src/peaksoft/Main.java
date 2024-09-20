package peaksoft;

import peaksoft.dao.FavoriteDao;
import peaksoft.dao.impl.AnnouncementDaoImpl;
import peaksoft.dao.impl.FavoriteDaoImpl;
import peaksoft.dao.impl.UserDaoImpl;
import peaksoft.databse.Database;
import peaksoft.enums.HouseType;
import peaksoft.models.Announcement;
import peaksoft.models.User;
import peaksoft.services.AnnouncementService;
import peaksoft.services.FavoriteService;
import peaksoft.services.UserService;
import peaksoft.services.impl.AnnouncementServiceImpl;
import peaksoft.services.impl.FavoriteServiceImpl;
import peaksoft.services.impl.UserServiceImpl;

import java.math.BigDecimal;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Database database = new Database();
        UserDaoImpl userDao = new UserDaoImpl(database);
        UserService userService = new UserServiceImpl(userDao);
        AnnouncementDaoImpl announcementDao = new AnnouncementDaoImpl(database);
        AnnouncementService announcementService = new AnnouncementServiceImpl(announcementDao, userDao);
        FavoriteDao favoriteDao = new FavoriteDaoImpl(database);
        FavoriteService favoriteService = new FavoriteServiceImpl(favoriteDao, userDao, announcementDao);

        User currentUser = null;  // new User();

        while (true) {
            System.out.println("""
                    Press to 0 -> get all users
                    Press to 1 -  save user  // register
                    Press to 2 -  find user by id
                    Press to 3 -  sign in
                    Press to 4 -  save announcement
                    Press to 5 -  get announcements by user  //baisalbek@gmail.com
                    Press to 6 -  get all announcements
                    Press to 7 -  add announcement to favorite
                    Press to 8 -  get favorite announcement by user
                  
                    """);

            switch (new Scanner(System.in).nextInt()) {

                case 0 -> {
                  List<User> userList = userService.findAll();
                    for (User user : userList) {
                        System.out.println(user);
                    }
                    System.out.println(listIsEmpty(userList, User.class));
                }
                case 1 -> {
                    Scanner scanner = new Scanner(System.in);
                    for (User user : createUsers()) {
                        userService.save(user);
                    }
                    System.out.println("Register user:");
                    System.out.print("Write full name: ");
                    String fullName =  scanner.nextLine(); // fullName
                    System.out.print("Write email: ");
                    String email = scanner.nextLine();
                    System.out.println("Write password: ");
                    String password = scanner.nextLine();
                    System.out.print("Write image link: ");
                    String avatar = scanner.nextLine();
                    System.out.print("Write phone number: ");
                    String phoneNumber = scanner.nextLine();
                    User user = new User(fullName, email, password, avatar, phoneNumber);
                    userService.save(user);
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
                        currentUser = userService.signIn(email, password);  // baisalbek

                    } catch (IllegalArgumentException e) {
                        System.err.println(e.getMessage());
                    }
                }

                case 4 -> {
                    if (currentUser != null){
                        String save = announcementService.save(
                                currentUser.getId(),
                                new Announcement(
                                        "Ihlas",
                                        "Address Bishkek",
                                        BigDecimal.valueOf(200),
                                        new ArrayList<>(List.of("https://sdlfjsljf")),
                                        HouseType.APARTMENT
                                )
                        );
                        System.out.println(save);
                    }else {
                        System.out.println("Current user is null");
                    }
                }
                case 5 ->{
                    if (currentUser != null){
                      List<Announcement> userAnnouncements = announcementService.getAllAnnouncementsByOwnerId(currentUser.getId());
                        for (Announcement userAnnouncement : userAnnouncements) {
                            System.out.println(userAnnouncement);
                        }
                        System.out.println(listIsEmpty(userAnnouncements, Announcement.class));
                    }
                }
                case 6 ->{
                    List<Announcement> all = announcementService.getAll();
                    for (Announcement announcement : all) {
                        System.out.println(announcement);
                    }
                    System.out.println(listIsEmpty(all, Announcement.class));
                }
                case 7 -> {
                    if (currentUser != null){
                        System.out.println("Write announcement id to favorite: ");
                      favoriteService.favoriteAnnouncement(currentUser.getId(), new Scanner(System.in).nextLong());
                    }
                }
                case 8 ->{
                    if (currentUser != null){
                        Map<User, List<Announcement>> allByUserId = favoriteService.getAllByUserId(currentUser.getId());

                        Set<Map.Entry<User, List<Announcement>>> entries = allByUserId.entrySet();

                        for (Map.Entry<User, List<Announcement>> entry : entries) {
                            System.out.println(entry.getKey());
                            System.out.println("Announcements: [");
                            for (Announcement announcement : entry.getValue()) {
                                System.out.println(announcement);
                            }
                            System.out.println("]");
                        }

                    }
                }
            }
        }
    }

    private static <T> String listIsEmpty(List<?> list, Class<T> object){
       return list.isEmpty() ? String.format("%s list empty", object.getSimpleName()) : null;
    }

    public static List<User> createUsers(){
        return new ArrayList<>(
                Arrays.asList(
                        new User(
                                "Baisalbek",
                                "baisalbek@gmail.com",
                                "Baisalbek18!",
                                "https:lasfjalkij2k3nflkjf",
                                "+996700600500"
                        ),
                        new User(
                                "Atai",
                                "atai@gmail.com",
                                "Atai!",
                                "https:lasfjalkij2k3nflkjf",
                                "+996700600555"
                        ),
                        new User(
                                "Babakhan",
                                "babakhan@gmail.com",
                                "Babakhan!",
                                "https:lasfjalkij2k3nflkjf",
                                "+996700600777"
                        )
                )
        );
    }
}