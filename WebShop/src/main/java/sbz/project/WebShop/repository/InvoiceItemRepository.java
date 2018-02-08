package sbz.project.WebShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import sbz.project.WebShop.model.InvoiceItem;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Integer>, JpaSpecificationExecutor<InvoiceItem> {

}
