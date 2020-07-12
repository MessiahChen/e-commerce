package com.ecommerce.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UploadImageService {
    List<String> uploadImages(List<MultipartFile> files);
}
