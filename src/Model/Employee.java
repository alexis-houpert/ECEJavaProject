package Model;

public class Employee implements User
{
    private int numEmployee;
    private String firstName;
    private String lastName;
    private String adress;

    private String email;
    private String hashPassword;

    public int GetNum()
    {
        return this.numEmployee;
    }

    public String GetFirstName()
    {
        return this.firstName;
    }
    public String GetLastName()
    {
        return this.lastName;
    }
    public String GetEmail()
    {
        return this.email;
    }

    public String GetHashPassword()
    {
        return this.hashPassword;
    }
    public String GetAdress()
    {
        return this.adress;
    }

}
