# Eshop
Deployment link: https://kaindraa-adpro-module-2.koyeb.app/

---
### Kaindra Rizq Sachio
### 2306274964   
### CSCM602223 - Pemrograman Lanjut

---
## Module 1: Coding Standards
### Reflection 1
> You already implemented two new features using Spring Boot. Check again your source code and evaluate the coding standards that you have learned in this module. Write clean code principles and secure coding practices that have been applied to your code.  If you find any mistake in your source code, please explain how to improve your code. Please write your reflection inside the repository's README.md file.

#### 1. Clean Code & Secure Coding yang Sudah Saya Terapkan
- **Penamaan Kelas dan Metode**: Kode saya sudah menggunakan nama yang deskriptif seperti `Product`, `ProductService`, dan `create`.
- **Pemisahan Tanggung Jawab**: Terdapat pemisahan jelas antara `Repository` (pengelolaan data) dan `Service` (logika bisnis) pada code saya.

#### 2. Clean Code & Secure Coding yang Belum Diterapkan dan Mitigasinya (3 poin)
1. **Validasi Input**  
   Belum ada validasi terhadap `productName` (misalnya, tidak boleh kosong) atau `productQuantity` (tidak boleh bernilai negatif).  
   **Mitigasi**: Saya dapat menambahkan logika validasi di layer Service atau Controller untuk mencegah data yang tidak valid.
2. **Penanganan Null**  
   Saat `findById` mengembalikan `null`, memanggil method pada objek tersebut akan memicu `NullPointerException`.  
   **Mitigasi**: Saya dapat menambahkan pengecekan `if (existingProduct == null)` sebelum melakukan operasi pada objek tersebut.
3. **Error Handling**  
   Saat terjadi kesalahan, metode seperti `findById` hanya mengembalikan `null` tanpa informasi tambahan. Ini dapat menyulitkan debugging dan tidak memberikan respons yang jelas kepada pengguna.  
   **Mitigasi**: Saya dapat mengganti pengembalian `null` dengan melempar `CustomException` agar error lebih terstruktur dan mudah ditangani.

### Reflection 2

#### 1. Unit Testing
> After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure that our unit tests are enough to verify our program? It would be good if you learned about code coverage. Code coverage is a metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean your code has no bugs or errors?
- Setelah menulis unit test, saya merasa lebih yakin dengan kode saya. Saya juga merasa senang ketika kode saya berhasil melewati semua unit test yang saya buat.
- Dalam membuat unit test, tidak ada batasan pasti, yang penting sudah mencakup case penting dan edge case untuk memastikan semua skenario teruji.
- Jika saya mempunyai 100% code coverage, belum tentu kode saya bebas dari bug atau error. Code coverage hanya mengukur bagian kode yang dieksekusi dalam pengujian, tetapi tidak menjamin bahwa semua logika atau skenario telah diuji dengan benar.

#### 2. Functional Testing dan Clean Code
> Suppose that after writing the CreateProductFunctionalTest.java along with the corresponding test case, you were asked to create another functional test suite that verifies the number of items in the product list. You decided to create a new Java class similar to the prior functional test suites with the same setup procedures and instance variables.
What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the code cleaner! Please write your reflection inside the repository's README.md file.
Ketika membuat functional test baru untuk memverifikasi jumlah item di daftar produk, dapat terjadi duplikasi kode, seperti setup dan inisialisasi variabel yang sama di setiap kelas test. Ini melanggar prinsip DRY (Don’t Repeat Yourself) dan dapat menurunkan kualitas kode karena perubahan harus dilakukan di banyak tempat dan menyebabkan terjadinya inkonsistensi.

Untuk mengatasi hal ini, saya dapat menggunakan kelas dasar (base test class) untuk menyimpan setup umum, sehingga functional test baru hanya perlu mengambil dari itu.

---
## Module 2: Continuous Integration, DevOps

### Reflection:

#### Code Quality Issues
> List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.

Code Quality Issues yang saya perbaiki adalah:
1. **Document empty method body**. Issue ini berkaitan dengan fungsi kosong yang tidak diberi comment. Untuk mengatasinya, saya menulis comment di fungsi tersebut untuk memperjelas bahwa fungsi tersebut sengaja dikosongkan
2. **The JUnit 5 test method name**. Issue ini berkaitan dengan penamaan method testing saya yang tidak sesuai dengan standard Java naming conventions (Camel case). Untuk mengatasinya, saya mengubah nama fungsi saya mengikuti aturan Camel case.
3. **Unnecessary modifier 'public' on interface method**. Issue ini berkaitan dengan redundansi sintaks oleh modifier public di class interface. Secara implisit, method di dalam class interface sudah 'public'. Untuk mengatasinya, saya menghapus semua modifier method public di class interface ``ProductService``

#### Implementation of CI/CD
> Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)

Ya, kode saya sudah memenuhi definisi Continuous Integration dan Continuous Deployment. Untuk CI, saya sudah menerapkan unit-test, Menjalankan analisis kode Scorecard (Code Security) dan PMD (Code quality). Untuk CD, saya sudah menerapkan deploy otomatis setiap ada push baru ke repository saya. Hal-hal tersebut memastikan setiap perubahan kode saya dapat langsung diuji, dianalisis, dan dideploy secara otomatis.
Tetapi, masih banyak penerapan lain yang dapat diterapkan di kode saya. CI/CD sangatlah luas dan tidak terbatas pada hal-hal yang saya sebutkan.

### Code Coverage
![image](https://github.com/user-attachments/assets/40b77fbc-f767-4c82-a81b-4f85a60294e6)



