import java.util.Scanner;

public class AplikasiBookingRuangan {

    static Scanner sc = new Scanner(System.in);

    static boolean menu = true;

    // Array Data user yang mendaftar
    static String[][] userDatabase = new String[1000][2];
    static int userCount = 0;

    // Array Form-Booking
    static String[][] listDataBooking = new String[3][3]; // 3 booking & 3 data
    static int bookingCount = 0;

    // Array List Booking
    // Array Status ruangan ketika terbooking atau tidak
    static boolean[] ruanganTerbooking = new boolean[3];
    // Array Harga Ruangan
    static int[] hargaRuangan = { 400000, 500000, 600000 };
    // Array untuk menyimpan data pembooking setiap ruangan
    static String[][] dataPembooking = new String[3][3];

    // Array Reservasi Ruangan Rapat
    static boolean[] ruangRptTerbooking = new boolean[3];

    // Array Checkin
    // Array Jam checkin
    static int jamCheckinGlobal;
    // Array bulan
    static String[] arrayBulan = { "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus",
            "September",
            "Oktober", "November", "Desember" };

    // Saldo pengguna
    static int saldo = 1000000;

    // Array penilaian
    // Array Penilaian 1 -5
    static int[] arrayPenilaian = { 1, 2, 3, 4, 5 };
    // Array Feedback
    static String[] feedbackArray = new String[10];
    // Array nama pengguna
    static String[] namaPenggunaArray = new String[10];
    // Array Nilai
    static int[] nilaiArray = new int[10];
    static int feedbackCount = 0;

    /*
     * Function Tampilkan Menu
     */
    static void tampilkanMenu() {

        boolean isUserTerdaftar = false;

        do {
            System.out.println("\n==================================================");
            System.out.println("=              Silahkan Pilih Menu               =");
            System.out.println("==================================================\n");
            System.out.println("[1] Daftar Akun");
            System.out.println("[2] Login");
            System.out.println("[0] Keluar");
            System.out.print("Pilih menu: ");
            int pilihanMenu = sc.nextInt();
            sc.nextLine();

            switch (pilihanMenu) {
                case 1:
                    daftarAkun();
                    break;
                case 2:
                    isUserTerdaftar = login();
                    break;
                case 0:
                    System.out.println("Terima Kasih telah Menggunakan Aplikasi Booking Ruangan");
                    System.exit(0);
                default:
                    System.out.println("\nPilihan tidak valid. Silakan coba lagi.");
            }
        } while (!isUserTerdaftar);

        System.out.println("Anda telah berhasil login. Selamat datang di Aplikasi Booking Ruangan!\n");

    }

    /*
     * Function Daftar Akun
     */
    static void daftarAkun() {
        System.out.println("\n==================================================");
        System.out.println("=                  Daftar Akun                   =");
        System.out.println("==================================================\n");

        // Input username user
        System.out.print("Masukan Username: ");
        String username = sc.nextLine();

        // Input password user
        System.out.print("Masukan Password: ");
        String password = sc.nextLine();

        // Menambahkan akun ke array akun terdaftar
        userDatabase[userCount][0] = username;
        userDatabase[userCount][1] = password;

        // Akun +1
        userCount++;

        System.out.println("\nPendaftaran akun telah berhasil! Silahkan memulai login di Aplikasi Booking Ruangan.");
    }

    /*
     * Function Login
     */
    static boolean login() {

        int percobaanLogin = 0;
        boolean isUserTerdaftar = false;

        // Perulangan percobaan login tidak boleh lebih dari 3
        while (percobaanLogin < 3) {

            System.out.println("\n==================================================");
            System.out.println("=                     Login                      =");
            System.out.println("==================================================\n");

            // Input Username
            System.out.print("Masukan Username: ");
            String username = sc.nextLine();

            // Input Password
            System.out.print("Masukan Password: ");
            String password = sc.nextLine();

            // For-each untuk memeriksa apakah akun sudah terdaftar di array daftar akun
            // atau belum
            for (int i = 0; i < userCount; i++) {
                // Kondisi ketika akun telah terdaftar dan username password login sesuai
                if (username.equals(userDatabase[i][0]) && password.equals(userDatabase[i][1])) {
                    System.out.println("\nLogin Berhasil");
                    System.out.println("Selamat Datang " + username + " di Aplikasi Booking Ruangan\n");
                    userMenu();
                    return true;
                }
            }

            // percoban login +1
            percobaanLogin++;
            // ketika username atau password yang dimasukkan salah
            System.out.println("\nUsername atau Password yang Anda Masukkan Salah!, Silahkan coba lagi!");
            // Sisa percobaan login -1
            System.out.println("Sisa percobaan login: " + (3 - percobaanLogin));

            // Kondisi ketika user salah memasukan username atau password maka akan
            // ditawarkan untuk membuat akun
            if (percobaanLogin < 3) {
                System.out.println("\nJika anda belum memiliki akun, ketik 'y' untuk daftar akun,");
                System.out.print("Jika anda sudah mempunyai akun ketik 't' untuk melanjutkan login: ");
                String jawaban = sc.nextLine();
                // Jika input 'y' maka akan ke function daftar akun
                if (jawaban.equalsIgnoreCase("y")) {
                    // Memanggil function daftar akun
                    daftarAkun();
                    isUserTerdaftar = true;
                    // Melanjutkan login
                } else {
                    isUserTerdaftar = false;
                }
                // Ketika batas percobaan login telah habis
            } else {
                System.out.println("\nBatas percobaan login telah habis!\n");
                // Keluar dari program jika sudah melebihi batas percobaan
                System.exit(0);
            }
        }

        // Output ketika username atau password yang dimasukan salah
        System.out.println("Username atau Password yang Anda masukkan Salah. Silakan coba lagi.");
        return false;
    }

