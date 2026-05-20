package co.edu.uptc.developer.domain;
// Entidad de composicion

public class Computer {
	private int computerId;
	private String brand;
	private String processor;
	private int ramMemory;
	private String operationSystem;
	public Computer(int computerId, String brand, String processor, int ramMemory, String operationSystem) {
		super();
		this.computerId = computerId;
		this.brand = brand;
		this.processor = processor;
		this.ramMemory = ramMemory;
		this.operationSystem = operationSystem;
	}
	public int getComputerId() {
		return computerId;
	}
	public void setComputerId(int computerId) {
		this.computerId = computerId;
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
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("\n===== Computer Info =====\n");
	    sb.append("ID Computador: ").append(computerId).append("\n");
	    sb.append("Marca: ").append(brand).append("\n");
	    sb.append("Procesador: ").append(processor).append("\n");
	    sb.append("Memoria RAM: ").append(ramMemory).append(" GB\n");
	    sb.append("Sistema Operativo: ").append(operationSystem).append("\n");
	    sb.append("=========================\n");
	    return sb.toString();
	}

	

}
