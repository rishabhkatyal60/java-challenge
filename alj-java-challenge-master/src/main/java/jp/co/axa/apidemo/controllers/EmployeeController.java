package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.*;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    /**
     * Logger to identify whether Redis Caching is used or not.
     */
    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    /**
     * Controller to retrieve the information for all employees.
     *
     * @return list of all employees
     */
    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        logger.debug(" >> EmployeeController : /employees GET call :");
        List<Employee> employees = employeeService.retrieveEmployees();
        return employees;
    }

    /**
     * Controller to retrieve the information of the employee by id .
     *
     * @param id employee id
     * @return employee information using employee id
     */
    @GetMapping("/employees/{id}")
    @Cacheable(value = "employees",key = "#id")
    public Employee getEmployee(@PathVariable(name="id")long id) {
        logger.debug(" >> EmployeeController : /employees/{} GET call :",id);
        return employeeService.getEmployee(id);
    }

    /**
     * Controller to create a new employee.
     *
     * @param employee employee
     *
     */
    @PostMapping("/employees")
    public void saveEmployee(Employee employee){
        logger.debug(" >> EmployeeController : /employees POST call :",employee.toString());
        employeeService.saveEmployee(employee);
        System.out.println("Employee Saved Successfully");
    }

    /**
     * Controller to delete an employee.
     *
     * @param id employee id
     *
     */
    @DeleteMapping("/employees/{id}")
    @CacheEvict(value = "employees",allEntries = false,key = "#id")
    public void deleteEmployee(@PathVariable(name="id")long id){
        logger.debug(" >> EmployeeController : /delete : ",id);
        employeeService.deleteEmployee(id);
        logger.debug(" << EmployeeController : /delete : ",id);
        System.out.println("Employee Deleted Successfully");
    }

    /**
     * Controller to update an employee's information.
     *
     * @param employee employee
     *
     */
    @PutMapping("/update")
    @CachePut(value = "employees",key = "#employee.id")
    public Employee updateEmployee(@RequestBody Employee employee) {
        Long id = employee.getId();
        Employee emp = employeeService.getEmployee(id);
        if(emp != null){
            logger.debug(" >> EmployeeController : /update PUT call: ",employee.toString());
            return employeeService.updateEmployee(employee);
        }
        return null;
    }

}
