package co.edu.uptc.developer.ui.view;

import javax.swing.JOptionPane;
import java.util.List;

import co.edu.uptc.developer.domain.Developer;
import co.edu.uptc.developer.dto.ResultDTO;
import co.edu.uptc.developer.ui.controller.DeveloperController;

public class DeveloperView {

	private DeveloperController developerController;

	public DeveloperView(DeveloperController developerController) {
		this.developerController = developerController;
	}

	public void menu() {
		int option = -1;
		do {
			String menu = """
					------- MENU DE DESARROLLADORES ----
					[1]. Crear developer
					[2]. Mostrar todos los developers
					[3]. Buscar developer por ID y nombre
					[4]. Actualizar developer
					[5]. Eliminar developer
					[0]. Salir
					""";

			String strOption = JOptionPane.showInputDialog(null, menu, "Menú Developer", JOptionPane.QUESTION_MESSAGE);

			if (strOption == null || !strOption.matches("^\\d$")) {
				continue;
			}

			option = Integer.parseInt(strOption);

			switch (option) {
			case 1 -> createDeveloper();
			case 2 -> listDevelopers();
			case 3 -> findDeveloper();
			case 4 -> updateDeveloper();
			case 5 -> deleteDeveloper();
			}
		} while (option != 0);
	}

	private void createDeveloper() {
		String id = JOptionPane.showInputDialog("Digite el ID del developer:");
		String name = JOptionPane.showInputDialog("Digite el nombre:");
		String lastName = JOptionPane.showInputDialog("Digite el apellido:");
		String mainLanguage = JOptionPane.showInputDialog("Digite el lenguaje principal:");
		String yearsExperience = JOptionPane.showInputDialog("Digite los años de experiencia:");
		String salary = JOptionPane.showInputDialog("Digite el salario:");
		String email = JOptionPane.showInputDialog("Digite el correo electrónico:");

		String idComputer = JOptionPane.showInputDialog("Digite el ID del computador:");
		String brandComputer = JOptionPane.showInputDialog("Digite la marca del computador:");
		String idProject = JOptionPane.showInputDialog("Digite el ID del proyecto:");
		String nameProject = JOptionPane.showInputDialog("Digite el nombre del proyecto:");

		ResultDTO resultDTO = developerController.createDeveloper(id, name, lastName, mainLanguage, yearsExperience,
				salary, email, idComputer, brandComputer, idProject, nameProject);

		if (!resultDTO.isSuccessful()) {
			JOptionPane.showMessageDialog(null, String.join("\n", resultDTO.getListMessageError()), "Errores",
					JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Developer creado exitosamente", "Éxito",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void listDevelopers() {
		List<Developer> developers = developerController.listDevelopers();
		if (developers.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay registros", "Lista de Developers",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		StringBuilder sb = new StringBuilder("Lista de Developers:\n");
		developers.forEach(d -> sb.append(d).append("\n"));
		JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Developers", JOptionPane.INFORMATION_MESSAGE);
	}

	private void findDeveloper() {
		String id = JOptionPane.showInputDialog("Digite el ID del developer:");
		String name = JOptionPane.showInputDialog("Digite el nombre del developer:");

		ResultDTO resultDTO = developerController.findDeveloperrByIdAndName(id, name);

		if (!resultDTO.isSuccessful()) {
			JOptionPane.showMessageDialog(null, String.join("\n", resultDTO.getListMessageError()), "Errores",
					JOptionPane.ERROR_MESSAGE);
		} else if (resultDTO.getObject() == null) {
			JOptionPane.showMessageDialog(null, "El developer no fue encontrado", "Resultado",
					JOptionPane.WARNING_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, resultDTO.getObject().toString(), "Resultado",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void updateDeveloper() {
		String id = JOptionPane.showInputDialog("Digite el ID del developer:");
		String name = JOptionPane.showInputDialog("Digite el nombre:");
		String lastName = JOptionPane.showInputDialog("Digite el apellido:");
		String mainLanguage = JOptionPane.showInputDialog("Digite el lenguaje principal:");
		String yearsExperience = JOptionPane.showInputDialog("Digite los años de experiencia:");
		String salary = JOptionPane.showInputDialog("Digite el salario:");
		String email = JOptionPane.showInputDialog("Digite el correo electrónico:");

		String idComputer = JOptionPane.showInputDialog("Digite el ID del computador:");
		String brandComputer = JOptionPane.showInputDialog("Digite la marca del computador:");
		String idProject = JOptionPane.showInputDialog("Digite el ID del proyecto:");
		String nameProject = JOptionPane.showInputDialog("Digite el nombre del proyecto:");

		ResultDTO resultDTO = developerController.updateDeveloper(id, name, lastName, mainLanguage, yearsExperience,
				salary, email, idComputer, brandComputer, idProject, nameProject);

		if (!resultDTO.isSuccessful()) {
			JOptionPane.showMessageDialog(null, String.join("\n", resultDTO.getListMessageError()), "Errores",
					JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, resultDTO.getMessage(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void deleteDeveloper() {
		String id = JOptionPane.showInputDialog("Digite el ID del developer:");
		String name = JOptionPane.showInputDialog("Digite el nombre del developer:");

		ResultDTO resultDTO = developerController.deleteDeveloper(id, name);

		if (!resultDTO.isSuccessful()) {
			JOptionPane.showMessageDialog(null, String.join("\n", resultDTO.getListMessageError()), "Errores",
					JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, resultDTO.getMessage(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
