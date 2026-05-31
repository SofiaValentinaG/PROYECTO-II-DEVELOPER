package co.edu.uptc.developer.ui.view;

import javax.swing.JOptionPane;
import java.util.List;
import java.text.ParseException;
import java.util.Date;

import com.toedter.calendar.JDateChooser;

import co.edu.uptc.developer.domain.Project;
import co.edu.uptc.developer.dto.ResultDTO;
import co.edu.uptc.developer.enums.StatusEnum;
import co.edu.uptc.developer.ui.controller.ProjectContoller;

public class ProjectView {

    private ProjectContoller projectController;

    public ProjectView() {
        this.projectController = new ProjectContoller();
    }

    public void menu() throws ParseException {
        int option = -1;
        do {
            String menu = """
                    ------- MENU DE PROYECTOS ----
                    [1]. Crear proyecto
                    [2]. Mostrar todos los proyectos
                    [3]. Buscar proyecto por ID y nombre
                    [4]. Actualizar proyecto
                    [5]. Eliminar proyecto
                    [0]. Salir
                    """;

            String strOption = JOptionPane.showInputDialog(null, menu, "Menú Project", JOptionPane.QUESTION_MESSAGE);

            if (strOption == null || !strOption.matches("^\\d$")) {
                continue;
            }

            option = Integer.parseInt(strOption);

            switch (option) {
                case 1 -> createProject();
                case 2 -> listProjects();
                case 3 -> findProject();
                case 4 -> updateProject();
                case 5 -> deleteProject();
            }
        } while (option != 0);
    }

    private void createProject() throws ParseException {
        String id = JOptionPane.showInputDialog("Digite el ID del proyecto:");
        String name = JOptionPane.showInputDialog("Digite el nombre del proyecto:");
        String budget = JOptionPane.showInputDialog("Digite el presupuesto:");
        String description = JOptionPane.showInputDialog("Digite la descripción:");

     
        JDateChooser dateChooser = new JDateChooser();
        JOptionPane.showMessageDialog(null, dateChooser, "Seleccione la fecha de inicio del proyecto", JOptionPane.PLAIN_MESSAGE);
        Date fechaInicio = dateChooser.getDate();

   
        StatusEnum status = (StatusEnum) JOptionPane.showInputDialog(
                null,
                "Seleccione el estado del proyecto:",
                "Estado",
                JOptionPane.QUESTION_MESSAGE,
                null,
                StatusEnum.values(),
                StatusEnum.PLANNING
        );

        ResultDTO resultDTO = projectController.createProject(id, name,
                fechaInicio != null ? new java.text.SimpleDateFormat("dd/MM/yyyy").format(fechaInicio) : "",
                budget, status, description);

        if (!resultDTO.isSuccessful()) {
            JOptionPane.showMessageDialog(null, String.join("\n", resultDTO.getListMessageError()), "Errores", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Proyecto creado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void listProjects() {
        List<Project> projects = projectController.listComputers(); 
        if (projects.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay registros", "Lista de Proyectos", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder sb = new StringBuilder("Lista de Proyectos:\n");
        projects.forEach(p -> sb.append(p).append("\n"));
        JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Proyectos", JOptionPane.INFORMATION_MESSAGE);
    }

    private void findProject() {
        String id = JOptionPane.showInputDialog("Digite el ID del proyecto:");
        String name = JOptionPane.showInputDialog("Digite el nombre del proyecto:");

        ResultDTO resultDTO = projectController.findProjectByIdAndName(id, name);

        if (!resultDTO.isSuccessful()) {
            JOptionPane.showMessageDialog(null, String.join("\n", resultDTO.getListMessageError()), "Errores", JOptionPane.ERROR_MESSAGE);
        } else if (resultDTO.getObject() == null) {
            JOptionPane.showMessageDialog(null, "El proyecto no fue encontrado", "Resultado", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, resultDTO.getObject().toString(), "Resultado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void updateProject() {
        String id = JOptionPane.showInputDialog("Digite el ID del proyecto:");
        String name = JOptionPane.showInputDialog("Digite el nombre del proyecto:");
        String budget = JOptionPane.showInputDialog("Digite el presupuesto:");
        String description = JOptionPane.showInputDialog("Digite la descripción:");

     
        JDateChooser dateChooser = new JDateChooser();
        JOptionPane.showMessageDialog(null, dateChooser, "Seleccione la nueva fecha de inicio del proyecto", JOptionPane.PLAIN_MESSAGE);
        Date fechaInicio = dateChooser.getDate();

    
        StatusEnum status = (StatusEnum) JOptionPane.showInputDialog(
                null,
                "Seleccione el nuevo estado del proyecto:",
                "Estado",
                JOptionPane.QUESTION_MESSAGE,
                null,
                StatusEnum.values(),
                StatusEnum.PLANNING
        );

        ResultDTO resultDTO = projectController.updateProject(id, name,
                fechaInicio != null ? new java.text.SimpleDateFormat("dd/MM/yyyy").format(fechaInicio) : "",
                budget, status, description);

        if (!resultDTO.isSuccessful()) {
            JOptionPane.showMessageDialog(null, String.join("\n", resultDTO.getListMessageError()), "Errores", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, resultDTO.getMessage(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void deleteProject() {
        String id = JOptionPane.showInputDialog("Digite el ID del proyecto:");
        String name = JOptionPane.showInputDialog("Digite el nombre del proyecto:");

        ResultDTO resultDTO = projectController.deleteProject(id, name);

        if (!resultDTO.isSuccessful()) {
            JOptionPane.showMessageDialog(null, String.join("\n", resultDTO.getListMessageError()), "Errores", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, resultDTO.getMessage(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}


