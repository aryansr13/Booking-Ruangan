import java.util.Scanner;

public class AplikasiBookingRuangan05 {

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

    // Array Reservasi Ruangan Rapat
    static boolean[] ruangRptTerbooking = new boolean[3];

    // Array Checkin
    // Array Jam checkin
    static int jamCheckinGlobal;
    // Array bulan
    static String[] arrayBulan = { "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus",
            "September",
            "Oktober", "November", "Desember" };

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

        System.out.print("Masukan Username: ");
        String username = sc.nextLine();

        System.out.print("Masukan Password: ");
        String password = sc.nextLine();

        // Menambahkan informasi akun ke database
        userDatabase[userCount][0] = username;
        userDatabase[userCount][1] = password;

        userCount++;

        System.out.println("\nPendaftaran akun telah berhasil! Silahkan memulai login di Aplikasi Booking Ruangan.");
    }

    static boolean login() {
        System.out.println("\n==================================================");
        System.out.println("=                     Login                      =");
        System.out.println("==================================================\n");

        int percobaanLogin = 0;

        while (percobaanLogin < 3) {

            System.out.print("Masukan Username: ");
            String username = sc.nextLine();

            System.out.print("Masukan Password: ");
            String password = sc.nextLine();

            // Memeriksa apakah informasi login sesuai dengan database
            for (int i = 0; i < userCount; i++) {

                if (username.equals(userDatabase[i][0]) && password.equals(userDatabase[i][1])) {
                    System.out.println("\nLogin Berhasil");
                    System.out.println("Selamat Datang " + username + " di Aplikasi Booking Ruangan\n");
                    userMenu();
                    return true;
                } else {
                    percobaanLogin++;
                    System.out.println("\nUsername atau Password yang Anda Masukkan Salah!, Silahkan coba lagi!");
                    System.out.println("Sisa percobaan login: " + (3 - percobaanLogin));

                    if (percobaanLogin < 3) {
                    } else {
                        System.out.println("\nBatas percobaan login telah habis!\n");
                        System.exit(0); // Keluar dari program jika sudah melebihi batas percobaan
                    }

                }
            }
        }

        System.out.println("Username atau Password yang Anda masukam Salah. Silakan coba lagi.");
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
            System.out.print("\nSilahkan masukan ruangan yang ingin anda pesan (1/2/3): ");
            ruang = sc.nextInt();

            // Kondisi ketika pilihan selain 1 - 3
            if (ruang > 3 || ruang < 1 || ruanganTerbooking[ruang - 1]) {
                System.out.println(
                        "\nRuang yang anda pilih tidak tersedia, silahkan untuk mencoba kembali!");

                // Kondisi ketika pilihan benar
            } else {
                isListBooking = true;
            }
        }

        // Status ruangan yang telah dibooking user menjadi true
        ruanganTerbooking[ruang - 1] = true;

        String konfirmasiRuangan;
        Boolean isKonfirmasiRuangan = false;

        // Perulangan konfirmasi pemilihan ruangan
        while (!isKonfirmasiRuangan) {

            System.out.println("\n==================================================");
            System.out.println("=                   Konfirmasi                   =");
            System.out.println("==================================================");

            // Pilihan user
            System.out.print("\nJika anda telah yakin dengan ruangan " + ruang + " ketik (y/t): ");
            konfirmasiRuangan = sc.next();

            // Kondisi ketika user memilih y maka ruangan berhasil dipesan
            if (konfirmasiRuangan.equalsIgnoreCase("y")) {
                System.out.println("\nAnda telah berhasil memesan ruangan " + ruang);
                isKonfirmasiRuangan = true;

                // Kondisi ketika user memilih t maka ruangan batal dipesan
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

            // Pilihan ruangan rapat oleh user
            System.out.print("\nSilahkan masukan ruangan yang ingin anda pesan (1/2/3): ");
            ruangRapat = sc.nextInt();

            // Kondisi ketika pilihan selain 1 - 3
            if (ruangRapat > 3 || ruangRapat < 1 || ruangRptTerbooking[ruangRapat - 1]) {
                System.out.println(
                        "\nRuang yang anda pilih tidak tersedia, silahkan untuk mencoba kembali!");

                // Kondisi ketika pilihan benar
            } else {
                isResRapat = true;
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
     * function payment methode
     */
    static void payment() {

        String konfirmasiPembayaran;
        int pilihanBank = 0;
        int pilihanKartu = 0;
        int payMethod;

        // pilihan pembayaran
        do {
            System.out.println("\n==================================================");
            System.out.println("=                Payment Methode                 =");
            System.out.println("==================================================\n");

            System.out.println("1. M-Banking");
            System.out.println("2. Kartu Kredit/Debit");
            System.out.println("3. Offline Payment");
            System.out.println("4. Bank Transfer");
            System.out.print("Pilih Metode Pembayaran (1/2/3/4): ");
            payMethod = sc.nextInt();

            // pilihan pembayaran a
            switch (payMethod) {
                case 1:
                    do {
                        System.out.println("\n1. Bank CBA");
                        System.out.println("2. Bank ABC");
                        System.out.println("3. Bank ACB");
                        System.out.println("0. batalkan pilihan");
                        System.out.print("Silahkan pilih bank (1/2/3/0): ");
                        pilihanBank = sc.nextInt();

                        switch (pilihanBank) {
                            case 1:
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                            case 0:
                                continue;
                            default:
                                System.out.println("Nomor yang anda masukan salah, silahkan pilih kembali!");
                                break;
                        }
                    } while (pilihanBank != 0 && (pilihanBank < 1 || pilihanBank > 3));
                    break;

                // pilihan pembayaran b
                case 2:
                    do {
                        System.out.println("\n1. Kartu kredit");
                        System.out.println("2. Kartu debit");
                        System.out.println("0. Batalkan pembayaran=");
                        System.out.print("Silahkan pilih jenis kartu (1/2/0): ");
                        pilihanKartu = sc.nextInt();

                        switch (pilihanKartu) {
                            case 1:
                                break;
                            case 2:
                                break;
                            case 0:
                                continue;
                            default:
                                System.out.println("Nomor yang anda masukan salah, silahkan coba lagi!");
                        }
                    } while (pilihanKartu != 0 && (pilihanKartu < 1 || pilihanKartu > 2));
                    break;

                // pilihan pembayaran c
                case 3:
                    break;

                // pilihan pembayaran d
                case 4:
                    do {
                        System.out.println("\n1. Bank CBA");
                        System.out.println("2. Bank ABC");
                        System.out.println("3. Bank ACB");
                        System.out.println("0. batalkan pilihan");
                        System.out.print("Silahkan pilih jenis bank (1/2/3/0): ");
                        pilihanBank = sc.nextInt();

                        switch (pilihanBank) {
                            case 1:
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                            case 0:
                                continue;
                            default:
                                System.out.println("Nomor yang anda masukan salah, Silahkan coba lagi!");
                        }
                    } while (pilihanBank != 0 && (pilihanBank < 1 || pilihanBank > 3));
                    break;
                default:
                    System.out.println("Pilihan pembayaran yang anda masukan salah, silahkan coba lagi!\n");
                    continue;
            }

            // ketika user input c
            if (payMethod == 3 || pilihanBank != 0 || pilihanKartu != 0) {
                do {
                    System.out.print("\nApakah anda sudah yakin dengan metode pembayaran ini? (y/t): ");
                    konfirmasiPembayaran = sc.next();
                    if (konfirmasiPembayaran.equalsIgnoreCase("t")) {
                        System.out.println("Pembayaran dibatalkan, kembali ke pilihan pembayaran!\n");
                        break;

                        // ketika user input a/b/d
                    } else if (konfirmasiPembayaran.equalsIgnoreCase("y")) {
                        switch (payMethod) {
                            case 1:
                                switch (pilihanBank) {
                                    case 1:
                                        System.out.println(
                                                "Silahkan transfer m-banking bank CBA pada nomor rekening 1234567890123456");
                                        break;
                                    case 2:
                                        System.out.println(
                                                "Silahkan transfer m-banking bank ABC pada nomor rekening 1234567890123456");
                                        break;
                                    case 3:
                                        System.out.println(
                                                "Silahkan transfer m-banking bank ACB pada nomor rekening 1234567890123456");
                                        break;
                                }
                                break;
                            case 2:
                                switch (pilihanKartu) {
                                    case 1:
                                        System.out.println(
                                                "Silahkan transfer menggunakan kartu kredit pada rekening 1234567890123456");
                                        break;
                                    case 2:
                                        System.out.println(
                                                "Silahkan transfer menggunakan kartu debit pada rekening 1234567890123456");
                                        break;
                                }
                                break;
                            case 4:
                                switch (pilihanBank) {
                                    case 1:
                                        System.out.println(
                                                "Silahkan transfer m-banking bank CBA pada nomor rekening 1234567890123456");
                                        break;
                                    case 2:
                                        System.out.println(
                                                "Silahkan transfer m-banking bank ABC pada nomor rekening 1234567890123456");
                                        break;
                                    case 3:
                                        System.out.println(
                                                "Silahkan transfer m-banking bank ACB pada nomor rekening 1234567890123456");
                                        break;
                                }
                                break;
                            case 3:
                                System.out.println("\nAnda telah mengkonfirmasi pembayaran.");
                                System.out.println("Silahkan siapkan uang anda saat melakukan checkin");
                                break;
                        }
                        System.out.println("\n==================================================");
                        System.out.println("=                  System out                    =");
                        System.out.println("==================================================\n");
                        return;

                        // ketika input salah
                    } else {
                        System.out.println("Input yang anda masukan salah, silahkan pilih 'y' atau 't'!");
                    }
                } while (!konfirmasiPembayaran.equalsIgnoreCase("y") && !konfirmasiPembayaran.equalsIgnoreCase("t"));
            }
        } while (true);
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
