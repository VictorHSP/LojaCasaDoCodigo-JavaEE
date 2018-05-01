package br.com.casadocodigo.loja.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.casadocodigo.loja.daos.BookDAO;
import br.com.casadocodigo.loja.models.Book;

@Model
public class HomeBean {
	
	@Inject
	private BookDAO bookDAO;
	
	@Inject
	private HttpServletRequest request;
	
	private List<Book> lastReleases = new ArrayList<>();
	private List<Book> olderBooks = new ArrayList<>();
	
	@PostConstruct
	private void loadObjects() {
		this.lastReleases = bookDAO.lastReleases();
		this.olderBooks = bookDAO.olderBooks();
	}
	
	public String save() {
		return null;
	}
	
	//gets and sets
	
	public HttpServletRequest getRequest() {
		return request;
	}
	
	public List<Book> getLastReleases() {
		return lastReleases;
	}
	
	public List<Book> getOlderBooks() {
		return olderBooks;
	}
	
}
