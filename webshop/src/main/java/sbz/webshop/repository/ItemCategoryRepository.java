package sbz.webshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import sbz.webshop.model.ItemCategory;

public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Integer>, JpaSpecificationExecutor<ItemCategory> {
	List<ItemCategory> findByParentCategoryNotNull();
	
}
