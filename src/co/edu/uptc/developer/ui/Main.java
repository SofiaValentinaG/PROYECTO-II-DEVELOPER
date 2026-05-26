package co.edu.uptc.developer.ui;

import java.util.Scanner;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import co.edu.uptc.developer.domain.Computer;
import co.edu.uptc.developer.domain.Developer;
import co.edu.uptc.developer.domain.Project;
import co.edu.uptc.developer.enums.Status;
import co.edu.uptc.developer.service.ComputerService;
import co.edu.uptc.developer.service.DeveloperService;
import co.edu.uptc.developer.service.ProjectService;

public class Main {

	static Scanner sc = new Scanner(System.in);
	static DeveloperService developerService = new DeveloperService();
	static ProjectService projectService = new ProjectService();
	static ComputerService computerService = new ComputerService();

	public static void main(String[] args) {
		Main app = new Main();
		app.ejecutar();
	}
 
	public void ejecutar() {
		int opcion;
		do {
			System.out.println("\n--- MENU PRINCIPAL ---");
			System.out.println("1. Developers");
			System.out.println("2. Proyectos");
			System.out.println("3. Computadores");
			System.out.println("0. Salir");
			System.out.print("Opcion: ");
			opcion = leerInt();
 
			switch (opcion) {
				case 1:
					menuDeveloper();
				case 2:
					menuProject();
				case 3: 
					menuComputer();
				case 0:
					System.out.println("Hasta luego 	");
				default:
					System.out.println("Opcion no valida");
			}
		} while (opcion != 0);
	}



	public static void menuDeveloper() {
		int opcion;
		do {
			System.out.println("\n--- MENU DEVELOPER ---");
			System.out.println("1. Crear");
			System.out.println("2. Listar todos");
			System.out.println("3. Buscar por ID");
			System.out.println("4. Actualizar");
			System.out.println("5. Eliminar");
			System.out.println("0. Volver");
			System.out.print("Opcion: ");
			opcion = leerInt();

			switch (opcion) {
				case 1:
					crearDeveloper();
				case 2:
					listarDevelopers();
				case 3:
					buscarDeveloper();
				case 4:
					actualizarDeveloper();
				case 5:
					eliminarDeveloper();
				case 0: 
					System.out.println("Volviendo...");
				
				default:
					System.out.println("Opcion no valida");
			}
		} while (opcion != 0);
	}

	public static void crearDeveloper() {
		System.out.println("\n-- Crear Developer --");
		System.out.print("ID: ");
		Long id = leerLong();
		System.out.print("Nombre: ");
		String nombre = sc.nextLine();
		System.out.print("Apellido: ");
		String apellido = sc.nextLine();
		System.out.print("Lenguaje principal: ");
		String lenguaje =sc.nextLine();
		System.out.print("Anos de experiencia: ");
		int anos = leerInt();
		System.out.print("Salario: ");
		double salario = leerDouble();
		System.out.print("Email: ");
		String email = sc.nextLine();

		Developer developer = new Developer(id, nombre, apellido, lenguaje, anos, salario, email, null, null);
		boolean resultado = developerService.createDeveloper(developer);
		if (resultado) {
			System.out.println("Developer creado!");
		} else {
			System.out.println("Ya existe un developer con ese ID y nombre");
		}
	}

	public static void listarDevelopers() {
		System.out.println("\n-- Lista de Developers --");
		List<Developer> lista = developerService.findAll();
		if (lista.isEmpty()) {
			System.out.println("No hay developers registrados");
		} else {
			for (Developer d : lista) {
				System.out.println(d);
			}
		}
	}

	public static void buscarDeveloper() {
		System.out.println("\n-- Buscar Developer --");
		System.out.print("ID: ");
		Long id = leerLong();
		System.out.print("Nombre: ");
		String nombre = sc.nextLine();
		Developer developer = developerService.findDeveloperById(id, nombre);
		if (developer != null) {
			System.out.println(developer);
		} else {
			System.out.println("No se encontro el developer");
		}
	}

	public static void actualizarDeveloper() {
		System.out.println("\n-- Actualizar Developer --");
		System.out.print("ID: ");
		Long id = leerLong();
		System.out.print("Nombre actual: ");
		String nombre = sc.nextLine();
		System.out.print("Nuevo apellido: ");
		String apellido = sc.nextLine();
		System.out.print("Nuevo lenguaje: ");
		String lenguaje = sc.nextLine();
		System.out.print("Nuevos anos de experiencia (0 = sin cambio): ");
		int anos = leerInt();
		System.out.print("Nuevo salario (0 = sin cambio): ");
		double salario = leerDouble();
		System.out.print("Nuevo email: ");
		String email = sc.nextLine();

		Developer developer = new Developer(id, nombre, apellido, lenguaje, anos, salario, email, null, null);
		boolean resultado = developerService.updateDeveloper(developer);
		if (resultado) {
			System.out.println("Developer actualizado!");
		} else {
			System.out.println("No se encontro el developer");
		}
	}

