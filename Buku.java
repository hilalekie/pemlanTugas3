import java.io.BufferedWriter;
import java.io.IOException;

public class Buku {
    private String judul;
    private String penulis;
    private int tahunTerbit;

    public Buku(String judul, String penulis, int tahunTerbit) {
        this.judul = judul;
        this.penulis = penulis;
        this.tahunTerbit = tahunTerbit;
    }

    public void tampilkanInfo() {
        System.out.println("Judul: " + judul);
    }

    public void tampilkanInfo(boolean detail) {
        if (detail) {
            System.out.println("Judul: " + judul);
            System.out.println("Penulis: " + penulis);
            System.out.println("Tahun Terbit: " + tahunTerbit);
        } else {
            tampilkanInfo();
        }
    }

    public String getJudul() {
        return judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public int getTahunTerbit() {
        return tahunTerbit;
    }

    public String toFileString() {
        return judul + "," + penulis + "," + tahunTerbit;
    }

    public static Buku fromFileString(String fileString) {
        String[] parts = fileString.split(",");
        return new Buku(parts[0], parts[1], Integer.parseInt(parts[2]));
    }

    public void simpanFile(BufferedWriter writer) throws IOException {
        writer.write(toFileString());
        writer.newLine();
    }
}
