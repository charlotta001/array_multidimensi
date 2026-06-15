import java.util.*;


public class hash_table {
    static final int SIZE = 31;

    static class Node {
        int val;
        Node next;
        Node(int v) { val = v; }
    }

    static Node[] table = new Node[SIZE];
    static Scanner sc = new Scanner(System.in);

    // collision teknik  : separate chaining
    static int hash(int key) { return Math.abs(key) % SIZE; }

    static void insert(int key) {
        int idx = hash(key);
        Node cur = table[idx];
        while (cur != null) {
            if (cur.val == key) { System.out.println("Duplikat! " + key + " sudah ada di index " + idx); return; }
            cur = cur.next;
        }
        Node newNode = new Node(key);
        newNode.next = table[idx];
        table[idx] = newNode;
        System.out.println("Inserted " + key + " at index " + idx);
    }

    static void delete(int key) {
        int idx = hash(key);
        Node cur = table[idx], prev = null;
        while (cur != null) {
            if (cur.val == key) {
                if (prev == null) table[idx] = cur.next;
                else prev.next = cur.next;
                System.out.println("Deleted " + key + " dari index " + idx);
                return;
            }
            prev = cur; cur = cur.next;
        }
        System.out.println("Data " + key + " tidak ditemukan.");
    }

    static void search(int key) {
        int idx = hash(key);
        Node cur = table[idx];
        int step = 0;
        while (cur != null) {
            step++;
            if (cur.val == key) {
                System.out.println("Data " + key + " ditemukan di index " + idx + " (step " + step + " dalam chain)");
                return;
            }
            cur = cur.next;
        }
        System.out.println("Data " + key + " tidak ditemukan.");
    }

    static void printTable() {
        System.out.println("\n--- ISI HASH TABLE (SIZE=" + SIZE + ") ---");
        int total = 0;
        for (int i = 0; i < SIZE; i++) {
            System.out.print("[" + i + "] -> ");
            if (table[i] == null) {
                System.out.println("null");
            } else {
                Node cur = table[i];
                int chainLen = 0;
                while (cur != null) {
                    System.out.print(cur.val);
                    if (cur.next != null) System.out.print(" -> ");
                    cur = cur.next;
                    chainLen++; total++;
                }
                if (chainLen > 1) System.out.print("  (chain=" + chainLen + ")");
                System.out.println();
            }
        }
        System.out.println("Total: " + total + " data\n");
    }

    public static void main(String[] args) {
        Random rng = new Random();
        Set<Integer> used = new LinkedHashSet<>();
        while (used.size() < 100) used.add(rng.nextInt(999) + 1);

        System.out.println("Mengisi 100 data random unik (range 1-999)...");
        for (int n : used) insert(n);
        System.out.println("Selesai! Ketik 4 untuk lihat isi tabel.\n");

        int choice;
        do {
            System.out.println("===== HASH TABLE MENU =====");
            System.out.println("1. Input Data");
            System.out.println("2. Hapus Data");
            System.out.println("3. Cari Data");
            System.out.println("4. Tampilkan Tabel");
            System.out.println("0. Keluar");
            System.out.print("Pilihan: ");
            choice = sc.nextInt();
            if (choice == 1) { System.out.print("Masukkan angka: "); insert(sc.nextInt()); }
            else if (choice == 2) { System.out.print("Angka yang dihapus: "); delete(sc.nextInt()); }
            else if (choice == 3) { System.out.print("Angka yang dicari: "); search(sc.nextInt()); }
            else if (choice == 4) { printTable(); }
        } while (choice != 0);
        System.out.println("Bye!");
    }
}