package org.springframework.samples.petclinic.owner;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private OwnerRepository ownerRepository;

	public String getOwnerFullName(int id) {
		Owner owner = this.ownerRepository.findById(id);
		if (owner != null) {
			return owner.getFirstName() + " " + owner.getLastName();
		}
		else {
			return "No se encontró al dueño";
		}
	}

}
