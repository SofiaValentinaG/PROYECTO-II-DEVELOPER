package co.edu.uptc.developer.domain;

public class Developer {
	
	private int idDeveloper;
	private String name;
	private String lastName;
	private String mainLanguage;
	private  int yearsExperience;
	private double salary;
	
	public Developer(int idDeveloper, String name, String lastName, String mainLanguage, int yearsExperience,
			double salary) {
		super();
		this.idDeveloper = idDeveloper;
		this.name = name;
		this.lastName = lastName;
		this.mainLanguage = mainLanguage;
		this.yearsExperience = yearsExperience;
		this.salary = salary;
	}
	
	public int getIdDeveloper() {
		return idDeveloper;
	}
	public void setIdDeveloper(int idDeveloper) {
		this.idDeveloper = idDeveloper;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMainLanguage() {
		return mainLanguage;
	}
	public void setMainLanguage(String mainLanguage) {
		this.mainLanguage = mainLanguage;
	}
	public int getYearsExperience() {
		return yearsExperience;
	}
	public void setYearsExperience(int yearsExperience) {
		this.yearsExperience = yearsExperience;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("\n===== Developer Info =====\n");
	    sb.append("ID: ").append(idDeveloper).append("\n");
	    sb.append("Nombre: ").append(name).append("\n");
	    sb.append("Apellido: ").append(lastName).append("\n");
	    sb.append("Lenguaje principal: ").append(mainLanguage).append("\n");
	    sb.append("Años de experiencia: ").append(yearsExperience).append("\n");
	    sb.append("Salario: $").append(salary).append("\n");
	    sb.append("==========================\n");
	    return sb.toString();
	}

	

}
