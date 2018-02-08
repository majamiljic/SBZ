package sbz.project.WebShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import sbz.project.WebShop.model.UsersProfile;

public interface UsersProfileRepository extends JpaRepository<UsersProfile, Integer>, JpaSpecificationExecutor<UsersProfile> {

}
