<h1 align="center">Program Manajemen Servis Elektronik Sederhana</h1>

- [Structure](#structure)
- [Flow](#flow)
- [Usage Examples](#usage-examples)

## Structure
- service
  - [`Service.java`](./service/Service.java): logika utama program (navigasi menu, operasi CRUD)
  - [`CRUDService.java`](./service/CRUDService.java): Interface yang mendefinisikan operasi dasar buat CRUD pada objek generik, jadi semua service yang mengimplementasikannya punya metode yang konsisten untuk CRUD-nya
- model
  - [`RepairSchedule.java`](./model/RepairSchedule.java): Class yang isinya jadwal servis (informasi tentang pelanggan, perangkat, dan tanggal servis)
  - [`Technician.java`](./model/Technician.java): Superclass yang isinya teknisi yang tersedia untuk servis
  - [`PhoneTechnician.java`](./model/PhoneTechnician.java): Subclass dari `Technician` yang khusus untuk teknisi ponsel, termasuk spesifikasi sistem operasi yang dikuasai
  - [`ComputerTechnician.java`](./model/ComputerTechnician.java): Subclass dari `Technician` yang khusus untuk teknisi komputer yang memiliki keahlian dalam hardware komputer
  - [`SparePart.java`](./model/SparePart.java): Class yang isinya sparepart yang tersedia

## Flow
1. Pas buka program, user langsung lihat menu utama buat ngatur jadwal servis, teknisi, atau sparepart.
2. User tinggal pilih kategori yang mau diurus, terus nanti muncul sub-menu buat liat, nambah, ngedit, atau hapus data.
3. Habis selesai, user bisa balik ke menu utama atau keluar dari aplikasinya.

## Usage Examples
contoh di kategori teknisi (class Technician). Untuk kategori lainnya sama aja, cuma beda input.

### 1. Read
![image](https://github.com/user-attachments/assets/196d598a-b7c9-4a2e-971e-b91db24d90a5)

### 2. Create
Contoh nambah teknisi baru yang khusus HP Android
![image](https://github.com/user-attachments/assets/ad26d143-5b52-4e44-a3b6-e655cd66c745)

### 3. Update
![image](https://github.com/user-attachments/assets/9a60bb6c-73ff-4438-8a50-2f229e966b4a)

### 4. Delete
![image](https://github.com/user-attachments/assets/ef595dd8-cb40-41e5-92c1-6506c5f7d013)

