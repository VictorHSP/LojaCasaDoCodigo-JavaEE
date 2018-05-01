package br.com.casadocodigo.loja.mb;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.casadocodigo.loja.daos.BookDAO;
import br.com.casadocodigo.loja.models.Book;

@Model
public class ProductDetailBean {
	
	@Inject
	private BookDAO bookDAO;
	private Book book;
	private Integer id;
	
	public void loadBook() {
		this.book = bookDAO.findById(id);
	}
	
	// Gets And Sets
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public Book getBook() {
		return book;
	}
}
