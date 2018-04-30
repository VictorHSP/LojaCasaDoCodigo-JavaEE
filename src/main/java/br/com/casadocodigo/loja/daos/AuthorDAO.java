package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.casadocodigo.loja.models.Author;

public class AuthorDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Author> list(){
		TypedQuery<Author> typedQuery = entityManager.createNamedQuery("Author.List", 
				Author.class);
		
		return typedQuery.getResultList();
	}
	
}
