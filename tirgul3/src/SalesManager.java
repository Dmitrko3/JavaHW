public class SalesManager extends Manager implements Sale
{
    private float commis;
    public SalesManager(float salary, float kmult, float commis)
    {
        super(salary, kmult);
        this.commis = commis;
    }
    public float ComputeSalary()
    {
        return salary * kmult * (1+commis/100);
    }

    @Override
    public float getCom() {
        return commis;
    }

    @Override
    public String getSaleName() {
        return "Sales Manager";
    }

    @Override
    public String getName() { return "Sales Manager"; }

}