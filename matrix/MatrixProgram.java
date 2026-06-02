import java.util.Scanner;
import java.util.Random;

public class MatrixProgram {

    static Scanner input = new Scanner(System.in);

    // Matrix 10x10 dengan data random (seed tetap agar hasilnya konsisten)
    static int[][] matrix = generateMatrix();

    static int rows = 10;
    static int cols = 10;

    static int[][] generateMatrix() {
        Random rand = new Random(42); // seed 42 agar angka selalu sama tiap run
        int[][] m = new int[10][10];
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                m[i][j] = rand.nextInt(99) + 1; // angka 1-99
        return m;
    }

    public static void main(String[] args) {
        System.out.println("=== PROGRAM OPERASI MATRIX 10x10 ===");
        System.out.println("\nMatrix awal:");
        cetakMatrix(matrix);

        int pilihan;
        do {
            tampilkanMenu();
            System.out.print("Pilihan Anda: ");
            pilihan = input.nextInt();

            switch (pilihan) {
                case 1: menuSort();     break;
                case 2: menuRotate();   break;
                case 3: menuTraversal();break;
                case 4:
                    System.out.println("\n=== SPIRAL FORM ===");
                    System.out.println("Matrix:");
                    cetakMatrix(matrix);
                    printSpiral(matrix, rows, cols);
                    break;
                case 5:
                    System.out.println("\n=== TRANSPOSE MATRIX ===");
                    transpose(matrix, rows, cols);
                    break;
                case 6:
                    System.out.println("Terima kasih! Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid! Coba lagi.");
            }
        } while (pilihan != 6);
    }

    // ===================== MENU =====================

