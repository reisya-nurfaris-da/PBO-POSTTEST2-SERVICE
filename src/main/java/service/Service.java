package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import model.ComputerTechnician;
import model.PhoneTechnician;
import model.RepairSchedule;
import model.SparePart;
import model.Technician;

// service untuk RepairSchedule
class RepairScheduleService implements CRUDService<RepairSchedule> {
    private final ArrayList<RepairSchedule> repairSchedules = new ArrayList<>();

    @Override
    public void add(RepairSchedule schedule) {
        repairSchedules.add(schedule);
    }

    @Override
    public void update(int id, RepairSchedule newSchedule) {
        for (RepairSchedule schedule : repairSchedules) {
            if (schedule.getId() == id) {
                schedule.setCustomerName(newSchedule.getCustomerName());
                schedule.setDeviceName(newSchedule.getDeviceName());
                schedule.setDate(newSchedule.getDate());
                break;
            }
        }
    }

    @Override
    public void delete(int id) {
        repairSchedules.removeIf(schedule -> schedule.getId() == id);
    }

    @Override
    public ArrayList<RepairSchedule> getAll() {
        return repairSchedules;
    }
}

// service untuk SparePart
class SparePartService implements CRUDService<SparePart> {
    private final ArrayList<SparePart> spareParts = new ArrayList<>();

    @Override
    public void add(SparePart part) {
        spareParts.add(part);
    }

    @Override
    public void update(int id, SparePart newPart) {
        for (SparePart part : spareParts) {
            if (part.getId() == id) {
                part.setName(newPart.getName());
                part.setQuantity(newPart.getQuantity());
                break;
            }
        }
    }

    @Override
    public void delete(int id) {
        spareParts.removeIf(part -> part.getId() == id);
    }

    @Override
    public ArrayList<SparePart> getAll() {
        return spareParts;
    }
}

// service untuk Technician
class TechnicianService implements CRUDService<Technician> {
    private final ArrayList<Technician> technicians = new ArrayList<>();

    @Override
    public void add(Technician technician) {
        technicians.add(technician);
    }

    @Override
    public void update(int id, Technician newTechnician) {
        for (Technician technician : technicians) {
            if (technician.getId() == id) {
                technician.setName(newTechnician.getName());
                // kalau teknisinya tipe hp, update OS-nya
                if (technician instanceof PhoneTechnician && newTechnician instanceof PhoneTechnician) {
                    ((PhoneTechnician) technician).setOS(((PhoneTechnician) newTechnician).getOS());
                }
                // kalau teknisinya tipe komputer, update skill hardware-nya
                else if (technician instanceof ComputerTechnician && newTechnician instanceof ComputerTechnician) {
                    ((ComputerTechnician) technician).setHardwareSkills(((ComputerTechnician) newTechnician).getHardwareSkills());
                }
                break;
            }
        }
    }
    

    @Override
    public void delete(int id) {
        technicians.removeIf(tech -> tech.getId() == id);
    }

    @Override
    public ArrayList<Technician> getAll() {
        return technicians;
    }
}

// bagian menu utama
public class Service {
    static RepairScheduleService repairScheduleService = new RepairScheduleService();
    static SparePartService sparePartService = new SparePartService();
    static TechnicianService technicianService = new TechnicianService();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;

            // data buat ngetest
            repairScheduleService.add(new RepairSchedule("Ejo", "Laptop Asus TUF", "2024-09-29"));
            repairScheduleService.add(new RepairSchedule("Krisna", "Hp Samsung", "2024-10-01"));
            repairScheduleService.add(new RepairSchedule("Ninda", "Hp iPhone 14", "2024-10-05"));

            technicianService.add(new ComputerTechnician("Juno Nadianto", Arrays.asList("Upgrade RAM", "Ganti HDD")));
            technicianService.add(new PhoneTechnician("Farhan Pertama", "Android"));
            technicianService.add(new PhoneTechnician("Kiki Fernandi", "iOS"));

            sparePartService.add(new SparePart("SSD Sata 512GB", 5));
            sparePartService.add(new SparePart("Baterai Samsung S21", 10));
            sparePartService.add(new SparePart("LCD iPhone 14", 2));

