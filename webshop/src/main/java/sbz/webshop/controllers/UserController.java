package sbz.webshop.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sbz.webshop.model.User;
import sbz.webshop.model.UsersProfile;
import sbz.webshop.service.UserService;

@RestController
@RequestMapping(value = "webshop/user")
public class UserController {
	
	@Autowired
	UserService userService;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.findAll();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
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

}
