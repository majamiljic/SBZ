package sbz.webshop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class InvoiceDiscount {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private int dicount;
	private String discountType;	// Regular, Extra
	
	public InvoiceDiscount() {
		super();
	}

	public InvoiceDiscount(Integer id, int dicount, String discountType) {
		super();
		this.id = id;
		this.dicount = dicount;
		this.discountType = discountType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getDicount() {
		return dicount;
	}

	public void setDicount(int dicount) {
		this.dicount = dicount;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	@Override
	public String toString() {
		return "InvoiceDiscount [id=" + id + ", dicount=" + dicount + ", discountType="
				+ discountType + "]";
	}
	
}
