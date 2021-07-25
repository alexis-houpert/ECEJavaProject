package Model.User;

public class UserSession
{
    public static User user;

    public static Role role;


    public static void setUser(User user) {
        UserSession.user = user;
    }

    public static void setRole(Role role) {
        UserSession.role = role;
    }

    public static User getUser() {
        return user;
    }

    public static Role getRole() {
        return role;
    }

}
