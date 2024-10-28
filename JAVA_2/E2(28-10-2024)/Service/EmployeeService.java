package Service;

import Entity.Department;
import Entity.Employee;
import Global.Global;
import IGeneric.IGeneral;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeService implements IGeneral<Employee> {
    private Set<Employee> employees;
    private List<Department> departments;
    public EmployeeService(Set<Employee> employees, List<Department> departments) {
        this.employees = employees;
        this.departments = departments;
    }
    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    @Override
    public Set<Employee> searchByName(String name) {
        Set <Integer> id = departments.stream()
                .filter(d-> Global.ignoreCase(d.getName(),name))
                .map(Department::getId)
                .collect(Collectors.toSet());
        return employees.stream().filter(e -> id.contains(e.getId())).collect(Collectors.toSet());
    }

    @Override
    public Map<String, Set<Employee>> groupByName() {
        Map<Integer, String> department = departments.stream()
                .collect(Collectors.toMap(Department::getId, Department::getName));
        return employees.stream()
                .collect(Collectors.groupingBy(
                        employee -> department.get(employee.getDeptId()),
                        Collectors.toSet()
                ));
    }


    @Override
    public Map<String, Long> countEmployees() {
        return Map.of();
    }
}
