import java.util.*;

public class graph {
    static int MAX = 10;
    static int[][] g = new int[MAX][MAX];
    static int[] vertices = new int[MAX];
    static int n = 0;
    static Scanner sc = new Scanner(System.in);

    static int indexOf(int v) {
        for (int i = 0; i < n; i++) if (vertices[i] == v) return i;
        return -1;
    }

    static void addVertex() {
        System.out.print("Vertex: "); int v = sc.nextInt();
        if (indexOf(v) != -1) { System.out.println("Already exists!"); return; }
        vertices[n++] = v;
        System.out.println("Vertex " + v + " added.");
    }

    static void removeVertex() {
        System.out.print("Vertex: "); int v = sc.nextInt();
        int i = indexOf(v);
        if (i == -1) { System.out.println("Not found!"); return; }
        for (int j = i; j < n - 1; j++) {
            vertices[j] = vertices[j + 1];
            for (int k = 0; k < n; k++) { g[j][k] = g[j+1][k]; g[k][j] = g[k][j+1]; }
        }
        n--;
        System.out.println("Vertex " + v + " removed.");
    }

    static void addEdge() {
        System.out.print("From - To: "); int x = sc.nextInt(), y = sc.nextInt();
        int i = indexOf(x), j = indexOf(y);
        if (i == -1 || j == -1) { System.out.println("Vertex not found!"); return; }
        if (i == j) { System.out.println("Same vertex!"); return; }
        g[i][j] = g[j][i] = 1;
        System.out.println("Edge " + x + "-" + y + " added.");
    }

    static void removeEdge() {
        System.out.print("From - To: "); int x = sc.nextInt(), y = sc.nextInt();
        int i = indexOf(x), j = indexOf(y);
        if (i == -1 || j == -1) { System.out.println("Vertex not found!"); return; }
        g[i][j] = g[j][i] = 0;
        System.out.println("Edge " + x + "-" + y + " removed.");
    }

    static void display() {
        System.out.print("   ");
        for (int i = 0; i < n; i++) System.out.printf("%3d", vertices[i]);
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.printf("%3d", vertices[i]);
            for (int j = 0; j < n; j++) System.out.printf("%3d", g[i][j]);
            System.out.println();
        }
    }

    static void dfs(int start) {
        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        stack.push(indexOf(start));
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

    static void bfs(int start) {
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        int s = indexOf(start); visited[s] = true; queue.add(s);
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
            System.out.println("\nMENU:\n1. Add Vertex\n2. Remove Vertex\n3. Add Edge\n4. Remove Edge\n5. Display\n6. DFS\n7. BFS\n8. Quit");
            System.out.print("Choice: "); choice = sc.nextInt();
            switch (choice) {
                case 1 -> addVertex();
                case 2 -> removeVertex();
                case 3 -> addEdge();
                case 4 -> removeEdge();
                case 5 -> display();
                case 6 -> { System.out.print("Start: "); dfs(sc.nextInt()); }
                case 7 -> { System.out.print("Start: "); bfs(sc.nextInt()); }
                case 8 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 8);
    }
}