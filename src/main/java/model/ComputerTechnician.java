package model;

import java.util.List;

public class ComputerTechnician extends Technician {
    private final List<String> hardwareSkills;
    public ComputerTechnician(String name, List<String> hardwareSkills) {
        super(name);
        this.hardwareSkills = hardwareSkills;
    }

    @Override
    public String toString() {
        String skills = String.join(", ", hardwareSkills);
        return super.toString() + " (Komputer. Keahlian: " + skills + ")";
    }
}