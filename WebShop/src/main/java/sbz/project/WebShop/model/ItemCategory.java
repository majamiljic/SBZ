package sbz.project.WebShop.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class ItemCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@ManyToOne
	private ItemCategory parentCategory;
	private String name;
	private double maxDiscount;
	private boolean isConsGood;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<SpecialOffer> specialOffers = new ArrayList<SpecialOffer>();
	
	public ItemCategory() {
		super();
	}
	
	public ItemCategory(Integer id, ItemCategory parentCategory, String name, double maxDiscount, boolean isConsGood,
			List<SpecialOffer> specialOffers) {
		super();
		this.id = id;
		this.parentCategory = parentCategory;
		this.name = name;
		this.maxDiscount = maxDiscount;
		this.isConsGood = isConsGood;
		this.specialOffers = specialOffers;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ItemCategory getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(ItemCategory parentCategory) {
		this.parentCategory = parentCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getMaxDiscount() {
		return maxDiscount;
	}

	public void setMaxDiscount(double maxDiscount) {
		this.maxDiscount = maxDiscount;
	}

	public boolean isConsGood() {
		return isConsGood;
	}

	public void setConsGood(boolean isConsGood) {
		this.isConsGood = isConsGood;
	}

	public List<SpecialOffer> getSpecialOffers() {
		return specialOffers;
	}

	public void setSpecialOffers(List<SpecialOffer> specialOffers) {
		this.specialOffers = specialOffers;
	}

	@Override
	public String toString() {
		return "ItemCategory [id=" + id + ", parentCategory=" + parentCategory + ", name=" + name + ", maxDiscount="
				+ maxDiscount + ", isConsGood=" + isConsGood + ", specialOffers=" + specialOffers + "]";
	}
}
