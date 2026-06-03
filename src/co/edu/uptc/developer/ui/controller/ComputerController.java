package co.edu.uptc.developer.ui.controller;

import java.util.List;

import co.edu.uptc.developer.service.ComputerService;
import co.edu.uptc.developer.domain.Computer;
import co.edu.uptc.developer.dto.*;

public class ComputerController {
	private ComputerService computerService;

	public ComputerController() {

		this.computerService = new ComputerService();
	}

	public ResultDTO createComputer(String idComputer, String brand, String processor, String ramMemory,
			String operationSystem, String storageCapacity) {

		ResultDTO resultDTO = this.validateRequiredFields(idComputer, brand, processor, ramMemory, operationSystem,
				storageCapacity);

		if (!resultDTO.isSuccessful()) {
			return resultDTO;
		}

		validateNumericField("ID del computador", idComputer, resultDTO, true);
		validateNumericField("Memoria RAM", ramMemory, resultDTO, true);
		validateNumericField("Capacidad de almacenamiento", storageCapacity, resultDTO, true);

		validateAlphanumericField("Marca del computador", brand, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", resultDTO, true);
		validateAlphanumericField("Procesador", processor, "^[a-zA-Z0-9 .\\-]{3,50}$", resultDTO, true);
		validateAlphanumericField("Sistema Operativo", operationSystem, "^[a-zA-Z0-9 .\\-]{3,40}$", resultDTO, true);

		if (!resultDTO.isSuccessful()) {
			return resultDTO;
		}
		boolean result = computerService.createComputer(new Computer(Long.parseLong(idComputer), brand, processor,
				Integer.parseInt(ramMemory), operationSystem, Integer.parseInt(storageCapacity)));

		if (!result) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("Ya existe un computador con ese id");
		}

		return resultDTO;
	}

	private ResultDTO validateRequiredFields(String idComputer, String brand, String processor, String ramMemory,
			String operationSystem, String storageCapacity) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setSuccessful(true);

		if (idComputer == null || idComputer.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El id del Computador no puede ser null ni vacío");
		}

		if (brand == null || brand.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("La marca del computador no puede ser null ni vacío");
		}

		if (processor == null || processor.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El procesador no puede ser null ni vacío");
		}

		if (ramMemory == null || ramMemory.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("La memoria Ram no puede ser null ni vacío");
		}

		if (operationSystem == null || operationSystem.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El sistema operativo no puede ser null ni vacío");
		}

		if (storageCapacity == null || storageCapacity.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("La capacidad de almacenamiento no puede ser null ni vacío");
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

	public List<Computer> listComputers() {
		return computerService.findAll();
	}

	public ResultDTO findComputerByIdAndBrand(String idComputer, String brand) {
		ResultDTO resultDTO = validateRequiredFieldsForKey(idComputer, brand);
		if (!resultDTO.isSuccessful()) {
			return resultDTO;
		}
		validateNumericField("idComputer", idComputer, resultDTO, true);
		validateAlphanumericField("Validacion Marca", brand, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", resultDTO, true);
		if (!resultDTO.isSuccessful()) {
			return resultDTO;
		}
		resultDTO.setObject(computerService.findComputerById(Long.parseLong(idComputer), brand));
		return resultDTO;
	}

	private ResultDTO validateRequiredFieldsForKey(String idComputer, String brand) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setSuccessful(true);
		if (idComputer == null || idComputer.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El id del computador no puede ser null ni vacío");
		}

		if (brand == null || brand.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("La marca del computador no puede ser null ni vacío");
		}
		return resultDTO;
	}

	public ResultDTO updateComputer(String idComputer, String brand, String processor, String ramMemory,
			String operationSystem, String storageCapacity) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setSuccessful(true);

		if (idComputer == null || idComputer.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El id del Computador no puede ser null ni vacío");
		}

		if (brand == null || brand.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("La marca del computador no puede ser null ni vacío");
		}

		if (processor == null || processor.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El procesador no puede ser null ni vacío");
		}

		if (ramMemory == null || ramMemory.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("La memoria Ram no puede ser null ni vacío");
		}

		if (operationSystem == null || operationSystem.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El sistema operativo no puede ser null ni vacío");
		}

		if (storageCapacity == null || storageCapacity.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("La capacidad de almacenamiento no puede ser null ni vacío");
		}

		if (!resultDTO.getListMessageError().isEmpty()) {
			return resultDTO;
		}

		boolean result = computerService.updateComputer(new Computer(Long.parseLong(idComputer), brand, processor,
				Integer.parseInt(ramMemory), operationSystem, Integer.parseInt(storageCapacity)));
		if (!result) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El computador no fue encontrada.");
		} else {
			resultDTO.setSuccessful(true);
			resultDTO.setMessage("Se actualizó el registro");
		}

		return resultDTO;
	}

	public ResultDTO deleteComputer(String idComputer, String brand) {
		ResultDTO resultDTO = validateRequiredFieldsForKey(idComputer, brand);
		if (!resultDTO.isSuccessful()) {
			return resultDTO;
		}
		validateNumericField("idComputer", idComputer, resultDTO, true);
		validateAlphanumericField("Validacion marca del computador", brand, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", resultDTO,
				true);
		if (!resultDTO.isSuccessful()) {
			return resultDTO;
		}
		boolean resultDelete = this.computerService.deleteComputerByIdAndBrand(Long.parseLong(idComputer), brand);
		if (!resultDelete) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El registro no se pudo eliminar");
			return resultDTO;
		}
		resultDTO.setMessage("El registro fue eliminado");
		return resultDTO;
	}

}
