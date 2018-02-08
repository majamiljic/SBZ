package sbz.project.WebShop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class InvoiceDiscount {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private double discount;
	private String discountType;	// Basic, Extra
	private String name;
	
	public InvoiceDiscount() {
		super();
	}

	public InvoiceDiscount(double discount, String discountType, String name) {
		super();
		this.discount = discount;
		this.discountType = discountType;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "InvoiceDiscount [id=" + id + ", discount=" + discount + ", discountType="
				+ discountType + ", name=" + name + "]";
	}
}
