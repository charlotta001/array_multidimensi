import java.util.*;

public class heap_excel {
    static int[][] minHeap = new int[200][2];
    static String[] minName = new String[200];
    static int minSize = 0;

    static int[][] maxHeap = new int[200][2];
    static String[] maxName = new String[200];
    static int maxSize = 0;

    // ===== MIN HEAP =====
    static void minSwap(int i, int j) {
        int[] ti = minHeap[i]; minHeap[i] = minHeap[j]; minHeap[j] = ti;
        String ts = minName[i]; minName[i] = minName[j]; minName[j] = ts;
    }
    static void minUp(int i) {
        while (i > 0 && minHeap[(i-1)/2][0] > minHeap[i][0]) { minSwap(i, (i-1)/2); i = (i-1)/2; }
    }
    static void minDown(int i) {
        int n = minSize;
        while (true) {
            int s = i, l = 2*i+1, r = 2*i+2;
            if (l < n && minHeap[l][0] < minHeap[s][0]) s = l;
            if (r < n && minHeap[r][0] < minHeap[s][0]) s = r;
            if (s == i) break;
            minSwap(i, s); i = s;
        }
    }
    static void minInsert(int id, String name) {
        minHeap[minSize][0] = id; minName[minSize] = name;
        minUp(minSize++);
    }
    static void minDelete() {
        if (minSize == 0) { System.out.println("Min-Heap kosong!"); return; }
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan ID yang ingin dihapus dari Min-Heap: ");
        int id = sc.nextInt();
        int idx = -1;
        for (int i = 0; i < minSize; i++) if (minHeap[i][0] == id) { idx = i; break; }
        if (idx == -1) { System.out.println("ID tidak ditemukan!"); return; }
        System.out.println("Data dihapus: " + minHeap[idx][0] + " - " + minName[idx]);
        minHeap[idx][0] = minHeap[minSize-1][0]; minName[idx] = minName[minSize-1]; minSize--;
        if (idx < minSize) { minUp(idx); minDown(idx); }
    }
    static void minPrint() {
        if (minSize == 0) { System.out.println("Min-Heap kosong!"); return; }
        int[] ids = new int[minSize]; String[] names = new String[minSize];
        for (int i = 0; i < minSize; i++) { ids[i] = minHeap[i][0]; names[i] = minName[i]; }
        // selection sort ascending
        for (int i = 0; i < minSize-1; i++)
            for (int j = i+1; j < minSize; j++)
                if (ids[i] > ids[j]) { int t = ids[i]; ids[i] = ids[j]; ids[j] = t; String ts = names[i]; names[i] = names[j]; names[j] = ts; }
        System.out.println("\n=== DATA MIN-HEAP (Ascending) ===");
        System.out.printf("%-8s %-15s%n", "ID", "Nama");
        System.out.println("-".repeat(25));
        for (int i = 0; i < minSize; i++) System.out.printf("%-8d %-15s%n", ids[i], names[i]);
    }

    // ===== MAX HEAP =====
    static void maxSwap(int i, int j) {
        int[] ti = maxHeap[i]; maxHeap[i] = maxHeap[j]; maxHeap[j] = ti;
        String ts = maxName[i]; maxName[i] = maxName[j]; maxName[j] = ts;
    }
    static void maxUp(int i) {
        while (i > 0 && maxHeap[(i-1)/2][0] < maxHeap[i][0]) { maxSwap(i, (i-1)/2); i = (i-1)/2; }
    }
    static void maxDown(int i) {
        int n = maxSize;
        while (true) {
            int s = i, l = 2*i+1, r = 2*i+2;
            if (l < n && maxHeap[l][0] > maxHeap[s][0]) s = l;
            if (r < n && maxHeap[r][0] > maxHeap[s][0]) s = r;
            if (s == i) break;
            maxSwap(i, s); i = s;
        }
    }
    static void maxInsert(int id, String name) {
        maxHeap[maxSize][0] = id; maxName[maxSize] = name;
        maxUp(maxSize++);
    }
    static void maxDelete() {
        if (maxSize == 0) { System.out.println("Max-Heap kosong!"); return; }
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan ID yang ingin dihapus dari Max-Heap: ");
        int id = sc.nextInt();
        int idx = -1;
        for (int i = 0; i < maxSize; i++) if (maxHeap[i][0] == id) { idx = i; break; }
        if (idx == -1) { System.out.println("ID tidak ditemukan!"); return; }
        System.out.println("Data dihapus: " + maxHeap[idx][0] + " - " + maxName[idx]);
        maxHeap[idx][0] = maxHeap[maxSize-1][0]; maxName[idx] = maxName[maxSize-1]; maxSize--;
        if (idx < maxSize) { maxUp(idx); maxDown(idx); }
    }
    static void maxPrint() {
        if (maxSize == 0) { System.out.println("Max-Heap kosong!"); return; }
        int[] ids = new int[maxSize]; String[] names = new String[maxSize];
        for (int i = 0; i < maxSize; i++) { ids[i] = maxHeap[i][0]; names[i] = maxName[i]; }
        // selection sort descending
        for (int i = 0; i < maxSize-1; i++)
            for (int j = i+1; j < maxSize; j++)
                if (ids[i] < ids[j]) { int t = ids[i]; ids[i] = ids[j]; ids[j] = t; String ts = names[i]; names[i] = names[j]; names[j] = ts; }
        System.out.println("\n=== DATA MAX-HEAP (Descending) ===");
        System.out.printf("%-8s %-15s%n", "ID", "Nama");
        System.out.println("-".repeat(25));
        for (int i = 0; i < maxSize; i++) System.out.printf("%-8d %-15s%n", ids[i], names[i]);
    }

