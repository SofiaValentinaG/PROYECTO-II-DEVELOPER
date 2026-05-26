package co.edu.uptc.developer.service;

import java.util.List;
import java.util.Objects;

import co.edu.uptc.developer.domain.Developer;
import co.edu.uptc.developer.repository.DeveloperRepository;

public class DeveloperService {
	private DeveloperRepository developerRepository;

	public DeveloperService() {
		super();
		this.developerRepository = new DeveloperRepository();
	}

	public boolean createDeveloper(Developer developer) {
		if (developerRepository.existsByIdAndName(developer.getIdDeveloper(), developer.getName())) {
			return false;
		}
		developerRepository.createDeveloper(developer);
		return true;
	}

	public Developer findDeveloperById(Long id, String nameDeveloper) {
		return developerRepository.findDeveloperByIdAndName(id, nameDeveloper);
	}

	public List<Developer> findAll() {
		return developerRepository.findAll();
	}

	public boolean updateDeveloper(Developer developer) {
		Developer developerAux = this.developerRepository.findDeveloperByIdAndName(developer.getIdDeveloper(),
				developer.getName());
		if (Objects.isNull(developer.getName()) || developer.getName().isBlank()) {
			developer.setName(developerAux.getName());
		}
		if (Objects.isNull(developer.getEmail()) || developer.getEmail().isBlank()) {
			developer.setEmail(developerAux.getEmail());
		}
		if (Objects.isNull(developer.getLastName()) || developer.getLastName().isBlank()) {
			developer.setLastName(developerAux.getLastName());
		}
		if (Objects.isNull(developer.getMainLanguage()) || developer.getMainLanguage().isBlank()) {
			developer.setMainLanguage(developerAux.getMainLanguage());
		}
		if (Objects.isNull(developer.getComputer())) {
			developer.setComputer(developerAux.getComputer());
		}
		if (developer.getYearsExperience() <= 0) {
			developer.setYearsExperience(developerAux.getYearsExperience());
		}
		if (developer.getSalary() <= 0) {
			developer.setSalary(developerAux.getSalary());
		}

		if (!developerRepository.existsByIdAndName(developer.getIdDeveloper(), developer.getName())) {
			return false;
		}
		developerRepository.updateDeveloper(developer);
		return true;
	}

	public boolean deleteDveloperByIdAndName(Long idDeveloper, String nameDeveloper) {
		return developerRepository.deleteDeveloperByIdAndName(idDeveloper, nameDeveloper);
	}
}
