package sbz.webshop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SpendingBoundary {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private double spendFrom;
	private double spendTo;
	private double bonusPoints;
	@ManyToOne
	private UsersCategory userCategory;
	
	public SpendingBoundary() {
		super();
	}

	public SpendingBoundary(Integer id, double spendFrom, double spendTo, double bonusPoints,
			UsersCategory userCategory) {
		super();
		this.id = id;
		this.spendFrom = spendFrom;
		this.spendTo = spendTo;
		this.bonusPoints = bonusPoints;
		this.userCategory = userCategory;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getSpendFrom() {
		return spendFrom;
	}

	public void setSpendFrom(double spendFrom) {
		this.spendFrom = spendFrom;
	}

	public double getSpendTo() {
		return spendTo;
	}

	public void setSpendTo(double spendTo) {
		this.spendTo = spendTo;
	}

	public double getBonusPoints() {
		return bonusPoints;
	}

	public void setBonusPoints(double bonusPoints) {
		this.bonusPoints = bonusPoints;
	}

	public UsersCategory getUserCategory() {
		return userCategory;
	}

	public void setUserCategory(UsersCategory userCategory) {
		this.userCategory = userCategory;
	}
	
}
