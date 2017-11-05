package sbz.webshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbz.webshop.model.User;
import sbz.webshop.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;

	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findOne(Integer id){
		return userRepository.findOne(id);
	}
	
	public User save(User user){
		return userRepository.save(user);
	}

}
