package Model.User;

import Model.DbConnect.DbInterface;

import java.sql.SQLException;

public class Customer extends User {
    private int numCust;
    private String lastName;
    private String firstName;
    private String email;
    private String adress;
    private String hashPassword;

    private static int nbCustommer = 0;

    public Customer( String email,  String hashPassword, int numCust, String firstName, String lastName, String adress)
    {
        super(email, hashPassword, firstName, lastName, adress);
        this.numCust = numCust;
    }

    public Customer(int numCust, String email, String hashPassword)
    {
        super(email, hashPassword);
        this.numCust = numCust;
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

    public static int GetNewNumCustomer()
    {
        nbCustommer += 1;
        return 1000 * nbCustommer;
    }
    public static void UpdateNbCustomer()
    {
        try{
           nbCustommer = DbInterface.GetNbCustomer();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}

