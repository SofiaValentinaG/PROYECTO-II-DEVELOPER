package co.edu.uptc.developer.ui.controller;

import java.util.List;

import co.edu.uptc.developer.service.ComputerService;
import co.edu.uptc.developer.service.DeveloperService;
import co.edu.uptc.developer.service.ProjectService;
import co.edu.uptc.developer.domain.Computer;
import co.edu.uptc.developer.domain.Developer;
import co.edu.uptc.developer.domain.Project;
import co.edu.uptc.developer.dto.*;

public class DeveloperController {
	private DeveloperService developerService;
	private ProjectService projectService;
	private ComputerService computerService;

	 public DeveloperController(DeveloperService developerService, ProjectService projectService, ComputerService computerService) {
	        this.developerService = developerService;
	        this.projectService = projectService;
	        this.computerService = computerService;
	    }

	public ResultDTO createDeveloper(String idDeveloper, String name, String lastName, String mainLanguage,
			String yearsExperience, String salary, String email, String idComputer, String brandComputer,
			String idProject, String nameProject) {

		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setSuccessful(true);
		validateRequiredFields(idDeveloper,  name, mainLanguage, email, idComputer,  brandComputer,idProject, nameProject);

		validateNumericField("ID del developer", idDeveloper, resultDTO, true);
		validateAlphanumericField("Nombre", name, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", resultDTO, true);
		validateAlphanumericField("Apellido", lastName, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", resultDTO, false);
		validateAlphanumericField("Lenguaje principal", mainLanguage, "^[a-zA-Z0-9 .\\-]{2,30}$", resultDTO, true);
		validateNumericField("Años de experiencia", yearsExperience, resultDTO, false);
		validateNumericField("Salario", salary, resultDTO, false);
		validateAlphanumericField("Email", email, "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", resultDTO, true);

		Computer computer = computerService.findComputerById(Long.parseLong(idComputer), brandComputer);
		if (computer == null) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("No existe un computador con ese ID y marca.");
		}

		Project project = projectService.findProjectById(Long.parseLong(idProject), nameProject);
		if (project == null) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("No existe un proyecto con ese ID y nombre.");
		}

		if (!resultDTO.isSuccessful()) {
			return resultDTO;
		}

		Developer developer = new Developer(Long.parseLong(idDeveloper), name, lastName, mainLanguage,
				Integer.parseInt(yearsExperience), Double.parseDouble(salary), email, project, computer);

		boolean result = developerService.createDeveloper(developer);

		if (!result) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("Ya existe un developer con ese id y nombre.");
		} else {
			resultDTO.setMessage("Developer creado exitosamente.");
		}

