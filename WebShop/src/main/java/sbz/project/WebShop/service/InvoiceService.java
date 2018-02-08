package sbz.project.WebShop.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbz.project.WebShop.model.CategoriesWithBoundaries;
import sbz.project.WebShop.model.Invoice;
import sbz.project.WebShop.model.InvoiceItem;
import sbz.project.WebShop.model.User;
import sbz.project.WebShop.model.UsersCategory;
import sbz.project.WebShop.repository.InvoiceItemRepository;
import sbz.project.WebShop.repository.InvoiceRepository;
import sbz.project.WebShop.repository.UserRepository;
import sbz.project.WebShop.repository.UsersCategoryRepository;

@Service
public class InvoiceService {

	@Autowired
	InvoiceItemRepository invoiceItemRepository;

	@Autowired
	InvoiceRepository invoiceRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UsersCategoryRepository usersCategoryRepository;
	
	private final KieContainer kContainer;

	@Autowired
	public InvoiceService(KieContainer kContainer){
		this.kContainer = kContainer;
	}

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
	
	public Invoice saveInvoice(Invoice invoice) {
		if(invoice.getId() == null) {
			invoice = basicDiscounts(invoice);
			for (InvoiceItem item : invoice.getInvoiceItems())
				basicDiscountsCheck(item);
			
			invoice = extraItemDiscount2(invoice);
			invoice = extraItemDiscount1(invoice);
			invoice = extraItemDiscountSpecialOffer(invoice);
			
			for (InvoiceItem item : invoice.getInvoiceItems())
				finalItemDiscount(item);
			
			invoice = invoiceDiscount(invoice);
			invoice = finalDiscount(invoice);
			
			userRepository.save(addBonusPoints(invoice));
		}
		
		return invoiceRepository.save(invoice);
	}

	public List<Invoice> findAllByBuyerId(int id){
		return invoiceRepository.findAllByBuyerId(id);
	}

	public List<Invoice> findAllByInvoiceStatus(String status){
		return invoiceRepository.findAllByInvoiceStatus(status);
	}
	
	/******   BASIC DISCOUNTS   ******/
	private Invoice basicDiscounts(Invoice invoice){
		KieSession kSession = kContainer.newKieSession();
		kSession.insert(invoice);
		kSession.getAgenda().getAgendaGroup("Basic discounts").setFocus();
		kSession.fireAllRules();
		kSession.dispose();
		return invoice;
	}
	
	private InvoiceItem basicDiscountsCheck(InvoiceItem item){
		KieSession kSession = kContainer.newKieSession();
		kSession.insert(item);
		kSession.getAgenda().getAgendaGroup("Basic discounts check").setFocus();
		kSession.fireAllRules();
		kSession.dispose();
		return item;
	}

	/******   EXTRA DISCOUNTS   ******/
	private void extraDiscount2(InvoiceItem item, Invoice invoice) {
		KieSession kieSession = kContainer.newKieSession();
        kieSession.insert(invoice);
        kieSession.insert(item);
        kieSession.getAgenda().getAgendaGroup("Extra discount 2").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
	}
	
	private Invoice extraItemDiscount2(Invoice invoice) {
		//Get Buyers Invoices
		List<Invoice> invoices = invoiceRepository.findAllByBuyerId(invoice.getBuyer().getId());
		
		//For every invoice item (in current invoice)
		//check if it is bought in the past 15 days (in that buyers invoices)
		for (InvoiceItem item : invoice.getInvoiceItems()) {
			int discounts = item.getDiscounts().size();
			for (Invoice inv : invoices) {
				extraDiscount2(item, inv);
				if(discounts != item.getDiscounts().size())
					break;
			}
		}
		return invoice;
	}
	
	private void extraDiscount1(InvoiceItem item, Invoice invoice) {
		KieSession kieSession = kContainer.newKieSession();
        kieSession.insert(invoice);
        kieSession.insert(item);
        kieSession.getAgenda().getAgendaGroup("Extra discount 1").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
	}
	
	private Invoice extraItemDiscount1(Invoice invoice) {
		//Get Buyers Invoices
		List<Invoice> invoices = invoiceRepository.findAllByBuyerId(invoice.getBuyer().getId());
		
		//For every invoice item (in current invoice)
		//check if a product from its category was bought in the past 30 days
		for (InvoiceItem item : invoice.getInvoiceItems()) {
			int discounts = item.getDiscounts().size();
			for (Invoice inv : invoices) {
				extraDiscount1(item, inv);
				if(discounts != item.getDiscounts().size())
					break;
			}
		}
		return invoice;
	}

	/******   SPECIAL OFFER DISCOUNT   ******/
	private void specialOfferDiscount(InvoiceItem item) {
		KieSession kieSession = kContainer.newKieSession();
        kieSession.insert(item);
        kieSession.getAgenda().getAgendaGroup("Extra discount - Special Offer").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
	}
	
	private Invoice extraItemDiscountSpecialOffer(Invoice invoice) {
		for (InvoiceItem item : invoice.getInvoiceItems())
			specialOfferDiscount(item);
		return invoice;
	}

	/******   FINAL ITEM DISCOUNT   ******/
	private void finalItemDiscount(InvoiceItem item){
		KieSession kieSession = kContainer.newKieSession();
        kieSession.insert(item);
        kieSession.getAgenda().getAgendaGroup("Final item discount").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
	}

	/******   INVOICE DISCOUNTS   ******/
	private Date twoYearsLess() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -2);
		return calendar.getTime();
	}
	
	private Invoice invoiceDiscount(Invoice invoice) {
		KieSession kieSession = kContainer.newKieSession();
		kieSession.setGlobal("twoYearsLess", twoYearsLess());
        kieSession.insert(invoice);
        kieSession.getAgenda().getAgendaGroup("Invoice discounts").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
		return invoice;
	}
	
	private Invoice finalDiscount(Invoice invoice){
		KieSession kieSession = kContainer.newKieSession();
        kieSession.insert(invoice);
        kieSession.getAgenda().getAgendaGroup("Final discount").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
		return invoice;
	}

	/******   BONUS POINTS   ******/
	private User addBonusPoints(Invoice invoice) {
		KieSession kieSession = kContainer.newKieSession();
		UsersCategory usersCategory = usersCategoryRepository.findOneById(invoice.getBuyer().getUsersProfile().getBuyerCategory().getId());
		CategoriesWithBoundaries categories = new CategoriesWithBoundaries(usersCategory, usersCategory.getSpendingBoundary());
		kieSession.insert(invoice);
        kieSession.insert(categories);
        kieSession.getAgenda().getAgendaGroup("Bonus points").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
		return invoice.getBuyer();
	}
}
