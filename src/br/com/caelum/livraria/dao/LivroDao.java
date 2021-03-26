package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.livraria.modelo.Livro;

@SuppressWarnings("serial")
public class LivroDao implements Serializable{
	
	@Inject
	EntityManager em;
	
	private DAO<Livro> livroDao;

	
	@PostConstruct
	void init() {
		this.livroDao = new DAO<Livro>(em, Livro.class);
	}
	
	
	public void adiciona(Livro t) {
		livroDao.adiciona(t);
	}

	public void remove(Livro t) {
		livroDao.remove(t);
	}

	public void atualiza(Livro t) {
		livroDao.atualiza(t);
	}

	public List<Livro> listaTodos() {
		return livroDao.listaTodos();
	}

	public Livro buscaPorId(Integer id) {
		return livroDao.buscaPorId(id);
	}
	
	

}
