package br.com.stefanini.maratonadev.dao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.eclipse.microprofile.opentracing.Traced;

import br.com.stefanini.maratonadev.model.Todo;

@RequestScoped
@Traced
public class TodoDao {

	@PersistenceContext
	EntityManager entityManager;

	@Transactional
	public void inserir(Todo todo) {

		String nomeSql = "INSERIR_TODO";

		Query query = entityManager.createNamedQuery(nomeSql);

		// query.setParameter("id", todo.getId());
		query.setParameter("nome", todo.getNome());
		query.setParameter("dataCriacao", todo.getDataCriacao());

		query.executeUpdate();

	}

	@SuppressWarnings("unused")
	@Transactional
	public void excluir(Long id) {
		String nomeSql = "EXCLUIR_TODO";
		Query query = entityManager.createNamedQuery(nomeSql);
		
		query.executeUpdate();
	}

	public List<Todo> listar() {
		String nomeConsulta = "CONSULTAR_TODO";
		List<Todo> listaRetorno;
		TypedQuery<Todo> query = entityManager.createNamedQuery(nomeConsulta, Todo.class);

		try {
			listaRetorno = query.getResultList();
		} catch (NoResultException e) {
			listaRetorno = new ArrayList<>();
		}
		return listaRetorno;
	}

}
