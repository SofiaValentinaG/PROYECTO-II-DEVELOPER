package co.edu.uptc.developer.repository;

import java.util.LinkedHashMap;
import java.util.Map;


import co.edu.uptc.developer.domain.Developer;

public class DeveloperRepository {
	
	private Map<String, Developer> mapDevelopers;

	public DeveloperRepository() {
	
		this.mapDevelopers = new LinkedHashMap <>();
	}
	
	
	public void createDeveloper(String idDeveloper, Developer developer) {
		this.mapDevelopers.put(idDeveloper, developer);
		
	}
	
	public Map<String, Developer> findAll(){
		return this.mapDevelopers;
	}
	public Developer findById(String id) {
		
		return this.mapDevelopers.get(id);
		
	}
	   public void updateComputer(String idDeveloper, Developer newDeveloper) {
	        if (this.mapDevelopers.containsKey(idDeveloper)) {
	            this.mapDevelopers.put(idDeveloper, newDeveloper);
	        }
	    }
	

	
	public void deleteComputer(String id) {
		this.mapDevelopers.remove(id);
		
	}
	
	
	
	

}
