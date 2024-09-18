package peaksoft.databse;

import peaksoft.models.Announcement;
import peaksoft.models.Favorite;
import peaksoft.models.User;

import java.util.ArrayList;
import java.util.List;

public class Database {
    public List<User> users = new ArrayList<>();
    public List<Announcement> announcements = new ArrayList<>();
    public List<Favorite> favorites = new ArrayList<>();
}
