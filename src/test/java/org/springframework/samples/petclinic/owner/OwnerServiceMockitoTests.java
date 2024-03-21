package org.springframework.samples.petclinic.owner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
public class OwnerServiceMockitoTests {

	@Mock
	private OwnerRepository ownerRepository;

	@InjectMocks
	private OwnerService ownerService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void getOwnerFullName_test_validOwner() {
		Owner owner = new Owner();
		owner.setId(Integer.valueOf(1));
		owner.setFirstName("Manuel");
		owner.setLastName("Del Test");
		owner.setAddress("Calle Virtual, 1");
		owner.setCity("Ciudad Mock");
		owner.setTelephone("1234567890");

		Mockito.when(ownerRepository.findById(1)).thenReturn(owner);
		String fullName = this.ownerService.getOwnerFullName(1);
		assertEquals("El nombre completo debería ser: ", "Manuel Del Test", fullName);
	}

	@Test
	public void getOwnerFullName_test_invalidOwner() {
		Mockito.when(ownerRepository.findById(1)).thenReturn(null);
		String fullName = this.ownerService.getOwnerFullName(1);
		assertEquals("El nombre devuelto debería ser: ", "No se encontró al dueño", fullName);
	}

}
