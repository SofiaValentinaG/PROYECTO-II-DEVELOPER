package co.edu.uptc.developer.ui.view;

import javax.swing.JOptionPane;
import java.util.List;

import co.edu.uptc.developer.domain.Computer;
import co.edu.uptc.developer.dto.ResultDTO;
import co.edu.uptc.developer.ui.controller.ComputerController;

public class ComputerView {

	private ComputerController computerController;

	public ComputerView() {
		this.computerController = new ComputerController();
	}

	public void menu() {
		int option = -1;
		do {
			String menu = """
					------- MENU DE COMPUTADORES ----
					[1]. Crear computador
					[2]. Mostrar todos los computadores
					[3]. Buscar computador por ID y marca
					[4]. Actualizar computador
					[5]. Eliminar computador
					[0]. Salir
					""";

			String strOption = JOptionPane.showInputDialog(null, menu, "Menú Principal", JOptionPane.QUESTION_MESSAGE);

			if (strOption == null || !strOption.matches("^\\d$")) {
				continue;
			}

			option = Integer.parseInt(strOption);

			switch (option) {
			case 1 -> createComputer();
			case 2 -> listComputers();
			case 3 -> findComputer();
			case 4 -> updateComputer();
			case 5 -> deleteComputer();
			}
		} while (option != 0);
	}

	private void createComputer() {
		String id = JOptionPane.showInputDialog("Digite el ID (numérico):");
		String brand = JOptionPane.showInputDialog("Digite la marca:");
		String processor = JOptionPane.showInputDialog("Digite el procesador:");
		String ramMemory = JOptionPane.showInputDialog("Digite la memoria RAM:");
		String operationSystem = JOptionPane.showInputDialog("Digite el sistema operativo:");
		String storageCapacity = JOptionPane.showInputDialog("Digite la capacidad de almacenamiento:");

		ResultDTO resultDTO = computerController.createComputer(id, brand, processor, ramMemory, operationSystem,
				storageCapacity);

		if (!resultDTO.isSuccessful()) {
			JOptionPane.showMessageDialog(null, String.join("\n", resultDTO.getListMessageError()), "Errores",
					JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "El computador fue creado exitosamente", "Éxito",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void listComputers() {
		List<Computer> computers = computerController.listComputers();
		if (computers.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay registros", "Lista de Computadores",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		StringBuilder sb = new StringBuilder("Lista de Computadores:\n");
		computers.forEach(c -> sb.append(c).append("\n"));
		JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Computadores", JOptionPane.INFORMATION_MESSAGE);
	}

	private void findComputer() {
		String id = JOptionPane.showInputDialog("Digite el ID del computador:");
		String brand = JOptionPane.showInputDialog("Digite la marca del computador:");

		ResultDTO resultDTO = computerController.findComputerByIdAndBrand(id, brand);

		if (!resultDTO.isSuccessful()) {
			JOptionPane.showMessageDialog(null, String.join("\n", resultDTO.getListMessageError()), "Errores",
					JOptionPane.ERROR_MESSAGE);
		} else if (resultDTO.getObject() == null) {
			JOptionPane.showMessageDialog(null, "El computador no fue encontrado", "Resultado",
					JOptionPane.WARNING_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, resultDTO.getObject().toString(), "Resultado",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void updateComputer() {
		String id = JOptionPane.showInputDialog("Digite el ID del computador:");
		String brand = JOptionPane.showInputDialog("Digite la marca:");
		String processor = JOptionPane.showInputDialog("Digite el procesador:");
		String ramMemory = JOptionPane.showInputDialog("Digite la memoria RAM:");
		String operationSystem = JOptionPane.showInputDialog("Digite el sistema operativo:");
		String storageCapacity = JOptionPane.showInputDialog("Digite la capacidad de almacenamiento:");

		ResultDTO resultDTO = computerController.updateComputer(id, brand, processor, ramMemory, operationSystem,
				storageCapacity);

		if (!resultDTO.isSuccessful()) {
			JOptionPane.showMessageDialog(null, String.join("\n", resultDTO.getListMessageError()), "Errores",
					JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, resultDTO.getMessage(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void deleteComputer() {
		String id = JOptionPane.showInputDialog("Digite el ID del computador:");
		String brand = JOptionPane.showInputDialog("Digite la marca del computador:");

		ResultDTO resultDTO = computerController.deleteComputer(id, brand);

		if (!resultDTO.isSuccessful()) {
			JOptionPane.showMessageDialog(null, String.join("\n", resultDTO.getListMessageError()), "Errores",
					JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, resultDTO.getMessage(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
