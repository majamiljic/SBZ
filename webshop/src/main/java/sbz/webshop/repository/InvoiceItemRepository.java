package sbz.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import sbz.webshop.model.InvoiceItem;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Integer>, JpaSpecificationExecutor<InvoiceItem> {

}
