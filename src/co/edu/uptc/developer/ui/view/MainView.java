package co.edu.uptc.developer.ui.view;

import java.text.ParseException;

import javax.swing.JOptionPane;

public class MainView {

    private ComputerView computerView;
    private DeveloperView developerView;
    private ProjectView projectView;

    public MainView() {
        this.computerView = new ComputerView();
        this.developerView = new DeveloperView();
        this.projectView = new ProjectView();
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
