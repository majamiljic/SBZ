package sbz.webshop.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SpecialOffer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private Date dateFrom;
	private Date dateTo;
	private double discount;
	
	public SpecialOffer() {
		super();
	}

	public SpecialOffer(Integer id, String name, Date dateFrom, Date dateTo, double discount) {
		super();
		this.id = id;
		this.name = name;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.discount = discount;
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

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		return "SpecialOffer [id=" + id + ", name=" + name + ", dateFrom=" + dateFrom + ", dateTo=" + dateTo
				+ ", discount=" + discount + "]";
	}
	
}
