package co.edu.uptc.developer.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import co.edu.uptc.developer.dto.ResultDTO;
import co.edu.uptc.developer.ui.controller.ComputerController;

public class ComputerControllerTest {

	private ComputerController computerController;

	@BeforeEach
	void setUp() {
		computerController = new ComputerController();
	}

	@Test
	void testCreateComputerSuccess() {
		ResultDTO result = computerController.createComputer("1", "Dell", "Intel i7", "16", "Windows 11", "512");

		assertTrue(result.isSuccessful(), "El computador debería crearse correctamente");
	}

	@Test
	void testCreateComputerDuplicate() {
		computerController.createComputer("2", "HP", "AMD Ryzen 5", "8", "Linux", "256");

		ResultDTO result = computerController.createComputer("2", "HP", "AMD Ryzen 5", "8", "Linux", "256");

		assertFalse(result.isSuccessful(), "No debería permitir crear un computador duplicado");
		assertTrue(result.getListMessageError().contains("Ya existe un computador con ese id"));
	}

	@Test
	void testFindComputerByIdAndBrand() {
		computerController.createComputer("3", "Lenovo", "Intel i5", "8", "Windows 10", "512");

		ResultDTO result = computerController.findComputerByIdAndBrand("3", "Lenovo");

		assertTrue(result.isSuccessful(), "El computador debería encontrarse");
		assertNotNull(result.getObject(), "El objeto Computer no debería ser null");
	}

	@Test
	void testUpdateComputer() {
		computerController.createComputer("4", "Acer", "Intel i3", "4", "Windows 10", "128");

		ResultDTO result = computerController.updateComputer("4", "Acer", "Intel i3", "4", "Windows 11", "256");

		assertTrue(result.isSuccessful(), "El computador debería actualizarse correctamente");
		assertEquals("Se actualizó el registro", result.getMessage());
	}

	@Test
	void testDeleteComputer() {
		computerController.createComputer("5", "Apple", "M1", "16", "MacOS", "512");

		ResultDTO result = computerController.deleteComputer("5", "Apple");

		assertTrue(result.isSuccessful(), "El computador debería eliminarse correctamente");
		assertEquals("El registro fue eliminado", result.getMessage());
	}

	@Test
	void testCreateComputerInvalidRam() {
		ResultDTO result = computerController.createComputer("6", "Samsung", "Intel i9", "RAM", "Windows 11", "1024");

		assertFalse(result.isSuccessful(), "El computador no debería crearse con RAM inválida");
		assertTrue(result.getListMessageError().contains("El campo Memoria RAM debe ser numérico."));
	}
}
