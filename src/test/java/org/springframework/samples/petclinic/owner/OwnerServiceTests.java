package org.springframework.samples.petclinic.owner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OwnerServiceTests {

	@Autowired
	private OwnerRepository ownerRepository;

	@Autowired
	private OwnerService ownerService;

	private Owner owner;

	@BeforeAll
	public void setUp() {
		owner = new Owner();
		owner.setId(Integer.valueOf(1));
		owner.setFirstName("Martín");
		owner.setLastName("de la Prueba");
		owner.setAddress("Calle Virtual, 2");
		owner.setCity("Ciudad Test");
		owner.setTelephone("1234567890");

		this.ownerRepository.save(owner);
	}

	@AfterAll
	public void tearDown() {
		this.ownerRepository.deleteById(owner.getId());
	}

	@Test
	public void getOwnerFullName_test_validOwner() {
		String fullName = this.ownerService.getOwnerFullName(1);
		assertEquals("El nombre completo debería ser: ", "Martín de la Prueba", fullName);
	}

	@Test
	public void getOwnerFullName_test_invalidOwner() {
		String fullName = this.ownerService.getOwnerFullName(999);
		assertTrue("El nombre devuelto debería ser: ", fullName.equals("No se encontró al dueño"));
	}

}