            while (running) {
                System.out.println("\n=== Sistem Manajemen Servis Elektronik ===");
                System.out.println("[1] Jadwal Servis");
                System.out.println("[2] Teknisi");
                System.out.println("[3] Sparepart");
                System.out.println("[4] Keluar");
                System.out.print("Pilih kategori: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> manageRepairSchedule(scanner);
                    case 2 -> manageTechnicians(scanner);
                    case 3 -> manageSpareParts(scanner);
                    case 4 -> {
                        running = false;
                        System.out.println("Keluar dari sistem.");
                    }
                    default -> System.out.println("Opsi tidak valid. Coba lagi.");
                }
            }
        }
    }

    static void manageRepairSchedule(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Manajemen Jadwal Servis ---");
            System.out.println("[1] Tampilkan Semua Jadwal");
            System.out.println("[2] Tambah Jadwal");
            System.out.println("[3] Edit Jadwal");
            System.out.println("[4] Hapus Jadwal");
            System.out.println("[5] Kembali");
            System.out.print("Pilih opsi: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> displayRepairSchedules();
                case 2 -> addRepairSchedule(scanner);
                case 3 -> editRepairSchedule(scanner);
                case 4 -> deleteRepairSchedule(scanner);
                case 5 -> back = true;
                default -> System.out.println("Opsi tidak valid.");
            }
        }
    }

    static void manageTechnicians(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Manajemen Teknisi ---");
            System.out.println("[1] Tampilkan Semua Teknisi");
            System.out.println("[2] Tambah Teknisi");
            System.out.println("[3] Edit Teknisi");
            System.out.println("[4] Hapus Teknisi");
            System.out.println("[5] Kembali");
            System.out.print("Pilih opsi: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> displayTechnicians();
                case 2 -> addTechnician(scanner);
                case 3 -> editTechnician(scanner);
                case 4 -> deleteTechnician(scanner);
                case 5 -> back = true;
                default -> System.out.println("Opsi tidak valid.");
            }
        }
    }

    static void manageSpareParts(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Manajemen Sparepart ---");
            System.out.println("[1] Tampilkan Semua Sparepart");
            System.out.println("[2] Tambah Sparepart");
            System.out.println("[3] Edit Sparepart");
            System.out.println("[4] Hapus Sparepart");
            System.out.println("[5] Kembali");
            System.out.print("Pilih opsi: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> displaySpareParts();
                case 2 -> addSparePart(scanner);
                case 3 -> editSparePart(scanner);
                case 4 -> deleteSparePart(scanner);
                case 5 -> back = true;
                default -> System.out.println("Opsi tidak valid.");
            }
        }
    }