    /*
     * function tampilan menu
     */
    static void userMenu() {

        boolean isUserLogout = false;

        // do while agar user tidak kembali ke login setelah selesai mengisi suatu menu
        do {
            System.out.println("==================================================");
            System.out.println("=                      Menu                      =");
            System.out.println("==================================================\n");
            System.out.println("[1] Form Booking");
            System.out.println("[2] List Booking");
            System.out.println("[3] Reservasi Ruangan Rapat");
            System.out.println("[4] Payment Methode");
            System.out.println("[5] Checkin");
            System.out.println("[6] Checkout");
            System.out.println("[7] Beri Penilaian");
            System.out.println("[0] Logout");
            System.out.print("Pilih menu: ");
            int pilihanMenu = sc.nextInt();
            sc.nextLine();

            switch (pilihanMenu) {
                case 1:
                    formBooking();
                    break;
                case 2:
                    listBooking();
                    break;
                case 3:
                    resRapat();
                    break;
                case 4:
                    payment();
                    break;
                case 5:
                    checkin();
                    break;
                case 6:
                    checkout();
                    break;
                case 7:
                    pilihPenilaian();
                    break;
                case 0:
                    logout();
                    break;
                default:
                    System.out.println("\nInput yang anda masukan salah, silahkan untuk mencoba lagi!\n");
            }
        } while (!isUserLogout);
    }

    /*
     * function form booking
     */
    static void formBooking() {
        System.out.println("\n==================================================");
        System.out.println("=                 Form Booking                   =");
        System.out.println("==================================================\n");

        boolean isNamaValid = false;

        while (!isNamaValid) {
            System.out.print("Nama: ");
            String inputNama = sc.next();
            if (!inputNama.trim().isEmpty()) {
                isNamaValid = true;
                listDataBooking[bookingCount][0] = inputNama;
            } else {
                System.out.println("Nama tidak boleh kosong. Silakan masukkan nama lagi.\n");
            }
        }

        boolean isAlamatValid = false;

        while (!isAlamatValid) {
            System.out.print("Alamat: ");
            String alamat = sc.next();
            if (!alamat.trim().isEmpty()) {
                isAlamatValid = true;
                listDataBooking[bookingCount][1] = alamat;
            } else {
                System.out.println("Alamat tidak boleh kosong. Silakan masukkan lagi.");
            }
        }

        boolean isNoHpValid = false;

        while (!isNoHpValid) {
            System.out.print("Nomor Telepon: ");
            String noHp = sc.next().trim();

            int panjangNoHp = noHp.length();
            if (panjangNoHp == 12 || panjangNoHp == 13) {
                isNoHpValid = true;
                listDataBooking[bookingCount][2] = noHp;
            } else {
                System.out.println("Nomor telepon harus 12 atau 13 angka. Silakan masukkan lagi!\n");
            }
        }

        System.out.println("\nData Diri Anda:");
        System.out.println("-------------------------------------");
        System.out.println("Nama           : " + listDataBooking[bookingCount][0]);
        System.out.println("Alamat         : " + listDataBooking[bookingCount][1]);
        System.out.println("Nomor Telepon  : " + listDataBooking[bookingCount][2]);
        System.out.println("-------------------------------------");

        System.out.println();

        boolean konfirmasiBenar = false;

        while (!konfirmasiBenar) {
            System.out.print("Ketik (y) untuk menyimpan data atau (t) untuk membatalkan : ");
            String konfirmasi = sc.next();

            if (konfirmasi.equalsIgnoreCase("y")) {
                konfirmasiBenar = true;
                System.out.println("Data Diri Anda Telah Tersimpan");
                bookingCount++;
            } else if (konfirmasi.equalsIgnoreCase("t")) {
                konfirmasiBenar = true;
                System.out.println("Pendaftaran dibatalkan. Anda akan kembali ke menu.");
            } else {
                System.out.println("Konfirmasi tidak sesuai! Silakan coba lagi!\n");
            }
        }
        System.out.println();
    }

