package com.example.employeemanage.query;

import com.example.employeemanage.core.data.Enitity.EmployeeEntity;
import com.example.employeemanage.core.data.Enitity.ImageEntity;
import com.example.employeemanage.core.data.Repository.EmployeeRepository;
import com.example.employeemanage.core.data.Repository.ImageRepository;
import com.example.employeemanage.core.events.employee.*;
import com.example.employeemanage.core.events.image.ImageCreatedEvent;
import com.example.employeemanage.core.events.image.ImageDeletedEvent;
import com.example.employeemanage.core.events.image.ImageUpdatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmployeeEventsHandler {
    private final EmployeeRepository employeeRepository;
    private final ImageRepository imageRepository;

    public EmployeeEventsHandler(EmployeeRepository employeeRepository,ImageRepository imageRepository) {
        this.employeeRepository = employeeRepository;
        this.imageRepository = imageRepository;
    }

    @EventHandler
    public void on(EmployeeCreatedEvent event) {
        try {
        Optional<EmployeeEntity> existingEmployee = Optional.ofNullable(employeeRepository.findByFirstNameAndLastName(event.getFirstName(), event.getLastName()));
        if (existingEmployee.isPresent()) {
            // Handle the case when the employee already exists
            System.out.println("this Employee already exists in the database.");
            throw new IllegalArgumentException("this Employee already exists in the database.");
        } else {
            // Save the new employee
            EmployeeEntity employeeEntity = new EmployeeEntity();
            BeanUtils.copyProperties(event, employeeEntity);
            employeeRepository.save(employeeEntity);
        }
    } catch (Exception e) {
        System.out.println("Error handling EmployeeCreatedEvent: " + e.getMessage());
    }
    }
    @EventHandler
    public void on(EmployeeDeletedEvent event) {

        Long employeeId = event.getEmployeeId();

        // Check if an employee with the given employeeId exists
        Optional<EmployeeEntity> existingEmployee = Optional.ofNullable(employeeRepository.findByEmployeeId(employeeId));

        if (existingEmployee.isPresent()) {
            // Employee with the given employeeId exists, proceed with deletion
            employeeRepository.deleteByEmployeeId(employeeId);
            System.out.println("Employee with employeeId " + employeeId + " deleted.");
        } else {
            // Employee with the given employeeId does not exist
            System.out.println("Employee with employeeId " + employeeId + " does not exist. Cannot delete.");
        }
    }

    @EventHandler
    public void on(EmployeeUpdatedEvent event) {
        try {
            Long employeeId = event.getEmployeeId();

            Optional<EmployeeEntity> existingEmployee = Optional.ofNullable(employeeRepository.findByEmployeeId(employeeId));

            if (existingEmployee.isPresent()) {
                // Employee exists, handle accordingly (e.g., update fields)
                EmployeeEntity existingEmployeeEntity = existingEmployee.get();
                existingEmployeeEntity.setFirstName(event.getFirstName());
                existingEmployeeEntity.setLastName(event.getLastName());
                existingEmployeeEntity.setDob(event.getDob());
                existingEmployeeEntity.setPosition(event.getPosition());
                existingEmployeeEntity.setSalary(event.getSalary());
                employeeRepository.save(existingEmployeeEntity);
                System.out.println("Employee updated: " + existingEmployeeEntity);
            } else {
                // Handle the case when the employee is not found
                System.out.println("Employee not found for update: Id" + employeeId );
            }
        } catch (Exception e) {
            System.out.println("Error handling EmployeeUpdatedEvent: " + e.getMessage());
        }
    }

    @EventHandler
    public void on(ImageCreatedEvent event) {
        try {
            // Check if an employee with the given employeeId exists
            Optional<EmployeeEntity> existingEmployee = Optional.ofNullable(employeeRepository.findByEmployeeId(Long.valueOf(event.getEmployeeId())));
            if (existingEmployee.isPresent()) {
                // มีรูปแล้ว
                Optional<ImageEntity> existingImage = imageRepository.findByEmployeeId(Long.valueOf(event.getEmployeeId()));

                if (existingImage.isPresent()) {
                    //  same employeeId already exists
                    System.out.println("Image with employeeId " + event.getEmployeeId() + " already exists in the database.");
                } else {
                    // Save image
                    ImageEntity imageEntity = new ImageEntity();
                    BeanUtils.copyProperties(event, imageEntity);
                    imageRepository.save(imageEntity);
                }
            } else {
                // the given employeeId does not exist
                System.out.println("Employee with employeeId " + event.getEmployeeId() + " does not exist in the database. Cannot save the image.");
            }
        } catch (Exception e) {
            System.out.println("Error handling ImageCreatedEvent: " + e.getMessage());
        }
    }

//update LeaveCount
    @EventHandler
    public void on(LeaveCountUpdatedEvent event) {
        try {
            Long employeeId = event.getEmployeeId();

            Optional<EmployeeEntity> existingEmployee = Optional.ofNullable(employeeRepository.findByEmployeeId(employeeId));

            if (existingEmployee.isPresent()) {
                // Employee exists, handle accordingly (e.g., update fields)
                EmployeeEntity existingEmployeeEntity = existingEmployee.get();
                existingEmployeeEntity.setLeaveCount(event.getLeaveCount());

                employeeRepository.save(existingEmployeeEntity);
                System.out.println("LeaveCount updated: " + existingEmployeeEntity);
            } else {
                // Handle the case when the employee is not found
                System.out.println("Employee not found for LeaveCount update: Id" + employeeId );
            }
        } catch (Exception e) {
            System.out.println("Error handling LeaveCountUpdatedEvent: " + e.getMessage());
        }
    }

    //update LateCount
    @EventHandler
    public void on(LateCountUpdatedEvent event) {
        try {
            Long employeeId = event.getEmployeeId();

            Optional<EmployeeEntity> existingEmployee = Optional.ofNullable(employeeRepository.findByEmployeeId(employeeId));

            if (existingEmployee.isPresent()) {
                // Employee exists,Can Update it
                EmployeeEntity existingEmployeeEntity = existingEmployee.get();
                existingEmployeeEntity.setLateCount(event.getLateCount());

                employeeRepository.save(existingEmployeeEntity);
                System.out.println("LeaveCount updated: " + existingEmployeeEntity);
            } else {
                // Handle the case when the employee is not found
                System.out.println("Employee not found for LeaveCount update: Id" + employeeId );
            }
        } catch (Exception e) {
            System.out.println("Error handling LeaveCountUpdatedEvent: " + e.getMessage());
        }
    }


    @EventHandler
    public void on(ImageUpdatedEvent event) {
        try {
            // Check if an employee with the given employeeId exists
            Optional<EmployeeEntity> existingEmployee = Optional.ofNullable(employeeRepository.findByEmployeeId(Long.valueOf(event.getEmployeeId())));

            if (existingEmployee.isPresent()) {
                // Check if an image with the given employeeId exists
                Optional<ImageEntity> existingImage = imageRepository.findByEmployeeId(Long.valueOf(event.getEmployeeId()));

                if (existingImage.isPresent()) {
                    // Update image
                    ImageEntity imageEntity = existingImage.get();
                    BeanUtils.copyProperties(event, imageEntity);
                    imageRepository.save(imageEntity);

                    System.out.println("Image with employeeId " + event.getEmployeeId() + " has been updated.");
                } else {
                    // The image with the given employeeId does not exist
                    System.out.println("Image with employeeId " + event.getEmployeeId() + " does not exist. Cannot update the image.");
                }
            } else {
                // The employee with the given employeeId does not exist
                System.out.println("Employee with employeeId " + event.getEmployeeId() + " does not exist. Cannot update the image.");
            }
        } catch (Exception e) {
            System.out.println("Error handling ImageUpdatedEvent: " + e.getMessage());
        }
    }

    @EventHandler
    public void on(ImageDeletedEvent event) {
        Long employeeId = event.getEmployeeId();

        // Check if an employee with the given employeeId exists
        Optional<ImageEntity> existingImage = imageRepository.findByEmployeeId(employeeId);

        if (existingImage.isPresent()) {
            // Employee with the given employeeId exists, proceed with deletion
            imageRepository.deleteByEmployeeId(employeeId);
            System.out.println("Image with employeeId " + employeeId + " deleted.");
        } else {
            // Employee with the given employeeId does not exist
            System.out.println("Image with employeeId " + employeeId + " does not exist. Cannot delete.");
        }
    }

}
