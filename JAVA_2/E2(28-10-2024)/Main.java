import Controller.EmployeeController;
import Entity.Department;
import Entity.Employee;
import Entity.Gender;
import Service.EmployeeService;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<Department> departments = new ArrayList<>();
        Set<Employee> employees = new HashSet<>();
        departments.add(new Department(1, "HR"));
        departments.add(new Department(2, "IT"));
        departments.add(new Department(3, "Sales"));

        employees.add(new Employee(1, "Nguyễn Thuý Hải", 1, "1990-01-01", "Nam", "Hà Nội", "HN", "0123456789"));
        employees.add(new Employee(2, "Nguyễn Thị Vinh", 2, "1992-02-02", "Nữ", "Thành phố Hồ Chí Minh", "HCM", "0987654321"));
        employees.add(new Employee(3, "Trần Phương Hiếu", 1, "1991-03-03", "Nam", "Đà Nẵng", "DN", "0456789123"));
        employees.add(new Employee(4, "Phi Hiếu", 3, "1988-04-04", "Nữ", "Cần Thơ", "CT", "0321654987"));
        EmployeeService eS = new EmployeeService(employees,departments);
        EmployeeController eC = new EmployeeController(eS);
        Set<Employee> emp = eC.searchByName("IT");
        System.out.println(emp);
        Map<String, Set<Employee>> groupedEmployees = eC.groupByDepartment();
        System.out.println(groupedEmployees);
    }
}
