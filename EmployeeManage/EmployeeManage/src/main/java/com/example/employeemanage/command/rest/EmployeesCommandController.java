package com.example.employeemanage.command.rest;


import com.example.employeemanage.command.rest.CUDCommand.employee.*;
import com.example.employeemanage.command.rest.CUDCommand.image.CreateImageCommand;
import com.example.employeemanage.command.rest.CUDCommand.image.DeleteImageCommand;
import com.example.employeemanage.command.rest.CUDCommand.image.UpdateImageCommand;
import com.example.employeemanage.util.ImageUtils;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping(value = "/employeesManage")
public class EmployeesCommandController {
    private final Environment env;
    private final CommandGateway commandGateway;




    @Autowired
    public EmployeesCommandController(Environment env, CommandGateway commandGateway) {
        this.env = env;
        this.commandGateway = commandGateway;

    }

    @PostMapping("/createEmployee")
    public String createEmployee(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String dob, @RequestParam String position, @RequestParam int salary) {

        CreateEmployeeCommand command = CreateEmployeeCommand.builder()
                .aggregateId(UUID.randomUUID().toString())
                .firstName(firstName)
                .lastName(lastName)
                .dob(dob)
                .position(position)
                .salary(salary)
                .leaveCount(0)
                .lateCount(0)
                .build();


        try {
            commandGateway.send(command);
        } catch (Exception e) {
            return e.getLocalizedMessage();
        }

        return "Create Command sent successfully";
    }


    @GetMapping
    public String getEmployee() {
        return "HTTP GET Handled " + env.getProperty("local.server.port");
    }

    @PutMapping("/update/{employeeId}")
    public String updateEmployee(@PathVariable Long employeeId,@RequestParam String firstName, @RequestParam String lastName, @RequestParam String dob, @RequestParam String position, @RequestParam int salary) {

        UpdateEmployeeCommand command = UpdateEmployeeCommand.builder()
                .aggregateId(UUID.randomUUID().toString())
                .employeeId(employeeId)
                .firstName(firstName)
                .lastName(lastName)
                .dob(dob)
                .position(position)
                .salary(salary)
                .build();
        try {
            commandGateway.send(command);
        } catch (Exception e) {
            return e.getLocalizedMessage();
        }

        return "Update Command sent successfully";
    }

    @DeleteMapping("/delete/{employeeId}")
    public String deleteEmployee(@PathVariable Long employeeId) {
        DeleteEmployeeCommand command = DeleteEmployeeCommand.builder()
                .aggregateId(UUID.randomUUID().toString())
                .employeeId(employeeId)
                .build();
        try {
            commandGateway.send(command);
        } catch (Exception e) {
            return e.getLocalizedMessage();
        }

        return "DeleteEmployee Command sent successfully";
    }



    @PostMapping("/imageAdd/{employeeId}")
    public String uploadImage(@PathVariable  Long employeeId,@RequestParam("image")MultipartFile file) throws IOException {

        CreateImageCommand command = CreateImageCommand.builder()
                .aggregateId(UUID.randomUUID().toString())
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .employeeId(employeeId)
                .imageData(ImageUtils.compressImage(file.getBytes())).build();
        try {
            commandGateway.send(command);
        } catch (Exception e) {
            return e.getLocalizedMessage();
        }

        return "ImageAdd Command sent successfully";
    }
//
    @PutMapping("/updateLeave/{employeeId}")
    public String updateLeaveCount(@PathVariable Long employeeId,@RequestParam Integer leaveCount) {
        UpdateLeaveCommand command = UpdateLeaveCommand.builder()
                .aggregateId(UUID.randomUUID().toString())
                .employeeId(employeeId)
                .leaveCount(leaveCount)
                .build();
        try {
            commandGateway.send(command);
        } catch (Exception e) {
            return e.getLocalizedMessage();
        }

        return "UpdateLeave Command sent successfully";
    }

    @PutMapping("/updateLate/{employeeId}")
    public String updateLateCount(@PathVariable Long employeeId,@RequestParam Integer lateCount) {
        UpdateLateCommand command = UpdateLateCommand.builder()
                .aggregateId(UUID.randomUUID().toString())
                .employeeId(employeeId)
                .lateCount(lateCount)
                .build();
        try {
            commandGateway.send(command);
        } catch (Exception e) {
            return e.getLocalizedMessage();
        }

        return "UpdateLate Command sent successfully";
    }


    @PutMapping("/imageUpdate/{employeeId}")
    public String updateImage(@PathVariable  Long employeeId,@RequestParam("image")MultipartFile file) throws IOException {

        UpdateImageCommand command = UpdateImageCommand.builder()
                .aggregateId(UUID.randomUUID().toString())
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .employeeId(employeeId)
                .imageData(ImageUtils.compressImage(file.getBytes())).build();
        try {
            commandGateway.send(command);
        } catch (Exception e) {
            return e.getLocalizedMessage();
        }

        return "ImageUpdate Command sent successfully";
    }
    @DeleteMapping("/imageDelete/{employeeId}")
    public String deleteImage(@PathVariable  Long employeeId) {
        DeleteImageCommand command = DeleteImageCommand.builder()
                .aggregateId(UUID.randomUUID().toString())
                .employeeId(employeeId)
                .build();
        try {
            commandGateway.send(command);
        } catch (Exception e) {
            return e.getLocalizedMessage();
        }

        return "ImageDelete Command sent successfully";
    }



}