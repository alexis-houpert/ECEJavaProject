package Model;

public class MemberCustomer extends Customer
{
    private double reductionCoupon;

    public MemberCustomer(int numCust, String lastName, String firstName, String email, String adress, String hashPassword)
    {
        super(numCust, lastName, firstName, email, adress, hashPassword);
    }

    public MemberCustomer(int numCust, String email, String hashPassword)
    {
        super(numCust, email, hashPassword);
    }

    public double GetReductionCoupon()
    {
        return this.reductionCoupon;
    }
}
