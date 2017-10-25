package sbz.webshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sbz.webshop.model.Item;
import sbz.webshop.service.ItemService;

@RestController
@RequestMapping(value = "webshop/items")
public class ItemController {
	
	@Autowired
	ItemService itemService;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<List<Item>> getItems() {
		List<Item> items = itemService.findAll();
		return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
	}
}
