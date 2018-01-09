package sbz.webshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sbz.webshop.model.ItemCategory;
import sbz.webshop.model.SpecialOffer;
import sbz.webshop.service.ItemCategoryService;
import sbz.webshop.service.SpecialOfferService;

@RestController
@RequestMapping(value = "webshop/manager")
public class ManagerController {

	@Autowired
	SpecialOfferService specialOfferService;
	
	@Autowired
	ItemCategoryService itemCategoryService;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<List<SpecialOffer>> getSpecialOffers() {
		List<SpecialOffer> offers = specialOfferService.findAll();
		return new ResponseEntity<List<SpecialOffer>>(offers, HttpStatus.OK);
	}

	@RequestMapping(value = "/updateOffer", method = RequestMethod.POST)
	public ResponseEntity<SpecialOffer> updateOffer(@RequestBody SpecialOffer specialOffer) {
		specialOfferService.save(specialOffer);
		return new ResponseEntity<SpecialOffer>(HttpStatus.OK);
	}

	@RequestMapping(value = "/updateItemCategory", method = RequestMethod.POST)
	public ResponseEntity<ItemCategory> updateItemCategory(@RequestBody ItemCategory itemCategory) {
		itemCategoryService.save(itemCategory);
		return new ResponseEntity<ItemCategory>(HttpStatus.OK);
	}
}
