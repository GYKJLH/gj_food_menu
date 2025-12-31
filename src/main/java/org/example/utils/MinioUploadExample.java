package org.example.utils;

import io.minio.MinioClient;
import io.minio.UploadObjectArgs;

public class MinioUploadExample {
    public static void main(String[] args) {
        try {
            // 1. 创建 MinIO 客户端
            MinioClient minioClient = MinioClient.builder()
                    .endpoint("http://127.0.01:9000") // MinIO 地址
                    .credentials("root", "123456")
                    .build();

            // 2. 定义桶名和文件路径
            String bucketName = "file";
            String objectName = "path/file"; // 上传到 MinIO 的文件名
            String filePath = "/file"; // 本地文件路径

            // 3. 分块上传大文件
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .filename(filePath)
                            .build()
            );

            System.out.println("上传成功: " + objectName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

