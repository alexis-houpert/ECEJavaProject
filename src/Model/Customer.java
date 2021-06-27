package Model;

public abstract class Customer implements User {
    private int numCust;
    private String lastName;
    private String firstName;
    private String email;
    private String adress;
    private String hashPassword;


    public Customer(int numCust, String lastName, String firstName, String email, String adress, String hashPassword)
    {
        this.numCust = numCust;
        this.lastName = lastName;
        this.firstName = firstName;
        this.adress = adress;
        this.email = email;
        this.hashPassword = hashPassword;
    }

    public Customer(int numCust, String email, String hashPassword)
    {
        this.numCust = numCust;
        this.email = email;
        this.hashPassword = hashPassword;
    }

    public int GetNum()
    {
        return this.numCust;
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
