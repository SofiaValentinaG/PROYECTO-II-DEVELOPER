package co.edu.uptc.developer.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import co.edu.uptc.developer.domain.Computer;
import co.edu.uptc.developer.domain.Project;
import co.edu.uptc.developer.enums.StatusEnum;
import co.edu.uptc.developer.ui.controller.DeveloperController;
import co.edu.uptc.developer.dto.ResultDTO;

public class DeveloperControllerTest {

	private DeveloperController developerController;

	@BeforeEach
	void setUp() {
		developerController = new DeveloperController();
	}

	@Test
	void testCreateDeveloperSuccess() {
		Project project = new Project(1233L, "Taller", new Date(), 8000.0, StatusEnum.PLANNING, "Proyecto para progra");
		Computer computer = new Computer(1L, "Dell", "Intel i7", 16, "Windows 11", 512);

		ResultDTO result = developerController.createDeveloper("1233", "Sofía", "Gonzalez", "Java", "5", "3000.0",
				"sofiagonzalez@mail.com", project, computer);

		assertTrue(result.isSuccessful(), "El developer debería crearse correctamente");
		assertEquals("Developer creado exitosamente.", result.getMessage());
	}

	@Test
	void testCreateDeveloperDuplicate() {
		Project project = new Project(1233L, "Taller", new Date(), 8000.0, StatusEnum.PLANNING, "Proyecto para progra");
		Computer computer = new Computer(1L, "Dell", "Intel i7", 16, "Windows 11", 512);

		developerController.createDeveloper("1233", "Sofía", "Gonzalez", "Java", "5", "3000.0",
				"sofiagonzalez@mail.com", project, computer);

		ResultDTO result = developerController.createDeveloper("1233", "Sofía", "Gonzalez", "Java", "5", "3000.0",
				"sofiagonzalez@mail.com", project, computer);

		assertFalse(result.isSuccessful(), "No debería permitir crear un developer duplicado");
		assertTrue(result.getListMessageError().contains("Ya existe un developer con ese id y nombre."));
	}

	@Test
	void testFindDeveloperById() {
		Project project = new Project(2000L, "Proyecto X", new Date(), 5000.0, StatusEnum.PLANNING, "Proyecto test");
		Computer computer = new Computer(2L, "HP", "AMD Ryzen", 8, "Linux", 256);

		developerController.createDeveloper("2000", "Angelica", "Beltran", "Java", "5", "3000.0", "angelica@mail.com",
				project, computer);

		ResultDTO result = developerController.findDeveloperrByIdAndName("2000", "Angelica");

		assertTrue(result.isSuccessful(), "El developer debería encontrarse");
		assertNotNull(result.getObject(), "El objeto Developer no debería ser null");
	}

	@Test
	void testUpdateDeveloper() {
		Project project = new Project(3000L, "Proyecto Y", new Date(), 6000.0, StatusEnum.PLANNING, "Proyecto test");
		Computer computer = new Computer(3L, "Lenovo", "Intel i5", 8, "Windows 10", 512);

		developerController.createDeveloper("3000", "Ana", "Lopez", "Java", "5", "2800.0", "ana@mail.com", project,
				computer);

		ResultDTO result = developerController.updateDeveloper("3000", "Ana", "Lopez", "Java", "5", "3500.0",
				"ana@mail.com", project, computer);

		assertTrue(result.isSuccessful(), "El developer debería actualizarse correctamente");
		assertEquals("Se actualizó el registro correctamente.", result.getMessage());
	}

	@Test
	void testDeleteDeveloper() {
		Project project = new Project(4000L, "Proyecto Z", new Date(), 7000.0, StatusEnum.PLANNING, "Proyecto test");
		Computer computer = new Computer(4L, "Acer", "Intel i3", 4, "Windows 10", 128);

		developerController.createDeveloper("4000", "Laura", "Martinez", "Java", "2", "2000.0", "laura@mail.com",
				project, computer);

		ResultDTO result = developerController.deleteDeveloper("4000", "Laura");

		assertTrue(result.isSuccessful(), "El developer debería eliminarse correctamente");
		assertEquals("El registro fue eliminado", result.getMessage());
	}
}
