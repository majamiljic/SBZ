package sbz.project.WebShop.service;

import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbz.project.WebShop.model.Item;
import sbz.project.WebShop.model.Items;
import sbz.project.WebShop.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	ItemRepository itemRepository;

	private final KieContainer kContainer;

	@Autowired
	public ItemService(KieContainer kContainer){
		this.kContainer = kContainer;
	}

	public List<Item> findAll(){
		return itemRepository.findAll();
	}
	
	public Item findOne(Integer id){
		return itemRepository.findOne(id);
	}
	
	public Item save(Item item){
		return itemRepository.save(item);
	}

	public List<Item> findAllByRefill(String refill){
		return itemRepository.findAllByRefill(refill);
	}
	
	public List<Item> getItemsToRefill(){
		Items items = new Items();
		items.setItems(itemRepository.findAll());
		orderItems(items);
		updateItems(items);
		return findAllByRefill("Yes");
	}
	
	public void updateItems(Items items) {
		for (Item item : items.getItems())
			itemRepository.save(item);
	}

	public List<Item> findAllByName(String name){
		return itemRepository.findAllByNameContains(name);
	}
	
	public List<Item> findAllByCode(int code){
		return itemRepository.findAllByCode(code);
	}
	
	public List<Item> findAllByCategoryId(int id){
		return itemRepository.findAllByCategoryId(id);
	}
	
	public List<Item> findAllByPrice(double priceFrom, double priceTo){
		return itemRepository.findAllByPriceBetween(priceFrom, priceTo);
	}
	
	/******   REFILL RULE   ******/
	private void orderItems(Items items){
		KieSession kSession = kContainer.newKieSession();
		kSession.insert(items);
		kSession.getAgenda().getAgendaGroup("Order items").setFocus();
		kSession.fireAllRules();
		kSession.dispose();
	}
}
