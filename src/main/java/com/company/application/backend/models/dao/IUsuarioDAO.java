package com.company.application.backend.models.dao;
import org.springframework.data.repository.CrudRepository;

import com.company.application.backend.models.entity.Usuario;

public interface IUsuarioDAO extends CrudRepository<Usuario,Long>{
	
	public Usuario findByUsername(String username);
}
