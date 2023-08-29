package com.dagoreca.chat.service.impl;

import com.dagoreca.chat.service.FileService;
import com.dagoreca.chat.service.dto.MessageFileDTO;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public MessageFileDTO addFile(MultipartFile file) {
        MessageFileDTO messageFileDTO = new MessageFileDTO();
        messageFileDTO.setTitle(file.getName());
        try {
            messageFileDTO.setContent(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        return messageFileDTO;
    }

}
