package com.example.employeemanage.command;

import com.example.employeemanage.command.rest.CUDCommand.image.CreateImageCommand;
import com.example.employeemanage.command.rest.CUDCommand.image.DeleteImageCommand;
import com.example.employeemanage.command.rest.CUDCommand.image.UpdateImageCommand;
import com.example.employeemanage.core.events.image.ImageCreatedEvent;
import com.example.employeemanage.core.events.image.ImageDeletedEvent;
import com.example.employeemanage.core.events.image.ImageUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class ImageAggregate {
    @AggregateIdentifier
    private String aggregateId;
    private String name;
    private String type;
    private byte[] imageData;
    private Long employeeId;

    public ImageAggregate(){
    }

    @CommandHandler
    public ImageAggregate(CreateImageCommand createImageCommand) {
        if (createImageCommand.getEmployeeId() == null ) {
            throw new IllegalArgumentException("EmployeeId cannot be empty");
        }
        ImageCreatedEvent imageCreatedEvent = new ImageCreatedEvent();
        BeanUtils.copyProperties(createImageCommand, imageCreatedEvent);
        AggregateLifecycle.apply(imageCreatedEvent);
    }
    @EventSourcingHandler
    public void on(ImageCreatedEvent imageCreatedEvent) {
        System.out.println("ON POST AGGREGATE");
        this.aggregateId = imageCreatedEvent.getAggregateId();
        this.name = imageCreatedEvent.getName();
        this.type = imageCreatedEvent.getType();
        this.imageData = imageCreatedEvent.getImageData();
        this.employeeId = imageCreatedEvent.getEmployeeId();

    }
    @CommandHandler
    public ImageAggregate(UpdateImageCommand updateImageCommand) {
        if (updateImageCommand.getEmployeeId() == null ) {
            throw new IllegalArgumentException("EmployeeId cannot be empty");
        }
        ImageUpdatedEvent imageUpdatedEvent = new ImageUpdatedEvent();
        BeanUtils.copyProperties(updateImageCommand, imageUpdatedEvent);
        AggregateLifecycle.apply(imageUpdatedEvent);
    }
    @EventSourcingHandler
    public void on(ImageUpdatedEvent imageUpdatedEvent) {
        System.out.println("ON POST AGGREGATE");
        this.aggregateId = imageUpdatedEvent.getAggregateId();
        this.name = imageUpdatedEvent.getName();
        this.type = imageUpdatedEvent.getType();
        this.imageData = imageUpdatedEvent.getImageData();
        this.employeeId = imageUpdatedEvent.getEmployeeId();

    }

    @CommandHandler
    public ImageAggregate(DeleteImageCommand deleteImageCommand) {
        if (deleteImageCommand.getEmployeeId() == null ) {
            throw new IllegalArgumentException("EmployeeId cannot be empty");
        }
        ImageDeletedEvent imageDeletedEvent = new ImageDeletedEvent();
        BeanUtils.copyProperties(deleteImageCommand, imageDeletedEvent);
        AggregateLifecycle.apply(imageDeletedEvent);
    }
    @EventSourcingHandler
    public void on(ImageDeletedEvent imageDeletedEvent) {
        System.out.println("ON POST AGGREGATE");
        this.aggregateId =  imageDeletedEvent.getAggregateId();
        this.employeeId =  imageDeletedEvent.getEmployeeId();

    }


}
