/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.apireststore.service.aws;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ZEAN
 */
@Service
public class AwsS3ServiceImpl implements AwsS3Service {

    private static final Logger LOG = LoggerFactory.getLogger(AwsS3ServiceImpl.class);

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Override
    public String uploadFile(MultipartFile file) {
        File imgFile = convertMulipartToFile(file);
        String imgName = System.currentTimeMillis() + "_" + imgFile.getName();
        LOG.info("Subiendo imagen con nombre: " + imgName);
        PutObjectRequest request = new PutObjectRequest(bucketName, imgName, imgFile);
        amazonS3.putObject(request);
        imgFile.delete();

        return imgName;
    }

    @Override
    public File convertMulipartToFile(MultipartFile file) {
        File imgFile = new File(file.getOriginalFilename());
        try ( FileOutputStream fos = new FileOutputStream(imgFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }

        return imgFile;
    }

    @Override
    public void deleteFile(String key) {
        try {
            amazonS3.deleteObject(new DeleteObjectRequest(bucketName, key));
        } catch (AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process it
            LOG.error(e.getMessage(), e);
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public String getFileUrl(String key) {
        return amazonS3.getUrl(bucketName, key).toString();
    }
}
