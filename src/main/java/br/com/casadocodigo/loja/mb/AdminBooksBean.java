package br.com.casadocodigo.loja.mb;

import br.com.casadocodigo.loja.daos.AuthorDAO;
import br.com.casadocodigo.loja.daos.BookDAO;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.infra.MessagesHelper;
import br.com.casadocodigo.loja.models.Author;
import br.com.casadocodigo.loja.models.Book;
import br.com.casadocodigo.loja.utils.LogConstants;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.servlet.http.Part;

@Model
public class AdminBooksBean {

    @Inject
    private Book product;

    @Inject
    private BookDAO bookDAO;
    
    @Inject
    private AuthorDAO authorDAO;
    
    @Inject
    private MessagesHelper messagesHelper;
    
    private Part summary;
    
    @Inject
    private FileSaver fileSaver;
    
    private List<Integer> selectedAuthorsIds = new ArrayList<>();
    private List<Author> selectedAuthors = new ArrayList<>();
    private List<Author> authors = new ArrayList<>();
    
    public String save(){
        LogConstants.getLogInfo(product.toString());
        String summaryPath = fileSaver.write("summaries", summary);
        this.product.setSummaryPath(summaryPath);
        bookDAO.save(product);
        messagesHelper.addFlash(new FacesMessage("Livro salvo com sucesso ! "), "succes-save");
        return "lista?faces-redirect=true";
    }

	@SuppressWarnings("unused")
	private void populateBookAuthor() {
    	selectedAuthorsIds.stream().map( (id) -> {
    		return new Author(id);
    	}).forEach(product::add);
    }
    
	
	
    @PostConstruct
    public void loadObjects() {
    	this.authors = authorDAO.list();
    }
    
    // GETS AND SETS
    
    public void setSummary(Part summary) {
		this.summary = summary;
	}
    
    public Part getSummary() {
		return summary;
	}
    
    public List<Author> getSelectedAuthors() {
		return selectedAuthors;
	}
    
    public void setSelectedAuthorsIds(List<Integer> selectedAuthorsIds) {
		this.selectedAuthorsIds = selectedAuthorsIds;
	}
    
    public List<Integer> getSelectedAuthorsIds() {
		return selectedAuthorsIds;
	}
    
    public List<Author> getAuthors() {
		return authors;
	}
    
    public Book getProduct() {
        return product;
    }
}
