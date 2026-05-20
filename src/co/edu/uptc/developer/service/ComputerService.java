package co.edu.uptc.developer.service;

import java.util.List;
import java.util.Objects;

import co.edu.uptc.developer.domain.Computer;
import co.edu.uptc.developer.repository.ComputerRepository;
import co.edu.uptc.product.domain.Product;

public class ComputerService {
	private ComputerRepository computerRepository;
	
	 public ComputerService() {
	        this.computerRepository = new ComputerRepository();
	    }
	 
	 
           public Computer createComputer(String id,Computer computer) {
			if(this.computerRepository.existById(id)) {
				return null;
			}
			this.computerRepository.createComputer(id, computer);
			return computer;
		}
		
		public Computer findById(String id) {
			return this.computerRepository.findById(id);
		}
		
		public boolean updateComputer( String id,Computer computer) {
			Computer computerAux=this.computerRepository.findById(id);
			if(Objects.isNull(computer.getBrand())) {
				computer.setBrand(computerAux.getBrand());
			
			}
			
			if(Objects.isNull(computer.getOperationSystem())) {
				computer.setOperationSystem(computerAux.getOperationSystem());
			
			}
			if(Objects.isNull(computer.getProcessor())) {
				computer.setProcessor(computerAux.getProcessor());
			
			}
			
			if(Objects.isNull(computer.getRamMemory())) {
				computer.setRamMemory(computerAux.getRamMemory());
			
			}
			if(Objects.isNull(computer.getStorageCapacity())) {
				computer.setStorageCapacity(computerAux.getStorageCapacity());
			
			}
			if(!this.computerRepository.existById(id)){
				return false;
			}
			
			this.computerRepository.createComputer(id, computer);
			return true;
		}
		
		public List<Computer> findAll(){
			return this.computerRepository.findAll();
			
		}
		
		public Computer updateComputer(Long id, String name, Double price) {
			Product product = new Product(id, name, price);
			if( !this.productRepository.existById(id) ) {
				return null;
			}
			return this.productRepository.updateProduct(product);
		}
		
		public boolean deleteProduct(Long id) {
			if( !this.productRepository.existById(id) ) {
				return false;
			}
			this.productRepository.deleteById(id);
			return true;
		}
		
		
		public boolean existById(String id) {
			if(this.computerRepository.findById(id)== null) {
				return false;
				
			}
			return true;
		}
	
	
	
	

}
