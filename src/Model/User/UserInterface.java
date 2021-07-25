package Model.User;

/**
 * Define the global behavior of User
 */
public interface UserInterface
{
    public int GetId();
    public String GetFirstName();
    public String GetLastName();
    public String GetEmail();
    public String GetAdress();
    public String GetHashPassword();
}
