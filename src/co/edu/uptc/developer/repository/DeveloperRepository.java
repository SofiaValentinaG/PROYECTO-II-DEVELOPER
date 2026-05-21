package co.edu.uptc.developer.repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import co.edu.uptc.developer.domain.Developer;

public class DeveloperRepository {
	private Map<String, Developer> mapDevelopers;

	public DeveloperRepository() {
		this.mapDevelopers = new LinkedHashMap<>();
	}

	public void createDeveloper(Developer developer) {
		mapDevelopers.put(this.generateKey(developer.getIdDeveloper(), developer.getName()), developer);
	}

	public Developer findDeveloperByIdAndName(Long idDeveloper, String name) {
		return mapDevelopers.get(this.generateKey(idDeveloper, name));
	}

	public List<Developer> findAll() {
		return new ArrayList<>(mapDevelopers.values());
	}

	public boolean deleteDeveloperByIdAndName(Long idDeveloper, String name) {
		return mapDevelopers.remove(this.generateKey(idDeveloper, name)) != null;
	}

	public boolean existsByIdAndName(Long idDeveloper, String name) {
		return mapDevelopers.containsKey(this.generateKey(idDeveloper, name));
	}

	private String generateKey(Long idDeveloper, String name) {
		StringBuilder key = new StringBuilder();
		key.append(idDeveloper);

		key.append(name);
		return key.toString();
	}
}
