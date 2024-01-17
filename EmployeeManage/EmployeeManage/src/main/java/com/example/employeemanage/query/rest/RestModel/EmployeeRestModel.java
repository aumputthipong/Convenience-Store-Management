package com.example.employeemanage.query.rest.RestModel;
import lombok.Data;

@Data
public class EmployeeRestModel {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String dob;
    private String position;
    private Integer salary;
    private Integer leaveCount;
    private Integer lateCount;
}
