package sbz.webshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import sbz.webshop.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer>, JpaSpecificationExecutor<Invoice> {
	public List<Invoice> findAllByBuyerId(int id);
	public List<Invoice> findAllByInvoiceStatus(String status);
}
