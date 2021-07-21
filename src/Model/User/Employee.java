package Model.User;

public class Employee extends User
{
    private static int nbEmployee = 0;
    private int numEmployee;
    private String firstName;
    private String lastName;
    private String adress;

    private String email;
    private String hashPassword;

    public Employee( String email,  String hashPassword, int numEmployee, String firstName, String lastName, String adress)
    {
        super(email, hashPassword, firstName, lastName, adress);
        this.numEmployee = numEmployee;
    }

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

    public static int GetNewNumEmployee()
    {
        nbEmployee += 1;
        return 100 * nbEmployee;
    }

}
