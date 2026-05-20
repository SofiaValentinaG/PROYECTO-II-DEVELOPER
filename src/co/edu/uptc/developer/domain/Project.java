package co.edu.uptc.developer.domain;
import co.edu.uptc.developer.enums.*;

import java.util.Date;
//Entidad asociada

public class Project {
	
	private String nameProject;
	private Date startDate;
	private double budget;
	private Status status;
	private String description;
	
	
	

	
	public Project(String nameProject, Date startDate, double budget, Status status, String description) {
		super();
		this.nameProject = nameProject;
		this.startDate = startDate;
		this.budget = budget;
		this.status = status;
		this.description = description;
	}
	
	
	
	
	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public String getNameProject() {
		return nameProject;
	}
	public void setNameProject(String nameProject) {
		this.nameProject = nameProject;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public double getBudget() {
		return budget;
	}
	public void setBudget(double budget) {
		this.budget = budget;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("\n===== Project Info =====\n");
	 
	    sb.append("Nombre: ").append(nameProject).append("\n");
	    sb.append("Fecha de inicio: ").append(startDate).append("\n");
	    sb.append("Presupuesto: $").append(budget).append("\n");
	    sb.append("Estado: ").append(status).append("\n");
	    sb.append("Descripcion: ").append(description).append("\n");
	    sb.append("========================\n");
	    return sb.toString();
	}

	
	

}
