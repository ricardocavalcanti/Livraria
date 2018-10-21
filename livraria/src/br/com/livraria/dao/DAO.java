package br.com.livraria.dao;

import java.util.List;

import javax.persistence.EntityManager;
//import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public class DAO<T> {

	private final Class<T> classe;
	private EntityManager manager;

	public DAO(EntityManager manager, Class<T> classe) {

		this.manager = manager;
		this.classe = classe;
	}

	public void adiciona(T t) {
		// persiste o objeto
		manager.persist(t);

	}

	public void remove(T t) {

		manager.remove(manager.merge(t));

	}

	public void atualiza(T t) {

		manager.merge(t);

	}

	public List<T> listaTodos() {
		// EntityManager manager = new JPAUtil().getEntityManager();
		CriteriaQuery<T> query = manager.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = manager.createQuery(query).getResultList();

		// manager.close();
		return lista;
	}

	public T buscaPorId(Integer id) {
		// EntityManager manager = new JPAUtil().getEntityManager();
		T instancia = manager.find(classe, id);
		// manager.close();
		return instancia;
	}

	public int contaTodos() {
		// EntityManager manager = new JPAUtil().getEntityManager();
		long result = (Long) manager.createQuery("select count(n) from livro n").getSingleResult();
		// manager.close();

		return (int) result;
	}

	public List<T> listaTodosPaginada(int firstResult, int maxResults) {
		// EntityManager manager = new JPAUtil().getEntityManager();
		CriteriaQuery<T> query = manager.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = manager.createQuery(query).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();

		// manager.close();
		return lista;
	}

}
