package br.com.casadocodigo.loja.mb;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.casadocodigo.loja.daos.BookDAO;
import br.com.casadocodigo.loja.models.Book;

@Model
public class AdminListBookBean {
	
	@Inject
	private BookDAO bookDAO;
	private List<Book> books = new ArrayList<>();
	
	public String form() {
		return "/livros/form?faces-redirect=true";
	}
	
	@PostConstruct
	private void loadObjects() {
		this.books = bookDAO.list();
	}
	
	public String convertDate(Calendar calendar) {
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		return format.format(calendar.getTime());
	}
	
	public String convertPrice(BigDecimal price) {
		
		NumberFormat format = NumberFormat.getCurrencyInstance();
		
		return format.format(price);
	}
	
	// GETS AND SETS
	
	public List<Book> getBooks() {
		return books;
	}
	
}
