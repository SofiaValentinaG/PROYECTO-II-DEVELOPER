package co.edu.uptc.developer.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import co.edu.uptc.developer.ui.controller.DeveloperController;
import co.edu.uptc.developer.service.ComputerService;
import co.edu.uptc.developer.service.DeveloperService;
import co.edu.uptc.developer.service.ProjectService;
import co.edu.uptc.developer.domain.Computer;
import co.edu.uptc.developer.domain.Project;
import co.edu.uptc.developer.dto.ResultDTO;
import co.edu.uptc.developer.enums.StatusEnum;


import java.util.Date;

public class DeveloperControllerTest {

	private DeveloperController developerController;
	private ComputerService computerService;
	private ProjectService projectService;
	private DeveloperService developerService;

	@BeforeEach
	void setUp() {
	
		developerController = new DeveloperController(developerService, projectService, computerService);
		computerService = new ComputerService();
		projectService = new ProjectService();

		Computer computer = new Computer(1L, "Dell", "Intel i7", 16, "Windows", 512);
		computerService.createComputer(computer);

		Project project = new Project(1L, "SistemaVentas", new Date(), 10000, StatusEnum.PLANNING, "proyecto final");
		projectService.createProject(project);
	}

	@Test
	void testCreateDeveloper() {
		ResultDTO result = developerController.createDeveloper("100", "Sofia", "Valentina", "Java", "3", "2500",
				"sofia@example.com", "1", "Dell", "1", "SistemaVentas");

		assertTrue(result.isSuccessful(), "El developer debería crearse correctamente");
	}

	@Test
	void testUpdateDeveloper() {

		developerController.createDeveloper("101", "Carlos", "Perez", "C++", "2", "1800", "carlos@example.com", "1",
				"Dell", "1", "SistemaVentas");

		ResultDTO result = developerController.updateDeveloper("101", "Carlos", "Perez", "Python", "3", "2200",
				"carlos@example.com", "1", "Dell", "1", "SistemaVentas");

		assertTrue(result.isSuccessful(), "El developer debería actualizarse correctamente");
	}

	@Test
	void testDeleteDeveloper() {
		developerController.createDeveloper("102", "Ana", "Lopez", "JavaScript", "1", "1500", "ana@example.com", "1",
				"Dell", "1", "SistemaVentas");

		ResultDTO result = developerController.deleteDeveloper("102", "Ana");
		assertTrue(result.isSuccessful(), "El developer debería eliminarse correctamente");
	}

	@Test
	void testFindDeveloperByIdAndName() {
		developerController.createDeveloper("103", "Luis", "Martinez", "Go", "4", "3000", "luis@example.com", "1",
				"Dell", "1", "SistemaVentas");

		ResultDTO result = developerController.findDeveloperrByIdAndName("103", "Luis");
		assertTrue(result.isSuccessful(), "El developer debería encontrarse por ID y nombre");
		assertNotNull(result.getObject(), "El objeto developer no debería ser null");
	}
}