    /*
     * Fungsi List Booking
     */
    static void listBooking() {

        // Boolean ketika semua kamar terbooking
        boolean semuaKamarTerbooking = true;

        // For-each Status ruangan
        for (boolean status : ruanganTerbooking) {
            if (!status) {
                semuaKamarTerbooking = false;
                break;
            }
        }

        // Kondisi ketika Semua kamar terbooking
        if (semuaKamarTerbooking) {
            System.out.println("\n==================================================");
            System.out.println("=        Maaf, semua kamar telah terbooking       =");
            System.out.println("=   Silakan menunggu hingga ada kamar yang kosong =");
            System.out.println("==================================================\n");
            return;
        }

        int ruang = 0;

        // Perulangan List Booking akan keluar jika hasil true
        boolean isListBooking = false;
        while (!isListBooking) {

            System.out.println("\n==================================================");
            System.out.println("=                 List Booking                   =");
            System.out.println("==================================================\n");

            // List ruangan
            System.out.println("List ruangan yang tersedia pada hari ini\n");

            // Fasilitas ruangan
            String[][] fasilitasRuangan = {
                    // Fasilitas ruang 1
                    { "1. Kamar tidur kapasitas 2 orang",
                            "2. Kamar mandi dalam dengan air panas",
                            "3. Handuk dan peralatan mandi - \n\nHarga: Rp. 400.000" },
                    // Fasilitas ruang 2
                    { "1. Tempat tidur dengan kapasitas 1 orang ",
                            "2. Kamar mandi dalam dengan air panas ",
                            "3. Snack yang telah disediakan didalam kamar \n\n - Harga: Rp. 500.000" },
                    // Fasilitas ruang 3
                    { "1. Kamar tidur kapasitas 1 orang ",
                            "2. Kamar mandi dalam dengan air panas ",
                            "3. Ruangan ber-AC \n\n- Harga: Rp. 600.000" }
            };

            // For-each fasilitas ruangan
            for (int i = 0; i < fasilitasRuangan.length; i++) {

                // Kondisi ketika ruangan belum terbooking
                if (!ruanganTerbooking[i]) {
                    System.out.println("\nRuangan " + (i + 1) + ":");
                    // For-each untuk menampilkan fasilitas ruangan yang belum terbooking
                    for (int j = 0; j < fasilitasRuangan[i].length; j++) {
                        System.out.println("  " + fasilitasRuangan[i][j]);
                    }

                    // Kondisi ketika ruangan telah terbooking
                } else {
                    System.out.println("\nRuangan " + (i + 1) + " (Terbooking)");
                }
            }

            // Pilihan ruangan oleh user
            System.out.print(
                    "\nSilahkan masukan ruangan yang ingin anda pesan (1/2/3) atau (0) untuk membatalkan pilihan: ");
            ruang = sc.nextInt();

            // Kondisi ketika pilihan selain 1 - 3 dan 0
            if ((ruang > 3 || ruang < 1) && ruang != 0) {
                System.out.println(
                        "\nRuang yang anda pilih tidak tersedia, silahkan untuk mencoba kembali!");

                // Kondisi ketika pilihan benar dan ruangan belum terbooking
            } else if (ruang != 0 && !ruanganTerbooking[ruang - 1]) {
                isListBooking = true;
                // Kondisi ketika pengguna memilih ruangan yang sudah dibooking
            } else if (ruang != 0 && ruanganTerbooking[ruang - 1]) {
                System.out.println("\nRuangan " + ruang + " telah terbooking, silahkan pilih ruangan yang lain.");
                // Kondisi ketika pengguna memilih 0 untuk membatalkan pilihan ruangan
            } else if (ruang == 0) {
                System.out.println("\nAnda membatalkan pemilihan ruangan.\n");
                return;
            }
        }

        // Status ruangan yang telah dibooking user menjadi true
        ruanganTerbooking[ruang - 1] = true;

        // Menyimpan data pembooking
        dataPembooking[ruang - 1][0] = listDataBooking[bookingCount - 1][0]; // Nama
        dataPembooking[ruang - 1][1] = listDataBooking[bookingCount - 1][1]; // Alamat
        dataPembooking[ruang - 1][2] = listDataBooking[bookingCount - 1][2]; // Nomor Telepon
        String konfirmasiRuangan;
        Boolean isKonfirmasiRuangan = false;

        // Perulangan konfirmasi pemilihan ruangan
        while (!isKonfirmasiRuangan) {

            System.out.println("\n==================================================");
            System.out.println("=                   Konfirmasi                   =");
            System.out.println("==================================================");

            // Pilihan user
            System.out.print("\nJika anda telah yakin dengan ruangan " + ruang + " ketik 'y', jika batal ketik 't': ");
            konfirmasiRuangan = sc.next();

            // Kondisi ketika user memilih y maka ruangan berhasil dipesan
            if (konfirmasiRuangan.equalsIgnoreCase("y")) {
                System.out.println("\nAnda telah berhasil memesan ruangan " + ruang);
                isKonfirmasiRuangan = true;

                // Menampilkan data pembooking
                System.out.println("\nData Pembooking Ruang " + ruang + ":");
                System.out.println("Nama           : " + dataPembooking[ruang - 1][0]);
                System.out.println("Alamat         : " + dataPembooking[ruang - 1][1]);
                System.out.println("Nomor Telepon  : " + dataPembooking[ruang - 1][2]);
            } else if (konfirmasiRuangan.equalsIgnoreCase("t")) {
                System.out.println("\nAnda membatalkan pemesanan ruangan " + ruang);

                // Status ruangan yang telah dibooking user menjadi false
                ruanganTerbooking[ruang - 1] = false;
                isKonfirmasiRuangan = true;

                // Kondisi ketika user memilih input selain y dan t
            } else {
                System.out.println("\nInput yang anda masukan salah silahkan coba lagi!");
            }
        }
    }

