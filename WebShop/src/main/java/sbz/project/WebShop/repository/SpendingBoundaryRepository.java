package sbz.project.WebShop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import sbz.project.WebShop.model.SpendingBoundary;
import sbz.project.WebShop.model.UsersCategory;

public interface SpendingBoundaryRepository extends JpaRepository<SpendingBoundary, Integer>, JpaSpecificationExecutor<SpendingBoundary> {
	public List<SpendingBoundary> findAllByUserCategory(UsersCategory uc);

}
