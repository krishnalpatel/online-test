package pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {

	@Id
	@Column(length=2)
	String catcode;
	@Column(length=20)
	String category;
	
	@OneToMany(mappedBy="category")
	private Set<Question> questions = new HashSet<>();
	
	public String getCatcode() {
		return catcode;
	}

	public void setCatcode(String catcode) {
		this.catcode = catcode;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Category [catcode=" + catcode + ", category=" + category + "]";
	}

}
