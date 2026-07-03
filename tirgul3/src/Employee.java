public class Employee
{
    public float salary;
    public Employee(float salary)
    {
        this.salary = salary;
    }
    public float ComputeSalary()
    {
        return salary;
    }
    public String getName() { return "Employee"; }
}