	public static void eliminarDeveloper() {
		System.out.println("\n-- Eliminar Developer --");
		System.out.print("ID: ");
		Long id = leerLong();
		System.out.print("Nombre: ");
		String nombre = sc.nextLine();
		boolean resultado = developerService.deleteDeveloperByIdAndName(id, nombre);
		if (resultado) {
			System.out.println("Developer eliminado!");
		} else {
			System.out.println("No se encontro el developer");
		}
	}



	public static void menuProject() {
		int opcion;
		do {
			System.out.println("\n--- MENU PROYECTO ---");
			System.out.println("1. Crear");
			System.out.println("2. Listar todos");
			System.out.println("3. Buscar por ID");
			System.out.println("4. Actualizar");
			System.out.println("5. Eliminar");
			System.out.println("0. Volver");
			System.out.print("Opcion: ");
			opcion = leerInt();

			switch (opcion) {
				case 1:
					crearProject();
				case 2:
					listarProjects();
				case 3:
					buscarProject();
				case 4:
					actualizarProject();
				case 5:
					eliminarProject();
				case 0:
					System.out.println("Volviendo...");
				default:
					System.out.println("Opcion no valida");
			}
		} while (opcion != 0);
	}

	public static void crearProject() {
		System.out.println("\n-- Crear Proyecto --");
		System.out.print("ID: ");
		Long id = leerLong();
		System.out.print("Nombre: ");
		String nombre = sc.nextLine();
		System.out.print("Fecha inicio (dd/MM/yyyy): ");
		Date fecha = leerFecha();
		System.out.print("Presupuesto: ");
		double presupuesto = leerDouble();
		Status estado = leerStatus();
		System.out.print("Descripcion: ");
		String descripcion = sc.nextLine();

		Project project = new Project(id, nombre, fecha, presupuesto, estado, descripcion);
		boolean resultado = projectService.createProject(project);
		if (resultado) {
			System.out.println("Proyecto creado!");
		} else {
			System.out.println("Ya existe un proyecto con ese ID y nombre");
		}
	}

	public static void listarProjects() {
		System.out.println("\n-- Lista de Proyectos --");
		List<Project> lista = projectService.findAll();
		if (lista.isEmpty()) {
			System.out.println("No hay proyectos registrados");
		} else {
			for (Project p : lista) {
				System.out.println(p);
			}
		}
	}

	public static void buscarProject() {
		System.out.println("\n-- Buscar Proyecto --");
		System.out.print("ID: ");
		Long id = leerLong();
		System.out.print("Nombre: ");
		String nombre = sc.nextLine();
		Project project = projectService.findProjectById(id, nombre);
		if (project != null) {
			System.out.println(project);
		} else {
			System.out.println("No se encontro el proyecto");
		}
	}

	public static void actualizarProject() {
		System.out.println("\n-- Actualizar Proyecto --");
		System.out.print("ID: ");
		Long id = leerLong();
		System.out.print("Nombre actual: ");
		String nombre = sc.nextLine();
		System.out.print("Nueva descripcion: ");
		String descripcion = sc.nextLine();
		System.out.print("Nuevo presupuesto (0 = sin cambio): ");
		double presupuesto = leerDouble();
		System.out.print("Nueva fecha (dd/MM/yyyy, Enter = sin cambio): ");
		Date fecha = leerFecha();
		Status estado = leerStatusOpcional();

		Project project = new Project(id, nombre, fecha, presupuesto, estado, descripcion);
		boolean resultado = projectService.updateProject(project);
		if (resultado) {
			System.out.println("Proyecto actualizado!");
		} else {
			System.out.println("No se encontro el proyecto");
		}
	}

	public static void eliminarProject() {
		System.out.println("\n-- Eliminar Proyecto --");
		System.out.print("ID: ");
		Long id = leerLong();
		System.out.print("Nombre: ");
		String nombre = sc.nextLine();
		boolean resultado = projectService.deleteProjectByIdAndName(id, nombre);
		if (resultado) {
			System.out.println("Proyecto eliminado!");
		} else {
			System.out.println("No se encontro el proyecto");
		}
	}

	// ---- COMPUTER ----

