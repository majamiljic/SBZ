package sbz.webshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import sbz.webshop.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>, JpaSpecificationExecutor<Item> {
	public List<Item> findAllByRefill(String refill);
	public List<Item> findAllByNameContains(String name);
	public List<Item> findAllByCode(int code);
	public List<Item> findAllByCategoryId(int id);
}
