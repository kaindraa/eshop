# Eshop   
### Kaindra Rizq Sachio
### 2306274964  
### CSCM602223 - Pemrograman Lanjut
# Reflection 1

## 1. Clean Code & Secure Coding yang Sudah Saya Terapkan
- **Penamaan Kelas dan Metode**: Kode saya sudah menggunakan nama yang deskriptif seperti `Product`, `ProductService`, dan `create`.
- **Pemisahan Tanggung Jawab**: Terdapat pemisahan jelas antara `Repository` (pengelolaan data) dan `Service` (logika bisnis) pada code saya.

## 2. Clean Code & Secure Coding yang Belum Diterapkan dan Mitigasinya (3 poin)
1. **Validasi Input**  
   Belum ada validasi terhadap `productName` (misalnya, tidak boleh kosong) atau `productQuantity` (tidak boleh bernilai negatif).  
   **Mitigasi**: Saya dapat menambahkan logika validasi di layer Service atau Controller untuk mencegah data yang tidak valid.
2. **Penanganan Null**  
   Saat `findById` mengembalikan `null`, memanggil method pada objek tersebut akan memicu `NullPointerException`.  
   **Mitigasi**: Saya dapat menambahkan pengecekan `if (existingProduct == null)` sebelum melakukan operasi pada objek tersebut.
3. **Error Handling**  
   Saat terjadi kesalahan, metode seperti `findById` hanya mengembalikan `null` tanpa informasi tambahan. Ini dapat menyulitkan debugging dan tidak memberikan respons yang jelas kepada pengguna.  
   **Mitigasi**: Saya dapat mengganti pengembalian `null` dengan melempar `CustomException` agar error lebih terstruktur dan mudah ditangani.






