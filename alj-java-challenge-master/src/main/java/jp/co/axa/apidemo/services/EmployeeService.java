package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> retrieveEmployees();

    public Employee getEmployee(Long id);

    public void saveEmployee(Employee employee);

    public void deleteEmployee(Long id);

    public Employee updateEmployee(Employee employee);
}