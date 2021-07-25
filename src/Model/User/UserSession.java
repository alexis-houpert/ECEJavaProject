package Model.User;

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
