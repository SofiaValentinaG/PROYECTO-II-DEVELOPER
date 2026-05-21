package co.edu.uptc.developer.domain;
// Entidad de composicion

public class Computer {

	private Long idComputer;
	private String brand;
	private String processor;
	private int ramMemory;
	private String operationSystem;
	private int storageCapacity;

	public Computer(Long idComputer, String brand, String processor, int ramMemory, String operationSystem,
			int storageCapacity) {
		super();
		this.idComputer = idComputer;
		this.brand = brand;
		this.processor = processor;
		this.ramMemory = ramMemory;
		this.operationSystem = operationSystem;
		this.storageCapacity = storageCapacity;
	}

	public int getStorageCapacity() {
		return storageCapacity;
	}

	public void setStorageCapacity(int storageCapacity) {
		this.storageCapacity = storageCapacity;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	public int getRamMemory() {
		return ramMemory;
	}

	public void setRamMemory(int ramMemory) {
		this.ramMemory = ramMemory;
	}

	public String getOperationSystem() {
		return operationSystem;
	}

	public void setOperationSystem(String operationSystem) {
		this.operationSystem = operationSystem;
	}

	public Long getIdComputer() {
		return idComputer;
	}

	public void setIdComputer(Long idComputer) {
		this.idComputer = idComputer;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n===== Computer Info =====\n");

		sb.append("ID Computador: ").append(idComputer).append("\n");

		sb.append("Capacidad de Almacenamiento: ").append(storageCapacity).append("\n");
		sb.append("Marca: ").append(brand).append("\n");
		sb.append("Procesador: ").append(processor).append("\n");
		sb.append("Memoria RAM: ").append(ramMemory).append(" GB\n");
		sb.append("Sistema Operativo: ").append(operationSystem).append("\n");
		sb.append("=========================\n");
		return sb.toString();
	}

}
