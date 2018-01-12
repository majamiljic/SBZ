package sbz.webshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import sbz.webshop.model.SpendingBoundary;
import sbz.webshop.model.UsersCategory;

public interface SpendingBoundaryRepository extends JpaRepository<SpendingBoundary, Integer>, JpaSpecificationExecutor<SpendingBoundary> {
	public List<SpendingBoundary> findAllByUserCategory(UsersCategory uc);
}
