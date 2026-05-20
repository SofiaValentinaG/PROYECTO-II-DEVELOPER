package co.edu.uptc.developer.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import co.edu.uptc.developer.domain.Project;

public class ProjectRepository {
	private List<Project> projectList;

	public ProjectRepository() {
		super();
		this.projectList = new ArrayList <>();
		
		
}
	public void createProject(Project project) {
		
		projectList.add(project);
		
	}
	
	public List<Project> findAll() {
		return this.projectList;
	}
	
	public Project findById(int idProject) {
		for(Project p: this.projectList) {
			if(p.getIdProject()== idProject) {
				return p;
			}
		}
		
		return null;
	
	}
	
	
	public boolean updateProject(Project newProject) {
		 Project oldProject= this.findById(newProject.getIdProject());
		 if(!Objects.isNull(oldProject)) {
			 
				if(Objects.isNull(newProject.getNameProject())) {
					newProject.setNameProject(oldProject.getNameProject());
				}
				if(Objects.isNull(newProject.getBudget())) {
					newProject.setBudget(oldProject.getBudget());
				}
				
				if(Objects.isNull(newProject.getStartDate())) {
					newProject.setStartDate(oldProject.getStartDate());
				}
				if(Objects.isNull(newProject.getStatus())) {
					newProject.setStatus(oldProject.getStatus());
					
				}
				
				
				
				this.projectList.remove(oldProject);
				this.projectList.add(newProject);
				
				return true;
			 
		 }
		 return false;
	}
	
	
	
	 public boolean deleteProject(int idProject) {
	        return this.projectList.removeIf(p -> p.getIdProject() == idProject);
	    }
	
	
	
	

}
