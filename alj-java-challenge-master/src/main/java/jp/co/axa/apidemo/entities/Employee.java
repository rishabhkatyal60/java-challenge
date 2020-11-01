package jp.co.axa.apidemo.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="EMPLOYEE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {

    /**
     * id of the employee.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * name of the employee.
     */
    @Column(name="EMPLOYEE_NAME")
    private String name;

    /**
     * salary of the employee.
     */
    @Column(name="EMPLOYEE_SALARY")
    private Integer salary;

    /**
     * department of the employee.
     */
    @Column(name="DEPARTMENT")
    private String department;

}