    /*
     * Fungsi Reservasi Ruangan Rapat
     */
    static void resRapat() {

        // Boolean ketika semua Ruangan raoat terbooking
        boolean semuaRuangRapatTerbooking = true;

        // For-each Status ruangan rapat
        for (boolean status : ruangRptTerbooking) {
            if (!status) {
                semuaRuangRapatTerbooking = false;
                break;
            }
        }

        // Kondisi ketika semua ruang rapat terbooking
        if (semuaRuangRapatTerbooking) {
            System.out.println("\n==================================================");
            System.out.println("=       Maaf, semua Ruangan telah terbooking      =");
            System.out.println("=   Silakan menunggu hingga ada kamar yang kosong =");
            System.out.println("==================================================\n");
            return;
        }

        int ruangRapat = 0;

        // Perulangan Reservasi Ruangan Rapat akan keluar jika hasil true
        boolean isResRapat = false;
        while (!isResRapat) {

            System.out.println("\n==================================================");
            System.out.println("=           Reservasi Ruangan Rapat              =");
            System.out.println("==================================================\n");

            // List ruangan
            System.out.println("List ruangan yang tersedia pada hari ini\n");

            // Fasilitas ruangan
            String[][] fasilitasRapat = {
                    // Fasilitas ruang rapat 1
                    { "1. Kapasitas ruangan untuk 8 orang",
                            "2. Ruangan Ber-AC",
                            "3. 1 Buah Proyektor- \n\nHarga: Rp. 400.000" },
                    // Fasilitas ruang rapat 2
                    { "1. Kapasitas ruangan untuk 10 orang ",
                            "2. Ruangan Ber-AC ",
                            "3. 2 Buah Proyektor \n\n - Harga: Rp. 500.000" },
                    // Fasilitas ruang rapat 3
                    { "1. Kapasitas ruaangan untuk 12 orang",
                            "2. 3 Buah Proyektor ",
                            "3. Ruangan Ber-AC \n\n- Harga: Rp. 600.000" }
            };

            // For-each fasilitas ruangan rapat
            for (int i = 0; i < fasilitasRapat.length; i++) {

                // Kondisi ketika ruangan rapat belum terbooking
                if (!ruangRptTerbooking[i]) {
                    System.out.println("\nRuangan " + (i + 1) + ":");
                    // For-each untuk menampilkan fasilitas ruangan rapat yang belum terbooking
                    for (int j = 0; j < fasilitasRapat[i].length; j++) {
                        System.out.println("  " + fasilitasRapat[i][j]);
                    }

                    // Kondisi ketika ruangan rapat telah terbooking
                } else {
                    System.out.println("\nRuangan " + (i + 1) + " (Terbooking)");
                }
            }

            // Input pengguna
            System.out.print(
                    "\nSilahkan masukan ruangan yang ingin anda pesan (1/2/3) atau (0) untuk membatalkan pilihan: ");
            ruangRapat = sc.nextInt();

            // Kondisi ketika pilihan selain 1 - 3 dan 0
            if ((ruangRapat > 3 || ruangRapat < 1) && ruangRapat != 0) {
                System.out.println(
                        "\nRuang yang anda pilih tidak tersedia, silahkan untuk mencoba kembali!");

                // Kondisi ketika pilihan benar dan ruangan belum terbooking
            } else if (ruangRapat != 0 && !ruangRptTerbooking[ruangRapat - 1]) {
                isResRapat = true;
                // Kondisi ketika pengguna memilih ruangan yang sudah dibooking
            } else if (ruangRapat != 0 && ruangRptTerbooking[ruangRapat - 1]) {
                System.out.println("\nRuangan " + ruangRapat + " telah terbooking, silahkan pilih ruangan yang lain.");
                // Kondisi ketika pengguna memilih 0 untuk membatalkan pilihan ruangan
            } else if (ruangRapat == 0) {
                System.out.println("\nAnda membatalkan pemilihan ruangan.\n");
                return;
            }
        }

        // Status ruangan rapat yang telah dibooking user menjadi true
        ruangRptTerbooking[ruangRapat - 1] = true;

        String konfirmasiResRapat;
        Boolean isKonfirmasiResRapat = false;

        // Perulangan konfirmasi pemilihan ruangan rapat
        while (!isKonfirmasiResRapat) {

            System.out.println("\n==================================================");
            System.out.println("=                   Konfirmasi                   =");
            System.out.println("==================================================");

            // Pilihan user
            System.out.print(
                    "\nJika anda telah yakin dengan ruangan " + ruangRapat + " ketik 'y', jika batal ketik 't': ");
            konfirmasiResRapat = sc.next();

            // Kondisi ketika user memilih y maka ruangan rapat berhasil dipesan
            if (konfirmasiResRapat.equalsIgnoreCase("y")) {
                System.out.println("\nAnda telah berhasil memesan ruangan " + ruangRapat);
                isKonfirmasiResRapat = true;

                // Kondisi ketika user memilih t maka ruangan rapat batal dipesan
            } else if (konfirmasiResRapat.equalsIgnoreCase("t")) {
                System.out.println("\nAnda membatalkan pemesanan ruangan " + ruangRapat);

                // Status ruangan rapat yang telah dibooking user menjadi false
                ruangRptTerbooking[ruangRapat - 1] = false;
                isKonfirmasiResRapat = true;

                // Kondisi ketika user memilih input selain y dan t
            } else {
                System.out.println("\nInput yang anda masukan salah silahkan coba lagi!");
            }
        }
    }

