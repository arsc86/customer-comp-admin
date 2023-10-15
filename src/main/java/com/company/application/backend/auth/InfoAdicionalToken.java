package com.company.application.backend.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Service;

import com.company.application.backend.models.entity.Usuario;
import com.company.application.backend.models.services.IUsuarioService;

@SuppressWarnings("deprecation")
@Service
public class InfoAdicionalToken implements TokenEnhancer{
	
	@Autowired
	private IUsuarioService usuarioService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		Usuario usuario = usuarioService.findByUsername(authentication.getName());
		Map<String,Object> info = new HashMap<>();
		info.put("nombre_usuario", usuario.getNombre());
		info.put("apellido_usuario", usuario.getApellido());
		info.put("email_usuario", usuario.getEmail());
		((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);
		return accessToken;
	}

}
