package model;

public class PhoneTechnician extends Technician {
    private final String OS;
    public PhoneTechnician(String name, String OS) {
        super(name);
        this.OS = OS;
    }

    @Override
    public String toString() {
        return super.toString() + " ("+OS+")";
    }
}