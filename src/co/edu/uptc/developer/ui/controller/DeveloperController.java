package co.edu.uptc.developer.ui.controller;

import java.util.List;

import co.edu.uptc.developer.service.DeveloperService;
import co.edu.uptc.developer.domain.Computer;
import co.edu.uptc.developer.domain.Developer;
import co.edu.uptc.developer.domain.Project;
import co.edu.uptc.developer.dto.*;

public class DeveloperController {
	private DeveloperService developerService;
	private ComputerController computerController;

	public DeveloperController() {

		this.developerService = new DeveloperService();

	}


	public ResultDTO createDeveloper(String idDeveloper, String name, String lastName, String mainLanguage,
			String yearsExperience, String salary, String email, Project project, Computer computer) {

		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setSuccessful(true);

		validateRequiredFields(idDeveloper, name, mainLanguage, email, project, computer);
		validateNumericField("ID del developer", idDeveloper, resultDTO, true);
		validateAlphanumericField("Nombre", name, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", resultDTO, true);
		validateAlphanumericField("Apellido", lastName, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", resultDTO, false);
		validateAlphanumericField("Lenguaje principal", mainLanguage, "^[a-zA-Z0-9 .\\-]{2,30}$", resultDTO, true);
		validateNumericField("Años de experiencia", yearsExperience, resultDTO, false);
		validateNumericField("Salario", salary, resultDTO, false);
		validateAlphanumericField("Email", email, "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", resultDTO, true);

		if (computer == null) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El computador no puede ser nulo.");
		} else {
			ResultDTO computerValidation = computerController.createComputer(String.valueOf(computer.getIdComputer()),
					computer.getBrand(), computer.getProcessor(), String.valueOf(computer.getRamMemory()),
					computer.getOperationSystem(), String.valueOf(computer.getStorageCapacity()));

			if (!computerValidation.isSuccessful()) {
				resultDTO.setSuccessful(false);
				resultDTO.getListMessageError().addAll(computerValidation.getListMessageError());
			}
		}

		if (project == null) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El proyecto no puede ser nulo.");
		} else {
			validateNumericField("ID del proyecto", String.valueOf(project.getIdProject()), resultDTO, true);
			validateAlphanumericField("Nombre del proyecto", project.getNameProject(), "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$",
					resultDTO, true);
			if (project.getStartDate() == null) {
				resultDTO.setSuccessful(false);
				resultDTO.getListMessageError().add("La fecha de inicio del proyecto no puede ser nula.");
			}
			if (project.getBudget() <= 0) {
				resultDTO.setSuccessful(false);
				resultDTO.getListMessageError().add("El presupuesto debe ser mayor a 0.");
			}
			if (project.getStatus() == null) {
				resultDTO.setSuccessful(false);
				resultDTO.getListMessageError().add("El estado del proyecto no puede ser nulo.");
			}
		}

		if (!resultDTO.isSuccessful()) {
			return resultDTO;
		}

		boolean result = developerService.createDeveloper(new Developer(Long.parseLong(idDeveloper), name, lastName,
				mainLanguage, Integer.parseInt(yearsExperience), Double.parseDouble(salary), email, project, computer));

		if (!result) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("Ya existe un developer con ese id y nombre.");
		} else {
			resultDTO.setMessage("Developer creado exitosamente.");
		}

