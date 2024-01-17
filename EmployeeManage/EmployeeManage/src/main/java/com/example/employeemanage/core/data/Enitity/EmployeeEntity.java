package com.example.employeemanage.core.data.Enitity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Entity
//
//@Table(name = "employees", uniqueConstraints = @UniqueConstraint(columnNames = {"firstName", "lastName"}))
@Table(name = "employees")
@Data
public class EmployeeEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 7161863954098173667L;

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    //    @Column(unique = true)
    private String firstName;
    private String lastName;
    private String dob;
    private String position;
    private Integer salary;
    private Integer leaveCount;
    private Integer lateCount;
}
