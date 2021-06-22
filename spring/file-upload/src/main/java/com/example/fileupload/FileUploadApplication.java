package com.example.fileupload;

import com.example.fileupload.storage.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class FileUploadApplication {

	public static void main(final String[] args) {
		SpringApplication.run(FileUploadApplication.class, args);
	}

}
