import java.util.Scanner;
import java.util.Random;

public class MatrixProgram {

    static Scanner input = new Scanner(System.in);
    static int[][] matrix;
    static int rows;
    static int cols;

    static int[][] generateMatrix(int r, int c) {
        Random rand = new Random(42);
        int[][] m = new int[r][c];
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                m[i][j] = rand.nextInt(99) + 1;
        return m;
    }

    public static void main(String[] args) {
        System.out.println("=== PROGRAM OPERASI MATRIX ===");
        System.out.print("Masukkan jumlah baris: ");
        rows = input.nextInt();
        System.out.print("Masukkan jumlah kolom: ");
        cols = input.nextInt();

        System.out.println("\nPilih cara pengisian matrix:");
        System.out.println("  1. Random otomatis");
        System.out.println("  2. Input manual");
        System.out.print("Pilihan: ");
        int cara = input.nextInt();

        if (cara == 1) {
            matrix = generateMatrix(rows, cols);
            System.out.println("\nMatrix " + rows + "x" + cols + " (random):");
        } else {
            matrix = new int[rows][cols];
            System.out.println("Masukkan elemen matrix (" + rows + "x" + cols + "):");
            for (int i = 0; i < rows; i++)
                for (int j = 0; j < cols; j++) {
                    System.out.print("  [" + i + "][" + j + "]: ");
                    matrix[i][j] = input.nextInt();
                }
            System.out.println("\nMatrix " + rows + "x" + cols + ":");
        }
        cetakMatrix(matrix);

        int pilihan;
        do {
            tampilkanMenu();
            System.out.print("Pilihan Anda: ");
            pilihan = input.nextInt();

            switch (pilihan) {
                case 1: menuSort();      break;
                case 2: menuRotate();    break;
                case 3: menuTraversal(); break;
                case 4:
                    System.out.println("\n=== SPIRAL FORM ===");
                    cetakMatrix(matrix);
                    printSpiral(matrix, rows, cols);
                    break;
                case 5:
                    System.out.println("\n=== TRANSPOSE MATRIX ===");
                    matrix = transpose(matrix);
                    rows = matrix.length;
                    cols = matrix[0].length;
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
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║       MENU OPERASI MATRIX " + rows + "x" + cols + "      ║");
        System.out.println("╠════════════════════════════════════╣");
        System.out.println("║  1. Sort Matrix                    ║");
        System.out.println("║     a. Row-wise                    ║");
        System.out.println("║     b. Column-wise                 ║");
        System.out.println("║  2. Rotate Matrix                  ║");
        System.out.println("║     a. Clockwise by 1              ║");
        System.out.println("║     b. Counter-Clockwise by 1      ║");
        System.out.println("║     c. Rotate 90 derajat           ║");
        System.out.println("║     d. Rotate 180 derajat          ║");
        System.out.println("║  3. Traversal Matrix               ║");
        System.out.println("║     a. Row-wise traversal          ║");
        System.out.println("║     b. Column-wise traversal       ║");
        System.out.println("║  4. Print Spiral Form              ║");
        System.out.println("║  5. Transpose Matrix               ║");
        System.out.println("║  6. Quit                           ║");
        System.out.println("╚════════════════════════════════════╝");
    }

    static void menuSort() {
        System.out.println("\n[SORT] Pilih sub-menu:");
        System.out.println("  a. Row-wise");
        System.out.println("  b. Column-wise");
        System.out.print("Pilihan: ");
        String sub = input.next();
        // matrix langsung dioper (bukan copy) supaya hasil sort tersimpan permanen
        if (sub.equalsIgnoreCase("a")) {
            sortRowWise(matrix);
        } else if (sub.equalsIgnoreCase("b")) {
            sortColumnWise(matrix);
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
            case "a":
                // ukuran tetap sama, cukup mutasi langsung pada matrix
                rotateClockwiseBy1(matrix);
                break;
            case "b":
                rotateCounterClockwiseBy1(matrix);
                break;
            case "c":
                // ukuran bisa berubah (jika matrix bukan persegi), jadi hasilnya
                // ditampung lalu dipasang kembali sebagai matrix utama
                matrix = rotate90(matrix);
                rows = matrix.length;
                cols = matrix[0].length;
                break;
            case "d":
                matrix = rotate180(matrix);
                break;
            default:
                System.out.println("Sub-pilihan tidak valid!");
                break;
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

    static void cetakMatrix(int[][] m) {
        int r = m.length, c = m[0].length;
        System.out.println("  Ukuran: " + r + "x" + c);
        for (int i = 0; i < r; i++) {
            System.out.print("  [ ");
            for (int j = 0; j < c; j++)
                System.out.printf("%3d ", m[i][j]);
            System.out.println("]");
        }
        System.out.println();
    }

    static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j]; arr[j] = arr[j+1]; arr[j+1] = temp;
                }
    }

    // ===================== 1-a. SORT ROW-WISE =====================
    static void sortRowWise(int[][] m) {
        System.out.println("\n=== SORT ROW-WISE ===");
        System.out.println("Sebelum:"); cetakMatrix(m);
        for (int i = 0; i < m.length; i++) bubbleSort(m[i]);
        System.out.println("Setelah (tiap baris urut):"); cetakMatrix(m);
    }

    // ===================== 1-b. SORT COLUMN-WISE =====================
    static void sortColumnWise(int[][] m) {
        System.out.println("\n=== SORT COLUMN-WISE ===");
        System.out.println("Sebelum:"); cetakMatrix(m);
        int r = m.length, c = m[0].length;
        for (int j = 0; j < c; j++) {
            int[] kolom = new int[r];
            for (int i = 0; i < r; i++) kolom[i] = m[i][j];
            bubbleSort(kolom);
            for (int i = 0; i < r; i++) m[i][j] = kolom[i];
        }
        System.out.println("Setelah (tiap kolom urut):"); cetakMatrix(m);
    }

    // ===================== 2-a. ROTATE CLOCKWISE BY 1 =====================
    static void rotateClockwiseBy1(int[][] m) {
        System.out.println("\n=== ROTATE CLOCKWISE BY 1 ===");
        System.out.println("Sebelum:"); cetakMatrix(m);
        int r = m.length, c = m[0].length;
        int temp = m[0][0];
        for (int i = 0; i < r - 1; i++) m[i][0] = m[i+1][0];
        for (int j = 0; j < c - 1; j++) m[r-1][j] = m[r-1][j+1];
        for (int i = r - 1; i > 0; i--) m[i][c-1] = m[i-1][c-1];
        for (int j = c - 1; j > 1; j--) m[0][j] = m[0][j-1];
        m[0][1] = temp;
        System.out.println("Setelah:"); cetakMatrix(m);
    }

    // ===================== 2-b. ROTATE COUNTER-CLOCKWISE BY 1 =====================
    static void rotateCounterClockwiseBy1(int[][] m) {
        System.out.println("\n=== ROTATE COUNTER-CLOCKWISE BY 1 ===");
        System.out.println("Sebelum:"); cetakMatrix(m);
        int r = m.length, c = m[0].length;
        int temp = m[0][0];
        for (int j = 0; j < c - 1; j++) m[0][j] = m[0][j+1];
        for (int i = 0; i < r - 1; i++) m[i][c-1] = m[i+1][c-1];
        for (int j = c - 1; j > 0; j--) m[r-1][j] = m[r-1][j-1];
        for (int i = r - 1; i > 1; i--) m[i][0] = m[i-1][0];
        m[1][0] = temp;
        System.out.println("Setelah:"); cetakMatrix(m);
    }

    // ===================== 2-c. ROTATE 90 DERAJAT =====================
    // Mengembalikan matrix hasil rotasi supaya bisa dipasang kembali
    // sebagai data utama (ukuran bisa berubah jika baris != kolom)
    static int[][] rotate90(int[][] m) {
        System.out.println("\n=== ROTATE 90 DERAJAT (CLOCKWISE) ===");
        System.out.println("Sebelum (" + m.length + "x" + m[0].length + "):"); cetakMatrix(m);
        int r = m.length, c = m[0].length;
        int[][] hasil = new int[c][r];
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                hasil[j][r-1-i] = m[i][j];
        System.out.println("Setelah (" + c + "x" + r + "):"); cetakMatrix(hasil);
        return hasil;
    }

    // ===================== 2-d. ROTATE 180 DERAJAT =====================
    static int[][] rotate180(int[][] m) {
        System.out.println("\n=== ROTATE 180 DERAJAT ===");
        System.out.println("Sebelum:"); cetakMatrix(m);
        int r = m.length, c = m[0].length;
        int[][] hasil = new int[r][c];
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                hasil[r-1-i][c-1-j] = m[i][j];
        System.out.println("Setelah:"); cetakMatrix(hasil);
        return hasil;
    }

    // ===================== 3-a. TRAVERSAL ROW-WISE =====================
    static void traversalRowWise(int[][] m) {
        System.out.println("\n=== ROW-WISE TRAVERSAL ===");
        System.out.print("Hasil: ");
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[i].length; j++)
                System.out.printf("%3d ", m[i][j]);
        System.out.println("\nTotal elemen: " + (m.length * m[0].length));
    }

