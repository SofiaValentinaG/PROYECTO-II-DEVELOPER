package co.edu.uptc.developer.domain;

public class Developer {

	private Long idDeveloper;
	private String name;
	private String lastName;
	private String mainLanguage;
	private int yearsExperience;
	private double salary;
	private String email;
	private Project project;
	private Computer computer;

	public Developer(Long idDeveloper, String name, String lastName, String mainLanguage, int yearsExperience, double salary,
			String email, Project project, Computer computer) {
		super();
		this.idDeveloper = idDeveloper;
		this.name = name;
		this.lastName = lastName;
		this.mainLanguage = mainLanguage;
		this.yearsExperience = yearsExperience;
		this.salary = salary;
		this.email = email;
		this.project = project;
		this.computer = computer;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Computer getComputer() {
		return computer;
	}

	public void setComputer(Computer computer) {
		this.computer = computer;
	}

	public Long getIdDeveloper() {
		return idDeveloper;
	}

	public void setIdDeveloper(Long id) {
		this.idDeveloper = id;
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
		sb.append("Email: ").append(email).append("\n");
		sb.append("Computer: ").append(computer).append("\n");
		sb.append("Project: ").append(project).append("\n");

		sb.append("==========================\n");
		return sb.toString();
	}

}
