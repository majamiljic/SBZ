package sbz.project.WebShop.model;

import java.util.ArrayList;
import java.util.List;

public class Items {
	private List<Item> items = new ArrayList<Item>();

	public Items() {
		super();
	}

	public Items(List<Item> items) {
		super();
		this.items = items;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Items [items=" + items + "]";
	}
	
}
