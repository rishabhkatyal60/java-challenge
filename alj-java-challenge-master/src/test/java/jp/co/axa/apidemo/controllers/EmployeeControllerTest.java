package jp.co.axa.apidemo.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import jp.co.axa.apidemo.ApiDemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import jp.co.axa.apidemo.entities.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:"+port+"/api/v1";
    }

    /**
     * Test for the create Employee Controller.
     */
    @Test
    public void testCreateEmployee() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("John");
        employee.setSalary(10000);
        employee.setDepartment("Core Engineering");
        ResponseEntity<Employee> postResponse = restTemplate.postForEntity(getRootUrl() + "/employees", employee, Employee.class);
        assertEquals("200 OK",postResponse.getStatusCode().toString());
    }

    /**
     * Test for the retrieve all employees information Controller.
     */
    @Test
    public void testGetAllEmployees() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/employees",
                HttpMethod.GET, entity, String.class);
        assertEquals("200 OK",response.getStatusCode().toString());
        assertNotNull(response.getBody());
    }

    /**
     * Test for the retrieve an employee information by id Controller.
     */
    @Test
    public void testGetEmployeeById() {
        Employee employee = restTemplate.getForObject(getRootUrl() + "/employees/1", Employee.class);
        System.out.println(employee.getName());
        assertNotNull(employee);
    }

    @Test
    public void testUpdateEmployee() {
        int id = 1;
        Employee employee = restTemplate.getForObject(getRootUrl() + "/update", Employee.class);
        employee.setName("Andre");
        employee.setSalary(10000);
        restTemplate.put(getRootUrl() + "/update" + id, employee);
        Employee updatedEmployee = restTemplate.getForObject(getRootUrl() + "/update" + id, Employee.class);
        assertNotNull(updatedEmployee);
    }

    /**
     * Test the Controller to delete an employee's information.
     */
    @Test
    public void testDeleteEmployee() {
        int id = 2;
        Employee employee = restTemplate.getForObject(getRootUrl() + "/employees/" + id, Employee.class);
        assertNotNull(employee);
        restTemplate.delete(getRootUrl() + "/employees/" + id);
        try {
            employee = restTemplate.getForObject(getRootUrl() + "/employees/" + id, Employee.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}