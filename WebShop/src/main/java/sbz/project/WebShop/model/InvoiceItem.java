package sbz.project.WebShop.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class InvoiceItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private int invoiceItemNumber;
	@ManyToOne
	private Item item;
	private double price;
	private int quantity;
	private double subtotalPrice;
	private double totalPrice;
	private double discount;
	@OneToMany(cascade = CascadeType.ALL)
	private List<ItemDiscount> discounts = new ArrayList<ItemDiscount>();
	
	public InvoiceItem() {
		super();
	}

	public InvoiceItem(Integer id, int invoiceItemNumber, Item item, double price, int quantity,
			double subtotalPrice, double totalPrice, double discount, List<ItemDiscount> discounts) {
		super();
		this.id = id;
		this.invoiceItemNumber = invoiceItemNumber;
		this.item = item;
		this.price = price;
		this.quantity = quantity;
		this.subtotalPrice = subtotalPrice;
		this.totalPrice = totalPrice;
		this.discount = discount;
		this.discounts = discounts;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getInvoiceItemNumber() {
		return invoiceItemNumber;
	}

	public void setInvoiceItemNumber(int invoiceItemNumber) {
		this.invoiceItemNumber = invoiceItemNumber;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getSubtotalPrice() {
		return subtotalPrice;
	}

	public void setSubtotalPrice(double subtotalPrice) {
		this.subtotalPrice = subtotalPrice;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public List<ItemDiscount> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(List<ItemDiscount> discounts) {
		this.discounts = discounts;
	}

	public void applyDiscount() {
		totalPrice = subtotalPrice - subtotalPrice*discount;
	}

	@Override
	public String toString() {
		return "InvoiceItem [id=" + id + ", invoiceItemNumber=" + invoiceItemNumber + ", item="
				+ item + ", price=" + price + ", quantity=" + quantity + ", subtotalPrice=" + subtotalPrice
				+ ", totalPrice=" + totalPrice + ", discount=" + discount + ", discounts=" + discounts + "]";
	}
}
