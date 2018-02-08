package sbz.project.DTO;

import sbz.project.WebShop.model.Item;

public class CartItemDTO {
	private Item item;
	private int numberOfItems;
	
	public CartItemDTO() {
		super();
	}

	public CartItemDTO(Item item, int numberOfItems) {
		super();
		this.item = item;
		this.numberOfItems = numberOfItems;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getNumberOfItems() {
		return numberOfItems;
	}

	public void setNumberOfItems(int numberOfItems) {
		this.numberOfItems = numberOfItems;
	}

	@Override
	public String toString() {
		return "CartDTO [item=" + item + ", numberOfItems=" + numberOfItems + "]";
	}
}
