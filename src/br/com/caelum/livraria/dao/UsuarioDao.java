package br.com.caelum.livraria.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.caelum.livraria.modelo.Usuario;

@SuppressWarnings("serial")
public class UsuarioDao implements Serializable {
	
	@Inject
	EntityManager em;
	
	public boolean existe (Usuario usuario) {
		
		TypedQuery<Usuario> query = em.createQuery("select u from Usuario u where u.email = :pEmail and u.password = :pPassword",
				Usuario.class);
		
		query.setParameter("pEmail", usuario.getEmail());
		query.setParameter("pPassword", usuario.getPassword());
		
		try {
			Usuario result = query.getSingleResult();
			
		} catch (NoResultException e) {
			return false;
		}
		
		em.close();
		
		return true;
	
	}

}
