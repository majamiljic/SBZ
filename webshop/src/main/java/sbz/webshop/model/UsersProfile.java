package sbz.webshop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UsersProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String address;
	private int points;
	@ManyToOne
	private UsersCategory buyerCategory;
	
	public UsersProfile() {
		super();
	}

	public UsersProfile(Integer id, String address, int points, UsersCategory buyerCategory) {
		super();
		this.id = id;
		this.address = address;
		this.points = points;
		this.buyerCategory = buyerCategory;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public UsersCategory getBuyerCategory() {
		return buyerCategory;
	}

	public void setBuyerCategory(UsersCategory buyerCategory) {
		this.buyerCategory = buyerCategory;
	}

	@Override
	public String toString() {
		return "UsersProfile [id=" + id + ", address=" + address + ", points=" + points + ","
				+ "buyerCategory=" + buyerCategory + "]";
	}
	
}
