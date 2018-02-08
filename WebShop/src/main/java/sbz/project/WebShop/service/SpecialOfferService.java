package sbz.project.WebShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbz.project.WebShop.model.SpecialOffer;
import sbz.project.WebShop.repository.SpecialOfferRepository;

@Service
public class SpecialOfferService {

	@Autowired
	SpecialOfferRepository specialOfferRepository;

	public List<SpecialOffer> findAll(){
		return specialOfferRepository.findAll();
	}
	
	public SpecialOffer findOne(Integer id){
		return specialOfferRepository.findOne(id);
	}
	
	public SpecialOffer save(SpecialOffer specialOffer){
		return specialOfferRepository.save(specialOffer);
	}
}
