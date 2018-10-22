package com.bibiloteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bibiloteca.models.UploadFileResponse;
import com.bibiloteca.services.StorageService;

@RestController
@RequestMapping("api/file")
public class FileUploudController {

	@Autowired
	private StorageService service;

	@PostMapping("/upload")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
		return service.storeFile(file);
	}

	@PostMapping("/read")
	public UploadFileResponse readFile(@RequestParam("file") MultipartFile file) {
		return service.readFile(file);
	}

	@PostMapping("/readAndWrite")
	public String readFileAndWrite(@RequestParam("file") MultipartFile file) {
		return service.readAndWriteFile(file);
	}
}