    /*
     * Function Checkin
     */
    static void checkin() {

        System.out.println("\n==================================================");
        System.out.println("=                    Checkin                     =");
        System.out.println("==================================================");

        // Bulan, tanggal, jam checkin
        int bulanCheckin, tanggalCheckin, jamCheckin;
        // Nama bulan, am-pm checkin
        String namaBulanCheckin = "", ampmCheckin = "";

        // input tahun checkin
        System.out.println();
        int tahunCheckin = 0;

        while (tahunCheckin != 2023) {

            System.out.print("Masukan Tahun Checkin (YYYY): ");
            tahunCheckin = sc.nextInt();

            if (tahunCheckin != 2023) {
                System.out.println("\nTahun Checkin yang Anda Masukan Tidak Valid!\n");
            }
        }

        // Bulan checkin
        boolean isBulanValid = false;

        do {
            // input bulan
            System.out.print("Masukkan bulan checkin Anda: ");
            bulanCheckin = sc.nextInt();

            // Menyimpan bulan berdasarkan nomor bulan
            if (bulanCheckin >= 1 && bulanCheckin <= 12) {
                namaBulanCheckin = arrayBulan[bulanCheckin - 1];
                isBulanValid = true;

            } else {
                System.out.println("\nBulan tidak valid!\n");
            }
        } while (!isBulanValid);

        // maksimal tanggal
        int maxTanggalCheckin = 31;

        // Ketika bulan februari
        if (bulanCheckin == 2) {
            // Tahun kabisat
            if ((tahunCheckin % 4 == 0 && tahunCheckin % 100 != 0) || (tahunCheckin % 400 == 0)) {
                maxTanggalCheckin = 29;
                // Bukan tahun kabisat
            } else {
                maxTanggalCheckin = 28;
            }
            // Bulan 4, 6, 9, 11
        } else if (bulanCheckin == 4 || bulanCheckin == 6 || bulanCheckin == 9 || bulanCheckin == 11) {
            maxTanggalCheckin = 30;
        }

        do {
            // input tanggal checkin
            System.out.print("Masukan Tanggal Checkin (1-" + maxTanggalCheckin + "): ");
            tanggalCheckin = sc.nextInt();

            // ketika input salah
            if (tanggalCheckin < 1 || tanggalCheckin > maxTanggalCheckin) {
                System.out.println("Tanggal yang Anda Masukkan Salah untuk Bulan " + namaBulanCheckin + "!");
            }
        } while (tanggalCheckin < 1 || tanggalCheckin > maxTanggalCheckin);

        do {
            // Jam checkin
            System.out.print("Masukan Jam Checkin (format 24 jam): ");
            jamCheckin = sc.nextInt();

            // Ketika input jam salah
            if (jamCheckin < 0 || jamCheckin >= 24) {
                System.out.println("Jam yang Anda Masukkan Salah!");
            }
        } while (jamCheckin < 0 || jamCheckin >= 24);

        // Am-Pm checkin
        ampmCheckin = jamCheckin < 12 ? "AM" : "PM";
        if (jamCheckin > 12) {
            jamCheckin -= 12;
        }

        // waktu checkout
        int waktuCheckout = (jamCheckin + 12) % 24;

        // Am-Pm checkout
        String ampmCheckout = waktuCheckout < 12 ? "AM" : "PM";
        if (waktuCheckout > 12) {
            waktuCheckout -= 12;
        }

        // Input konfirmasi checkin
        String konfirmCheckin;
        boolean isConfirm = false;

        while (!isConfirm) {
            System.out.print("\nApakah anda yakin ingin memulai checkin?(y/t): ");
            konfirmCheckin = sc.next();

            // jika y
            if (konfirmCheckin.equalsIgnoreCase("y")) {

                // Menyimpan jam checkin ke variabel global
                jamCheckinGlobal = jamCheckin;

                // Checkin berhasil
                System.out.println("\nSelamat checkin anda berhasil\n");

                System.out.println(
                        "Anda Memulai Checkin Pada " + tanggalCheckin + " " + namaBulanCheckin + " " + tahunCheckin +
                                " Pada Jam " + jamCheckin + " " + ampmCheckin);
                System.out.println("Anda harus Check-out sebelum jam " + waktuCheckout + " " +
                        ampmCheckout);
                isConfirm = true;

                // Checkin dibatalkan
            } else if (konfirmCheckin.equalsIgnoreCase("t")) {
                System.out.println("\nAnda batal checkin\n");
                isConfirm = true;

            } else {
                System.out.println("\nInput yang anda masukan salah, silahkan coba kembali!\n");
            }
        }
    }

