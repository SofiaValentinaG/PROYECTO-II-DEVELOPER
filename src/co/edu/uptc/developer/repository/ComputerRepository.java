package co.edu.uptc.developer.repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.edu.uptc.developer.domain.*;



public class ComputerRepository {
	
	private Map <String, Computer> computersMaps;

	public ComputerRepository() {
		
		this.computersMaps = new  HashMap<>();
	}
	
	
	public void createComputer(String idComputer, Computer computer) {
		this.computersMaps.put(idComputer, computer);
		
	}
	
	public List<Computer> findAll(){
		return  new ArrayList <Computer>(this.computersMaps.values());
	}
	public Computer findById(String id) {
		
		return this.computersMaps.get(id);
		
	}
	   public void updateComputer(String idComputer, Computer newComputer) {
	        if (this.computersMaps.containsKey(idComputer)) {
	            this.computersMaps.put(idComputer, newComputer);
	        }
	    }
	

	
	public void deleteComputer(String id) {
		this.computersMaps.remove(id);
		
	}
	
	public boolean existById(String id) {
		if(this.findById(id)== null) {
			return false;
			
		}
		return true;
	}
	
	
}
