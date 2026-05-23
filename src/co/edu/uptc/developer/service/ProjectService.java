package co.edu.uptc.developer.service;

import java.util.List;
import java.util.Objects;

import co.edu.uptc.developer.domain.Project;

import co.edu.uptc.developer.repository.ProjectRepository;

public class ProjectService {
	private ProjectRepository projectRepository;

	public ProjectService() {
		super();
		this.projectRepository = new ProjectRepository();
	}

	public boolean createDeveloper(Project project) {
		if (projectRepository.existsByIdAndName(project.getIdProject(), project.getNameProject())) {
			return false;
		}
		projectRepository.createProject(project);
		return true;
	}

	public Project findDeveloperById(Long id, String nameProject) {
		return projectRepository.findProjectByIdAndName(id, nameProject);
	}

	public List<Project> findAll() {
		return projectRepository.findAll();
	}

	public boolean updateProject(Project project) {

		Project projectAux = this.projectRepository.findProjectByIdAndName(project.getIdProject(),
				project.getNameProject());

		if (Objects.isNull(projectAux)) {
			return false;
		}

		if (Objects.isNull(project.getNameProject()) || project.getNameProject().isBlank()) {
			project.setNameProject(projectAux.getNameProject());
		}
		if (Objects.isNull(project.getDescription()) || project.getDescription().isBlank()) {
			project.setDescription(projectAux.getDescription());
		}
		if (Objects.isNull(project.getStatus())) {
			project.setStatus(projectAux.getStatus());
		}
		if (project.getBudget() <= 0) {
			project.setBudget(projectAux.getBudget());
		}
		if (Objects.isNull(project.getStartDate())) {
			project.setStartDate(projectAux.getStartDate());
		}

		this.projectRepository.updateProject(project);
		return true;
	}

	public boolean deleteprojectByIdAndName(Long idProject, String nameProject) {
		return projectRepository.deleteProjectByIdAndName(idProject, nameProject);
	}
}