    /*
     * Function Checkout
     */
    static void checkout() {
        System.out.println("\n==================================================");
        System.out.println("=                   Checkout                     =");
        System.out.println("==================================================\n");

        // Konfirmasi checkout
        boolean konfirmasiCheckout = false;
        while (!konfirmasiCheckout) {
            System.out.print("\nSilahkan Ketik (y) Untuk Checkout dan (t) untuk batal: ");
            String konfirmasi = sc.next();

            // Input y
            if (konfirmasi.equalsIgnoreCase("y")) {
                konfirmasiCheckout = true;
                System.out.println();

                // Input T
            } else if (konfirmasi.equalsIgnoreCase("t")) {
                konfirmasiCheckout = true;
            } else {
                System.out.println("\nKonfirmasi tidak sesuai! Silahkan coba lagi!\n");
            }
        }

        // Input waktu checkout
        int jamCheckout;

        do {
            System.out.print("Masukan Jam Checkout (format 24 jam): ");
            jamCheckout = sc.nextInt();

            if (jamCheckout < 0 || jamCheckout >= 24) {
                System.out.println("Jam checkout yang Anda Masukan Salah!\n");
            }

        } while (jamCheckout < 0 || jamCheckout >= 24);

        // Am-Pm Checkout
        String ampmCheckout = jamCheckout < 12 ? "AM" : "PM";

        // Mengambil jam checkin dari variabel global
        int jamCheckin = jamCheckinGlobal;

        // Menambah 24 jam jika waktu checkout lebih kecil dari waktu checkin
        if (jamCheckout < jamCheckin) {
            jamCheckout += 24;
        }

        // Menghitung durasi menginap
        int durasiInapJam = jamCheckout - jamCheckin;

        if (durasiInapJam <= 12) {
            // User menginaop <= 12 jam
            System.out.println("Anda telah menginap selama " + durasiInapJam + " jam. Tidak ada denda.\n");

        } else {
            // User menginap lebih dari 12 Jam
            int denda = (durasiInapJam - 12) * 50000; // Denda 50.000 per jam melebihi 12 jam
            System.out.println("Anda telah menginap selama " + durasiInapJam +
                    " jam. Anda dikenakan denda sebesar Rp " + denda + ".\n");
        }

    }

