import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManajemenBuku {
    private static final String FILE_NAME = "dataBuku.txt";
    private static List<Buku> daftarBuku = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    private static void simpanDataKeFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Buku buku : daftarBuku) {
                buku.simpanFile(writer);
            }
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data ke file: " + e.getMessage());
        }
    }

    private static void bacaDataDariFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Buku buku = Buku.fromFileString(line);
                daftarBuku.add(buku);
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca data dari file: " + e.getMessage());
        }
    }

    private static void tambahBuku() {
        System.out.print("Masukkan judul buku: ");
        String judul = scanner.nextLine();
        System.out.print("Masukkan nama penulis: ");
        String penulis = scanner.nextLine();
        System.out.print("Masukkan tahun terbit: ");
        int tahunTerbit = Integer.parseInt(scanner.nextLine());

        Buku buku = new Buku(judul, penulis, tahunTerbit);
        daftarBuku.add(buku);
        simpanDataKeFile();
        System.out.println("Buku berhasil ditambahkan!");
    }

    private static void tampilkanDaftarBuku() {
        if (daftarBuku.isEmpty()) {
            System.out.println("Daftar buku kosong.");
        } else {
            System.out.print("Tampilkan informasi detail? (y/n): ");
            String pilihan = scanner.nextLine();
            boolean detail = pilihan.equalsIgnoreCase("y");

            for (Buku buku : daftarBuku) {
                buku.tampilkanInfo(detail);
                System.out.println();
            }
        }
    }

    private static void tampilkanMenu() {
        System.out.println("\n=== Sistem Manajemen Buku ===");
        System.out.println("1. Tambah Buku Baru");
        System.out.println("2. Tampilkan Daftar Buku");
        System.out.println("3. Keluar");
        System.out.print("Pilih menu (1/2/3): ");
    }

    public static void main(String[] args) {
        bacaDataDariFile();

        boolean isRunning = true;
        while (isRunning) {
            tampilkanMenu();
            String pilihan = scanner.nextLine();

            switch (pilihan) {
                case "1":
                    tambahBuku();
                    break;
                case "2":
                    tampilkanDaftarBuku();
                    break;
                case "3":
                    isRunning = false;
                    System.out.println("Terima kasih telah menggunakan program ini!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }

    }
}
