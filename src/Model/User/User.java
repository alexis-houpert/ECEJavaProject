package Model.User;

/**
 * @author Alexis HOUPERT
 * User is use to represent User data in client side. Use to transmit and store data
 */
public class User implements UserInterface {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String hashPasswd;
    private String adress;


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private String role;

    public User()
    {

    }

    public User(String email, String hashPasswd)
    {
        this.email = email;
        this.hashPasswd = hashPasswd;
    }

    public User(int id, String email, String hashPasswd, String role, String firstName, String lastName, String adress)
    {
        this.id = id;
        this.email = email;
        this.hashPasswd = hashPasswd;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.adress = adress;
    }


    @Override
    public int GetId() {
        return this.id;
    }

    @Override
    public String GetFirstName() {
        return this.firstName;
    }

    @Override
    public String GetLastName() {
        return this.lastName;
    }

    @Override
    public String GetEmail() {
        return this.email;
    }

    @Override
    public String GetAdress() {
        return this.adress;
    }

    @Override
    public String GetHashPassword() {
        return this.hashPasswd;
    }
}
