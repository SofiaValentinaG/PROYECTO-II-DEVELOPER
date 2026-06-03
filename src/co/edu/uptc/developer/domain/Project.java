package co.edu.uptc.developer.domain;

import co.edu.uptc.developer.enums.*;

import java.util.Date;
//Entidad asociada

public class Project {

	private Long idProject;
	private String nameProject;
	private Date startDate;
	private double budget;
	private StatusEnum status;
	private String description;

	public Project(Long idProject, String nameProject, Date startDate, double budget, StatusEnum status,
			String description) {
		super();
		this.idProject = idProject;
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

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public Long getIdProject() {
		return idProject;
	}

	public void setIdProject(Long idProject) {
		this.idProject = idProject;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n===== Project Info =====\n");
		sb.append("ID: ").append(idProject).append("\n");

		sb.append("Nombre: ").append(nameProject).append("\n");
		sb.append("Fecha de inicio: ").append(startDate).append("\n");
		sb.append("Presupuesto: $").append(budget).append("\n");
		sb.append("Estado: ").append(status).append("\n");
		sb.append("Descripcion: ").append(description).append("\n");
		sb.append("========================\n");
		return sb.toString();
	}

}