		return resultDTO;
	}



	private ResultDTO validateRequiredFields(String idDeveloper, String name, String mainLanguage, String email,
			Project project, Computer computer) {
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

		if (computer == null) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El computador no puede ser null");
		} else {
			if (computer.getBrand() == null || computer.getBrand().trim().isEmpty()) {
				resultDTO.setSuccessful(false);
				resultDTO.getListMessageError().add("La marca del computador no puede ser null ni vacía");
			}
			if (computer.getProcessor() == null || computer.getProcessor().trim().isEmpty()) {
				resultDTO.setSuccessful(false);
				resultDTO.getListMessageError().add("El procesador no puede ser null ni vacío");
			}
			if (computer.getOperationSystem() == null || computer.getOperationSystem().trim().isEmpty()) {
				resultDTO.setSuccessful(false);
				resultDTO.getListMessageError().add("El sistema operativo no puede ser null ni vacío");
			}
			if (computer.getStorageCapacity() <= 0) {
				resultDTO.setSuccessful(false);
				resultDTO.getListMessageError().add("La capacidad de almacenamiento debe ser mayor a 0");
			}
		}

		if (project == null) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El proyecto no puede ser null");
		} else {
			if (project.getNameProject() == null || project.getNameProject().trim().isEmpty()) {
				resultDTO.setSuccessful(false);
				resultDTO.getListMessageError().add("El nombre del proyecto no puede ser null ni vacío");
			}
			if (project.getStartDate() == null) {
				resultDTO.setSuccessful(false);
				resultDTO.getListMessageError().add("La fecha de inicio del proyecto no puede ser null");
			}
			if (project.getBudget() <= 0) {
				resultDTO.setSuccessful(false);
				resultDTO.getListMessageError().add("El presupuesto debe ser mayor a 0");
			}
			if (project.getStatus() == null) {
				resultDTO.setSuccessful(false);
				resultDTO.getListMessageError().add("El estado del proyecto no puede ser null");
			}
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

	// CORREGIDO
	public List<Developer> listDevelopers() {
		return developerService.findAll();
	}

	// CORREGIDO

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
			String yearsExperience, String salary, String email, Project project, Computer computer) {
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
		if (lastName == null || lastName.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El apellido no puede ser null ni vacío");
		}
		if (mainLanguage == null || mainLanguage.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El lenguaje principal no puede ser null ni vacío");
		}
		if (email == null || email.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El email no puede ser null ni vacío");
		}

		if (computer == null) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El computador no puede ser null");
		} else {
			if (computer.getBrand() == null || computer.getBrand().trim().isEmpty()) {
				resultDTO.setSuccessful(false);
				resultDTO.getListMessageError().add("La marca del computador no puede ser null ni vacía");
			}
			if (computer.getProcessor() == null || computer.getProcessor().trim().isEmpty()) {
				resultDTO.setSuccessful(false);
				resultDTO.getListMessageError().add("El procesador no puede ser null ni vacío");
			}
			if (computer.getOperationSystem() == null || computer.getOperationSystem().trim().isEmpty()) {
				resultDTO.setSuccessful(false);
				resultDTO.getListMessageError().add("El sistema operativo no puede ser null ni vacío");
			}
			if (computer.getStorageCapacity() <= 0) {
				resultDTO.setSuccessful(false);
				resultDTO.getListMessageError().add("La capacidad de almacenamiento debe ser mayor a 0");
			}
		}

		if (project == null) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El proyecto no puede ser nulo");
		} else {
			if (project.getNameProject() == null || project.getNameProject().trim().isEmpty()) {
				resultDTO.setSuccessful(false);
				resultDTO.getListMessageError().add("El nombre del proyecto no puede ser null ni vacío");
			}
			if (project.getStartDate() == null) {
				resultDTO.setSuccessful(false);
				resultDTO.getListMessageError().add("La fecha de inicio del proyecto no puede ser null");
			}
			if (project.getBudget() <= 0) {
				resultDTO.setSuccessful(false);
				resultDTO.getListMessageError().add("El presupuesto debe ser mayor a 0");
			}
			if (project.getStatus() == null) {
				resultDTO.setSuccessful(false);
				resultDTO.getListMessageError().add("El estado del proyecto no puede ser nulo");
			}
		}

		if (!resultDTO.getListMessageError().isEmpty()) {
			return resultDTO;
		}

		Developer developer = new Developer(Long.parseLong(idDeveloper), name, lastName, mainLanguage,
				Integer.parseInt(yearsExperience), Double.parseDouble(salary), email, project, computer);

		boolean result = developerService.updateDeveloper(developer);
		if (!result) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El developer no fue encontrado para actualizar.");
		} else {
			resultDTO.setSuccessful(true);
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