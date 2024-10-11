package model;

public final class SparePart {
    private static int nextId = 1;
    private final int id;
    private String name;
    private int quantity;

    public SparePart(String name, int quantity) {
        this.id = nextId++;
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nama Sparepart: " + name + ", Stok: " + quantity;
    }
}