    /*
     * Fungsi Payment Methode
     */
    static void payment() {
        System.out.println("\n==================================================");
        System.out.println("=               Payment Methode                  =");
        System.out.println("==================================================\n");

        // Input nomor ruangan yang akan dibayar
        System.out.print("Masukkan nomor ruangan yang akan dibayar (1/2/3): ");
        int nomorRuangan = sc.nextInt();
        sc.nextLine();

        // Kondisi jika nomor ruangan tidak valid
        if (nomorRuangan < 1 || nomorRuangan > 3 || !ruanganTerbooking[nomorRuangan - 1]) {
            System.out.println("\nNomor ruangan tidak valid atau belum terbooking.");
            return;
        }

        // Harga ruangan yang harus dibayar
        int totalHarga = hargaRuangan[nomorRuangan - 1];

        // Menampilkan detail ruangan, total harga, dan saldo pengguna
        System.out.println("\nDetail Ruangan yang akan dibayar:");
        System.out.println("Nomor Ruangan: " + nomorRuangan);
        System.out.println("Harga Ruangan: Rp. " + totalHarga);
        System.out.println("Saldo Pengguna: Rp. " + saldo);

        // Pilihan metode pembayaran
        System.out.println("\nPilih metode pembayaran:");
        System.out.println("[1] Kartu Kredit");
        System.out.println("[2] Transfer Bank");
        System.out.print("Masukkan pilihan (1/2): ");
        int metodePembayaran = sc.nextInt();
        sc.nextLine();

        // Proses pembayaran berdasarkan metode yang dipilih
        switch (metodePembayaran) {
            case 1:
                processCreditCardPayment(nomorRuangan, totalHarga);
                break;
            case 2:
                processBankTransferPayment(nomorRuangan, totalHarga);
                break;
            default:
                System.out.println("\nPilihan metode pembayaran tidak valid.");
                return;
        }
    }

    /*
     * Fungsi untuk proses pembayaran dengan Kartu Kredit
     */
    static void processCreditCardPayment(int nomorRuangan, int totalHarga) {
        // Memastikan saldo mencukupi sebelum proses pembayaran
        if (saldo < totalHarga) {
            System.out.println("\nSaldo tidak mencukupi untuk pembayaran. Silakan isi saldo terlebih dahulu.");
            return;
        }

        // Memproses pembayaran dengan Kartu Kredit
        System.out.println("\nProses pembayaran dengan Kartu Kredit sedang diproses...");
        System.out.println("Pembayaran dengan Kartu Kredit berhasil untuk ruangan " + nomorRuangan + ".");

        // Mengurangkan saldo setelah pembayaran
        saldo -= totalHarga;

        // Menyelesaikan pembayaran
        completePayment(nomorRuangan);
    }

    /*
     * Fungsi untuk proses pembayaran dengan Transfer Bank
     */
    static void processBankTransferPayment(int nomorRuangan, int totalHarga) {
        // Memastikan saldo mencukupi sebelum proses pembayaran
        if (saldo < totalHarga) {
            System.out.println("\nSaldo tidak mencukupi untuk pembayaran. Silakan isi saldo terlebih dahulu.");
            return;
        }

        // Memproses pembayaran dengan Transfer Bank
        System.out.println("\nAnda telah memilih Transfer Bank. Silakan transfer ke rekening yang berikut");

        // Menampilkan informasi transfer
        System.out.println("Nomor Rekening Tujuan: 1234567890");
        System.out.println("Jumlah yang harus ditransfer: Rp. " + totalHarga);

        // Mengurangkan saldo setelah pembayaran
        saldo -= totalHarga;

        // Menyelesaikan pembayaran
        completePayment(nomorRuangan);
    }

    /*
     * Fungsi untuk menyelesaikan pembayaran dan mengupdate status ruangan
     */
    static void completePayment(int nomorRuangan) {

        System.out.println("\nPembayaran berhasil untuk ruangan " + nomorRuangan + ".");
        System.out.println("Sisa saldo anda: Rp. " + saldo);

        ruanganTerbooking[nomorRuangan - 1] = false;

    }

