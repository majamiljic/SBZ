package sbz.project.WebShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import sbz.project.WebShop.model.UsersCategory;

public interface UsersCategoryRepository extends JpaRepository<UsersCategory, String>, JpaSpecificationExecutor<UsersCategory> {
	public UsersCategory findOneByName(String name);
	public UsersCategory findOneById(int id);

}
