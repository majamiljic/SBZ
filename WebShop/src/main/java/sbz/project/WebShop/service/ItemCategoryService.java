package sbz.project.WebShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbz.project.WebShop.model.ItemCategory;
import sbz.project.WebShop.repository.ItemCategoryRepository;

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

	public List<ItemCategory> findAllParentCategories(){
		return categoryRepository.findByParentCategoryIsNull();
	}
}
