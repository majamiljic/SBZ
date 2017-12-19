package sbz.webshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbz.webshop.model.Invoice;
import sbz.webshop.model.InvoiceItem;
import sbz.webshop.repository.InvoiceItemRepository;
import sbz.webshop.repository.InvoiceRepository;

@Service
public class InvoiceService {

	@Autowired
	InvoiceItemRepository invoiceItemRepository;

	@Autowired
	InvoiceRepository invoiceRepository;

	public List<InvoiceItem> findAll(){
		return invoiceItemRepository.findAll();
	}
	
	public InvoiceItem findOne(Integer id){
		return invoiceItemRepository.findOne(id);
	}
	
	public InvoiceItem save(InvoiceItem invoiceItem){
		return invoiceItemRepository.save(invoiceItem);
	}

	public List<Invoice> findAllInvoices(){
		return invoiceRepository.findAll();
	}
	
	public Invoice findOneInvoice(Integer id){
		return invoiceRepository.findOne(id);
	}
	
	public Invoice saveInvoice(Invoice invoice){
		return invoiceRepository.save(invoice);
	}

	public List<Invoice> findAllByBuyerId(int id){
		return invoiceRepository.findAllByBuyerId(id);
	}

	public List<Invoice> findAllByInvoiceStatus(String status){
		return invoiceRepository.findAllByInvoiceStatus(status);
	}
	
}
