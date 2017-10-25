package sbz.webshop.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(unique = true)
	private int code;
	private String name;
	@ManyToOne
	private ItemCategory category;
	private double price;
	private int stockStatus;
	private int minStockStatus;
	private Date dateRecorded;
	private String refill;
	private String status;		// Active, Archive
	
	public Item() {
		super();
	}

	public Item(Integer id, int code, String name, ItemCategory category, double price, int stockStatus,
			int minStockStatus, Date dateRecorded, String refill, String status) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.category = category;
		this.price = price;
		this.stockStatus = stockStatus;
		this.minStockStatus = minStockStatus;
		this.dateRecorded = dateRecorded;
		this.refill = refill;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ItemCategory getCategory() {
		return category;
	}

	public void setCategory(ItemCategory category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(int stockStatus) {
		this.stockStatus = stockStatus;
	}

	public int getMinStockStatus() {
		return minStockStatus;
	}

	public void setMinStockStatus(int minStockStatus) {
		this.minStockStatus = minStockStatus;
	}

	public Date getDateRecorded() {
		return dateRecorded;
	}

	public void setDateRecorded(Date dateRecorded) {
		this.dateRecorded = dateRecorded;
	}

	public String getRefill() {
		return refill;
	}

	public void setRefill(String refill) {
		this.refill = refill;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", code=" + code + ", name=" + name + ", category=" + category + ", price=" + price
				+ ", stockStatus=" + stockStatus + ", minStockStatus=" + minStockStatus + ", dateRecorded="
				+ dateRecorded + ", refill=" + refill + ", status=" + status + "]";
	}
	
}
