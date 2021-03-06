package sbz.project.WebShop.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sbz.project.DTO.CartItemDTO;
import sbz.project.DTO.PricesDTO;
import sbz.project.WebShop.model.Invoice;
import sbz.project.WebShop.model.InvoiceItem;
import sbz.project.WebShop.model.Item;
import sbz.project.WebShop.model.ItemCategory;
import sbz.project.WebShop.model.User;
import sbz.project.WebShop.service.InvoiceService;
import sbz.project.WebShop.service.ItemCategoryService;
import sbz.project.WebShop.service.ItemService;
import sbz.project.WebShop.service.UserService;

@RestController
@RequestMapping(value = "webshop/items")
public class ItemController {

	@Autowired
	ItemService itemService;
	
	@Autowired
	ItemCategoryService categoryService;
	
	@Autowired
	InvoiceService invoiceService;
	
	@Autowired
	UserService userService;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<List<Item>> getItems() {
		List<Item> items = itemService.findAllByRefill("No");
		return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
	}

	@RequestMapping(value = "/getCategories", method = RequestMethod.GET)
	public ResponseEntity<List<ItemCategory>> getCategories() {
		List<ItemCategory> categories = categoryService.findByParentCategoryNotNull();
		return new ResponseEntity<List<ItemCategory>>(categories, HttpStatus.OK);
	}

	@RequestMapping(value = "/getItemsByName/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<Item>> getItemsByName(@PathVariable String name) {
		List<Item> items = itemService.findAllByName(name);
		if(!items.isEmpty())
			return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
		else
			return new ResponseEntity<List<Item>>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/getItemByCode/{code}", method = RequestMethod.GET)
	public ResponseEntity<List<Item>> getItemsByName(@PathVariable int code) {
		List<Item> items = itemService.findAllByCode(code);
		if(!items.isEmpty())
			return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
		else
			return new ResponseEntity<List<Item>>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/getItemsByCategory/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Item>> getItemsByCategory(@PathVariable int id) {
		List<Item> items = itemService.findAllByCategoryId(id);
		if(!items.isEmpty())
			return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
		else
			return new ResponseEntity<List<Item>>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/getItemsByPrice", method = RequestMethod.POST)
	public ResponseEntity<List<Item>> getItemsByPrice(@RequestBody PricesDTO prices) {
		List<Item> items = itemService.findAllByPrice(prices.getPriceFrom(), prices.getPriceTo());
		if(!items.isEmpty())
			return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
		else
			return new ResponseEntity<List<Item>>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/purchase", method = RequestMethod.POST)
	public ResponseEntity<Invoice> purchase(@RequestBody List<CartItemDTO> cartItems) {
		Invoice invoice = new Invoice();
		
		User user = userService.findOne(3);
		invoice.setBuyer(user);
		invoice.setDate(new Date());
		invoice.setInvoiceStatus("Ordered");
		
		List<InvoiceItem> invoiceItems = new ArrayList<InvoiceItem>();
		
		int invoiceItemNumber = 0;
		for (CartItemDTO cartItemDTO : cartItems) {
			InvoiceItem invoiceItem = new InvoiceItem();
			invoiceItem.setInvoiceItemNumber(invoiceItemNumber);
			invoiceItem.setItem(cartItemDTO.getItem());
			invoiceItem.setPrice(cartItemDTO.getItem().getPrice());
			invoiceItem.setQuantity(cartItemDTO.getNumberOfItems());
			
			double subtotalPrice = invoiceItem.getPrice()*invoiceItem.getQuantity();
			invoiceItem.setSubtotalPrice(subtotalPrice);
			
			invoiceItems.add(invoiceItem);
			invoiceItemNumber++;
		}
		
		double invoiceSubotal = 0;
		for (InvoiceItem invoiceItem : invoiceItems)
			invoiceSubotal += invoiceItem.getSubtotalPrice();
		
		invoice.setSubtotal(invoiceSubotal);
		invoice.setInvoiceItems(invoiceItems);
		
		invoiceService.saveInvoice(invoice);

		return new ResponseEntity<Invoice>(invoice, HttpStatus.OK);
	}
}
