package com.example.employeemanage.query.rest;
import com.example.employeemanage.query.FindQuery.*;
import com.example.employeemanage.query.rest.RestModel.EmployeeRestModel;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesQueryController {
    private final QueryGateway queryGateway;
    private final RabbitTemplate rabbitTemplate;
    @Autowired
    public EmployeesQueryController(QueryGateway queryGateway,RabbitTemplate rabbitTemplate) {
        this.queryGateway = queryGateway;
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/getAll")
    public List<EmployeeRestModel> getEmployees() {
        FindAllEmployeeQuery findEmployeeQuery = new FindAllEmployeeQuery();
        return queryGateway.query(
                findEmployeeQuery,
                ResponseTypes.multipleInstancesOf(EmployeeRestModel.class)
        ).join();
    }

    @GetMapping("/getEmployee/{employeeId}")
    public List<EmployeeRestModel> getEmployee(@PathVariable Long employeeId) {
        FindEmployeeByIdQuery findEmployeeByNameQuery = new FindEmployeeByIdQuery(employeeId);
        return queryGateway.query(
                findEmployeeByNameQuery,
                ResponseTypes.multipleInstancesOf(EmployeeRestModel.class)
        ).join();
    }

@GetMapping("/getImage/{employeeId}")
public ResponseEntity<byte[]> getImage(@PathVariable Long employeeId) {
    // ส่งคำขอไปยัง RabbitMQ
    byte[] imageData = (byte[]) rabbitTemplate.convertSendAndReceive("ImageExchange","getImg", employeeId);
    if (imageData != null && imageData.length > 0) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_PNG)
                .body(imageData);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
//filterName
    @GetMapping("/searchByName/{firstName}")
    public List<EmployeeRestModel> searchEmployeeByName(@PathVariable String firstName) {
        FilterEmployeesByNameQuery findAllEmployeesByNameQuery= new FilterEmployeesByNameQuery(firstName);
        return queryGateway.query(
                findAllEmployeesByNameQuery,
                ResponseTypes.multipleInstancesOf(EmployeeRestModel.class)
        ).join();
    }
//filterLastName
    @GetMapping("/searchLastName/{lastName}")
    public List<EmployeeRestModel> searchEmployeeByLastName(@PathVariable String lastName) {
        FilterEmployeesByLastQuery findAllEmployeesByLastQuery= new FilterEmployeesByLastQuery(lastName);
        return queryGateway.query(
                findAllEmployeesByLastQuery,
                ResponseTypes.multipleInstancesOf(EmployeeRestModel.class)
        ).join();
    }

    @GetMapping("/filterSalary/{salary1}/{salary2}")
    public List<EmployeeRestModel> filterEmployeeBySalary(@PathVariable Integer salary1, @PathVariable Integer salary2) {
        FilterEmployeesBetweenSalaryQuery findEmployeeBetweenSalaryQuery= new FilterEmployeesBetweenSalaryQuery(salary1, salary2);
        return queryGateway.query(
                findEmployeeBetweenSalaryQuery,
                ResponseTypes.multipleInstancesOf(EmployeeRestModel.class)
        ).join();
    }
    @GetMapping("/searchPosition/{position}")
    public List<EmployeeRestModel> filterPosition(@PathVariable String position) {
        FilterEmployeeByPositionQuery filterEmployeeByPositionQuery= new FilterEmployeeByPositionQuery(position);
        return queryGateway.query(
                filterEmployeeByPositionQuery,
                ResponseTypes.multipleInstancesOf(EmployeeRestModel.class)
        ).join();
    }

    @GetMapping("/filterLeave/{leaveCount1}/{leaveCount2}")
    public List<EmployeeRestModel> filterLeave(@PathVariable Integer leaveCount1,@PathVariable Integer leaveCount2) {
        FilterEmployeeByLeaveQuery filterEmployeeByLeaveQuery= new FilterEmployeeByLeaveQuery(leaveCount1 ,leaveCount2);
        return queryGateway.query(
                filterEmployeeByLeaveQuery,
                ResponseTypes.multipleInstancesOf(EmployeeRestModel.class)
        ).join();
    }

    @GetMapping("/filterLate/{lateCount1}/{lateCount2}")
    public List<EmployeeRestModel> filterLate(@PathVariable Integer lateCount1,@PathVariable Integer lateCount2) {
        FilterEmployeeByLateQuery filterEmployeeByLateQuery= new FilterEmployeeByLateQuery(lateCount1 , lateCount2);
        return queryGateway.query(
                filterEmployeeByLateQuery,
                ResponseTypes.multipleInstancesOf(EmployeeRestModel.class)
        ).join();
    }



}