    static void tampilkanMenu() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║           MENU OPERASI MATRIX        ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║  1. Sort Matrix                      ║");
        System.out.println("║     a. Row-wise                      ║");
        System.out.println("║     b. Column-wise                   ║");
        System.out.println("║  2. Rotate Matrix                    ║");
        System.out.println("║     a. Clockwise by 1                ║");
        System.out.println("║     b. Counter-Clockwise by 1        ║");
        System.out.println("║     c. Rotate 90 derajat             ║");
        System.out.println("║     d. Rotate 180 derajat            ║");
        System.out.println("║  3. Traversal Matrix                 ║");
        System.out.println("║     a. Row-wise traversal            ║");
        System.out.println("║     b. Column-wise traversal         ║");
        System.out.println("║  4. Print Spiral Form                ║");
        System.out.println("║  5. Transpose Matrix                 ║");
        System.out.println("║  6. Quit                             ║");
        System.out.println("╚══════════════════════════════════════╝");
    }

    static void menuSort() {
        System.out.println("\n[SORT] Pilih sub-menu:");
        System.out.println("  a. Row-wise");
        System.out.println("  b. Column-wise");
        System.out.print("Pilihan: ");
        String sub = input.next();
        if (sub.equalsIgnoreCase("a")) {
            sortRowWise(copyMatrix(matrix));
        } else if (sub.equalsIgnoreCase("b")) {
            sortColumnWise(copyMatrix(matrix));
        } else {
            System.out.println("Sub-pilihan tidak valid!");
        }
    }

    static void menuRotate() {
        System.out.println("\n[ROTATE] Pilih sub-menu:");
        System.out.println("  a. Clockwise by 1");
        System.out.println("  b. Counter-Clockwise by 1");
        System.out.println("  c. 90 derajat");
        System.out.println("  d. 180 derajat");
        System.out.print("Pilihan: ");
        String sub = input.next();
        switch (sub.toLowerCase()) {
            case "a": rotateClockwiseBy1(copyMatrix(matrix));        break;
            case "b": rotateCounterClockwiseBy1(copyMatrix(matrix)); break;
            case "c": rotate90(copyMatrix(matrix));                   break;
            case "d": rotate180(copyMatrix(matrix));                  break;
            default:  System.out.println("Sub-pilihan tidak valid!"); break;
        }
    }

    static void menuTraversal() {
        System.out.println("\n[TRAVERSAL] Pilih sub-menu:");
        System.out.println("  a. Row-wise");
        System.out.println("  b. Column-wise");
        System.out.print("Pilihan: ");
        String sub = input.next();
        if (sub.equalsIgnoreCase("a")) {
            traversalRowWise(matrix);
        } else if (sub.equalsIgnoreCase("b")) {
            traversalColumnWise(matrix);
        } else {
            System.out.println("Sub-pilihan tidak valid!");
        }
    }

    // ===================== HELPER =====================

    // Salin matrix agar matrix asli tidak berubah saat operasi dijalankan
    static int[][] copyMatrix(int[][] src) {
        int r = src.length;
        int c = src[0].length;
        int[][] copy = new int[r][c];
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                copy[i][j] = src[i][j];
        return copy;
    }

    // Cetak matrix dengan format rapi
    static void cetakMatrix(int[][] m) {
        System.out.println();
        for (int i = 0; i < m.length; i++) {
            System.out.print("  [ ");
            for (int j = 0; j < m[i].length; j++) {
                System.out.printf("%3d ", m[i][j]);
            }
            System.out.println("]");
        }
        System.out.println();
    }

    // Bubble sort untuk array 1 dimensi (ascending)
    static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j]   = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // ===================== 1-a. SORT ROW-WISE =====================
    // Tiap baris diurutkan secara terpisah dari kecil ke besar
    static void sortRowWise(int[][] m) {
        System.out.println("\n=== 1-a. SORT ROW-WISE ===");
        System.out.println("Matrix sebelum sort:");
        cetakMatrix(m);

        for (int i = 0; i < m.length; i++) {
            bubbleSort(m[i]); // urutkan tiap baris
        }

        System.out.println("Matrix setelah sort row-wise (tiap baris urut):");
        cetakMatrix(m);
    }

    // ===================== 1-b. SORT COLUMN-WISE =====================
    // Tiap kolom diurutkan secara terpisah dari kecil ke besar
    static void sortColumnWise(int[][] m) {
        System.out.println("\n=== 1-b. SORT COLUMN-WISE ===");
        System.out.println("Matrix sebelum sort:");
        cetakMatrix(m);

        int r = m.length;
        int c = m[0].length;

        for (int j = 0; j < c; j++) {
            // Ambil seluruh kolom j ke array sementara
            int[] kolom = new int[r];
            for (int i = 0; i < r; i++) kolom[i] = m[i][j];

            // Sort array kolom
            bubbleSort(kolom);

            // Kembalikan hasil sort ke kolom j
            for (int i = 0; i < r; i++) m[i][j] = kolom[i];
        }

        System.out.println("Matrix setelah sort column-wise (tiap kolom urut):");
        cetakMatrix(m);
    }

    // ===================== 2-a. ROTATE CLOCKWISE BY 1 =====================
    // Semua elemen di border luar bergeser 1 posisi searah jarum jam
    static void rotateClockwiseBy1(int[][] m) {
        System.out.println("\n=== 2-a. ROTATE CLOCKWISE BY 1 ===");
        System.out.println("Matrix sebelum rotate:");
        cetakMatrix(m);

        int r = m.length;
        int c = m[0].length;

        // Simpan elemen pojok kiri atas sebelum ditimpa
        int temp = m[0][0];

        // 1. Geser kolom kiri ke ATAS (m[i][0] <- m[i+1][0])
        for (int i = 0; i < r - 1; i++) m[i][0] = m[i + 1][0];

        // 2. Geser baris bawah ke KIRI (m[r-1][j] <- m[r-1][j+1])
        for (int j = 0; j < c - 1; j++) m[r - 1][j] = m[r - 1][j + 1];

        // 3. Geser kolom kanan ke BAWAH (m[i][c-1] <- m[i-1][c-1])
        for (int i = r - 1; i > 0; i--) m[i][c - 1] = m[i - 1][c - 1];

        // 4. Geser baris atas ke KANAN (m[0][j] <- m[0][j-1])
        for (int j = c - 1; j > 1; j--) m[0][j] = m[0][j - 1];

        // Taruh elemen yang disimpan di posisi [0][1]
        m[0][1] = temp;

        System.out.println("Matrix setelah rotate clockwise 1 posisi:");
        cetakMatrix(m);
    }

    // ===================== 2-b. ROTATE COUNTER-CLOCKWISE BY 1 =====================
    // Semua elemen di border luar bergeser 1 posisi berlawanan jarum jam
    static void rotateCounterClockwiseBy1(int[][] m) {
        System.out.println("\n=== 2-b. ROTATE COUNTER-CLOCKWISE BY 1 ===");
        System.out.println("Matrix sebelum rotate:");
        cetakMatrix(m);

        int r = m.length;
        int c = m[0].length;

        // Simpan elemen pojok kiri atas
        int temp = m[0][0];

        // 1. Geser baris atas ke KIRI (m[0][j] <- m[0][j+1])
        for (int j = 0; j < c - 1; j++) m[0][j] = m[0][j + 1];

        // 2. Geser kolom kanan ke ATAS (m[i][c-1] <- m[i+1][c-1])
        for (int i = 0; i < r - 1; i++) m[i][c - 1] = m[i + 1][c - 1];

        // 3. Geser baris bawah ke KANAN (m[r-1][j] <- m[r-1][j-1])
        for (int j = c - 1; j > 0; j--) m[r - 1][j] = m[r - 1][j - 1];

        // 4. Geser kolom kiri ke BAWAH (m[i][0] <- m[i-1][0])
        for (int i = r - 1; i > 1; i--) m[i][0] = m[i - 1][0];

        // Taruh elemen yang disimpan di posisi [1][0]
        m[1][0] = temp;

        System.out.println("Matrix setelah rotate counter-clockwise 1 posisi:");
        cetakMatrix(m);
    }

    // ===================== 2-c. ROTATE 90 DERAJAT CLOCKWISE =====================
    // Rumus: elemen [i][j] pindah ke [j][r-1-i]
    // Ukuran matrix berubah: rows x cols -> cols x rows
    static void rotate90(int[][] m) {
        System.out.println("\n=== 2-c. ROTATE 90 DERAJAT (CLOCKWISE) ===");
        System.out.println("Matrix sebelum rotate:");
        cetakMatrix(m);

        int r = m.length;
        int c = m[0].length;

        // Hasil: dimensi berubah menjadi c x r
        int[][] hasil = new int[c][r];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                hasil[j][r - 1 - i] = m[i][j];
            }
        }

        System.out.println("Matrix setelah rotate 90 derajat clockwise:");
        cetakMatrix(hasil);
    }

    // ===================== 2-d. ROTATE 180 DERAJAT =====================
    // Rumus: elemen [i][j] pindah ke [r-1-i][c-1-j]
    // Sama seperti membalik matrix secara horizontal DAN vertikal
    static void rotate180(int[][] m) {
        System.out.println("\n=== 2-d. ROTATE 180 DERAJAT ===");
        System.out.println("Matrix sebelum rotate:");
        cetakMatrix(m);

        int r = m.length;
        int c = m[0].length;

        int[][] hasil = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                hasil[r - 1 - i][c - 1 - j] = m[i][j];
            }
        }

        System.out.println("Matrix setelah rotate 180 derajat:");
        cetakMatrix(hasil);
    }

    // ===================== 3-a. TRAVERSAL ROW-WISE =====================
    // Baca elemen dari kiri ke kanan, baris demi baris
    static void traversalRowWise(int[][] m) {
        System.out.println("\n=== 3-a. ROW-WISE TRAVERSAL ===");
        System.out.println("Urutan baca: kiri ke kanan, baris demi baris\n");
        System.out.print("  Hasil: ");
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                System.out.printf("%3d ", m[i][j]);
            }
        }
        System.out.println();
        System.out.println("\nTotal elemen: " + (m.length * m[0].length));
    }

    // ===================== 3-b. TRAVERSAL COLUMN-WISE =====================
    // Baca elemen dari atas ke bawah, kolom demi kolom
    static void traversalColumnWise(int[][] m) {
        System.out.println("\n=== 3-b. COLUMN-WISE TRAVERSAL ===");
        System.out.println("Urutan baca: atas ke bawah, kolom demi kolom\n");
        System.out.print("  Hasil: ");
        for (int j = 0; j < m[0].length; j++) {
            for (int i = 0; i < m.length; i++) {
                System.out.printf("%3d ", m[i][j]);
            }
        }
        System.out.println();
        System.out.println("\nTotal elemen: " + (m.length * m[0].length));
    }

    // ===================== 4. SPIRAL FORM =====================
    // Baca elemen melingkar: kanan -> bawah -> kiri -> atas, mengecil ke tengah
    static void printSpiral(int[][] m, int r, int c) {
        System.out.println("Urutan baca: kanan -> bawah -> kiri -> atas (spiral)\n");

        int top    = 0, bottom = r - 1;
        int left   = 0, right  = c - 1;
        int count  = 0;

        System.out.print("  Hasil: ");
        while (top <= bottom && left <= right) {

            // Gerak ke KANAN sepanjang baris atas
            for (int j = left; j <= right; j++) {
                System.out.printf("%3d ", m[top][j]);
                count++;
            }
            top++;

            // Gerak ke BAWAH sepanjang kolom kanan
            for (int i = top; i <= bottom; i++) {
                System.out.printf("%3d ", m[i][right]);
                count++;
            }
            right--;

            // Gerak ke KIRI sepanjang baris bawah
            if (top <= bottom) {
                for (int j = right; j >= left; j--) {
                    System.out.printf("%3d ", m[bottom][j]);
                    count++;
                }
                bottom--;
            }

            // Gerak ke ATAS sepanjang kolom kiri
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    System.out.printf("%3d ", m[i][left]);
                    count++;
                }
                left++;
            }
        }
        System.out.println();
        System.out.println("\nTotal elemen: " + count);
    }

    // ===================== 5. TRANSPOSE =====================
    // Tukar baris dan kolom: m[i][j] menjadi m[j][i]
    static void transpose(int[][] m, int r, int c) {
        System.out.println("Matrix asli (10x10):");
        cetakMatrix(m);

        // Hasil transpose: c x r (untuk matrix 10x10 tetap 10x10)
        int[][] hasil = new int[c][r];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                hasil[j][i] = m[i][j]; // tukar posisi baris dan kolom
            }
        }

        System.out.println("Matrix setelah transpose (baris jadi kolom, kolom jadi baris):");
        cetakMatrix(hasil);
    }
}
