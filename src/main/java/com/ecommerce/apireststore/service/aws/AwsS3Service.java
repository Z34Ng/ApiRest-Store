/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.apireststore.service.aws;

import java.io.File;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ZEAN
 */
public interface AwsS3Service {
        
    String uploadFile(MultipartFile file);
    void deleteFile(String key);
    String getFileUrl(String key);    
    File convertMulipartToFile(MultipartFile file);
}
