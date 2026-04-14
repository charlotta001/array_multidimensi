import java.util.Scanner;

public class bst {

    // ─── Node ────────────────────────────────────────────────────────────────
    static class Node {
        int id;
        String nama;
        Node left, right;

        Node(int id, String nama) {
            this.id   = id;
            this.nama = nama;
        }
    }

    // ─── BST Operations ──────────────────────────────────────────────────────
    static Node insert(Node root, int id, String nama) {
        if (root == null) return new Node(id, nama);
        if (id < root.id)       root.left  = insert(root.left,  id, nama);
        else if (id > root.id)  root.right = insert(root.right, id, nama);
        else                    System.out.println("  [!] ID " + id + " sudah ada, dilewati.");
        return root;
    }

    static Node search(Node root, int id) {
        if (root == null || root.id == id) return root;
        return id < root.id ? search(root.left, id) : search(root.right, id);
    }

    // Cari node terkecil di subtree kanan (untuk pengganti saat hapus)
    static Node minNode(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    static Node delete(Node root, int id) {
        if (root == null) return null;
        if (id < root.id) {
            root.left  = delete(root.left,  id);
        } else if (id > root.id) {
            root.right = delete(root.right, id);
        } else {
            // Node ditemukan
            if (root.left == null)  return root.right;
            if (root.right == null) return root.left;
            // Punya dua anak: ganti dengan successor (min di subtree kanan)
            Node succ = minNode(root.right);
            root.id   = succ.id;
            root.nama = succ.nama;
            root.right = delete(root.right, succ.id);
        }
        return root;
    }

    // ─── Traversal ───────────────────────────────────────────────────────────
    static void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);
        System.out.printf("  %-6d %s%n", root.id, root.nama);
        inorder(root.right);
    }

    static void preorder(Node root) {
        if (root == null) return;
        System.out.printf("  %-6d %s%n", root.id, root.nama);
        preorder(root.left);
        preorder(root.right);
    }

    static void postorder(Node root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.printf("  %-6d %s%n", root.id, root.nama);
    }

    // ─── Data Testing (semua dari PDF) ───────────────────────────────────────
    static final int[]    IDS  = {
        5288,5993,8689,8043,8699,2156,4457,8938,2618,9033,9971,3874,5914,2398,
        3725,5210,7363,7631,4513,5656,6453,8783,8194,9783,3685,4490,8294,8563,
        1070,5408,8258,9309,1138,2751,3258,6402,7921,9781,3818,5204,6119,1928,
        4207,7255,5309,2897,8028,1660,3248,5641,7376,3525,4492,7187,1305,6602,
        8153,3561,5082,7151,7524,9178,9817,4304,6820,9151,3482,3316,5192,7572,
        7660,9224,5083,6362,6465,9888,4159,4969,5097,6271,9250,3409,4577,6244,
        8612,4650,6799,9298,4361,4379,6928,3195,5741,6852,8147,8902,8967,1302,
        2363,6861
    };
    static final String[] NAMES = {
        "pensil","pulpen","penghapus","buku","sampul","penggaris","kertas","cat",
        "stabilo","mobil","motor","becak","sepeda","kereta","pesawat","perahu",
        "kapal","rakit","kipas","charger","peci","sarung","sajadah","smartphone",
        "jam","televisi","laptop","komputer","mouse","keyboard","tablet","jendela",
        "kaca","pintu","kompor","lemari","kasur","ranjang","bantal","baju","kaos",
        "celana","mukena","jilbab","pigura","antena","kulkas","dispenser","meja",
        "kursi","kemoceng","sapu","gayung","sabun","sikat","shampo","botol","gelas",
        "piring","panci","wajan","blender","galon","cobek","termos","kran","selang",
        "karpet","tikar","keset","sepatu","kaos kaki","jaket","piama","piano",
        "gitar","angklung","suling","toples","parfum","sisir","topi","gunting",
        "pisau","kaleng","tisu","tas","ikat pinggang","korek api","kopi","gula",
        "cabai","wortel","timun","apel","jeruk","tomat","pisang","pepaya","bawang"
    };

    // ─── Main ────────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        Node root = null;
        Scanner sc = new Scanner(System.in);

        // --- Masukkan semua data ---
        System.out.println("======================================");
        System.out.println("  BINARY SEARCH TREE — Barang/Produk");
        System.out.println("======================================");
        System.out.println("\n[*] Memasukkan " + IDS.length + " data ke BST...");
        for (int i = 0; i < IDS.length; i++) {
            root = insert(root, IDS[i], NAMES[i]);
        }
        System.out.println("[*] Selesai.\n");

        // --- Menu Interaktif ---
        boolean running = true;
        while (running) {
            System.out.println("─────────────────────────────────────");
            System.out.println("  MENU");
            System.out.println("  1. Tambah Data");
            System.out.println("  2. Cari Data");
            System.out.println("  3. Hapus Data");
            System.out.println("  4. Traversal Inorder");
            System.out.println("  5. Traversal Preorder");
            System.out.println("  6. Traversal Postorder");
            System.out.println("  0. Keluar");
            System.out.println("─────────────────────────────────────");
            System.out.print("  Pilih: ");

            int pilih = sc.nextInt();
            System.out.println();

            switch (pilih) {
                case 1:
                    System.out.print("  ID   : "); int newId = sc.nextInt();
                    System.out.print("  Nama : "); String newNama = sc.next();
                    root = insert(root, newId, newNama);
                    System.out.println("  [+] Data berhasil ditambahkan.\n");
                    break;

                case 2:
                    System.out.print("  Cari ID: "); int cariId = sc.nextInt();
                    Node found = search(root, cariId);
                    if (found != null)
                        System.out.printf("  [✓] Ditemukan → ID: %-6d | Nama: %s%n%n", found.id, found.nama);
                    else
                        System.out.println("  [✗] ID " + cariId + " tidak ditemukan.\n");
                    break;

                case 3:
                    System.out.print("  Hapus ID: "); int hapusId = sc.nextInt();
                    Node cek = search(root, hapusId);
                    if (cek != null) {
                        root = delete(root, hapusId);
                        System.out.println("  [-] Data ID " + hapusId + " berhasil dihapus.\n");
                    } else {
                        System.out.println("  [✗] ID " + hapusId + " tidak ditemukan.\n");
                    }
                    break;

                case 4:
                    System.out.println("  === INORDER (Ascending by ID) ===");
                    inorder(root);
                    System.out.println();
                    break;

                case 5:
                    System.out.println("  === PREORDER (Root → Left → Right) ===");
                    preorder(root);
                    System.out.println();
                    break;

                case 6:
                    System.out.println("  === POSTORDER (Left → Right → Root) ===");
                    postorder(root);
                    System.out.println();
                    break;

                case 0:
                    System.out.println("  Sampai jumpa!");
                    running = false;
                    break;

                default:
                    System.out.println("  [!] Pilihan tidak valid.\n");
            }
        }
        sc.close();
    }
}