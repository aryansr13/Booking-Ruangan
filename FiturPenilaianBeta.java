import java.util.Scanner;

public class FiturPenilaianBeta {

    static Scanner sc = new Scanner(System.in);
    static int[] arrayPenilaian = { 1, 2, 3, 4, 5 };
    static String[] feedbackArray = new String[10]; // Misalnya, batasi hingga 10 feedback
    static int feedbackCount = 0;

    static void penilaian() {
        int penilaian = 0;
        boolean isPenilaian = false;

        // input penilaian 1-5
        while (!isPenilaian) {
            System.out.println("\n=============================================================");
            System.out.println("=                        Penilaian                          =");
            System.out.println("=============================================================\n");

            // input penilaian pelanggan
            System.out.println("Beri penilaian berdasarkan pengalaman anda terhadap aplikasi ini");
            System.out.println("1 = Sangat Tidak Puas");
            System.out.println("2 = Tidak Puas");
            System.out.println("3 = Biasa Saja");
            System.out.println("4 = Puas");
            System.out.println("5 = Sangat Puas");
            System.out.print("Silahkan isi penilaian sesuai kepuasan anda: ");
            penilaian = sc.nextInt();

            for (int i : arrayPenilaian) {
                if (penilaian == i) {
                    isPenilaian = true;
                    break;
                }
            }

            if (!isPenilaian) {
                System.out.println("\nAngka yang anda masukan salah, silahkan coba lagi!\n");
            }
        }

        // input feedback kepuasan pengguna
        System.out.println("\n=============================================================");
        System.out.println("=                         feedback                          =");
        System.out.println("=============================================================\n");
        System.out.println("Kami ingin mendengar lebih lanjut, silahkan berikan feedback / saran anda: ");
        sc.nextLine(); // Membersihkan newline
        String feedback = sc.nextLine();

        // Tambahkan feedback ke array
        feedbackArray[feedbackCount] = feedback;
        feedbackCount++;

        System.out.println("\nFeedback anda telah tersimpan");
        System.out.println("Terima kasih telah meluangkan waktu untuk memberi feedback.");

        System.out.println("\n============================================================");
        System.out.println("=                       System out                         =");
        System.out.println("============================================================\n");

        // Tampilkan feedback yang telah tersimpan
        lihatFeedback(penilaian);
    }

    // Fungsi untuk menampilkan feedback yang telah tersimpan
    static void lihatFeedback(int penilaian) {
        System.out.println("\n=============================================================");
        System.out.println("=                    Daftar Feedback                        =");
        System.out.println("=============================================================\n");

        // Tampilkan nilai pengguna ke-n
        System.out.println("\nNilai Pengguna ke-" + feedbackCount + ": " + penilaian);

        // Tampilkan feedback yang telah tersimpan
        for (int i = 0; i < feedbackCount; i++) {
            System.out.println("Feedback Pengguna ke-" + (i + 1) + ": " + feedbackArray[i]);
        }
    }

    public static void main(String[] args) {
        penilaian();
    }
}
