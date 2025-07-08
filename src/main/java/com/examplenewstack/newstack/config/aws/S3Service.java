package com.examplenewstack.newstack.config.aws; // Ajuste este pacote se o seu for diferente

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;

import java.io.IOException;
import java.util.Map; // Importar para retornar Map
import java.util.UUID;

@Service
public class S3Service {

    private final S3Client s3Client;

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    @Value("${aws.region}")
    private String region;

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    // Alterado para retornar Map<String, String> com nomeNoS3 e urlS3
    public Map<String, String> uploadFile(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .contentType(file.getContentType())
                .build();


        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));

        String fileUrl = getFileUrl(fileName); // Obter a URL aqui
        return Map.of("nomeNoS3", fileName, "urlS3", fileUrl); // Retorna Map com os dois
    }

    public String getFileUrl(String fileName) {
        return "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + fileName;
    }
}