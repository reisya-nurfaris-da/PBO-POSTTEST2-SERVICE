package model;

public final class RepairSchedule {
    private static int nextId = 1; // ini yang untuk di increment
    private final int id; // ini yang jadi id-nya
    private String customerName;
    private String deviceName;
    private String date;

    public RepairSchedule(String customerName, String deviceName, String date) {
        this.id = nextId++;  // assign id, habis itu increment 
        this.customerName = customerName;
        this.deviceName = deviceName;
        this.date = date;
    }
    
    // method buat ngambil sama ganti value
    // method get-nya gak jadi dipake tapi saya biarin aja, siapa tau dipake nanti
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public int getId() {
        return id;
    }

    @Override // memang disuruh pake dari debugger-nya
    public String toString() {
        return "ID: " + id + ", Pelanggan: " + customerName + ", Perangkat: " + deviceName + ", Tanggal: " + date;
    }
}