	public static void menuComputer() {
		int opcion;
		do {
			System.out.println("\n--- MENU COMPUTADOR ---");
			System.out.println("1. Crear");
			System.out.println("2. Listar todos");
			System.out.println("3. Buscar por ID");
			System.out.println("4. Actualizar");
			System.out.println("5. Eliminar");
			System.out.println("0. Volver");
			System.out.print("Opcion: ");
			opcion = leerInt();

			switch (opcion) {
				case 1:
					crearComputer();
				case 2:
					listarComputers();
				case 3:
					buscarComputer();
				case 4:
					actualizarComputer();
				case 5:
					eliminarComputer();
				case 0:
					System.out.println("Volviendo...");
				default:
					System.out.println("Opcion no valida");
			}
		} while (opcion != 0);
	}

	public static void crearComputer() {
		System.out.println("\n-- Crear Computador --");
		System.out.print("ID: ");
		Long id = leerLong();
		System.out.print("Marca: ");
		String marca = sc.nextLine();
		System.out.print("Procesador: ");
		String procesador = sc.nextLine();
		System.out.print("RAM (GB): ");
		int ram = leerInt();
		System.out.print("Sistema operativo: ");
		String so = sc.nextLine();
		System.out.print("Almacenamiento (GB): ");
		int almacenamiento = leerInt();

		Computer computer = new Computer(id, marca, procesador, ram, so, almacenamiento);
		boolean resultado = computerService.createComputer(computer);
		if (resultado) {
			System.out.println("Computador creado!");
		} else {
			System.out.println("Ya existe un computador con ese ID y marca");
		}
	}

	public static void listarComputers() {
		System.out.println("\n-- Lista de Computadores --");
		List<Computer> lista = computerService.findAll();
		if (lista.isEmpty()) {
			System.out.println("No hay computadores registrados");
		} else {
			for (Computer c : lista) {
				System.out.println(c);
			}
		}
	}

	public static void buscarComputer() {
		System.out.println("\n-- Buscar Computador --");
		System.out.print("ID: ");
		Long id = leerLong();
		System.out.print("Marca: ");
		String marca = sc.nextLine();
		Computer computer = computerService.findComputerById(id, marca);
		if (computer != null) {
			System.out.println(computer);
		} else {
			System.out.println("No se encontro el computador");
		}
	}

	public static void actualizarComputer() {
		System.out.println("\n-- Actualizar Computador --");
		System.out.print("ID: ");
		Long id = leerLong();
		System.out.print("Marca actual: ");
		String marca = sc.nextLine();
		System.out.print("Nuevo procesador: ");
		String procesador = sc.nextLine();
		System.out.print("Nueva RAM (0 = sin cambio): ");
		int ram = leerInt();
		System.out.print("Nuevo sistema operativo: ");
		String so = sc.nextLine();
		System.out.print("Nuevo almacenamiento (0 = sin cambio): ");
		int almacenamiento = leerInt();

		Computer computer = new Computer(id, marca, procesador, ram, so, almacenamiento);
		boolean resultado = computerService.updateComputer(computer);
		if (resultado) {
			System.out.println("Computador actualizado!");
		} else {
			System.out.println("No se encontro el computador");
		}
	}

	public static void eliminarComputer() {
		System.out.println("\n-- Eliminar Computador --");
		System.out.print("ID: ");
		Long id = leerLong();
		System.out.print("Marca: ");
		String marca = sc.nextLine();
		boolean resultado = computerService.deleteComputerByIdAndBrand(id, marca);
		if (resultado) {
			System.out.println("Computador eliminado!");
		} else {
			System.out.println("No se encontro el computador");
		}
	}

	

	public static int leerInt() {
		try {
			return Integer.parseInt(sc.nextLine().trim());
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public static Long leerLong() {
		try {
			return Long.parseLong(sc.nextLine().trim());
		} catch (NumberFormatException e) {
			return 0L;
		}
	}

	public static double leerDouble() {
		try {
			return Double.parseDouble(sc.nextLine().trim());
		} catch (NumberFormatException e) {
			return 0.0;
		}
	}

	public static Date leerFecha() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			String input = sc.nextLine().trim();
			if (input.isEmpty()) return null;
			return sdf.parse(input);
		} catch (ParseException e) {
			System.out.println("Fecha invalida, se dejara en null");
			return null;
		}
	}

	public static Status leerStatus() {
		System.out.println("1.PLANNING  2.IN_PROCESS  3.ON_HOLD  4.COMPLETED  5.CANCELLED");
		System.out.print("Selecciona: ");
		int op = leerInt();
		Status[] estados = Status.values();
		if (op >= 1 && op <= estados.length) return estados[op - 1];
		return Status.PLANNING;
	}

	public static Status leerStatusOpcional() {
		System.out.println("1.PLANNING  2.IN_PROCESS  3.ON_HOLD  4.COMPLETED  5.CANCELLED  0.Sin cambio");
		System.out.print("Selecciona: ");
		int op = leerInt();
		Status[] estados = Status.values();
		if (op >= 1 && op <= estados.length) return estados[op - 1];
		return null;
	}
}