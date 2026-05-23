package co.edu.uptc.developer.repository;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import co.edu.uptc.developer.domain.Project;

public class ProjectRepository {
	private Map<String, Project> mapProjects;

	public ProjectRepository() {
		this.mapProjects = new HashMap<>();
	}

	public void createProject(Project project) {
		mapProjects.put(this.generateKey(project.getIdProject(), project.getNameProject()), project);
	}

	public Project findProjectByIdAndName(Long idProject, String nameProject) {
		return mapProjects.get(this.generateKey(idProject, nameProject));
	}

	public List<Project> findAll() {
		return new ArrayList<>(mapProjects.values());

	}

	public boolean updateProject(Project newProject) {
		String key = this.generateKey(newProject.getIdProject(), newProject.getNameProject());
		if (!mapProjects.containsKey(key)) {
			return false;
		}
		mapProjects.put(key, newProject);
		return true;
	}

	public boolean deleteProjectByIdAndName(Long idProject, String nameProject) {
		return mapProjects.remove(this.generateKey(idProject, nameProject)) != null;
	}

	public boolean existsByIdAndName(Long idProject, String nameProject) {
		return mapProjects.containsKey(this.generateKey(idProject, nameProject));
	}

	private String generateKey(Long idProject, String nameProject) {
		StringBuilder key = new StringBuilder();
		key.append(idProject);

		key.append(nameProject);
		return key.toString();
	}
}
