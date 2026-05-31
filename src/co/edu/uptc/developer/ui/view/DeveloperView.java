package co.edu.uptc.developer.ui.view;

import javax.swing.JOptionPane;
import java.util.List;
import java.util.Date;

import com.toedter.calendar.JDateChooser;

import co.edu.uptc.developer.domain.Developer;
import co.edu.uptc.developer.domain.Computer;
import co.edu.uptc.developer.domain.Project;
import co.edu.uptc.developer.dto.ResultDTO;
import co.edu.uptc.developer.enums.StatusEnum;
import co.edu.uptc.developer.ui.controller.DeveloperController;

public class DeveloperView {

	private DeveloperController developerController;

	public DeveloperView() {
		this.developerController = new DeveloperController();
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

		JDateChooser dateChooser = new JDateChooser();
		JOptionPane.showMessageDialog(null, dateChooser, "Seleccione la fecha de inicio del proyecto",
				JOptionPane.PLAIN_MESSAGE);
		Date fechaInicio = dateChooser.getDate();

		Project project = new Project(1L, "Proyecto X", fechaInicio, 5000.0, StatusEnum.PLANNING, "Proyecto demo");
		Computer computer = new Computer(1L, "Dell", "Intel i7", 16, "Windows 11", 512);

		ResultDTO resultDTO = developerController.createDeveloper(id, name, lastName, mainLanguage, yearsExperience,
				salary, email, project, computer);

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

	
		JDateChooser dateChooser = new JDateChooser();
		JOptionPane.showMessageDialog(null, dateChooser, "Seleccione la nueva fecha de inicio del proyecto",
				JOptionPane.PLAIN_MESSAGE);
		Date fechaInicio = dateChooser.getDate();

		Project project = new Project(1L, "Proyecto Y", fechaInicio, 6000.0, StatusEnum.PLANNING, "Proyecto demo");
		Computer computer = new Computer(2L, "HP", "AMD Ryzen", 8, "Linux", 256);

		ResultDTO resultDTO = developerController.updateDeveloper(id, name, lastName, mainLanguage, yearsExperience,
				salary, email, project, computer);

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
