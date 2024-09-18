package peaksoft.models;

public class IdGenerator {
    private static Long userId = 1L;
    private static Long announcementId = 1L;
    private static Long favoriteId = 1L;

    public static Long getUserId(){
        return userId++;
    }

    public static Long getAnnouncementId(){
        return announcementId++;
    }

    public static Long getFavoriteId(){
        return favoriteId++;
    }

}
