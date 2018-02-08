package sbz.project.WebShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbz.project.WebShop.model.SpendingBoundary;
import sbz.project.WebShop.model.User;
import sbz.project.WebShop.model.UsersCategory;
import sbz.project.WebShop.model.UsersProfile;
import sbz.project.WebShop.repository.SpendingBoundaryRepository;
import sbz.project.WebShop.repository.UserRepository;
import sbz.project.WebShop.repository.UsersCategoryRepository;
import sbz.project.WebShop.repository.UsersProfileRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UsersCategoryRepository usersCategoryRepository;
	
	@Autowired
	UsersProfileRepository usersProfileRepository;
	
	@Autowired
	SpendingBoundaryRepository spendingBoundaryRepository;

	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findOne(Integer id){
		return userRepository.findOne(id);
	}
	
	public User save(User user){
		return userRepository.save(user);
	}
	
	public User findOneByEmail(String email) {
		return userRepository.findOneByEmail(email);
	}
	
	public UsersCategory findOne(String category) {
		return usersCategoryRepository.findOneByName(category);
	}
	
	public UsersCategory findOneById(int id) {
		return usersCategoryRepository.findOneById(id);
	}
	
	public UsersProfile save(UsersProfile profile) {
		return usersProfileRepository.save(profile);
	}

	public List<UsersCategory> findAllUserCategories(){
		return usersCategoryRepository.findAll();
	}

	public List<SpendingBoundary> findAllSpendingBoundariesByUserCategory(UsersCategory uc){
		return spendingBoundaryRepository.findAllByUserCategory(uc);
	}
	
	public SpendingBoundary saveSpendingBoundary(SpendingBoundary sb) {
		return spendingBoundaryRepository.save(sb);
	}
}