    // ===================== 3-b. TRAVERSAL COLUMN-WISE =====================
    static void traversalColumnWise(int[][] m) {
        System.out.println("\n=== COLUMN-WISE TRAVERSAL ===");
        System.out.print("Hasil: ");
        for (int j = 0; j < m[0].length; j++)
            for (int i = 0; i < m.length; i++)
                System.out.printf("%3d ", m[i][j]);
        System.out.println("\nTotal elemen: " + (m.length * m[0].length));
    }

    // ===================== 4. SPIRAL FORM =====================
    static void printSpiral(int[][] m, int r, int c) {
        System.out.println("Urutan: kanan -> bawah -> kiri -> atas\n");
        int top = 0, bottom = r-1, left = 0, right = c-1, count = 0;
        System.out.print("Hasil: ");
        while (top <= bottom && left <= right) {
            for (int j = left; j <= right; j++)  { System.out.printf("%3d ", m[top][j]); count++; }
            top++;
            for (int i = top; i <= bottom; i++)  { System.out.printf("%3d ", m[i][right]); count++; }
            right--;
            if (top <= bottom) {
                for (int j = right; j >= left; j--) { System.out.printf("%3d ", m[bottom][j]); count++; }
                bottom--;
            }
            if (left <= right) {
                for (int i = bottom; i >= top; i--) { System.out.printf("%3d ", m[i][left]); count++; }
                left++;
            }
        }
        System.out.println("\nTotal elemen: " + count);
    }

    // ===================== 5. TRANSPOSE =====================
    // Mengembalikan hasil transpose supaya dipasang sebagai data utama
    static int[][] transpose(int[][] m) {
        int r = m.length, c = m[0].length;
        System.out.println("Sebelum (" + r + "x" + c + "):"); cetakMatrix(m);
        int[][] hasil = new int[c][r];
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                hasil[j][i] = m[i][j];
        System.out.println("Setelah transpose (" + c + "x" + r + "):"); cetakMatrix(hasil);
        return hasil;
    }
}