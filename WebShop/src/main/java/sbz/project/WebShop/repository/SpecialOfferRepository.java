package sbz.project.WebShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import sbz.project.WebShop.model.SpecialOffer;

public interface SpecialOfferRepository extends JpaRepository<SpecialOffer, Integer>, JpaSpecificationExecutor<SpecialOffer> {

}
