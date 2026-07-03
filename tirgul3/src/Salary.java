public class Salary
{
    public static void main(String[] args)
    {
        // Create four different types of employees
        Employee employee = new Employee(1000);
        Manager manager = new Manager(1000, 1.5f);
        SalesEmployee salesEmployee = new SalesEmployee(1000, 10);
        SalesManager salesManager = new SalesManager(1000, 1.5f, 10);

        // Print their names and salaries
        System.out.println(employee.getName() + " - Salary: " + employee.ComputeSalary());
        System.out.println(manager.getName() + " - Salary: " + manager.ComputeSalary());
        System.out.println(salesEmployee.getName() + " - Salary: " + salesEmployee.ComputeSalary());
        System.out.println(salesManager.getName() + " - Salary: " + salesManager.ComputeSalary());
    }
}