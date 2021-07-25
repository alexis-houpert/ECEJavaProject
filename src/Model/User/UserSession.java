package Model.User;

/**
 * Static class that is use to store information about the user connected
 */
public class UserSession
{
    public static User user;

    public static void setUser(User user) {
        UserSession.user = user;
    }

    public static User getUser() {
        return user;
    }



}
