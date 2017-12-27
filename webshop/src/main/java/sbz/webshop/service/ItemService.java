package sbz.webshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbz.webshop.model.Item;
import sbz.webshop.repository.ItemRepository;

@Service
public class ItemService {
	
	@Autowired
	ItemRepository itemRepository;

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
	
}
