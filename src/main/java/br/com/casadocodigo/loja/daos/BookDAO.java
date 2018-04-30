package br.com.casadocodigo.loja.daos;

import br.com.casadocodigo.loja.models.Book;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

public class BookDAO {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Transactional
    public void save(Book book){
        entityManager.persist(book);
    }

	public List<Book> list() {
		
		TypedQuery<Book> typedQuery = entityManager.createNamedQuery("Book.List",
				Book.class);
		
		return typedQuery.getResultList();
	}

}
