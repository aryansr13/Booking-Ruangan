import java.util.Scanner;

public class FiturPenilaianBeta {

    static Scanner sc = new Scanner(System.in);
    // Array penilaian 1-5
    static int[] arrayPenilaian = { 1, 2, 3, 4, 5 };
    // Array Feedback
    static String[] feedbackArray = new String[10];
    // Array nama pengguna
    static String[] namaPenggunaArray = new String[10];
    // Array Nilai
    static int[] nilaiArray = new int[10];
    static int feedbackCount = 0;

    // Menu
    static boolean menu = true;

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
            System.out.print("Pilih menu (1/2/3): ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    penilaian();
                    break;
                case 2:
                    lihatFeedback();
                    break;
                case 0:
                    menu = false;
                    System.out.println();
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

    public static void main(String[] args) {
        do {
            pilihPenilaian();
        } while (menu);
    }

}
