package sbz.webshop.DTO;

public class PricesDTO {
	
	private double priceFrom;
	private double priceTo;
	
	public PricesDTO() {
		super();
	}

	public PricesDTO(double priceFrom, double priceTo) {
		super();
		this.priceFrom = priceFrom;
		this.priceTo = priceTo;
	}

	public double getPriceFrom() {
		return priceFrom;
	}

	public void setPriceFrom(double priceFrom) {
		this.priceFrom = priceFrom;
	}

	public double getPriceTo() {
		return priceTo;
	}

	public void setPriceTo(double priceTo) {
		this.priceTo = priceTo;
	}

	@Override
	public String toString() {
		return "PricesDTO [priceFrom=" + priceFrom + ", priceTo=" + priceTo + "]";
	}
	
}
