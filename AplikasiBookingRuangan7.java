import java.util.Scanner;

public class AplikasiBookingRuangan7 {

    static boolean menu = true;
    static Scanner sc = new Scanner(System.in);

    // Form-Booking
    static String[][] listDataBooking = new String[3][3]; // 3 booking & 3 data
    static int bookingCount = 0;

    // List Booking
    static boolean[] ruanganTerbooking = new boolean[3];

    // Reservasi Ruangan Rapat
    static boolean[] ruangRptTerbooking = new boolean[3];

    // Array penilaian 1-5
    static int[] arrayPenilaian = { 1, 2, 3, 4, 5 };
    // Array Feedback
    static String[] feedbackArray = new String[10];
    // Array nama pengguna
    static String[] namaPenggunaArray = new String[10];
    // Array Nilai
    static int[] nilaiArray = new int[10];
    static int feedbackCount = 0;

    static void tampilkanMenu() {
        System.out.println("==================================================");
        System.out.println("=                      Menu                      =");
        System.out.println("==================================================\n");
        System.out.println("[1]. Login");
        System.out.println("[0]. keluar");
        System.out.print("Pilih menu: ");
        int pilihanMenu = sc.nextInt();
        sc.nextLine();

        switch (pilihanMenu) {
            case 1:
                login();
                break;
            case 0:
                menu = false;
                System.out.println("\nAnda telah keluar dari program.");
                System.exit(pilihanMenu);
                break;
            default:
                System.out.println("\nPilihan tidak valid. Silakan pilih lagi.\n");
        }
    }

