package sbz.webshop.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UsersCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	@JsonIgnore
	@OneToMany(mappedBy = "userCategory", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SpendingBoundary> spendingBoundary;
	
	public UsersCategory() {
		super();
	}

	public UsersCategory(Integer id, String name, List<SpendingBoundary> spendingBoundary) {
		super();
		this.id = id;
		this.name = name;
		this.spendingBoundary = spendingBoundary;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SpendingBoundary> getSpendingBoundary() {
		return spendingBoundary;
	}

	public void setSpendingBoundary(List<SpendingBoundary> spendingBoundary) {
		this.spendingBoundary = spendingBoundary;
	}

	@Override
	public String toString() {
		return "UsersCategory [id=" + id + ", name=" + name + ", spendingBoundary=" + spendingBoundary + "]";
	}
	
}
