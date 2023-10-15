package com.company.application.backend.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.company.application.backend.models.entity.Cliente;
import com.company.application.backend.models.entity.Region;
import com.company.application.backend.models.services.IClienteService;
import com.company.application.backend.models.services.UploadFileServiceImpl;

@CrossOrigin(origins ={"http://localhost:4200","*"})
@RestController
@RequestMapping("/api")
public class ClienteRestController {
	
	@Autowired
	private IClienteService clienteServiceImpl;
	
	@Autowired
	private UploadFileServiceImpl fileServiceImpl;
	
	@GetMapping("/clientes")
	public List<Cliente> index(){
		return clienteServiceImpl.findAll();
	}
	
	@GetMapping("/clientes/page/{page}")
	public Page<Cliente> index(@PathVariable Integer page){
		return clienteServiceImpl.findAll(PageRequest.of(page, 4));
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER" })
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> getClienteById(@PathVariable Long id){	
		Map<String, Object> response = new HashMap<String, Object>();
		Cliente cliente = null;
		try {
			cliente = clienteServiceImpl.findById(id);
		}catch(DataAccessException e)
		{
			response.put("mensaje", "Error al realizar la consulta!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
				
		if(cliente==null) {
			response.put("mensaje", "El cliente ID : ".concat(id.toString().concat("  no existe registrado!")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cliente>(cliente,HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN" })
	@PostMapping("/clientes")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> createCliente(@Valid @RequestBody Cliente cliente, BindingResult result) {
		Map<String, Object> response = new HashMap<String, Object>();
		Cliente clienteNuevo = null;
		
		if(result.hasErrors())
		{
			List<String> errors = result.getFieldErrors()
		                    .stream()
		                    .map(error -> "El campo '" + error.getField() +"' " + error.getDefaultMessage())
		                    .collect(Collectors.toList());
			
			response.put("errors",errors);
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
		}
		
		try 
		{
			clienteNuevo = clienteServiceImpl.save(cliente);
		}catch(DataAccessException e)
		{
			response.put("mensaje", "Error al realizar el ingreso del nuevo cliente!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El cliente fue creado exitosamente!");
		response.put("cliente", clienteNuevo);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	@Secured({"ROLE_ADMIN" })
	@DeleteMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> deleteCliente(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		Cliente cliente = clienteServiceImpl.findById(id);
		
		try {
			clienteServiceImpl.delete(id);
			fileServiceImpl.eliminar(cliente.getFoto());
			
		}catch(DataAccessException e)
		{
			response.put("mensaje", "Error al realizar la elminacion del cliente!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El cliente fue eliminado exitosamente!");
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN" })
	@PutMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> updateCliente(@Valid @RequestBody Cliente cliente, @PathVariable Long id, BindingResult result) {
		Map<String, Object> response = new HashMap<String, Object>();
		
		if(result.hasErrors())
		{
			List<String> errors = result.getFieldErrors()
		                    .stream()
		                    .map(error -> "El campo '" + error.getField() +"' " + error.getDefaultMessage())
		                    .collect(Collectors.toList());
			
			response.put("errors",errors);
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
		}
		
		Cliente clienteActual = clienteServiceImpl.findById(id);
		Cliente clienteUpdated = null;
		
		if(clienteActual == null)
		{
			response.put("mensaje", "No se puede realizar la actualizacion, el cliente ID : ".concat(id.toString().concat("  no existe registrado!")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setEmail(cliente.getEmail());			
			clienteActual.setCreateAt(cliente.getCreateAt());
			clienteActual.setRegion(cliente.getRegion());
			clienteUpdated = clienteServiceImpl.save(clienteActual);
		}catch(DataAccessException e)
		{
			response.put("mensaje", "Error al realizar la actualizacion del cliente!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
							
		response.put("mensaje", "El cliente fue actualizado exitosamente!");
		response.put("cliente", clienteUpdated);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	@PostMapping("/clientes/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile file, @RequestParam("id") Long id)
	{
		Map<String, Object> response = new HashMap<String, Object>();
		Cliente cliente = clienteServiceImpl.findById(id);
		String nombreArchivo = null;
		
		if(!file.isEmpty())
		{
			try {
				nombreArchivo = fileServiceImpl.copiar(file);
			} catch (IOException e) {
				response.put("mensaje","Error al cargar la imagen "+nombreArchivo);
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			fileServiceImpl.eliminar(cliente.getFoto());
			
			cliente.setFoto(nombreArchivo);
			clienteServiceImpl.save(cliente);
		}
		
		response.put("mensaje", "La imagen fue cargada exitosamente!");
		response.put("cliente", cliente);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	@GetMapping("/uploads/img/{foto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String foto)
	{
		
		Resource resource    = null;
		try {
			resource = fileServiceImpl.cargar(foto);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(resource,headers,HttpStatus.OK);
	}
	
	@GetMapping("clientes/regiones")
	public List<Region> listarRegiones()
	{
		return clienteServiceImpl.findAllRegiones();
	}
}
