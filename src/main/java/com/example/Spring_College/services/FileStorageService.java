package com.example.Spring_College.services;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {
    public byte[] getFileData(MultipartFile file) throws IOException {
        return file.getBytes();
    }
}
