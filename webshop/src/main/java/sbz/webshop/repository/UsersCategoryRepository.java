package sbz.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import sbz.webshop.model.UsersCategory;

public interface UsersCategoryRepository extends JpaRepository<UsersCategory, String>, JpaSpecificationExecutor<UsersCategory> {
	public UsersCategory findOneByName(String name);
	public UsersCategory findOneById(int id);
}
