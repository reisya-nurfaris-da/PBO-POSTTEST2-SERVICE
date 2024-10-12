package model;

public class PhoneTechnician extends Technician {
    private String OS;
    public PhoneTechnician(String name, String OS) {
        super(name);
        this.OS = OS;
    }

    @Override
    public String toString() {
        return super.toString() + " ("+OS+")";
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String oS) {
        OS = oS;
    }
}