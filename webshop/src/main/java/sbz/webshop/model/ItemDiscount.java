package sbz.webshop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ItemDiscount {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private int dicount;
	private String discountType;	// Regular, Extra
	private String name;
	
	public ItemDiscount() {
		super();
	}

	public ItemDiscount(Integer id, int dicount, String discountType, String name) {
		super();
		this.id = id;
		this.dicount = dicount;
		this.discountType = discountType;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ItemDiscount [id=" + id + ", dicount=" + dicount + ", discountType=" + discountType + ", name=" + name
				+ "]";
	}
	
}
