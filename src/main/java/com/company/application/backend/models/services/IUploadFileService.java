package com.company.application.backend.models.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {
	public Resource cargar(String nombreFoto) throws MalformedURLException;
	public String copiar(MultipartFile file) throws IOException;
	public boolean eliminar(String nombreFoto);
	public Path getPath(String nombreFoto);
}
