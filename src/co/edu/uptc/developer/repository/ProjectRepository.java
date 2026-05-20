package co.edu.uptc.developer.repository;

import java.util.HashMap;

import java.util.Map;

import co.edu.uptc.developer.domain.Project;

public class ProjectRepository {
	private Map <String, Project> mapProjects;

	public ProjectRepository() {
		super();
		this.mapProjects = new HashMap <>();
		
		
}
	public void createProject(String idProject, Project project) {
		this.mapProjects.put(idProject, project);
		
	}
	
	public Map <String, Project> findAll(){
		return this.mapProjects;
	}
	public Project findById(String id) {
		
		return this.mapProjects.get(id);
		
	}
	   public void updateProject(String idProject, Project newProject) {
	        if (this.mapProjects.containsKey(idProject)) {
	            this.mapProjects.put(idProject, newProject);
	        }
	    }
	

	
	public void deleteProject(String id) {
		this.mapProjects.remove(id);
		
	}
	

}
