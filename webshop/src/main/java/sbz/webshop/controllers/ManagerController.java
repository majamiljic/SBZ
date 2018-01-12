package sbz.webshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sbz.webshop.model.ItemCategory;
import sbz.webshop.model.SpecialOffer;
import sbz.webshop.model.SpendingBoundary;
import sbz.webshop.model.UsersCategory;
import sbz.webshop.service.ItemCategoryService;
import sbz.webshop.service.SpecialOfferService;
import sbz.webshop.service.UserService;

@RestController
@RequestMapping(value = "webshop/manager")
public class ManagerController {

	@Autowired
	SpecialOfferService specialOfferService;
	
	@Autowired
	ItemCategoryService itemCategoryService;
	
	@Autowired
	UserService userService;

	@RequestMapping(value = "/getCategories", method = RequestMethod.GET)
	public ResponseEntity<List<ItemCategory>> getCategories() {
		List<ItemCategory> categories = itemCategoryService.findAll();
		return new ResponseEntity<List<ItemCategory>>(categories, HttpStatus.OK);
	}

	@RequestMapping(value = "/getParentCategories", method = RequestMethod.GET)
	public ResponseEntity<List<ItemCategory>> getParentCategories() {
		List<ItemCategory> categories = itemCategoryService.findAllParentCategories();
		return new ResponseEntity<List<ItemCategory>>(categories, HttpStatus.OK);
	}

	@RequestMapping(value = "/getOffers", method = RequestMethod.GET)
	public ResponseEntity<List<SpecialOffer>> getSpecialOffers() {
		List<SpecialOffer> offers = specialOfferService.findAll();
		return new ResponseEntity<List<SpecialOffer>>(offers, HttpStatus.OK);
	}

	@RequestMapping(value = "/updateItemCategory", method = RequestMethod.POST)
	public ResponseEntity<ItemCategory> updateItemCategory(@RequestBody ItemCategory itemCategory) {
		itemCategoryService.save(itemCategory);
		return new ResponseEntity<ItemCategory>(HttpStatus.OK);
	}

	@RequestMapping(value = "/updateOffer", method = RequestMethod.POST)
	public ResponseEntity<SpecialOffer> updateOffer(@RequestBody SpecialOffer specialOffer) {
		specialOfferService.save(specialOffer);
		return new ResponseEntity<SpecialOffer>(HttpStatus.OK);
	}

	@RequestMapping(value = "/addItemCategory", method = RequestMethod.POST)
	public ResponseEntity<List<ItemCategory>> addItemCategory(@RequestBody ItemCategory newItemCategory) {
		List<ItemCategory> categories = itemCategoryService.findAll();
		for (ItemCategory itemCategory : categories) {
			if(itemCategory.getName().equals(newItemCategory.getName()))
				return new ResponseEntity<List<ItemCategory>>(HttpStatus.BAD_REQUEST);
		}
		
		itemCategoryService.save(newItemCategory);
		return new ResponseEntity<List<ItemCategory>>(itemCategoryService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/addOffer", method = RequestMethod.POST)
	public ResponseEntity<List<SpecialOffer>> addOffer(@RequestBody SpecialOffer newOffer) {
		List<SpecialOffer> specialOffers = specialOfferService.findAll();
		for (SpecialOffer specialOffer : specialOffers) {
			if(specialOffer.getName().equals(newOffer.getName()))
				return new ResponseEntity<List<SpecialOffer>>(HttpStatus.BAD_REQUEST);
		}
		
		specialOfferService.save(newOffer);
		return new ResponseEntity<List<SpecialOffer>>(specialOfferService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/getUserCategories", method = RequestMethod.GET)
	public ResponseEntity<List<UsersCategory>> getUserCategories() {
		List<UsersCategory> userCategories = userService.findAllUserCategories();
		return new ResponseEntity<List<UsersCategory>>(userCategories, HttpStatus.OK);
	}

	@RequestMapping(value = "/getSpendingBoundariesByUserCategory/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<SpendingBoundary>> getSpendingBoundariesByUserCategory(@PathVariable int id) {
		UsersCategory uc = userService.findOneById(id);
		List<SpendingBoundary> spendingBoundaries = userService.findAllSpendingBoundariesByUserCategory(uc);
		return new ResponseEntity<List<SpendingBoundary>>(spendingBoundaries, HttpStatus.OK);
	}

	@RequestMapping(value = "/addSpendingBoundary", method = RequestMethod.POST)
	public ResponseEntity<List<SpendingBoundary>> addSpendingBoundary(@RequestBody SpendingBoundary sb) {
		userService.saveSpendingBoundary(sb);
		return new ResponseEntity<List<SpendingBoundary>>(HttpStatus.OK);
	}

	@RequestMapping(value = "/updateSpendingBoundary", method = RequestMethod.POST)
	public ResponseEntity<SpendingBoundary> updateSpendingBoundary(@RequestBody SpendingBoundary sb) {
		userService.saveSpendingBoundary(sb);
		return new ResponseEntity<SpendingBoundary>(HttpStatus.OK);
	}
}
