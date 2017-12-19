package sbz.webshop.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sbz.webshop.model.Invoice;
import sbz.webshop.model.InvoiceItem;
import sbz.webshop.service.InvoiceService;
import sbz.webshop.service.ItemService;

@RestController
@RequestMapping(value = "webshop/seller")
public class SellerController {

	@Autowired
	InvoiceService invoiceService;

	@Autowired
	ItemService itemService;

	@RequestMapping(value = "/getInvoices", method = RequestMethod.GET)
	public ResponseEntity<List<Invoice>> getInvoices() {
		List<Invoice> invoices = invoiceService.findAllByInvoiceStatus("Ordered");
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

}