    static void login() {

        String bookingUsername = "user";
        String bookingPassword = "user";

        int percobaanLogin = 0; // Menyimpan jumlah percobaan login

        while (percobaanLogin < 3) {

            System.out.println("\n==================================================");
            System.out.println("=                     Login                      =");
            System.out.println("==================================================\n");

            System.out.print("Masukan Username Anda : ");
            String usernameIn = sc.next();
            System.out.print("Masukan Password Anda : ");
            String passwordIn = sc.next();

            if (usernameIn.equals(bookingUsername) && passwordIn.equals(bookingPassword)) {
                System.out.println("\nLogin Berhasil");
                System.out.println("Selamat Datang di Aplikasi Booking Ruangan\n");
                userMenu();
                return;
            } else {
                percobaanLogin++;
                System.out.println("\nUsername atau Password yang Anda Masukkan Salah!");
                System.out.println("Sisa percobaan login: " + (3 - percobaanLogin));

                if (percobaanLogin < 3) {
                } else {
                    System.out.println("\nAnda telah melebihi batas percobaan login.\n");
                    System.exit(0); // Keluar dari program jika sudah melebihi batas percobaan
                }
            }
        }
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
                    checkout(arrayPenilaian);
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
            System.out.print("Ketik (Konfirmasi) atau (Batal) menyimpan data: ");
            String konfirmasi = sc.next();

            if (konfirmasi.equalsIgnoreCase("Konfirmasi")) {
                konfirmasiBenar = true;
                System.out.println("Data Diri Anda Telah Tersimpan");
                bookingCount++;
            } else if (konfirmasi.equalsIgnoreCase("Batal")) {
                konfirmasiBenar = true;
                System.out.println("Pendaftaran dibatalkan. Anda akan kembali ke menu.");
            } else {
                System.out.println("Konfirmasi tidak sesuai! Silakan coba lagi!\n");
            }
        }
        System.out.println();
    }

    /*
     * function list booking
     */
    static void listBooking() {

        boolean semuaKamarTerbooking = true;

        for (boolean status : ruanganTerbooking) {
            if (!status) {
                semuaKamarTerbooking = false;
                break;
            }
        }

        if (semuaKamarTerbooking) {
            System.out.println("\n==================================================");
            System.out.println("=        Maaf, semua kamar telah terbooking       =");
            System.out.println("=   Silakan menunggu hingga ada kamar yang kosong =");
            System.out.println("==================================================\n");
            return;
        }

        int ruang = 0;

        boolean isListBooking = false;
        while (!isListBooking) {

            System.out.println("\n==================================================");
            System.out.println("=                 List Booking                   =");
            System.out.println("==================================================\n");

            System.out.println("List ruangan yang tersedia pada hari ini\n");
            for (int i = 0; i < ruanganTerbooking.length; i++) {
                System.out.println((i + 1) + ". Ruangan " + (i + 1) + (ruanganTerbooking[i] ? " (Terbooking)" : ""));
            }

            System.out.print("Silahkan masukan ruangan yang ingin anda pesan (1/2/3): ");
            ruang = sc.nextInt();

            if (ruang > 3 || ruang < 1 || ruanganTerbooking[ruang - 1]) {
                System.out.println(
                        "\nRuang yang anda pilih tidak tersedia atau sudah terbooking, silahkan untuk mencoba kembali!");
            } else {
                isListBooking = true;
            }
        }

        // Set status ruangan yang telah dibooking menjadi true
        ruanganTerbooking[ruang - 1] = true;

        Boolean isDetailRuangan = false;
        while (!isDetailRuangan) {

            System.out.print("\nApakah Anda Ingin Melihat Detail Fasilitas Ruangan " + ruang + " (y/t)?: ");
            String detail = sc.next();

            if (detail.equalsIgnoreCase("y")) {

                if (ruang >= 1 && ruang <= 3) {
                    System.out.println("\n==================================================");
                    System.out.println("=          Detail fasilitas ruangan " + ruang + "            =");
                    System.out.println("==================================================\n");

                    String fasilitasRuangan[][] = new String[3][3];
                    fasilitasRuangan[0][0] = "1. Kamar tidur kapasitas 2 orang";
                    fasilitasRuangan[0][1] = "2. Kamar mandi dalam dengan air panas";
                    fasilitasRuangan[0][2] = "3. Handuk dan peralatan mandi";
                    fasilitasRuangan[1][0] = "1. Tempat tidur dengan kapasitas 1 orang";
                    fasilitasRuangan[1][1] = "2. Kamar mandi dalam dengan air panas";
                    fasilitasRuangan[1][2] = "3. Snack yang telah disediakan didalam kamar";
                    fasilitasRuangan[2][0] = "1. Kamar tidur kapasitas 1 orang";
                    fasilitasRuangan[2][1] = "2. Kamar mandi dalam dengan air panas";
                    fasilitasRuangan[2][2] = "3. Ruangan ber-AC";

                    for (int i = 0; i < fasilitasRuangan[ruang - 1].length; i++) {
                        System.out.println(fasilitasRuangan[ruang - 1][i]);
                    }
                }
                isDetailRuangan = true;

            } else if (detail.equalsIgnoreCase("t")) {
                System.out.println("\nAnda tidak melihat detail ruangan.");
                isDetailRuangan = true;

            } else {
                System.out.println("\nInput yang anda masukan salah, silahkan coba lagi!");
            }
        }

        String konfirmasiRuangan;
        Boolean isKonfirmasiRuangan = false;
        while (!isKonfirmasiRuangan) {

            System.out.println("\n==================================================");
            System.out.println("=                   Konfirmasi                   =");
            System.out.println("==================================================");

            System.out.print("\nJika anda telah yakin dengan ruangan " + ruang + " ketik (konfirmasi/batal): ");
            konfirmasiRuangan = sc.next();

            if (konfirmasiRuangan.equalsIgnoreCase("konfirmasi")) {
                System.out.println("\nAnda telah berhasil memesan ruangan " + ruang);
                isKonfirmasiRuangan = true;

            } else if (konfirmasiRuangan.equalsIgnoreCase("batal")) {
                System.out.println("\nAnda membatalkan pemesanan ruangan " + ruang);
                isKonfirmasiRuangan = true;

            } else {
                System.out.println("\nInput yang anda masukan salah silahkan coba lagi!");
            }
        }
    }

    /*
     * function reservasi rapat
     */
    static void resRapat() {

        boolean semuarptTerbooking = true;

        for (boolean status : ruangRptTerbooking) {
            if (!status) {
                semuarptTerbooking = false;
                break;
            }
        }

        if (semuarptTerbooking) {
            System.out.println("\n==================================================");
            System.out.println("=        Maaf, semua ruang telah terbooking       =");
            System.out.println("=   Silakan menunggu hingga ada kamar yang kosong =");
            System.out.println("==================================================\n");
            return;
        }

        int ruang = 0;

        boolean isListBooking = false;
        while (!isListBooking) {

            System.out.println("\n==================================================");
            System.out.println("=                Reservasi Rapat                 =");
            System.out.println("==================================================\n");

            System.out.println("List ruang rapat yang tersedia pada hari ini\n");
            for (int i = 0; i < ruangRptTerbooking.length; i++) {
                System.out
                        .println((i + 1) + ". Ruang rapat " + (i + 1) + (ruangRptTerbooking[i] ? " (Terbooking)" : ""));
            }

            System.out.print("Silahkan masukan ruang rapat yang ingin anda pesan (1/2/3): ");
            ruang = sc.nextInt();

            if (ruang > 3 || ruang < 1 || ruangRptTerbooking[ruang - 1]) {
                System.out.println(
                        "\nRuang rapat yang anda pilih tidak tersedia atau sudah terbooking, silahkan untuk mencoba kembali!");
            } else {
                isListBooking = true;
            }
        }

        // Set status ruangan yang telah dibooking menjadi true
        ruangRptTerbooking[ruang - 1] = true;

        Boolean isDetailRuangan = false;
        while (!isDetailRuangan) {

            System.out.print("\nApakah Anda Ingin Melihat Detail Fasilitas Ruang rapat " + ruang + " (y/t)?: ");
            String detail = sc.next();

            if (detail.equalsIgnoreCase("y")) {

                if (ruang >= 1 && ruang <= 3) {
                    System.out.println("\n==================================================");
                    System.out.println("=          Detail fasilitas ruang rapat " + ruang + "         =");
                    System.out.println("==================================================\n");

                    String fsltsRpt[][] = new String[3][3];
                    fsltsRpt[0][0] = "1. Kapasitas ruangan untuk 8 orang";
                    fsltsRpt[0][1] = "2. Ruangan Ber-AC";
                    fsltsRpt[0][2] = "3. Proyektor";
                    fsltsRpt[1][0] = "1. Kapasitas ruangan untuk 10 orang";
                    fsltsRpt[1][1] = "2. Ruangan exslusif";
                    fsltsRpt[1][2] = "3. Proyektor";
                    fsltsRpt[2][0] = "1. Kapasitas ruaangan untuk 12 orang";
                    fsltsRpt[2][1] = "2. Proyektor";
                    fsltsRpt[2][2] = "3. Snack dan makanan untuk sarapan";

                    for (int i = 0; i < fsltsRpt[ruang - 1].length; i++) {
                        System.out.println(fsltsRpt[ruang - 1][i]);
                    }
                }
                isDetailRuangan = true;

            } else if (detail.equalsIgnoreCase("t")) {
                isDetailRuangan = true;

            } else {
                System.out.println("\nInput yang anda masukan salah, silahkan coba lagi!");
            }
        }

        String konfirmasiRuangan;
        Boolean isKonfirmasiRuangan = false;
        while (!isKonfirmasiRuangan) {

            System.out.println("\n==================================================");
            System.out.println("=                   Konfirmasi                   =");
            System.out.println("==================================================");

            System.out.print("\nJika anda telah yakin dengan ruangan rapat " + ruang + " ketik (konfirmasi/batal): ");
            konfirmasiRuangan = sc.next();

            if (konfirmasiRuangan.equalsIgnoreCase("konfirmasi")) {
                System.out.println("\nAnda telah berhasil memesan ruangan rapat " + ruang);
                isKonfirmasiRuangan = true;

            } else if (konfirmasiRuangan.equalsIgnoreCase("batal")) {
                System.out.println("\nAnda membatalkan pemesanan ruangan rapat" + ruang);
                isKonfirmasiRuangan = true;

            } else {
                System.out.println("\nInput yang anda masukan salah silahkan coba lagi!");
            }
        }

        System.out.println("\n==================================================");
        System.out.println("=                  System out                    =");
        System.out.println("==================================================\n");
    }

    /*
     * function checkin
     */
    static void checkin() {

        // Array nama bulan
        String arrayBulan[] = { "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September",
                "Oktober", "November", "Desember" };

        // input Checkin
        System.out.println("\n==================================================");
        System.out.println("=                    Checkin                     =");
        System.out.println("==================================================");

        int bulanCheckin, tanggalCheckin, waktuCheckin;
        String namaBulanCheckin = ""; // Variable untuk menyimpan nama bulan

        // Tahun checkin
        int tahunCheckin = 0;
        while (tahunCheckin != 2023) {
            System.out.print("Masukan Tahun Checkin (YYYY): ");
            tahunCheckin = sc.nextInt();
            if (tahunCheckin != 2023) {
                System.out.println("Tahun Checkin yang Anda Masukan Tidak Valid!\n");
            }
        }

        // Bulan checkin
        System.out.println("Masukan bulan checkin anda: ");
        bulanCheckin = sc.nextInt();

        // Menyimpan nama bulan berdasarkan nomor bulan
        if (bulanCheckin >= 1 && bulanCheckin <= 12) {
            namaBulanCheckin = arrayBulan[bulanCheckin - 1];
        } else {
            System.out.println("Nomor bulan tidak valid!");
            System.exit(0);
        }

        // Input tanggal checkin
        int maxTanggalCheckin = 31;
        if (bulanCheckin == 2) {
            if ((tahunCheckin % 4 == 0 && tahunCheckin % 100 != 0) || (tahunCheckin % 400 == 0)) {
                maxTanggalCheckin = 29;
            } else {
                maxTanggalCheckin = 28;
            }
        } else if (bulanCheckin == 4 || bulanCheckin == 6 || bulanCheckin == 9 || bulanCheckin == 11) {
            maxTanggalCheckin = 30;
        }

        do {
            System.out.print("Masukan Tanggal Checkin (1-" + maxTanggalCheckin + "): ");
            tanggalCheckin = sc.nextInt();
            if (tanggalCheckin < 1 || tanggalCheckin > maxTanggalCheckin) {
                System.out.println("Tanggal yang Anda Masukan Salah untuk Bulan " + namaBulanCheckin + "!");
            }
        } while (tanggalCheckin < 1 || tanggalCheckin > maxTanggalCheckin);

        // Input Jam Checkin
        do {
            System.out.print("Masukan Jam Checkin (format 24 jam): ");
            waktuCheckin = sc.nextInt();
            if (waktuCheckin < 0 || waktuCheckin >= 24) {
                System.out.println("Waktu yang Anda Masukan Salah!");
            }
        } while (waktuCheckin < 0 || waktuCheckin >= 24);

        String ampmCheckin = waktuCheckin < 12 ? "AM" : "PM";
        if (waktuCheckin > 12) {
            waktuCheckin -= 12;
        }

        // Hitung waktu check-out (12 jam setelah check-in)
        int waktuCheckout = (waktuCheckin + 12) % 24;
        String ampmCheckout = waktuCheckout < 12 ? "AM" : "PM";
        if (waktuCheckout > 12) {
            waktuCheckout -= 12;
        }

        // Output checkin
        System.out.println("konfirmasi checkin");
        String konfirmCheckin;
        boolean isConfirm = false;

        while (!isConfirm) {
            System.out.print("Apakah anda yakin ingin memulai checkin?(y/t): ");
            konfirmCheckin = sc.next();

            if (konfirmCheckin.equalsIgnoreCase("y")) {
                System.out.println("\nSelamat checkin anda berhasil\n");
                System.out.println(
                        "Anda Memulai Checkin Pada " + tanggalCheckin + " " + namaBulanCheckin + " " + tahunCheckin +
                                " Pada Jam " + waktuCheckin + " " + ampmCheckin);

                System.out.println("Anda harus Check-out sebelum jam " + waktuCheckout + " " + ampmCheckout);
                isConfirm = true;
            } else if (konfirmCheckin.equalsIgnoreCase("t")) {
                System.out.println("Anda batal checkin");
                isConfirm = true;
            } else {
                System.out.println("Input yang anda masukan salah, silahkan coba kembali!");
            }
        }

        System.out.println("\n==================================================");
        System.out.println("=                  System out                    =");
        System.out.println("==================================================\n");

    }

    /*
     * function checkout
     */

    static void checkout(int[] ruanganTerbooking) {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n==================================================");
        System.out.println("=                   Checkout                     =");
        System.out.println("==================================================\n");

        // Konfirmasi checkout
        boolean konfirmasiCheckout = false;
        while (!konfirmasiCheckout) {

            System.out.print("\nSilahkan Ketik (Konfirmasi) Untuk Checkout Dari Ruangan: ");
            String konfirmasi = sc.next();

            if (konfirmasi.equalsIgnoreCase("Konfirmasi")) {
                konfirmasiCheckout = true;
                System.out.println();

            } else if (konfirmasi.equalsIgnoreCase("Batal")) {
                konfirmasiCheckout = true;

            } else {
                System.out.println("Konfirmasi tidak sesuai! Silahkan coba lagi!\n");
            }
        }

        // Input waktu checkout
        int waktuCheckout;
        String ampmCheckout;

        do {

            System.out.print("Masukan Waktu Checkout (format 24 jam): ");
            waktuCheckout = sc.nextInt();

            if (waktuCheckout < 0 || waktuCheckout >= 24) {
                System.out.println("Waktu checkout yang Anda Masukan Salah!\n");
            }

        } while (waktuCheckout < 0 || waktuCheckout >= 24);

        ampmCheckout = waktuCheckout < 12 ? "AM" : "PM";

        if (waktuCheckout > 12) {
            waktuCheckout -= 12;
        }

        System.out.println("Anda Telah Checkout Pada Jam " + waktuCheckout + " " + ampmCheckout);
        // Output waktu checkout

        // Menghitung durasi menginap
        int durasiInap = waktuCheckout - ruanganTerbooking[0]; // Di sini diasumsikan ruangan 1

        if (durasiInap <= 12) {
            System.out.println(" Tidak ada denda.\n");

        } else {

            int denda = (durasiInap - 12) * 50000; // Denda 50.000 per jam melebihi 12 jam
            System.out.println("Anda telah menginap selama " + durasiInap + " jam. Anda dikenakan denda sebesar Rp "
                    + denda + ".\n");
        }

        System.out.println("\n==================================================");
        System.out.println("=                  System out                    =");
        System.out.println("==================================================\n");
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
     * Pilihan penilaian
     */
    static void pilihPenilaian() {
        int choice;

        do {
            System.out.println("\n============================================================");
            System.out.println("=                        Penilaian                         =");
            System.out.println("============================================================\n");
            System.out.println("[1] Beri Penilaian dan Feedback");
            System.out.println("[2] Lihat Penilaian dan Feedback Pengguna");
            System.out.println("[0] Keluar");
            System.out.print("Pilih menu (1/2/0): ");
            choice = sc.nextInt();

            switch (choice) {
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
        } while (choice != 0);
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