    /*
     * Function Pilihan penilaian
     */
    static void pilihPenilaian() {
        int pilihanMenu;

        do {
            System.out.println("\n============================================================");
            System.out.println("=                        Penilaian                         =");
            System.out.println("============================================================\n");
            System.out.println("[1] Beri Penilaian dan Feedback");
            System.out.println("[2] Lihat Penilaian dan Feedback Pengguna");
            System.out.println("[0] Keluar");
            System.out.print("Pilih menu (1/2/0): ");
            pilihanMenu = sc.nextInt();

            switch (pilihanMenu) {
                case 1:
                    penilaian();
                    break;
                case 2:
                    lihatFeedback();
                    break;
                case 0:
                    System.out.println("Kembali ke menu");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
            }
        } while (pilihanMenu != 0);
    }

    /*
     * Function Penilaian
     */
    static void penilaian() {
        int penilaian = 0;
        boolean isPenilaian = false;

        // Penilaian yang akan dipilih pengguna
        while (!isPenilaian) {
            System.out.println("\n=============================================================");
            System.out.println("=                        Penilaian                          =");
            System.out.println("=============================================================\n");

            // input nama pengguna
            System.out.print("Masukkan Nama Anda: ");
            namaPenggunaArray[feedbackCount] = sc.next();
            System.out.println();

            // input penilaian pengguna
            System.out.println("Beri penilaian berdasarkan pengalaman anda terhadap aplikasi ini");
            System.out.println("1 = Sangat Tidak Puas");
            System.out.println("2 = Tidak Puas");
            System.out.println("3 = Biasa Saja");
            System.out.println("4 = Puas");
            System.out.println("5 = Sangat Puas");
            System.out.print("Silahkan isi penilaian sesuai kepuasan anda: ");
            penilaian = sc.nextInt();

            // Pilihan user
            for (int i : arrayPenilaian) {
                if (penilaian == i) {
                    isPenilaian = true;
                    break;
                }
            }

            // Input user ketika salah
            if (!isPenilaian) {
                System.out.println("\nAngka yang anda masukan salah, silahkan coba lagi!\n");
            }
        }

        // input feedback oleh pengguna
        System.out.println("\n=============================================================");
        System.out.println("=                         feedback                          =");
        System.out.println("=============================================================\n");
        System.out.println("Kami ingin mendengar penilaian anda, silahkan berikan feedback / saran anda: ");
        sc.nextLine();
        String feedback = sc.nextLine();

        // Menambahkan feedback
        feedbackArray[feedbackCount] = feedback;
        // Menambahkan Nilai Penngguna
        nilaiArray[feedbackCount] = penilaian;
        // Feedback +1
        feedbackCount++;

        System.out.println("\nFeedback anda telah tersimpan");
        System.out.println("Terima kasih telah meluangkan waktu untuk memberi feedback.");

        System.out.println("\n============================================================");
        System.out.println("=                       System out                         =");
        System.out.println("============================================================\n");

    }

    /*
     * Function lihat feedback
     */
    static void lihatFeedback() {

        System.out.println("\n=============================================================");
        System.out.println("=                    Daftar Feedback                        =");
        System.out.println("=============================================================\n");

        // Ketika belum ada feedback
        if (feedbackCount == 0) {
            System.out.println("\nBelum ada feedback yang tersimpan.");
            return;
        }

        // Nilai Rata-rata
        double totalNilai = 0;

        // Menampilkan data feedback yang tersimpan
        for (int i = 0; i < feedbackCount; i++) {

            // Rumus total nilai
            totalNilai += nilaiArray[i];

            System.out.println("\nFeedback Pengguna ke-" + (i + 1) + ":");
            System.out.println("Nama Pengguna: " + namaPenggunaArray[i]);
            System.out.println("Nilai: " + nilaiArray[i]);
            System.out.println("Feedback: " + feedbackArray[i]);

        }

        // Menghitung nilai rata-rata
        double rataRata = totalNilai / feedbackCount;
        System.out.printf("\nRata-rata nilai: %.2f\n", rataRata);
    }

    /*
     * Function Logout
     */
    static void logout() {

        System.out.println("\n==================================================");
        System.out.println("=              Logout dari aplikasi              =");
        System.out.println("==================================================\n");

        String knfirmLogout;
        Boolean isLogout = false;

        while (!isLogout) {

            System.out.print("Apakah anda yakin ingin keluar?(y/t): ");
            knfirmLogout = sc.next();

            if (knfirmLogout.equalsIgnoreCase("y")) {
                System.out.println("\nTerimakasih telah menggunakan Aplikasi Booking Ruangan!");
                isLogout = true;
                tampilkanMenu();

            } else if (knfirmLogout.equalsIgnoreCase("t")) {
                System.out.println("Anda kembali ke menu.");
                isLogout = true;

            } else {
                System.out.println("Input anda tidak valid, silahkan ulang kembali!\n");
            }
        }
    }

    public static void main(String[] args) {
        do {
            tampilkanMenu();
        } while (menu);
    }

}