    // ===== INIT DATA =====
    static void initData() {
        int[][] data = {
            {5288,0},{5993,0},{8689,0},{8043,0},{8699,0},{2156,0},{4457,0},{8938,0},{2618,0},{9033,0},
            {9971,0},{3874,0},{5914,0},{2398,0},{3725,0},{5210,0},{7363,0},{7631,0},{4513,0},{5656,0},
            {6453,0},{8783,0},{8194,0},{9783,0},{3685,0},{4490,0},{8294,0},{8563,0},{1070,0},{5408,0},
            {8258,0},{9309,0},{1138,0},{2751,0},{3258,0},{6402,0},{7921,0},{9781,0},{3818,0},{5204,0},
            {6119,0},{1928,0},{4207,0},{7255,0},{5309,0},{2897,0},{8028,0},{1660,0},{3248,0},{5641,0},
            {7376,0},{3525,0},{4492,0},{7187,0},{1305,0},{6602,0},{8153,0},{3561,0},{5082,0},{7151,0},
            {7524,0},{9178,0},{9817,0},{4304,0},{6820,0},{9151,0},{3482,0},{3316,0},{5192,0},{7572,0},
            {7660,0},{9224,0},{5083,0},{6362,0},{6465,0},{9888,0},{4159,0},{4969,0},{5097,0},{6271,0},
            {9250,0},{3409,0},{4577,0},{6244,0},{8612,0},{4650,0},{6799,0},{9298,0},{4361,0},{4379,0},
            {6928,0},{3195,0},{5741,0},{6852,0},{8147,0},{8902,0},{8967,0},{1302,0},{2363,0},{6861,0}
        };
        String[] names = {
            "pensil","pulpen","penghapus","buku","sampul","penggaris","kertas","cat","stabilo","mobil",
            "motor","becak","sepeda","kereta","pesawat","perahu","kapal","rakit","kipas","charger",
            "peci","sarung","sajadah","smartphone","jam","televisi","laptop","komputer","mouse","keyboard",
            "tablet","jendela","kaca","pintu","kompor","lemari","kasur","ranjang","bantal","baju",
            "kaos","celana","mukena","jilbab","pigura","antena","kulkas","dispenser","meja","kursi",
            "kemoceng","sapu","gayung","sabun","sikat","shampo","botol","gelas","piring","panci",
            "wajan","blender","galon","cobek","termos","kran","selang","karpet","tikar","keset",
            "sepatu","kaos kaki","jaket","piama","piano","gitar","angklung","suling","toples","parfum",
            "sisir","topi","gunting","pisau","kaleng","tisu","tas","ikat pinggang","korek api","kopi",
            "gula","cabai","wortel","timun","apel","jeruk","tomat","pisang","pepaya","bawang"
        };
        for (int i = 0; i < data.length; i++) { minInsert(data[i][0], names[i]); maxInsert(data[i][0], names[i]); }
        System.out.println("Data awal berhasil dimuat (" + data.length + " item).");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        initData();
        int pilihan;
        do {
            System.out.println("\n========== MENU HEAP ==========");
            System.out.println("1. Tambah data (Min-Heap & Max-Heap)");
            System.out.println("2. Tampilkan data ascending (Min-Heap)");
            System.out.println("3. Tampilkan data descending (Max-Heap)");
            System.out.println("4. Hapus data dari Min-Heap");
            System.out.println("5. Hapus data dari Max-Heap");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = sc.nextInt();
            switch (pilihan) {
                case 1:
                    System.out.print("ID: "); int id = sc.nextInt();
                    System.out.print("Nama: "); String nama = sc.next();
                    minInsert(id, nama); maxInsert(id, nama);
                    System.out.println("Data berhasil ditambahkan ke Min-Heap dan Max-Heap.");
                    break;
                case 2: minPrint(); break;
                case 3: maxPrint(); break;
                case 4: minDelete(); break;
                case 5: maxDelete(); break;
                case 6: System.out.println("Program selesai."); break;
                default: System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 6);
        sc.close();
    }
}