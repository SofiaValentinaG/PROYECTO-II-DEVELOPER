package co.edu.uptc.developer.service;

import java.util.List;
import java.util.Objects;

import co.edu.uptc.developer.domain.Computer;
import co.edu.uptc.developer.repository.ComputerRepository;

public class ComputerService {
    private ComputerRepository computerRepository;

    public ComputerService() {
        this.computerRepository= new ComputerRepository();
    }

  
    public boolean createComputer(Computer computer) {
        if (computerRepository.existsByIdAndBrand(computer.getIdComputer(), computer.getBrand())) {
            return false; 
        }
        computerRepository.createComputer(computer);
        return true;
    }

   
    public Computer findComputerById(Long idComputer, String brand) {
        return computerRepository.findComputerByIdAndBrand(idComputer, brand);
    }

  
    public List<Computer> findAll() {
        return computerRepository.findAll();
    }


    public boolean updateComputer(Computer computer) {
        Computer computerAux = this.computerRepository.findComputerByIdAndBrand(
                computer.getIdComputer(), computer.getBrand());

        if (Objects.isNull(computerAux)) {
            return false; 
        }

        if (Objects.isNull(computer.getBrand()) || computer.getBrand().isBlank()) {
            computer.setBrand(computerAux.getBrand());
        }
        if (Objects.isNull(computer.getProcessor()) || computer.getProcessor().isBlank()) {
            computer.setProcessor(computerAux.getProcessor());
        }
        if (computer.getRamMemory() == 0) {
            computer.setRamMemory(computerAux.getRamMemory());
        }
        if (Objects.isNull(computer.getOperationSystem()) || computer.getOperationSystem().isBlank()) {
            computer.setOperationSystem(computerAux.getOperationSystem());
        }
        if (computer.getStorageCapacity() == 0) {
            computer.setStorageCapacity(computerAux.getStorageCapacity());
        }

        computerRepository.updateComputer(computer);
        return true;
    }

   
    public boolean deleteComputerByIdAndBrand(Long idComputer, String brand) {
        return computerRepository.deleteComputerByIdAndBrand(idComputer, brand);
    }
}
