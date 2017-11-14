package sbz.webshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbz.webshop.model.User;
import sbz.webshop.model.UsersCategory;
import sbz.webshop.model.UsersProfile;
import sbz.webshop.repository.UserRepository;
import sbz.webshop.repository.UsersCategoryRepository;
import sbz.webshop.repository.UsersProfileRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UsersCategoryRepository usersCategoryRepository;
	
	@Autowired
	UsersProfileRepository usersProfileRepository;

	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findOne(Integer id){
		return userRepository.findOne(id);
	}
	
	public User save(User user){
		return userRepository.save(user);
	}
	
	public UsersCategory findOne(String category) {
		return usersCategoryRepository.findOneByName(category);
	}
	
	public UsersProfile save(UsersProfile profile) {
		return usersProfileRepository.save(profile);
	}

}
