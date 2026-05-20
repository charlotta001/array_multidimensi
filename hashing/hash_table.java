import java.util.*;

public class hash_table {
    static final int SIZE = 200;
    static int[] table = new int[SIZE];
    static boolean[] deleted = new boolean[SIZE];
    static Scanner sc = new Scanner(System.in);

    static int hash(int key) { return Math.abs(key) % SIZE; }

    static void insert(int key) {
        int idx = hash(key);
        do {
            if (table[idx] == 0 || deleted[idx]) {
                table[idx] = key; deleted[idx] = false;
                System.out.println("Inserted " + key + " at index " + idx);
                return;
            }
            if (table[idx] == key && !deleted[idx]) { System.out.println("Duplikat!"); return; }
            idx = (idx + 1) % SIZE;
        } while (idx != hash(key));
        System.out.println("Tabel penuh!");
    }

    static void delete(int key) {
        int idx = hash(key), start = idx;
        do {
            if (table[idx] == key && !deleted[idx]) {
                deleted[idx] = true;
                System.out.println("Deleted " + key + " dari index " + idx);
                return;
            }
            if (table[idx] == 0 && !deleted[idx]) break;
            idx = (idx + 1) % SIZE;
        } while (idx != start);
        System.out.println("Data " + key + " tidak ditemukan.");
    }

    static void search(int key) {
        int idx = hash(key), start = idx;
        do {
            if (table[idx] == key && !deleted[idx]) {
                System.out.println("Data " + key + " ditemukan di index " + idx);
                return;
            }
            if (table[idx] == 0 && !deleted[idx]) break;
            idx = (idx + 1) % SIZE;
        } while (idx != start);
        System.out.println("Data " + key + " tidak ditemukan.");
    }

    static void printTable() {
        System.out.println("\n--- ISI HASH TABLE ---");
        int count = 0;
        for (int i = 0; i < SIZE; i++)
            if (table[i] != 0 && !deleted[i]) { System.out.print(table[i] + " "); count++; }
        System.out.println("\nTotal: " + count + " data\n");
    }

    public static void main(String[] args) {
        Arrays.fill(table, 0);
        // Isi 100 angka random unik (range 1-9999)
        Random rng = new Random();
        Set<Integer> used = new LinkedHashSet<>();
        while (used.size() < 100) used.add(rng.nextInt(9999) + 1);
        System.out.println("Mengisi 100 data random...");
        for (int n : used) insert(n);

        int choice;
        do {
            System.out.println("\n===== HASH TABLE MENU =====");
            System.out.println("1. Input Data");
            System.out.println("2. Hapus Data");
            System.out.println("3. Cari Data");
            System.out.println("4. Tampilkan Tabel");
            System.out.println("0. Keluar");
            System.out.print("Pilihan: ");
            choice = sc.nextInt();
            if (choice == 1) {
                System.out.print("Masukkan angka: ");
                insert(sc.nextInt());
            } else if (choice == 2) {
                System.out.print("Angka yang dihapus: ");
                delete(sc.nextInt());
            } else if (choice == 3) {
                System.out.print("Angka yang dicari: ");
                search(sc.nextInt());
            } else if (choice == 4) {
                printTable();
            }
        } while (choice != 0);
        System.out.println("Bye!");
    }
}