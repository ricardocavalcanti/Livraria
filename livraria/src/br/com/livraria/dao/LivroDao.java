package br.com.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.livraria.modelo.Livro;

public class LivroDao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	public void adiciona(Livro t) {
		dao.adiciona(t);
	}


	public void remove(Livro t) {
		dao.remove(t);
	}


	public void atualiza(Livro t) {
		dao.atualiza(t);
	}


	public List<Livro> listaTodos() {
		return dao.listaTodos();
	}


	public Livro buscaPorId(Integer id) {
		return dao.buscaPorId(id);
	}


	@Inject
	EntityManager manager;
	private DAO<Livro> dao;
	
	
	@PostConstruct
	void init() {
		
		this.dao = new DAO<Livro>(this.manager, Livro.class);
	}
	
	

}
