import java.util.Scanner;

public class CheckinBeta {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] ruanganTerbooking = new int[3];

        String arrayBulan[] = { "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September",
                "Oktober", "November", "Desember" };

        // input Checkin
        System.out.println("\n==================================================");
        System.out.println("=                    Checkin                     =");
        System.out.println("==================================================");

        int bulanCheckin, tanggalCheckin, jamCheckin, menitCheckin;
        String namaBulanCheckin = "", ampmCheckin = ""; // Variabel untuk menyimpan nama bulan dan AM/PM

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
        System.out.print("Masukan bulan checkin anda: ");
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

        // Input Jam dan Menit Checkin
        do {
            System.out.print("Masukan Jam Checkin (format 24 jam): ");
            jamCheckin = sc.nextInt();
            if (jamCheckin < 0 || jamCheckin >= 24) {
                System.out.println("Jam yang Anda Masukan Salah!");
            }
        } while (jamCheckin < 0 || jamCheckin >= 24);

        do {
            System.out.print("Masukan Menit Checkin: ");
            menitCheckin = sc.nextInt();
            if (menitCheckin < 0 || menitCheckin >= 60) {
                System.out.println("Menit yang Anda Masukan Salah!");
            }
        } while (menitCheckin < 0 || menitCheckin >= 60);

        ampmCheckin = jamCheckin < 12 ? "AM" : "PM";
        if (jamCheckin > 12) {
            jamCheckin -= 12;
        }

        // Hitung waktu check-out (12 jam setelah check-in)
        int waktuCheckout = (jamCheckin + 12) % 24;
        int menitCheckout = menitCheckin;
        String ampmCheckout = waktuCheckout < 12 ? "AM" : "PM";
        if (waktuCheckout > 12) {
            waktuCheckout -= 12;
        }

        // Output checkin
        String konfirmCheckin;
        boolean isConfirm = false;

        while (!isConfirm) {
            System.out.print("Apakah anda yakin ingin memulai checkin?(y/t): ");
            konfirmCheckin = sc.next();

            if (konfirmCheckin.equalsIgnoreCase("y")) {
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

        checkout(ruanganTerbooking, jamCheckin, menitCheckin);
    }

    static void checkout(int[] ruanganTerbooking, int jamCheckin, int menitCheckin) {
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

        if (jamCheckout < jamCheckin || (jamCheckout == jamCheckin && menitCheckout < menitCheckin)) {
            jamCheckout += 24; // Menambah 24 jam jika waktu checkout lebih kecil dari waktu checkin
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
        sc.close();
    }
}
