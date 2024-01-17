package com.example.employeemanage.service;

import com.example.employeemanage.core.data.Enitity.ImageEntity;
import com.example.employeemanage.core.data.Repository.ImageRepository;
import com.example.employeemanage.util.ImageUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageStorageService {
    @Autowired
    private ImageRepository repository;
//    private  RabbitTemplate rabbitTemplate;


    @RabbitListener(queues = "GetImageQueue")
    public byte[] downloadImage(Long employeeId) {
        Optional<ImageEntity> dbImageData = repository.findByEmployeeId(employeeId);

        if (dbImageData.isPresent()) {
            byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
            return images;
        } else {
            return null; // หรือส่งข้อมูลที่เหมาะสมในกรณีไม่พบรูป
        }
    }
}
