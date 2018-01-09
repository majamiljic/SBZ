package sbz.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import sbz.webshop.model.SpecialOffer;

public interface SpecialOfferRepository extends JpaRepository<SpecialOffer, Integer>, JpaSpecificationExecutor<SpecialOffer> {

}