// bagian untuk menu crud
    static void addRepairSchedule(Scanner scanner) {
        System.out.print("Masukkan nama pelanggan: ");
        String customerName = scanner.nextLine();
        System.out.print("Masukkan nama perangkat: ");
        String deviceName = scanner.nextLine();
        System.out.print("Masukkan tanggal servis (yyyy-mm-dd): ");
        String date = scanner.nextLine();
        repairScheduleService.add(new RepairSchedule(customerName, deviceName, date));
        System.out.println("Jadwal servis berhasil ditambahkan.");
    }

    static void displayRepairSchedules() {
        System.out.println("\nDaftar Jadwal Servis:");
        for (RepairSchedule schedule : repairScheduleService.getAll()) {
            System.out.println(schedule);
        }
    }

    static void editRepairSchedule(Scanner scanner) {
        System.out.print("Masukkan ID jadwal yang ingin diedit: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        RepairSchedule repairScheduleToEdit = null;
        for (RepairSchedule sched : repairScheduleService.getAll()) {
            if (sched.getId() == id) {
                repairScheduleToEdit = sched;
                break;
            }
        }

        if (repairScheduleToEdit != null) {
            System.out.print("Masukkan nama pelanggan baru (biarkan kosong jika tidak ingin diubah): ");
            String newCustomerName = scanner.nextLine();
            if (!newCustomerName.isEmpty()) {
                repairScheduleToEdit.setCustomerName(newCustomerName);
            }

            System.out.print("Masukkan nama perangkat baru (biarkan kosong jika tidak ingin diubah): ");
            String newDeviceName = scanner.nextLine();
            if (!newDeviceName.isEmpty()) {
                repairScheduleToEdit.setDeviceName(newDeviceName);
            }

            System.out.print("Masukkan tanggal servis baru (yyyy-mm-dd) (biarkan kosong jika tidak ingin diubah): ");
            String newDate = scanner.nextLine();
            if (!newDate.isEmpty()) {
                repairScheduleToEdit.setDate(newDate);
            }

            repairScheduleService.update(id, repairScheduleToEdit);
            System.out.println("Jadwal servis berhasil diperbarui.");
        } else {
            System.out.println("Jadwal dengan ID tersebut tidak ditemukan.");
        }
    }

    static void deleteRepairSchedule(Scanner scanner) {
        System.out.print("Masukkan ID jadwal yang ingin dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        RepairSchedule scheduleToDelete = null;
        for (RepairSchedule sched : repairScheduleService.getAll()) {
            if (sched.getId() == id) {
                scheduleToDelete = sched;
                break;
            }
        }

        if (scheduleToDelete != null) {
            repairScheduleService.delete(id);
            System.out.println("Jadwal servis berhasil dihapus.");
        } else {
            System.out.println("Jadwal dengan ID tersebut tidak ditemukan.");
        }
    }

    static void addTechnician(Scanner scanner) {
        System.out.print("Masukkan nama teknisi: ");
        String name = scanner.nextLine();
        
        System.out.println("Pilih jenis teknisi: ");
        System.out.println("[1] Teknisi HP");
        System.out.println("[2] Teknisi Komputer");
        System.out.print("Pilihan: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
    
        switch (choice) {
            case 1 -> {
                System.out.print("Masukkan sistem operasi yang dikuasai (misal: Android, iOS): ");
                String os = scanner.nextLine();
                technicianService.add(new PhoneTechnician(name, os));
                System.out.println("Teknisi HP berhasil ditambahkan.");
            }
            case 2 -> {
                List<String> hardwareSkills = new ArrayList<>();
                int i = 1;
                while (true) {
                    System.out.println("Masukkan keahlian perangkat keras #" + i + " (kosongkan kalau sudah): ");
                    String skill = scanner.nextLine();
                    if (skill.isEmpty()) {
                        break; // loopnya berhenti kalau input kosong
                    }
                    hardwareSkills.add(skill);
                    i++;
                }
                technicianService.add(new ComputerTechnician(name, hardwareSkills));
                System.out.println("Teknisi Komputer berhasil ditambahkan.");
            }
            default -> System.out.println("Pilihan tidak valid, teknisi tidak ditambahkan.");
        }
    }

    static void displayTechnicians() {
        System.out.println("\nDaftar Teknisi:");
        for (Technician tech : technicianService.getAll()) {
            System.out.println(tech);
        }
    }

    static void editTechnician(Scanner scanner) {
        System.out.print("Masukkan ID teknisi yang ingin diedit: ");
        int id = scanner.nextInt();
        scanner.nextLine();
    
        Technician technicianToEdit = null;
        int indexToEdit = -1;
    
        for (int i = 0; i < technicianService.getAll().size(); i++) {
            if (technicianService.getAll().get(i).getId() == id) {
                technicianToEdit = technicianService.getAll().get(i);
                indexToEdit = i;
                break;
            }
        }
    
        if (technicianToEdit != null) {
            System.out.print("Masukkan nama teknisi baru (biarkan kosong jika tidak ingin diubah): ");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) {
                technicianToEdit.setName(newName);
            }
    
            System.out.println("Pilih tipe teknisi baru (biarkan kosong jika tidak ingin diubah): ");
            System.out.println("[1] Teknisi HP");
            System.out.println("[2] Teknisi Komputer");
            System.out.print("Pilihan: ");
            String choice = scanner.nextLine();
    
            Technician updatedTechnician = null;
    
            if (!choice.isEmpty()) {
                switch (choice) {
                    case "1" -> {
                        System.out.print("Masukkan sistem operasi yang dikuasai (misal: Android, iOS): ");
                        String newOs = scanner.nextLine();
                        updatedTechnician = new PhoneTechnician(technicianToEdit.getName(), newOs); // Buat objek baru
                    }
                    case "2" -> {
                        List<String> newHardwareSkills = new ArrayList<>();
                        int i = 1;
                        while (true) {
                            System.out.println("Masukkan keahlian perangkat keras #" + i + " (kosongkan kalau sudah): ");
                            String skill = scanner.nextLine();
                            if (skill.isEmpty()) {
                                break;
                            }
                            newHardwareSkills.add(skill);
                            i++;
                        }
                        updatedTechnician = new ComputerTechnician(technicianToEdit.getName(), newHardwareSkills); // Buat objek baru
                    }
                    default -> System.out.println("Pilihan tidak valid, tipe teknisi tidak diubah.");
                }
            }
    
            if (updatedTechnician != null) {
                updatedTechnician.setId(technicianToEdit.getId()); // supaya idnya tetap sama
                technicianService.getAll().set(indexToEdit, updatedTechnician); // ganti teknisi di listnya
                System.out.println("Tipe teknisi berhasil diubah.");
            } else {
                technicianService.update(id, technicianToEdit); // update biasa kalau tipenya gak berubah
                System.out.println("Teknisi berhasil diperbarui.");
            }
        } else {
            System.out.println("Teknisi dengan ID tersebut tidak ditemukan.");
        }
    }
    
    static void deleteTechnician(Scanner scanner) {
        System.out.print("Masukkan ID teknisi yang ingin dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Technician technicianToDelete = null;
        for (Technician tech : technicianService.getAll()) {
            if (tech.getId() == id) {
                technicianToDelete = tech;
                break;
            }
        }

        if (technicianToDelete != null) {
            technicianService.delete(id);
            System.out.println("Teknisi berhasil dihapus.");
        } else {
            System.out.println("Teknisi dengan ID tersebut tidak ditemukan.");
        }
    }

    static void addSparePart(Scanner scanner) {
        System.out.print("Masukkan nama sparepart: ");
        String name = scanner.nextLine();
        System.out.print("Masukkan jumlah sparepart: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        sparePartService.add(new SparePart(name, quantity));
        System.out.println("Sparepart berhasil ditambahkan.");
    }

    static void displaySpareParts() {
        System.out.println("\nDaftar Sparepart:");
        for (SparePart part : sparePartService.getAll()) {
            System.out.println(part);
        }
    }

    static void editSparePart(Scanner scanner) {
        System.out.print("Masukkan ID sparepart yang ingin diedit: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        SparePart sparePartToEdit = null;
        for (SparePart part : sparePartService.getAll()) {
            if (part.getId() == id) {
                sparePartToEdit = part;
                break;
            }
        }

        if (sparePartToEdit != null) {
            System.out.print("Masukkan nama sparepart baru (biarkan kosong jika tidak ingin diubah): ");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) {
                sparePartToEdit.setName(newName);
            }

            System.out.print("Masukkan jumlah sparepart baru (biarkan kosong jika tidak ingin diubah): ");
            String newQuantity = scanner.nextLine();
            if (!newQuantity.isEmpty()) {
                sparePartToEdit.setQuantity(Integer.parseInt(newQuantity));
            }

            sparePartService.update(id, sparePartToEdit);
            System.out.println("Sparepart berhasil diperbarui.");
        } else {
            System.out.println("Sparepart dengan ID tersebut tidak ditemukan.");
        }
    }

    static void deleteSparePart(Scanner scanner) {
        System.out.print("Masukkan ID sparepart yang ingin dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        SparePart sparePartToDelete = null;
        for (SparePart part : sparePartService.getAll()) {
            if (part.getId() == id) {
                sparePartToDelete = part;
                break;
            }
        }

        if (sparePartToDelete != null) {
            sparePartService.delete(id);
            System.out.println("Sparepart berhasil dihapus.");
        } else {
            System.out.println("Sparepart dengan ID tersebut tidak ditemukan.");
        }
    }
}