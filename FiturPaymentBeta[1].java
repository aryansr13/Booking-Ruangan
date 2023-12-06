import java.util.Scanner;

public class FiturPaymentBeta {

    // pilihan pembayaran
    static void payment() {
        Scanner sc = new Scanner(System.in);

        String konfirmasiPembayaran;
        int pilihanBank = 0;
        int pilihanKartu = 0;
        int payMethod;

        String[] banks = new String[3];

        banks = new String[] { "Bank CBA", "Bank ABC", "Bank ACB" };

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

            // pilihan pembayaran 1
            switch (payMethod) {
                case 1:
                    do {
                        System.out.println("\nPilih Bank:");

                        for (int i = 0; i < banks.length; i++) {
                            System.out.println((i + 1) + ". " + banks[i]);
                        }

                        System.out.println("0. batalkan pilihan");
                        System.out.print("Silahkan pilih bank (1/2/3/0): ");
                        pilihanBank = sc.nextInt();

                        if (pilihanBank > 0 && pilihanBank <= banks.length) {
                            System.out.println("\nAnda memilih " + banks[pilihanBank - 1]);
                        } else if (pilihanBank == 0) {
                            continue;
                        } else {
                            System.out.println("Pilihan yang anda masukan salah, silahkan pilih kembali!");
                        }
                    } while (pilihanBank != 0 && (pilihanBank < 1 || pilihanBank > banks.length));
                    break;

                // pilihan pembayaran 2
                case 2:
                    do {
                        System.out.println("\n1. Kartu kredit");
                        System.out.println("2. Kartu debit");
                        System.out.println("0. Batalkan pembayaran");
                        System.out.print("Silahkan pilih jenis kartu (1/2/0): ");
                        pilihanKartu = sc.nextInt();

                        switch (pilihanKartu) {
                            case 1:
                                System.out.println("\nAnda memilih Kartu kredit");
                                break;
                            case 2:
                                System.out.println("\nAnda memilih Kartu debit");
                                break;
                            case 0:
                                continue;
                            default:
                                System.out.println("\nPilihan yang anda masukan salah, silahkan coba lagi!");
                        }
                    } while (pilihanKartu != 0 && (pilihanKartu < 1 || pilihanKartu > 2));
                    break;

                // pilihan pembayaran 3
                case 3:
                    break;

                // pilihan pembayaran 4
                case 4:
                    do {
                        System.out.println("\nPilih Bank:");

                        for (int i = 0; i < banks.length; i++) {
                            System.out.println((i + 1) + ". " + banks[i]);
                        }

                        System.out.println("0. batalkan pilihan");
                        System.out.print("Silahkan pilih jenis bank (1/2/3/0): ");
                        pilihanBank = sc.nextInt();

                        if (pilihanBank > 0 && pilihanBank <= banks.length) {
                            System.out.println("\nAnda memilih " + banks[pilihanBank - 1]);
                        } else if (pilihanBank == 0) {
                            continue;
                        } else {
                            System.out.println("Pilihan yang anda masukan salah, Silahkan coba lagi!");
                        }
                    } while (pilihanBank != 0 && (pilihanBank < 1 || pilihanBank > banks.length));
                    break;
                default:
                    System.out.println("\nPilihan pembayaran yang anda masukan salah, silahkan coba lagi!\n");
                    continue;
            }

            // ketika user input 3
            if (payMethod == 3 || pilihanBank != 0 || pilihanKartu != 0) {
                do {
                    System.out.print("\nApakah anda yakin dengan metode pembayaran ini? (y/t): ");
                    konfirmasiPembayaran = sc.next();
                    if (konfirmasiPembayaran.equalsIgnoreCase("t")) {
                        System.out.println("\nPembayaran dibatalkan, kembali ke pilihan pembayaran!\n");
                        break;

                        // ketika user input a/b/d
                    } else if (konfirmasiPembayaran.equalsIgnoreCase("y")) {
                        switch (payMethod) {
                            case 1:
                                System.out.println("\nSilahkan transfer m-banking " + banks[pilihanBank - 1]
                                        + " pada nomor rekening 1234567890123456");
                                break;
                            case 2:
                                System.out.println("\nSilahkan transfer menggunakan "
                                        + (pilihanKartu == 1 ? "kartu kredit" : "kartu debit")
                                        + " pada rekening 1234567890123456");
                                break;
                            case 4:
                                System.out.println("\nSilahkan transfer m-banking " + banks[pilihanBank - 1]
                                        + " pada nomor rekening 1234567890123456");
                                break;
                            case 3:
                                System.out.println("\nSilahkan siapkan uang anda saat melakukan check-in");
                                break;
                        }
                        System.out.println("\n==================================================");
                        System.out.println("=                  System out                    =");
                        System.out.println("==================================================\n");
                        return;

                        // ketika input salah
                    } else {
                        System.out.println("\nPilihan yang anda masukan salah, silahkan pilih 'y' atau 't'!");
                    }
                } while (!konfirmasiPembayaran.equalsIgnoreCase("y") && !konfirmasiPembayaran.equalsIgnoreCase("t"));
            }
        } while (true);
    }

    public static void main(String[] args) {
        payment();
    }
}
