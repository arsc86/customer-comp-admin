package com.company.application.backend.models.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileServiceImpl implements IUploadFileService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private final static String DIR_UPLOAD = "uploads";

	@Override
	public Resource cargar(String nombreFoto) throws MalformedURLException {
		
		Path rutaArchivo     = getPath(nombreFoto);
		log.info(rutaArchivo.toString());
		Resource resource    = new UrlResource(rutaArchivo.toUri());
		
		if(!resource.exists() && !resource.isReadable())
		{
			rutaArchivo     = Paths.get("src/main/resource/static/images").resolve("no-user.png").toAbsolutePath();
			
			resource = new UrlResource(rutaArchivo.toUri());
			
			log.error("Error al cargar la imagen "+nombreFoto);
		}
		
		return resource;
	}

	@Override
	public String copiar(MultipartFile file) throws IOException {
		String nombreArchivo = UUID.randomUUID().toString()+"_"+file.getOriginalFilename().replace(" ", "");
		Path rutaArchivo     = getPath(nombreArchivo);
	    Files.copy(file.getInputStream(), rutaArchivo);
		return nombreArchivo;
	}

	@Override
	public boolean eliminar(String nombreFoto) {
		if(nombreFoto!=null && nombreFoto.length()>0)
		{
			Path rutaArchivo     = getPath(nombreFoto);
			File archivoAnterior = rutaArchivo.toFile();
			if(archivoAnterior.exists() && archivoAnterior.canRead())
			{
				archivoAnterior.delete();
				return true;
			}
		}
		return false;
	}

	@Override
	public Path getPath(String nombreFoto) {
		return Paths.get(DIR_UPLOAD).resolve(nombreFoto).toAbsolutePath();
	}

}
