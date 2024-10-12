package model;

public abstract class Technician {
    private static int nextId = 1;
    private int id;
    private String name;

    public Technician(String name) {
        this.id = nextId++;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nama: " + name;
    }

    public void setId(int id) {
        this.id = id;
    }
}
