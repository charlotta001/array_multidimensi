import java.util.ArrayList;
import java.util.Scanner;

public class heap_max {

    static ArrayList<Integer> heap = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n=== MAX HEAP MENU ===");
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Heapify (Build Heap dari array)");
            System.out.println("4. Tampilkan Heap");
            System.out.println("0. Keluar");
            System.out.print("Pilihan: ");
            pilihan = sc.nextInt();

            switch (pilihan) {
                case 1 -> {
                    System.out.println("  [1] Insert 1 nilai");
                    System.out.println("  [2] Insert banyak nilai (1 baris)");
                    System.out.print("  Pilihan: ");
                    int subPilihan = sc.nextInt();

                    if (subPilihan == 1) {
                        System.out.print("Masukkan nilai: ");
                        int val = sc.nextInt();
                        insert(val);
                        System.out.println("Inserted: " + val);

                    } else if (subPilihan == 2) {
                        System.out.print("Masukkan nilai (pisah spasi, akhiri Enter): ");
                        sc.nextLine(); // buang newline sisa
                        String line = sc.nextLine();
                        String[] tokens = line.trim().split("\\s+");
                        for (String token : tokens) {
                            insert(Integer.parseInt(token));
                        }
                        System.out.println("Inserted " + tokens.length + " nilai.");

                    } else {
                        System.out.println("Sub-pilihan tidak valid.");
                    }

                    tampilkan();
}
                case 2 -> {
                    System.out.print("Nilai yang ingin dihapus: ");
                    int val = sc.nextInt();
                    deletion(val);
                    tampilkan();
                }
                case 3 -> {
                    System.out.print("Berapa banyak elemen? ");
                    int n = sc.nextInt();
                    int[] arr = new int[n];
                    System.out.print("Masukkan " + n + " elemen: ");
                    for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
                    heap = new ArrayList<>();
                    for (int v : arr) heap.add(v);
                    for (int i = n / 2 - 1; i >= 0; i--) heapify(i, n);
                    System.out.println("Heap setelah heapify:");
                    tampilkan();
                }
                case 4 -> tampilkan();
                case 0 -> System.out.println("Keluar.");
                default -> System.out.println("Pilihan tidak valid.");
            }

        } while (pilihan != 0);

        sc.close();
    }

    static void insert(int value) {
        heap.add(value);
        int index = heap.size() - 1;
        while (index > 0 && heap.get((index - 1) / 2) < heap.get(index)) {
            int parent = (index - 1) / 2;
            int tmp = heap.get(index);
            heap.set(index, heap.get(parent));
            heap.set(parent, tmp);
            index = parent;
        }
    }

    static void deletion(int value) {
        int index = -1;
        for (int i = 0; i < heap.size(); i++) {
            if (heap.get(i) == value) { index = i; break; }
        }
        if (index == -1) { System.out.println("Nilai " + value + " tidak ditemukan."); return; }

        heap.set(index, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);

        while (true) {
            int left  = 2 * index + 1;
            int right = 2 * index + 2;
            int largest = index;

            if (left  < heap.size() && heap.get(left)  > heap.get(largest)) largest = left;
            if (right < heap.size() && heap.get(right) > heap.get(largest)) largest = right;

            if (largest != index) {
                int tmp = heap.get(index);
                heap.set(index, heap.get(largest));
                heap.set(largest, tmp);
                index = largest;
            } else break;
        }
    }

    static void heapify(int i, int n) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && heap.get(l) > heap.get(largest)) largest = l;
        if (r < n && heap.get(r) > heap.get(largest)) largest = r;

        if (largest != i) {
            int tmp = heap.get(i);
            heap.set(i, heap.get(largest));
            heap.set(largest, tmp);
            heapify(largest, n);
        }
    }

    static void tampilkan() {
        if (heap.isEmpty()) { System.out.println("Heap kosong."); return; }
        System.out.print("Heap: ");
        for (int v : heap) System.out.print(v + " ");
        System.out.println();
    }
}