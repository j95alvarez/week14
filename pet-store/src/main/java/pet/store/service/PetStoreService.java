package pet.store.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pet.store.controller.model.PetStoreData;
import pet.store.dao.PetStoreDao;
import pet.store.entity.PetStore;

@Service
public class PetStoreService {
	@Autowired
	private PetStoreDao petStoreDao;

	public PetStoreData savePetStore(PetStoreData petStoreData) {
		// TODO Auto-generated method stub
		PetStore petStore = findOrCreatePetStore(petStoreData.getPetStoreId());
		copyPetStoreFields(petStore, petStoreData);
		
		return new PetStoreData(petStoreDao.save(petStore));
	}
	
	private void copyPetStoreFields(PetStore petStore, PetStoreData petStoreData) {
		// TODO Auto-generated method stub
		petStore.setPetStoreName(petStoreData.getPetStoreName());
		petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
		petStore.setPetStoreCity(petStoreData.getPetStoreCity());
		petStore.setPetStoreState(petStoreData.getPetStoreState());
		petStore.setPetStoreZip(petStoreData.getPetStoreZip());
		petStore.setPetStorePhone(petStoreData.getPetStorePhone());
	}

	private PetStore findOrCreatePetStore(Long petStoreId) {
		// TODO Auto-generated method stub
		
		if (petStoreId == null)
			return new PetStore();
		else
			return petStoreDao.findById(petStoreId).orElseThrow(
					()-> new NoSuchElementException("Pet Store with ID = " + petStoreId + " not found."));
	}
	
}
