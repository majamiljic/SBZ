package sbz.project.WebShop.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sbz.project.DTO.LoginUserDTO;
import sbz.project.WebShop.model.Invoice;
import sbz.project.WebShop.model.User;
import sbz.project.WebShop.model.UsersProfile;
import sbz.project.WebShop.service.InvoiceService;
import sbz.project.WebShop.service.UserService;

@RestController
@RequestMapping(value = "webshop/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	InvoiceService invoiceService;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.findAll();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<User> login(@RequestBody LoginUserDTO loginUser) {
		if(loginUser == null)
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		
		User user = userService.findOneByEmail(loginUser.getEmail());
		if(user == null)
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		
		if(!user.getPassword().equals(loginUser.getPassword()))
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/registrate", method = RequestMethod.POST)
	public ResponseEntity<User> registrate(@RequestBody User user) {
		if (!checkInput(user))
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);

		if(userService.findOneByEmail(user.getEmail()) != null)
			return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);

		user.getUsersProfile().setBuyerCategory(userService.findOne("Regular"));
		user.setRole("Buyer");
		userService.save(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	private boolean checkInput(User user) {
		if(user.getName() == null || user.getSurname() == null || user.getEmail() == null
		|| user.getPassword() == null || user.getUsersProfile().getAddress() == null)
			return false;

		if(user.getName().equals("") || user.getSurname().equals("") || user.getEmail().equals("")
		|| user.getPassword().equals("") || user.getUsersProfile().getAddress().equals(""))
			return false;

		return true;
	}

	@RequestMapping(value = "/registerSeller", method = RequestMethod.PUT)
	public ResponseEntity<User> registerSeller(User user) {
		UsersProfile profile = new UsersProfile();
		profile.setId(user.getId());
		profile.setBuyerCategory(userService.findOne("Regular"));
		userService.save(profile);
		
		user.setRegistrationDate(new Date());
		user.setRole("Seller");
		user.setUsersProfile(profile);
		
		userService.save(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/getInvoices/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Invoice>> getInvoices(@PathVariable int id) {
		List<Invoice> invoices = invoiceService.findAllByBuyerId(id);
		return new ResponseEntity<List<Invoice>>(invoices, HttpStatus.OK);
	}
}
