package Model.User;

public class User implements UserInterface {

    private int num;
    private String firstName;
    private String lastName;
    private String email;
    private String hashPasswd;
    private String adress;

    public User(String email, String hashPasswd)
    {
        this.email = email;
        this.hashPasswd = hashPasswd;
    }


    @Override
    public int GetNum() {
        return this.num;
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
