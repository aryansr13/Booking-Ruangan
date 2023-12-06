import java.util.Scanner;

public class CheckindanCheckoutBeta {

    static Scanner sc = new Scanner(System.in);
    static int jamCheckinGlobal; // Variabel global untuk jam checkin
    static int menitCheckinGlobal; // Variabel global untuk menit checkin
    static String[] arrayBulan = {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September",
            "Oktober", "November", "Desember"};

    public static void main(String[] args) {
        tampilkanMenu();
    }

    static void tampilkanMenu() {
        System.out.println("==================================================");
        System.out.println("=                      Menu                      =");
        System.out.println("==================================================\n");
        System.out.println("[1]. Login");
        System.out.println("[0]. Keluar");
        System.out.print("Pilih menu: ");
        int pilihanMenu = sc.nextInt();
        sc.nextLine();

        switch (pilihanMenu) {
            case 1:
                login();
                break;
            case 0:
                System.out.println("\nAnda telah keluar dari program.");
                System.exit(0);
                break;
            default:
                System.out.println("\nPilihan tidak valid. Silakan pilih lagi.\n");
                tampilkanMenu();
        }
    }

    static void login() {
        String bookingUsername = "user";
        String bookingPassword = "user";

        int percobaanLogin = 0;

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

                if (percobaanLogin >= 3) {
                    System.out.println("\nAnda telah melebihi batas percobaan login.\n");
                    System.exit(0);
                }
            }
        }
    }

    static void userMenu() {
        boolean isUserLogout = false;

        do {
            System.out.println("==================================================");
            System.out.println("=                      Menu                      =");
            System.out.println("==================================================\n");
            System.out.println("[1] Check-in");
            System.out.println("[2] Check-out");
            System.out.println("[0] Logout");
            System.out.print("Pilih menu: ");
            int pilihanMenu = sc.nextInt();
            sc.nextLine();

            switch (pilihanMenu) {
                case 1:
                    checkin();
                    break;
                case 2:
                    checkout();
                    break;
                case 0:
                    logout();
                    break;
                default:
                    System.out.println("\nInput yang anda masukan salah, silahkan untuk mencoba lagi!\n");
            }
        } while (!isUserLogout);
    }

    static void checkin() {
        // (Kode check-in yang sudah ada)
        int[] ruanganTerbooking = new int[3];

        System.out.println("\n==================================================");
        System.out.println("=                    Checkin                     =");
        System.out.println("==================================================");

        int bulanCheckin, tanggalCheckin, jamCheckin, menitCheckin;
        String namaBulanCheckin = "", ampmCheckin = "";

        int tahunCheckin = 0;
        while (tahunCheckin != 2023) {
            System.out.print("Masukan Tahun Checkin (YYYY): ");
            tahunCheckin = sc.nextInt();
            if (tahunCheckin != 2023) {
                System.out.println("Tahun Checkin yang Anda Masukan Tidak Valid!\n");
            }
        }

        System.out.print("Masukan bulan checkin anda: ");
        bulanCheckin = sc.nextInt();

        if (bulanCheckin >= 1 && bulanCheckin <= 12) {
            namaBulanCheckin = arrayBulan[bulanCheckin - 1];
        } else {
            System.out.println("Nomor bulan tidak valid!");
            System.exit(0);
        }

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
                System.out.println("Tanggal yang Anda Masukkan Salah untuk Bulan " + namaBulanCheckin + "!");
            }
        } while (tanggalCheckin < 1 || tanggalCheckin > maxTanggalCheckin);

        do {
            System.out.print("Masukan Jam Checkin (format 24 jam): ");
            jamCheckin = sc.nextInt();
            if (jamCheckin < 0 || jamCheckin >= 24) {
                System.out.println("Jam yang Anda Masukkan Salah!");
            }
        } while (jamCheckin < 0 || jamCheckin >= 24);

        do {
            System.out.print("Masukan Menit Checkin: ");
            menitCheckin = sc.nextInt();
            if (menitCheckin < 0 || menitCheckin >= 60) {
                System.out.println("Menit yang Anda Masukkan Salah!");
            }
        } while (menitCheckin < 0 || menitCheckin >= 60);

        ampmCheckin = jamCheckin < 12 ? "AM" : "PM";
        if (jamCheckin > 12) {
            jamCheckin -= 12;
        }

        int waktuCheckout = (jamCheckin + 12) % 24;
        int menitCheckout = menitCheckin;
        String ampmCheckout = waktuCheckout < 12 ? "AM" : "PM";
        if (waktuCheckout > 12) {
            waktuCheckout -= 12;
        }

        String konfirmCheckin;
        boolean isConfirm = false;

        while (!isConfirm) {
            System.out.print("Apakah anda yakin ingin memulai checkin?(y/t): ");
            konfirmCheckin = sc.next();

            if (konfirmCheckin.equalsIgnoreCase("y")) {
                // Simpan jam dan menit checkin ke variabel global
                jamCheckinGlobal = jamCheckin;
                menitCheckinGlobal = menitCheckin;

                System.out.println("\nSelamat checkin anda berhasil\n");
                System.out.println(
                        "Anda Memulai Checkin Pada " + tanggalCheckin + " " + namaBulanCheckin + " " + tahunCheckin +
                                " Pada Jam " + jamCheckin + ":" + menitCheckin + " " + ampmCheckin);
                System.out.println("Anda harus Check-out sebelum jam " + waktuCheckout + ":" + menitCheckout + " " +
                        ampmCheckout);
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

    static void checkout() {
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
        int jamCheckout, menitCheckout;
        String ampmCheckout;

        do {
            System.out.print("Masukan Jam Checkout (format 24 jam): ");
            jamCheckout = sc.nextInt();

            if (jamCheckout < 0 || jamCheckout >= 24) {
                System.out.println("Jam checkout yang Anda Masukan Salah!\n");
            }

        } while (jamCheckout < 0 || jamCheckout >= 24);

        do {
            System.out.print("Masukan Menit Checkout: ");
            menitCheckout = sc.nextInt();
            if (menitCheckout < 0 || menitCheckout >= 60) {
                System.out.println("Menit checkout yang Anda Masukan Salah!");
            }
        } while (menitCheckout < 0 || menitCheckout >= 60);

        ampmCheckout = jamCheckout < 12 ? "AM" : "PM";

        // Ambil jam checkin dan menit checkin dari variabel global
        int jamCheckin = jamCheckinGlobal;
        int menitCheckin = menitCheckinGlobal;

        if (jamCheckout < jamCheckin || (jamCheckout == jamCheckin && menitCheckout < menitCheckin)) {
            jamCheckout += 24; // Tambahkan 24 jam jika waktu checkout lebih kecil dari waktu checkin
        }

        // Hitung durasi menginap
        int durasiInapJam = jamCheckout - jamCheckin;
        int durasiInapMenit = menitCheckout - menitCheckin;

        if (durasiInapMenit < 0) {
            durasiInapJam--; // Kurangi satu jam jika menit checkout kurang dari menit checkin
            durasiInapMenit += 60; // Tambahkan 60 menit
        }

        if (durasiInapJam < 0) {
            durasiInapJam += 24; // Tambahkan 24 jam jika jam checkout kurang dari jam checkin
        }

        if (durasiInapJam <= 12) {
            System.out.println("Anda telah menginap selama " + durasiInapJam + " jam " + durasiInapMenit +
                    " menit. Tidak ada denda.\n");

        } else {
            int denda = (durasiInapJam - 12) * 50000; // Denda 50.000 per jam melebihi 12 jam
            System.out.println("Anda telah menginap selama " + durasiInapJam + " jam " + durasiInapMenit +
                    " menit. Anda dikenakan denda sebesar Rp " + denda + ".\n");
        }

        System.out.println("\n==================================================");
        System.out.println("=                  System out                    =");
        System.out.println("==================================================\n");
    }

    static void logout() {
        System.out.println("\nAnda telah logout.");
        tampilkanMenu();
    }
}
