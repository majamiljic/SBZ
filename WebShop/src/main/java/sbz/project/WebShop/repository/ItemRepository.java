package sbz.project.WebShop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import sbz.project.WebShop.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>, JpaSpecificationExecutor<Item> {
	public List<Item> findAllByRefill(String refill);
	public List<Item> findAllByNameContains(String name);
	public List<Item> findAllByCode(int code);
	public List<Item> findAllByCategoryId(int id);
	public List<Item> findAllByPriceBetween(double priceFrom, double priceTo);

}
