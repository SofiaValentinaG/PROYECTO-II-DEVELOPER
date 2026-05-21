package co.edu.uptc.developer.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.edu.uptc.developer.domain.Computer;

public class ComputerRepository {
    private Map<String, Computer> mapComputers;

    public ComputerRepository() {
        this.mapComputers = new HashMap<>();
    }

    
    public void createComputer(Computer computer) {
        mapComputers.put(this.generateKey(computer.getIdComputer(), computer.getBrand()), computer);
    }


    public Computer findComputerByIdAndBrand(Long idComputer, String brand) {
        return mapComputers.get(this.generateKey(idComputer, brand));
    }

  
    public List<Computer> findAll() {
        return new ArrayList<>(mapComputers.values());
    }

 
    public boolean deleteComputerByIdAndBrand(Long idComputer, String brand) {
        return mapComputers.remove(this.generateKey(idComputer, brand)) != null;
    }

  
    public boolean existsByIdAndBrand(Long idComputer, String brand) {
        return mapComputers.containsKey(this.generateKey(idComputer, brand));
    }

 
    private String generateKey(Long idComputer, String brand) {
        StringBuilder key = new StringBuilder();
        key.append(idComputer);
        key.append(brand);
        return key.toString();
    }
}
