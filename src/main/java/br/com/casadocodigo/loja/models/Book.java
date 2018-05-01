package br.com.casadocodigo.loja.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
	@NamedQuery(name = "Book.List", query = "select distinct(b) from Book b join fetch b.authors"),
	@NamedQuery(name = "Book.LastReleases", 
		query = Book.SELECT_LASTDATE)
})
public class Book {
		
	public static final String SELECT_LASTDATE 
		= "select b from Book b where b.releaseDate <= now() order by b.id desc";
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @NotNull
    private String title;
    
    @NotBlank
    @NotNull
    @Length(min = 5)
    private String description;
    
    @Min(50)
    private int numberOfpages;
    
    @DecimalMin("20")
    private BigDecimal price;
    
    @ManyToMany()
    @Size(min = 1, message = "Selecione pelo menos 1 Autor para o Livro.")
    @NotNull
    private List<Author> authors = new ArrayList<>();
    
    @NotNull
    @Future
    private Calendar releaseDate;
    
    private String summaryPath;
    
    public Book(){}

    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // gets and sets
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberOfpages() {
        return numberOfpages;
    }

    public void setNumberOfpages(int numberOfpages) {
        this.numberOfpages = numberOfpages;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<Author> getAuthors() {
		return authors;
	}
    
    public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
    
    public void add(Author author) {
    	authors.add(author);
    }
    
    public void setReleaseDate(Calendar releaseDate) {
		this.releaseDate = releaseDate;
	}
    
    public Calendar getReleaseDate() {
		return releaseDate;
	}
    
    public void setSummaryPath(String summaryPath) {
		this.summaryPath = summaryPath;
	}
    
    public String getSummaryPath() {
		return summaryPath;
	}
    
    // ToString
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", numberOfpages=" + numberOfpages +
                ", price=" + price +
                '}';
    }
}
