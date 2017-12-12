package sbz.webshop.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sbz.webshop.DTO.CartItemDTO;
import sbz.webshop.model.Invoice;
import sbz.webshop.model.InvoiceItem;
import sbz.webshop.model.Item;
import sbz.webshop.model.ItemCategory;
import sbz.webshop.model.User;
import sbz.webshop.service.InvoiceService;
import sbz.webshop.service.ItemCategoryService;
import sbz.webshop.service.ItemService;
import sbz.webshop.service.UserService;

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
		List<Item> items = itemService.findAll();
		return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
	}

	@RequestMapping(value = "/getCategories", method = RequestMethod.GET)
	public ResponseEntity<List<ItemCategory>> getCategories() {
		List<ItemCategory> categories = categoryService.findByParentCategoryNotNull();
		return new ResponseEntity<List<ItemCategory>>(categories, HttpStatus.OK);
	}

	@RequestMapping(value = "/purchase", method = RequestMethod.POST)
	public ResponseEntity<Invoice> purchase(@RequestBody List<CartItemDTO> cartItems) {
		Invoice invoice = new Invoice();
		
		User user = userService.findOne(3);
		invoice.setBuyer(user);
		invoice.setDate(new Date());
		invoice.setInvoiceStatus("Successfull");
		
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
			invoiceItem.setTotalPrice(subtotalPrice);
			
			invoiceItems.add(invoiceItem);
			invoiceService.save(invoiceItem);
			
			invoiceItemNumber++;
		}
		
		double invoiceTotal = 0;
		for (InvoiceItem invoiceItem : invoiceItems)
			invoiceTotal += invoiceItem.getTotalPrice();
		
		invoice.setInvoiceItems(invoiceItems);
		invoice.setSubtotal(invoiceTotal);
		invoice.setTotal(invoiceTotal);
		
		invoiceService.saveInvoice(invoice);
		
		return new ResponseEntity<Invoice>(HttpStatus.OK);
	}
}
