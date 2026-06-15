import java.util.*;

public class graph {
    static int MAX = 10;
    static int[][] g = new int[MAX][MAX];
    static String[] vertices = new String[MAX];
    static int n = 0;
    static Scanner sc = new Scanner(System.in);

    static int indexOf(String v) {
        for (int i = 0; i < n; i++) if (vertices[i].equals(v)) return i;
        return -1;
    }

    static void addVertices() {
        System.out.print("Jumlah vertex: ");
        int count = sc.nextInt();
        System.out.print("Masukkan " + count + " nama vertex (pisah spasi): ");
        for (int i = 0; i < count; i++) {
            String v = sc.next();
            if (indexOf(v) != -1) { System.out.println("'" + v + "' sudah ada, dilewati."); continue; }
            if (n >= MAX) { System.out.println("Tabel penuh!"); break; }
            vertices[n++] = v;
            System.out.println("Vertex '" + v + "' ditambahkan.");
        }
    }

    static void removeVertex() {
        System.out.print("Vertex: "); String v = sc.next();
        int i = indexOf(v);
        if (i == -1) { System.out.println("Not found!"); return; }
        for (int j = i; j < n - 1; j++) {
            vertices[j] = vertices[j + 1];
            for (int k = 0; k < n; k++) { g[j][k] = g[j+1][k]; g[k][j] = g[k][j+1]; }
        }
        vertices[n - 1] = null;
        for (int k = 0; k < n; k++) { g[n-1][k] = 0; g[k][n-1] = 0; }
        n--;
        System.out.println("Vertex '" + v + "' removed.");
    }

    static void addEdge() {
        System.out.print("From - To: "); String x = sc.next(), y = sc.next();
        int i = indexOf(x), j = indexOf(y);
        if (i == -1 || j == -1) { System.out.println("Vertex not found!"); return; }
        if (i == j) { System.out.println("Same vertex!"); return; }
        g[i][j] = g[j][i] = 1;
        System.out.println("Edge " + x + "-" + y + " added.");
    }

    static void removeEdge() {
        System.out.print("From - To: "); String x = sc.next(), y = sc.next();
        int i = indexOf(x), j = indexOf(y);
        if (i == -1 || j == -1) { System.out.println("Vertex not found!"); return; }
        g[i][j] = g[j][i] = 0;
        System.out.println("Edge " + x + "-" + y + " removed.");
    }

    static void display() {
        if (n == 0) { System.out.println("Graph kosong."); return; }
        System.out.printf("%5s", "");
        for (int i = 0; i < n; i++) System.out.printf("%5s", vertices[i]);
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.printf("%5s", vertices[i]);
            for (int j = 0; j < n; j++) System.out.printf("%5d", g[i][j]);
            System.out.println();
        }
    }

    static void dfs(String start) {
        int s = indexOf(start);
        if (s == -1) { System.out.println("Vertex tidak ditemukan!"); return; }
        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        stack.push(s);
        System.out.print("DFS: ");
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            if (visited[cur]) continue;
            visited[cur] = true;
            System.out.print(vertices[cur] + " ");
            for (int j = n - 1; j >= 0; j--)
                if (g[cur][j] == 1 && !visited[j]) stack.push(j);
        }
        System.out.println();
    }

    static void bfs(String start) {
        int s = indexOf(start);
        if (s == -1) { System.out.println("Vertex tidak ditemukan!"); return; }
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        visited[s] = true; queue.add(s);
        System.out.print("BFS: ");
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            System.out.print(vertices[cur] + " ");
            for (int j = 0; j < n; j++)
                if (g[cur][j] == 1 && !visited[j]) { visited[j] = true; queue.add(j); }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\nMENU:");
            System.out.println("1. Add Vertices");
            System.out.println("2. Remove Vertex");
            System.out.println("3. Add Edge");
            System.out.println("4. Remove Edge");
            System.out.println("5. Display");
            System.out.println("6. DFS");
            System.out.println("7. BFS");
            System.out.println("8. Quit");
            System.out.print("Choice: "); choice = sc.nextInt();
            switch (choice) {
                case 1 -> addVertices();
                case 2 -> removeVertex();
                case 3 -> addEdge();
                case 4 -> removeEdge();
                case 5 -> display();
                case 6 -> { System.out.print("Start vertex: "); dfs(sc.next()); }
                case 7 -> { System.out.print("Start vertex: "); bfs(sc.next()); }
                case 8 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 8);
    }
}