package Model.User;

public class Employee extends User
{
    private static int nbEmployee = 0;
    private int numEmployee;


    public Employee(int id, String email,  String hashPassword, int numEmployee, String firstName, String lastName, String adress)
    {
        super(id, email, hashPassword, firstName, lastName, adress);
        this.numEmployee = numEmployee;
    }

    public int GetNum()
    {
        return this.numEmployee;
    }


    public static int GetNewNumEmployee()
    {
        nbEmployee += 1;
        return 100 * nbEmployee;
    }

}
