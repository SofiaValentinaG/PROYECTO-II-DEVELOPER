package co.edu.uptc.developer.test;

import static org.junit.jupiter.api.Assertions.*;
import co.edu.uptc.developer.service.*;

import java.text.ParseException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import co.edu.uptc.developer.dto.ResultDTO;
import co.edu.uptc.developer.enums.StatusEnum;
import co.edu.uptc.developer.ui.controller.ProjectContoller;

public class ProjectControllerTest {

	private ProjectContoller projectController;
	private ProjectService  projectService;

	@BeforeEach
	void setUp() {
		projectController = new ProjectContoller(projectService);
	}

	@Test
	void testCreateProjectSuccess() throws ParseException {
		ResultDTO result = projectController.createProject("1001", "Proyecto A", "31/05/2026", "5000",
				StatusEnum.PLANNING, "Proyecto de prueba");

		assertTrue(result.isSuccessful(), "El proyecto debería crearse correctamente");
		assertEquals("Proyecto creado exitosamente.", result.getMessage());
	}

	@Test
	void testCreateProjectDuplicate() throws ParseException {
		projectController.createProject("1002", "Proyecto B", "31/05/2026", "6000", StatusEnum.PLANNING,
				"Proyecto duplicado");

		ResultDTO result = projectController.createProject("1002", "Proyecto B", "31/05/2026", "6000",
				StatusEnum.PLANNING, "Proyecto duplicado");

		assertFalse(result.isSuccessful(), "No debería permitir crear un proyecto duplicado");
		assertTrue(result.getListMessageError().contains("Ya existe un proyecto con ese id."));
	}

	@Test
	void testFindProjectByIdAndName() throws ParseException {
		projectController.createProject("1003", "Proyecto C", "31/05/2026", "7000", StatusEnum.PLANNING,
				"Proyecto de búsqueda");

		ResultDTO result = projectController.findProjectByIdAndName("1003", "Proyecto C");

		assertTrue(result.isSuccessful(), "El proyecto debería encontrarse");
		assertNotNull(result.getObject(), "El objeto Project no debería ser null");
	}

	@Test
	void testUpdateProject() throws ParseException {
		projectController.createProject("1004", "Proyecto D", "31/05/2026", "8000", StatusEnum.PLANNING,
				"Proyecto inicial");

		ResultDTO result = projectController.updateProject("1004", "Proyecto D", "31/05/2026", "9000",
				StatusEnum.PLANNING, "Proyecto actualizado");

		assertTrue(result.isSuccessful(), "El proyecto debería actualizarse correctamente");
		assertEquals("Se actualizó el proyecto correctamente.", result.getMessage());
	}

	@Test
	void testDeleteProject() throws ParseException {
		projectController.createProject("1005", "Proyecto E", "31/05/2026", "10000", StatusEnum.PLANNING,
				"Proyecto a eliminar");

		ResultDTO result = projectController.deleteProject("1005", "Proyecto E");

		assertTrue(result.isSuccessful(), "El proyecto debería eliminarse correctamente");
		assertEquals("El proyecto fue eliminado correctamente.", result.getMessage());
	}

	@Test
	void testCreateProjectInvalidDate() throws ParseException {
		ResultDTO result = projectController.createProject("1006", "Proyecto F", "2026-05-31", "5000",
				StatusEnum.PLANNING, "Formato de fecha inválido");

		assertFalse(result.isSuccessful(), "El proyecto no debería crearse con fecha inválida");
		assertTrue(result.getListMessageError().contains("La fecha no tiene el formato correcto (dd/MM/yyyy)."));
	}
}
