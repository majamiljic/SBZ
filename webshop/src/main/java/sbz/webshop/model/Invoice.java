package sbz.webshop.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Date date;
	@ManyToOne
	private User buyer;
	private String invoiceStatus;	// Ordered, Cancelled, Successful
	private double subtotal;
	private double total;
	private double discount;
	private int pointsSpent;
	private int pointsEarned;
	@OneToMany(cascade = CascadeType.ALL)
	private List<InvoiceItem> invoiceItems = new ArrayList<InvoiceItem>();
	@OneToMany(cascade = CascadeType.ALL)
	private List<InvoiceDiscount> discounts;
	
	public Invoice() {
		super();
	}

	public Invoice(Integer id, Date date, User buyer, String invoiceStatus, double subtotal, double total, double discount,
			int pointsSpent, int pointsEarned, List<InvoiceItem> invoiceItems, List<InvoiceDiscount> discounts) {
		super();
		this.id = id;
		this.date = date;
		this.buyer = buyer;
		this.invoiceStatus = invoiceStatus;
		this.subtotal = subtotal;
		this.total = total;
		this.discount = discount;
		this.pointsSpent = pointsSpent;
		this.pointsEarned = pointsEarned;
		this.invoiceItems = invoiceItems;
		this.discounts = discounts;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public String getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public int getPointsSpent() {
		return pointsSpent;
	}

	public void setPointsSpent(int pointsSpent) {
		this.pointsSpent = pointsSpent;
	}

	public int getPointsEarned() {
		return pointsEarned;
	}

	public void setPointsEarned(int pointsEarned) {
		this.pointsEarned = pointsEarned;
	}

	public List<InvoiceItem> getInvoiceItems() {
		return invoiceItems;
	}

	public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
		this.invoiceItems = invoiceItems;
	}

	public List<InvoiceDiscount> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(List<InvoiceDiscount> discounts) {
		this.discounts = discounts;
	}

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", date=" + date + ", buyer=" + buyer + ", invoiceStatus=" + invoiceStatus
				+ ", subtotal=" + subtotal + ", total=" + total + ", discount=" + discount + ", pointsSpent="
				+ pointsSpent + ", pointsEarned=" + pointsEarned + ", invoiceItems=" + invoiceItems + ", discounts="
				+ discounts + "]";
	}
	
}
