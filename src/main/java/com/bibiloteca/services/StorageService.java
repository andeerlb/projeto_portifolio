package com.bibiloteca.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bibiloteca.context.properties.FileStorageProperties;
import com.bibiloteca.controllers.FileUploudController;
import com.bibiloteca.models.UploadFileResponse;

@Service
public class StorageService {

	private static final Logger logger = LoggerFactory.getLogger(FileUploudController.class);

	private final Path fileStorageLocation;

	@Autowired
	public StorageService(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new RuntimeException(
					"Não foi possível criar o diretório onde os arquivos enviados serão armazenados.", ex);
		}
	}

	public UploadFileResponse storeFile(MultipartFile file) {
		// Nome do arquivo
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// verifica se o nome do arquivo possui digitos inválidos
			if (fileName.contains("..")) {
				throw new RuntimeException("Desculpe! Arquivo com nome inválido.");
			}

			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			throw new RuntimeException(String.format("Não foi possível salvar o arquivo %s", fileName));
		}

		return fileResponse(file);
	}

	public UploadFileResponse readFile(MultipartFile file) {
		String conteudo = null;
		try {
			conteudo = new String(file.getBytes(), "ISO-8859-1");
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return fileResponse(file).setConteudo(conteudo);
	}

	public String readAndWriteFile(MultipartFile file) {
		StringBuilder strBuilder = new StringBuilder();
		UploadFileResponse fileResponse = fileResponse(file);

		try {
			String conteudo = new String(file.getBytes(), "ISO-8859-1");
			strBuilder
					.append(conteudo.trim().isEmpty() ? String.format("Arquivo: %s vázio.", fileResponse.getFileName())
							: conteudo);
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new RuntimeException("Erro ao ler arquivo");
		}

		LocalDateTime hour = LocalDateTime.now();
		strBuilder.append(String.format("%n%n\tArquivo lido com sucesso, hora atual: %s:%s:%s", hour.getHour(),
				hour.getMinute(), hour.getSecond()));

		return strBuilder.toString();
	}

	private UploadFileResponse fileResponse(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
				.path(fileName).toUriString();

		return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
	}
}
