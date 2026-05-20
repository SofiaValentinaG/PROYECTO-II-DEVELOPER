package co.edu.uptc.developer.repository;

import java.util.Set;
import java.util.TreeSet;
import co.edu.uptc.developer.domain.Computer;

public class ComputerRepository {
    private Set<Computer> computers;

    public ComputerRepository() {
        this.computers = new TreeSet<>();
    }

    public void createComputer(Computer computer) {
        this.computers.add(computer);
    }

    public Set<Computer> findAll() {
        return this.computers;
    }

    public Computer findById(int idComputer) {
        for (Computer c : this.computers) {
            if (c.getComputerId() == idComputer) {
                return c;
            }
        }
        return null;
    }

    public void updateComputer(Computer newComputer) {
        Computer oldComputer = this.findById(newComputer.getComputerId());
        if (oldComputer != null) {
            this.computers.remove(oldComputer);
            this.computers.add(newComputer);
        }
    }

    public void deleteComputer(int idComputer) {
        this.computers.removeIf(c -> c.getComputerId() == idComputer);
    }
}
