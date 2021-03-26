package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;

@Named
@ViewScoped
public class AutorBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Autor autor = new Autor();

	@Inject
	private AutorDao autorDao;
	
	
	public void carregaPeloId() {
		Integer id = this.autor.getId();
		this.autor = this.autorDao.buscaPorId(id);
		if (this.autor == null) {
			this.autor = new Autor();
		}
	}
	
	public List<Autor> getAutores() {
		return this.autorDao.listaTodos();
	}
	
	public void carregar(Autor autor) {
		System.out.println("Carregando autor");
		this.autor = autor;
	}

	public void remover(Autor autor) {
		System.out.println("Removendo autor");
		this.autorDao.remove(autor);
	}
	
	private Integer getId() {
		return this.autor.getId();
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		if (this.autor.getId() == null) {
			this.autorDao.adiciona(this.autor);
		}else {
			this.autorDao.atualiza(this.autor);
		}
		this.autor = new Autor();
		return "livro?faces-redirect=true";
	}
}
