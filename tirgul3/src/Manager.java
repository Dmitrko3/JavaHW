public class Manager extends Employee
{
    public float kmult;
    public Manager(float salary, float kmult)
    {
        super(salary);
        this.kmult = kmult;
    }
    public float ComputeSalary()
    {
        return salary * kmult;
    }
    public String getName() { return "Manager"; }

}