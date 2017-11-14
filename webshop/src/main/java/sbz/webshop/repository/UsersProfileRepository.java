package sbz.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import sbz.webshop.model.UsersProfile;

public interface UsersProfileRepository extends JpaRepository<UsersProfile, Integer>, JpaSpecificationExecutor<UsersProfile> {

}
