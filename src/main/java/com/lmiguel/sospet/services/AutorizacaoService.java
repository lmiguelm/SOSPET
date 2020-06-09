package com.lmiguel.sospet.services;

import com.lmiguel.sospet.domain.enums.Perfil;
import com.lmiguel.sospet.security.UserSS;
import com.lmiguel.sospet.services.exceptions.AuthorizationException;

public abstract class AutorizacaoService {
	public static void verificarAutorizacao(Long id) {
		UserSS user = UserService.authenticated();
		
		if (user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
	}
}
