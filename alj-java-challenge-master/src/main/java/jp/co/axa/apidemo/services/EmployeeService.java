package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;

import java.util.List;

public interface EmployeeService {

    /**
     * retrieve the information for all employees.
     *
     * @return List<Employee>. Information of all employees
     */
    List<Employee> retrieveEmployees();

    /**
     * retrieve the information of an employee by employee id.
     *
     * @param id employee id
     * @return Employee Information of an employee
     */
    Employee getEmployee(Long id);

    /**
     * save an employee to the database.
     *
     * @param employee Information of an employee
     */
    void saveEmployee(Employee employee);

    /**
     * delete the information of an employee by employee id.
     *
     * @param id employee id
     */
    void deleteEmployee(Long id);

    /**
     * Update an employee's information to the database.
     *
     * @param employee Information of an employee
     * @return employee Updated Information of an employee
     */
    Employee updateEmployee(Employee employee);
}