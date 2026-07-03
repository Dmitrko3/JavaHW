public class SalesEmployee extends Employee implements Sale
{
    private float commis;
    public SalesEmployee(float salary, float commis)
    {
        super(salary);
        this.commis = commis;
    }
    public float ComputeSalary()
    {
        return salary * (1+commis/100);
    }
    public String getName() { return "Sales Employee"; }

    @Override
    public float getCom() {
        return commis;
    }

    @Override
    public String getSaleName() {
        return "Sales Employee";
    }
}
