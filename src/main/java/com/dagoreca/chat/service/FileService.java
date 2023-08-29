package com.dagoreca.chat.service;

import com.dagoreca.chat.service.dto.MessageFileDTO;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    MessageFileDTO addFile(MultipartFile file);
}
