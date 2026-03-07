
class Employee {
    String name;
    int id;
    double baseSalary;


    Employee(String name, int id, double baseSalary) {
        this.name = name;
        this.id = id;
        this.baseSalary = baseSalary;
    }


    double calculateSalary() {
        return baseSalary;
    }

 
    void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
    }
}


class FullTimeEmployee extends Employee {
    double bonus;

    FullTimeEmployee(String name, int id, double baseSalary, double bonus) {
        super(name, id, baseSalary);
        this.bonus = bonus;
    }

    @Override
    double calculateSalary() {
        return baseSalary + bonus;
    }
}


class PartTimeEmployee extends Employee {
    int hoursWorked;
    double hourlyRate;

    PartTimeEmployee(String name, int id, double baseSalary, int hoursWorked, double hourlyRate) {
        super(name, id, baseSalary);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
}

public class q1 {
    public static void main(String[] args) {

        Employee[] employees = new Employee[2];

        employees[0] = new FullTimeEmployee("Shubham", 101, 50000, 10000);
        employees[1] = new PartTimeEmployee("Rahul", 102, 0, 20, 500);

        for (Employee emp : employees) {
            emp.displayDetails();
            System.out.println("Salary: " + emp.calculateSalary());
            
        }
    }
}
    


