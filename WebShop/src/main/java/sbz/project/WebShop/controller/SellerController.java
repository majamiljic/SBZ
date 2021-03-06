package sbz.project.WebShop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sbz.project.WebShop.model.Invoice;
import sbz.project.WebShop.model.InvoiceItem;
import sbz.project.WebShop.model.Item;
import sbz.project.WebShop.service.InvoiceService;
import sbz.project.WebShop.service.ItemService;

@RestController
@RequestMapping(value = "webshop/seller")
public class SellerController {

	@Autowired
	InvoiceService invoiceService;

	@Autowired
	ItemService itemService;

	@RequestMapping(value = "/getAllInvoices", method = RequestMethod.GET)
	public ResponseEntity<List<Invoice>> getAllInvoices() {
		List<Invoice> invoices = invoiceService.findAllInvoices();
		return new ResponseEntity<List<Invoice>>(invoices, HttpStatus.OK);
	}

	@RequestMapping(value = "/getInvoicesByStatus/{status}", method = RequestMethod.GET)
	public ResponseEntity<List<Invoice>> getInvoicesByStatus(@PathVariable String status) {
		List<Invoice> invoices = invoiceService.findAllByInvoiceStatus(status);
		return new ResponseEntity<List<Invoice>>(invoices, HttpStatus.OK);
	}

	@RequestMapping(value = "/approve/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Invoice>> approve(@PathVariable int id) {
		Invoice invoice = invoiceService.findOneInvoice(id);
		
		if(approve(invoice)) {
			for (InvoiceItem i : invoice.getInvoiceItems()) {
				i.getItem().setStockStatus(i.getItem().getStockStatus() - i.getQuantity());
				itemService.save(i.getItem());
			}
			invoice.setInvoiceStatus("Successful");
			invoiceService.saveInvoice(invoice);
			
			List<Invoice> invoices = invoiceService.findAllByInvoiceStatus("Ordered");
			return new ResponseEntity<List<Invoice>>(invoices, HttpStatus.OK);
		}
		else
			return new ResponseEntity<List<Invoice>>(HttpStatus.BAD_REQUEST);
	}
	
	public boolean approve(Invoice invoice) {
		ArrayList<String> retVals = new ArrayList<String>();
		
		for (InvoiceItem i : invoice.getInvoiceItems()) {
			if (i.getQuantity() < i.getItem().getStockStatus())
				retVals.add("true");
			else
				retVals.add("false");
		}
		
		if(retVals.contains("false"))
			return false;
		else
			return true;
	}

	@RequestMapping(value = "/decline/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Invoice>> decline(@PathVariable int id) {
		Invoice invoice = invoiceService.findOneInvoice(id);
		invoice.setInvoiceStatus("Cancelled");
		invoiceService.saveInvoice(invoice);
		
		List<Invoice> invoices = invoiceService.findAllByInvoiceStatus("Ordered");
		return new ResponseEntity<List<Invoice>>(invoices, HttpStatus.OK);
	}

	@RequestMapping(value = "/getItemsToRefill", method = RequestMethod.GET)
	public ResponseEntity<List<Item>> getItemsToRefill() {
		List<Item> items = itemService.getItemsToRefill();
		return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
	}

	@RequestMapping(value = "/refill", method = RequestMethod.POST)
	public ResponseEntity<List<Item>> refill(@RequestBody int numberOfItems) {
		List<Item> items = itemService.findAllByRefill("Yes");
		for (Item item : items) {
			int stockStatus = item.getStockStatus();
			item.setStockStatus(stockStatus + numberOfItems);
			item.setRefill("No");
			itemService.save(item);
		}
		return new ResponseEntity<List<Item>>(HttpStatus.OK);
	}
}
