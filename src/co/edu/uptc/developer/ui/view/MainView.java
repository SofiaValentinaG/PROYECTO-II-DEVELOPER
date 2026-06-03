package co.edu.uptc.developer.ui.view;

import java.text.ParseException;
import javax.swing.JOptionPane;

import co.edu.uptc.developer.service.ComputerService;
import co.edu.uptc.developer.service.DeveloperService;
import co.edu.uptc.developer.service.ProjectService;

import co.edu.uptc.developer.ui.controller.ComputerController;
import co.edu.uptc.developer.ui.controller.DeveloperController;
import co.edu.uptc.developer.ui.controller.ProjectContoller;

public class MainView {

    private ComputerService computerService;
    private ProjectService projectService;
    private DeveloperService developerService;

    private ComputerView computerView;
    private ProjectView projectView;
    private DeveloperView developerView;

    public MainView() {
      
        this.computerService = new ComputerService();
        this.projectService = new ProjectService();
        this.developerService = new DeveloperService();

        ComputerController computerController = new ComputerController(computerService);
        ProjectContoller projectController = new ProjectContoller(projectService);
        DeveloperController developerController = new DeveloperController(developerService, projectService, computerService);

      
        this.computerView = new ComputerView(computerController);
        this.projectView = new ProjectView(projectController);
        this.developerView = new DeveloperView(developerController);
    }

    public void runApp() throws ParseException {
        int option = -1;
        do {
            String menu = """
                    ----- MENU PRINCIPAL -----
                    [1]. Administración de la información de Computer
                    [2]. Administración de la información de Project
                    [3]. Administración de la información de Developer
                    [4]. Salir
                    """;

            String strOption = JOptionPane.showInputDialog(null, menu, "Menú Principal", JOptionPane.QUESTION_MESSAGE);

            if (strOption == null || !strOption.matches("^\\d$")) {
                continue;
            }

            option = Integer.parseInt(strOption);

            switch (option) {
                case 1 -> computerView.menu();
                case 2 -> projectView.menu();
                case 3 -> developerView.menu();
                case 4 -> {
                    JOptionPane.showMessageDialog(null, "Saliendo de la aplicación...", "Salir", JOptionPane.INFORMATION_MESSAGE);
                    option = 0;
                }
                default -> JOptionPane.showMessageDialog(null, "Opción incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (option != 0);
    }
}
