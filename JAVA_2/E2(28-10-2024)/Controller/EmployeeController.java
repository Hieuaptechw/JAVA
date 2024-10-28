package Controller;

import Entity.Employee;
import Service.EmployeeService;

import java.util.Map;
import java.util.Set;

public class EmployeeController {
    private EmployeeService eS;

    public EmployeeController(EmployeeService eS) {
        this.eS = eS;
    }
    public Set<Employee> searchByName(String name) {
        return eS.searchByName(name);
    }
    public Map<String, Set<Employee>> groupByDepartment() {
        return eS.groupByName();
    }
}
