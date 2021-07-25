package Model.User;

import Model.DbConnect.DbInterface;

import java.sql.SQLException;

public class Customer extends User {
    private int numCust;

    private static int nbCustommer = 0;

    public Customer(int id, String email,  String hashPassword, int numCust, String firstName, String lastName, String adress)
    {
        super(id, email, hashPassword, firstName, lastName, adress);
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

