package sbz.webshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbz.webshop.model.ItemCategory;
import sbz.webshop.repository.ItemCategoryRepository;

@Service
public class ItemCategoryService {

	@Autowired
	ItemCategoryRepository categoryRepository;

	public List<ItemCategory> findAll(){
		return categoryRepository.findAll();
	}
	
	public ItemCategory findOne(Integer id){
		return categoryRepository.findOne(id);
	}
	
	public ItemCategory save(ItemCategory category){
		return categoryRepository.save(category);
	}
	
	public List<ItemCategory> findByParentCategoryNotNull(){
		return categoryRepository.findByParentCategoryNotNull();
	}
	
}
