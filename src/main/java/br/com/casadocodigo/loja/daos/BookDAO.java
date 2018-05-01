package br.com.casadocodigo.loja.daos;

import br.com.casadocodigo.loja.models.Book;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Stateful
public class BookDAO {

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
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

	public List<Book> lastReleases() {
		TypedQuery<Book> typedQuery = entityManager.createNamedQuery("Book.LastReleases", Book.class);
		return typedQuery.setMaxResults(3).getResultList();
	}

	public List<Book> olderBooks() {
		TypedQuery<Book> typedQuery = entityManager.createNamedQuery("Book.List", Book.class);
		return typedQuery.setMaxResults(20).getResultList();
	}

	public Book findById(Integer id) {
		
		return entityManager.find(Book.class, id);
	}

}
