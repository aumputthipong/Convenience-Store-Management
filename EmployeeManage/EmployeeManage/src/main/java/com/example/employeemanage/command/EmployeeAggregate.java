package com.example.employeemanage.command;

import com.example.employeemanage.command.rest.CUDCommand.employee.*;
import com.example.employeemanage.core.events.employee.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class EmployeeAggregate {

    @AggregateIdentifier
    private String aggregateId;
    private Long employeeId;
    private String searchFirstName;
    private String searchLastName;
    private String firstName;
    private String lastName;
    private String dob;
    private String position;
    private Integer salary;
    private Integer leaveCount;
    private Integer lateCount;

    public EmployeeAggregate() {
    }
    @CommandHandler
    public EmployeeAggregate(CreateEmployeeCommand createEmployeeCommand) {

        if (createEmployeeCommand.getFirstName() == null || createEmployeeCommand.getFirstName().isBlank()) {
            throw new IllegalArgumentException("FirstName cannot be empty");
        }
        if (createEmployeeCommand.getLastName() == null || createEmployeeCommand.getLastName().isBlank()) {
            throw new IllegalArgumentException("LastName cannot be empty");
        }
        if (createEmployeeCommand.getDob() == null || createEmployeeCommand.getDob().isBlank()) {
            throw new IllegalArgumentException("Date of Birth cannot be empty");
        }
        if (createEmployeeCommand.getSalary() == null ) {
            throw new IllegalArgumentException("Salary cannot be empty");
        }


        EmployeeCreatedEvent employeeCreatedEvent = new EmployeeCreatedEvent();
        BeanUtils.copyProperties(createEmployeeCommand, employeeCreatedEvent);
        AggregateLifecycle.apply(employeeCreatedEvent);
    }
    @EventSourcingHandler
    public void on(EmployeeCreatedEvent employeeCreatedEvent) {
        System.out.println("ON POST AGGREGATE");
        this.aggregateId = employeeCreatedEvent.getAggregateId();
        this.firstName = employeeCreatedEvent.getFirstName();
        this.lastName = employeeCreatedEvent.getLastName();
        this.dob = employeeCreatedEvent.getDob();
        this.position = employeeCreatedEvent.getPosition();
        this.salary = employeeCreatedEvent.getSalary();
    }

    @CommandHandler
    public EmployeeAggregate(UpdateEmployeeCommand updateEmployeeCommand) {
        if (updateEmployeeCommand.getEmployeeId() == null ) {
            throw new IllegalArgumentException("employeeId cannot be empty");
        }
        EmployeeUpdatedEvent employeeUpdatedEvent = new EmployeeUpdatedEvent();
        BeanUtils.copyProperties(updateEmployeeCommand, employeeUpdatedEvent);
        AggregateLifecycle.apply(employeeUpdatedEvent);
        System.out.println(employeeUpdatedEvent);
    }

    @EventSourcingHandler
    public void on(EmployeeUpdatedEvent employeeUpdatedEvent) {

        System.out.println("ON UPDATE AGGREGATE");

        if (employeeUpdatedEvent.getFirstName() == null || employeeUpdatedEvent.getFirstName().isBlank()) {
            throw new IllegalArgumentException("FirstName cannot be empty");
        }
        if (employeeUpdatedEvent.getLastName() == null || employeeUpdatedEvent.getLastName().isBlank()) {
            throw new IllegalArgumentException("LastName cannot be empty");
        }
        if (employeeUpdatedEvent.getDob() == null || employeeUpdatedEvent.getDob().isBlank()) {
            throw new IllegalArgumentException("Date of Birth cannot be empty");
        }
        if (employeeUpdatedEvent.getSalary() == null ) {
            throw new IllegalArgumentException("Salary cannot be empty");
        }

        this.aggregateId = employeeUpdatedEvent.getAggregateId();
        this.employeeId = employeeUpdatedEvent.getEmployeeId();
        this.firstName = employeeUpdatedEvent.getFirstName();
        this.lastName = employeeUpdatedEvent.getLastName();
        this.dob = employeeUpdatedEvent.getDob();
        this.position = employeeUpdatedEvent.getPosition();
        this.salary = employeeUpdatedEvent.getSalary();
    }
    @CommandHandler
    public EmployeeAggregate(DeleteEmployeeCommand deleteEmployeeCommand) {
        EmployeeDeletedEvent employeeDeletedEvent = new EmployeeDeletedEvent();
        BeanUtils.copyProperties(deleteEmployeeCommand, employeeDeletedEvent);
        AggregateLifecycle.apply(employeeDeletedEvent);
    }
    @EventSourcingHandler
    public void on(EmployeeDeletedEvent employeeDeletedEvent) {
        if (employeeDeletedEvent.getEmployeeId() == null ) {
            throw new IllegalArgumentException("EmployeeId cannot be empty");
        }
        System.out.println("ON Delete AGGREGATE");
        this.aggregateId = employeeDeletedEvent.getAggregateId();
        this.employeeId = employeeDeletedEvent.getEmployeeId();
    }


    @CommandHandler
    public EmployeeAggregate(UpdateLeaveCommand updateLeaveCommand) {
        if (updateLeaveCommand.getEmployeeId() == null ) {
            throw new IllegalArgumentException("employeeId cannot be empty");
        }
        if (updateLeaveCommand.getLeaveCount() == null ) {
            throw new IllegalArgumentException("LeaveCount cannot be empty");
        }
        LeaveCountUpdatedEvent leaveCountUpdatedEvent = new LeaveCountUpdatedEvent();
        BeanUtils.copyProperties(updateLeaveCommand, leaveCountUpdatedEvent);
        AggregateLifecycle.apply(leaveCountUpdatedEvent);
        System.out.println(leaveCountUpdatedEvent);
    }

    @EventSourcingHandler
    public void on(LeaveCountUpdatedEvent leaveCountUpdatedEvent) {

        System.out.println("ON UPDATE Leave AGGREGATE");
        this.aggregateId = leaveCountUpdatedEvent.getAggregateId();
        this.employeeId = leaveCountUpdatedEvent.getEmployeeId();
        this.leaveCount = leaveCountUpdatedEvent.getLeaveCount();

    }

    @CommandHandler
    public EmployeeAggregate(UpdateLateCommand updateLateCommand) {
        if (updateLateCommand.getEmployeeId() == null ) {
            throw new IllegalArgumentException("employeeId cannot be empty");
        }
        if (updateLateCommand.getLateCount() == null ) {
            throw new IllegalArgumentException("LateCount cannot be empty");
        }
        LateCountUpdatedEvent lateCountUpdatedEvent = new LateCountUpdatedEvent();
        BeanUtils.copyProperties(updateLateCommand, lateCountUpdatedEvent);
        AggregateLifecycle.apply(lateCountUpdatedEvent);
        System.out.println(lateCountUpdatedEvent);
    }

    @EventSourcingHandler
    public void on(LateCountUpdatedEvent lateCountUpdatedEvent) {

        System.out.println("ON late UPDATE AGGREGATE");
        this.aggregateId = lateCountUpdatedEvent.getAggregateId();
        this.employeeId = lateCountUpdatedEvent.getEmployeeId();
        this.lateCount = lateCountUpdatedEvent.getLateCount();
    }
}