		return resultDTO;
	}

	private ResultDTO validateRequiredFields(String idDeveloper, String name, String mainLanguage, String email,
			String idProject, String nameProject, String idComputer, String brand) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setSuccessful(true);

		if (idDeveloper == null || idDeveloper.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El id del desarrollador no puede ser null ni vacío");
		}

		if (name == null || name.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El nombre no puede ser null ni vacío");
		}

		if (mainLanguage == null || mainLanguage.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El lenguaje principal no puede ser null ni vacío");
		}

		if (email == null || email.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El email no puede ser null ni vacío");
		}
		if (idProject == null || idProject.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El id del proyecto no puede ser null ni vacío");
		}

		if (nameProject == null || nameProject.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El nombre del proyecto no puede ser null ni vacío");
		}
		if (idComputer == null || idComputer.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El id del computador no puede ser null ni vacío");
		}

		if (brand == null || brand.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("la marca del computador no puede ser null ni vacío");
		}


		

		
		

		return resultDTO;
	}

	private ResultDTO validateNumericField(String fieldName, String fieldValue, ResultDTO resultDTO, boolean required) {
		if ((!required) && (fieldValue == null || fieldValue.trim().isBlank())) {
			return resultDTO;
		}
		if (fieldValue == null || !fieldValue.matches("\\d+")) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El campo " + fieldName + " debe ser numérico.");
		}
		return resultDTO;
	}

	private ResultDTO validateAlphanumericField(String nameValidation, String field, String pattern,
			ResultDTO resultDTO, boolean required) {
		boolean result = field.matches(pattern);
		if ((!required) && (field == null || field.trim().isBlank())) {
			return resultDTO;
		}
		if (!result) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("Falló la validación denominada:  " + nameValidation);
		}
		return resultDTO;
	}


	public List<Developer> listDevelopers() {
		return developerService.findAll();
	}



	public ResultDTO findDeveloperrByIdAndName(String idDeveloper, String name) {
		ResultDTO resultDTO = validateRequiredFieldsForKey(idDeveloper, name);
		if (!resultDTO.isSuccessful()) {
			return resultDTO;
		}
		validateNumericField("idDeveloper", idDeveloper, resultDTO, true);
		validateAlphanumericField("Validacion name", name, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", resultDTO, true);
		if (!resultDTO.isSuccessful()) {
			return resultDTO;
		}
		resultDTO.setObject(developerService.findDeveloperById(Long.parseLong(idDeveloper), name));
		return resultDTO;
	}

	private ResultDTO validateRequiredFieldsForKey(String idDeveloper, String name) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setSuccessful(true);
		if (idDeveloper == null || idDeveloper.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El id del developer no puede ser null ni vacío");
		}

		if (name == null || name.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("el nombre del desarrollador no puede ser null ni vacío");
		}
		return resultDTO;
	}

	public ResultDTO updateDeveloper(String idDeveloper, String name, String lastName, String mainLanguage,
			String yearsExperience, String salary, String email, String idComputer, String brandComputer,
			String idProject, String nameProject) {

		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setSuccessful(true);

		validateNumericField("ID del developer", idDeveloper, resultDTO, true);
		validateAlphanumericField("Nombre", name, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", resultDTO, true);
		validateAlphanumericField("Apellido", lastName, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", resultDTO, false);
		validateAlphanumericField("Lenguaje principal", mainLanguage, "^[a-zA-Z0-9 .\\-]{2,30}$", resultDTO, true);
		validateNumericField("Años de experiencia", yearsExperience, resultDTO, false);
		validateNumericField("Salario", salary, resultDTO, false);
		validateAlphanumericField("Email", email, "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", resultDTO, true);

		Computer computer = computerService.findComputerById(Long.parseLong(idComputer), brandComputer);
		if (computer == null) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("No existe un computador con ese ID y marca.");
		}

		Project project = projectService.findProjectById(Long.parseLong(idProject), nameProject);
		if (project == null) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("No existe un proyecto con ese ID y nombre.");
		}

		if (!resultDTO.isSuccessful()) {
			return resultDTO;
		}

		Developer developer = new Developer(Long.parseLong(idDeveloper), name, lastName, mainLanguage,
				Integer.parseInt(yearsExperience), Double.parseDouble(salary), email, project, computer);

		boolean result = developerService.updateDeveloper(developer);

		if (!result) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El developer no fue encontrado para actualizar.");
		} else {
			resultDTO.setMessage("Se actualizó el registro correctamente.");
		}

		return resultDTO;
	}

	public ResultDTO deleteDeveloper(String idDeveloper, String name) {
		ResultDTO resultDTO = validateRequiredFieldsForKey(idDeveloper, name);
		if (!resultDTO.isSuccessful()) {
			return resultDTO;
		}
		validateNumericField("idDeveloper", idDeveloper, resultDTO, true);
		validateAlphanumericField("Validacion nombre del developer", name, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", resultDTO, true);
		if (!resultDTO.isSuccessful()) {
			return resultDTO;
		}
		boolean resultDelete = this.developerService.deleteDveloperByIdAndName(Long.parseLong(idDeveloper), name);
		if (!resultDelete) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El registro no se pudo eliminar");
			return resultDTO;
		}
		resultDTO.setMessage("El registro fue eliminado");
		return resultDTO;
	}

}