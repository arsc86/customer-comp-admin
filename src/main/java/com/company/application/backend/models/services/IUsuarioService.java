package com.company.application.backend.models.services;

import com.company.application.backend.models.entity.Usuario;

public interface IUsuarioService {
	
	public Usuario findByUsername(String username);

}
