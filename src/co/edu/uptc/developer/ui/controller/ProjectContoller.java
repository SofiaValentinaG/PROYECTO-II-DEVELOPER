package co.edu.uptc.developer.ui.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import co.edu.uptc.developer.domain.Project;
import co.edu.uptc.developer.dto.ResultDTO;
import co.edu.uptc.developer.enums.StatusEnum;

import co.edu.uptc.developer.service.ProjectService;

public class ProjectContoller {
	private ProjectService projectService;

	public ProjectContoller() {

		this.projectService = new ProjectService();
	}

	public ResultDTO createProject(String idProject, String nameProject, String startDate, String budget,StatusEnum status, String description) throws ParseException {

		ResultDTO resultDTO = this.validateRequiredFields(idProject, nameProject, startDate, budget, status,
				description);

		if (!resultDTO.isSuccessful()) {
			return resultDTO;
		}

		validateNumericField("ID del proyecto", idProject, resultDTO, true);
		validateNumericField("Presupuesto", budget, resultDTO, true);

		validateAlphanumericField("Nombre del Proyecto", nameProject, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", resultDTO, true);

		String regexFecha = "^(?:(?:31\\/(?:0[13578]|1[02]))|(?:29|30)\\/(?:0[13-9]|1[0-2]))\\/(?:19|20)\\d\\d$"
				+ "|^(?:29\\/02\\/(?:(?:19|20)(?:[02468][048]|[13579][26])|2000))$"
				+ "|^(?:0[1-9]|1\\d|2[0-8])\\/(?:0[1-9]|1[0-2])\\/(?:19|20)\\d\\d$";

		if (!startDate.matches(regexFecha)) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("La fecha no tiene el formato correcto (dd/MM/yyyy).");
			return resultDTO;
		}

		if (!resultDTO.isSuccessful()) {
			return resultDTO;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaInicio = sdf.parse(startDate);

		double presupuesto = Double.parseDouble(budget);
		boolean result = projectService.createProject(
				new Project(Long.parseLong(idProject), nameProject, fechaInicio, presupuesto, status, description));

		if (!result) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("Ya existe un proyecto con ese id.");
		} else {
			resultDTO.setMessage("Proyecto creado exitosamente.");
		}

		return resultDTO;
	}

	private ResultDTO validateRequiredFields(String idProject, String nameProject, String startDate, String budget,
			StatusEnum status, String description) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setSuccessful(true);

		if (idProject == null || idProject.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El id del proyecto no puede ser null ni vacío");
		}

		if (nameProject == null || nameProject.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El nombre del proyecto no puede ser null ni vacío");
		}

		if (startDate == null || startDate.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("La fecha de inicio no puede ser null");
		}

		if (budget == null || budget.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El presupuesto no puede ser null ni vacío");
		}

		if (status == null) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El estado del proyecto no puede ser null");
		}

		if (description == null || description.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("La descripción no puede ser null ni vacía");
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

	private ResultDTO validateAlphanumericField(String nameValidation, String field, String pattern,ResultDTO resultDTO, boolean required) {
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

	public List<Project> listComputers() {
		return projectService.findAll();
	}

	public ResultDTO findProjectByIdAndName(String idProject, String nameProject) {
		ResultDTO resultDTO = validateRequiredFieldsForKey(idProject, nameProject);
		if (!resultDTO.isSuccessful()) {
			return resultDTO;
		}
		validateNumericField("idProject", idProject, resultDTO, true);
		validateAlphanumericField("Nombre del Proyecto", nameProject, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", resultDTO, true);
		if (!resultDTO.isSuccessful()) {
			return resultDTO;
		}
		resultDTO.setObject(projectService.findProjectById(Long.parseLong(idProject), nameProject));
		return resultDTO;
	}

	private ResultDTO validateRequiredFieldsForKey(String idProject, String nameProject) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setSuccessful(true);
		if (idProject == null || idProject.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El id del proyecto no puede ser null ni vacío");
		}

		if (nameProject == null || nameProject.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El nombre del proyecto no puede ser null ni vacío");
		}
		return resultDTO;
	}

	public ResultDTO updateProject(String idProject, String nameProject, String startDate, String budget,StatusEnum status, String description) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setSuccessful(true);

		if (idProject == null || idProject.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El id del proyecto no puede ser null ni vacío");
		}
		if (nameProject == null || nameProject.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El nombre del proyecto no puede ser null ni vacío");
		}
		if (startDate == null || startDate.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("La fecha de inicio no puede ser null ni vacía");
		}
		if (budget == null || budget.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El presupuesto no puede ser null ni vacío");
		}
		if (status == null) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El estado del proyecto no puede ser null");
		}
		if (description == null || description.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("La descripción no puede ser null ni vacía");
		}

		if (!resultDTO.getListMessageError().isEmpty()) {
			return resultDTO;
		}

		validateNumericField("ID del proyecto", idProject, resultDTO, true);
		validateNumericField("Presupuesto", budget, resultDTO, true);
		validateAlphanumericField("Nombre del Proyecto", nameProject, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", resultDTO, true);
		validateAlphanumericField("Descripción", description, "^[a-zA-Z0-9ÁÉÍÓÚáéíóúÑñ .\\-]{3,100}$", resultDTO, true);

		if (!resultDTO.isSuccessful()) {
			return resultDTO;
		}

		Long id = Long.parseLong(idProject);
		double presupuesto = Double.parseDouble(budget);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaInicio = null;
		try {
			fechaInicio = sdf.parse(startDate);
		} catch (Exception e) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("La fecha no tiene el formato correcto (dd/MM/yyyy).");
			return resultDTO;
		}

		Project project = new Project(id, nameProject, fechaInicio, presupuesto, status, description);

		boolean result = projectService.updateProject(project);
		if (!result) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El proyecto no fue encontrado para actualizar.");
		} else {
			resultDTO.setSuccessful(true);
			resultDTO.setMessage("Se actualizó el proyecto correctamente.");
		}

		return resultDTO;
	}

	public ResultDTO deleteProject(String idProject, String nameProject) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setSuccessful(true);

		if (idProject == null || idProject.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El id del proyecto no puede ser null ni vacío");
		}
		if (nameProject == null || nameProject.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El nombre del proyecto no puede ser null ni vacío");
		}

		if (!resultDTO.isSuccessful()) {
			return resultDTO;
		}

		validateNumericField("idProject", idProject, resultDTO, true);
		validateAlphanumericField("Validación nombre del proyecto", nameProject, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", resultDTO,
				true);

		if (!resultDTO.isSuccessful()) {
			return resultDTO;
		}

		boolean resultDelete = this.projectService.deleteProjectByIdAndName(Long.parseLong(idProject), nameProject);

		if (!resultDelete) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El proyecto no se pudo eliminar.");
			return resultDTO;
		}

		resultDTO.setMessage("El proyecto fue eliminado correctamente.");
		return resultDTO;
	}

}
