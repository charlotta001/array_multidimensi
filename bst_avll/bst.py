class Node:
    def __init__(self, node_id, nama):
        self.id = node_id
        self.nama = nama
        self.left = None
        self.right = None

# ── BST Operations ────────────────────────────────────────────────────────
def insert(root, node_id, nama):
    if root is None:
        return Node(node_id, nama)
    
    if node_id < root.id:
        root.left = insert(root.left, node_id, nama)
    elif node_id > root.id:
        root.right = insert(root.right, node_id, nama)
    else:
        print(f"  [!] ID {node_id} sudah ada, tidak ditambahkan.")
    return root

def search(root, node_id):
    if root is None or root.id == node_id:
        return root
    
    if node_id < root.id:
        return search(root.left, node_id)
    else:
        return search(root.right, node_id)

def min_node(root):
    current = root
    while current.left is not None:
        current = current.left
    return current

def delete(root, node_id):
    if root is None:
        print(f"  [!] ID {node_id} tidak ditemukan.")
        return None
    
    if node_id < root.id:
        root.left = delete(root.left, node_id)
    elif node_id > root.id:
        root.right = delete(root.right, node_id)
    else:
        # Jika node hanya memiliki satu anak atau tidak ada anak
        if root.left is None:
            return root.right
        if root.right is None:
            return root.left
        
        # Node dengan dua anak: Dapatkan in-order successor (terkecil di sub-tree kanan)
        succ = min_node(root.right)
        root.id = succ.id
        root.nama = succ.nama
        root.right = delete(root.right, succ.id)
        
    return root

# ── Traversal ─────────────────────────────────────────────────────────────
def inorder(root):
    if root is not None:
        inorder(root.left)
        print(f"  [{root.id}] {root.nama}")
        inorder(root.right)

def preorder(root):
    if root is not None:
        print(f"  [{root.id}] {root.nama}")
        preorder(root.left)
        preorder(root.right)

def postorder(root):
    if root is not None:
        postorder(root.left)
        postorder(root.right)
        print(f"  [{root.id}] {root.nama}")

# ── Main ──────────────────────────────────────────────────────────────────
def main():
    root = None

    # ── Data dari file data100.xlsx ───────────────────────────────────────
    ids = [
        5288, 5993, 8689, 8043, 8699, 2156, 4457, 8938, 2618, 9033,
        9971, 3874, 5914, 2398, 3725, 5210, 7363, 7631, 4513, 5656,
        6453, 8783, 8194, 9783, 3685, 4490, 8294, 8563, 1070, 5408,
        8258, 9309, 1138, 2751, 3258, 6402, 7921, 9781, 3818, 5204,
        6119, 1928, 4207, 7255, 5309, 2897, 8028, 1660, 3248, 5641,
        7376, 3525, 4492, 7187, 1305, 6602, 8153, 3561, 5082, 7151,
        7524, 9178, 9817, 4304, 6820, 9151, 3482, 3316, 5192, 7572,
        7660, 9224, 5083, 6362, 6465, 9888, 4159, 4969, 5097, 6271,
        9250, 3409, 4577, 6244, 8612, 4650, 6799,  9298, 4361, 4379,
        6928, 3195, 5741, 6852, 8147, 8902, 8967, 1302, 2363, 6861
    ]
    
    namas = [
        "pensil", "pulpen", "penghapus", "buku", "sampul",
        "penggaris", "kertas", "cat", "stabilo", "mobil",
        "motor", "becak", "sepeda", "kereta", "pesawat",
        "perahu", "kapal", "rakit", "kipas", "charger",
        "peci", "sarung", "sajadah", "smartphone", "jam",
        "televisi", "laptop", "komputer", "mouse", "keyboard",
        "tablet", "jendela", "kaca", "pintu", "kompor",
        "lemari", "kasur", "ranjang", "bantal", "baju",
        "kaos", "celana", "mukena", "jilbab", "pigura",
        "antena", "kulkas", "dispenser", "meja", "kursi",
        "kemoceng", "sapu", "gayung", "sabun", "sikat",
        "shampo", "botol", "gelas", "piring", "panci",
        "wajan", "blender", "galon", "cobek", "termos",
        "kran", "selang", "karpet", "tikar", "keset",
        "sepatu", "kaos kaki", "jaket", "piama", "piano",
        "gitar", "angklung", "suling", "toples", "parfum",
        "sisir", "topi", "gunting", "pisau", "kaleng",
        "tisu", "tas", "ikat pinggang", "korek api", "kopi",
        "gula", "cabai", "wortel", "timun", "apel",
        "jeruk", "tomat", "pisang", "pepaya", "bawang"
    ]

    print("=== Memasukkan 100 data dari data100.xlsx ===")
    for i in range(len(ids)):
        root = insert(root, ids[i], namas[i])
    print("  100 data berhasil dimasukkan ke BST.\n")

    # ── Menu interaktif ───────────────────────────────────────────────────
    running = True
    while running:
        print("╔══════════════════════════════╗")
        print("║        MENU BST              ║")
        print("╠══════════════════════════════╣")
        print("║ 1. Tambah Data               ║")
        print("║ 2. Cari Data                 ║")
        print("║ 3. Hapus Data                ║")
        print("║ 4. Traversal Inorder         ║")
        print("║ 5. Traversal Preorder        ║")
        print("║ 6. Traversal Postorder       ║")
        print("║ 0. Keluar                    ║")
        print("╚══════════════════════════════╝")
        
        try:
            pilihan = int(input("Pilihan: "))
        except ValueError:
            print("  [!] Harap masukkan angka yang valid.\n")
            continue

        if pilihan == 1:
            try:
                new_id = int(input("Masukkan ID   : "))
                new_nama = input("Masukkan Nama : ")
                root = insert(root, new_id, new_nama)
                print(f"  Data [{new_id}] {new_nama} berhasil ditambahkan.\n")
            except ValueError:
                print("  [!] ID harus berupa angka.\n")
                
        elif pilihan == 2:
            try:
                cari_id = int(input("Masukkan ID yang dicari: "))
                hasil = search(root, cari_id)
                if hasil is not None:
                    print(f"  Ditemukan -> [{hasil.id}] {hasil.nama}\n")
                else:
                    print(f"  Data dengan ID {cari_id} tidak ditemukan.\n")
            except ValueError:
                print("  [!] ID harus berupa angka.\n")
                
        elif pilihan == 3:
            try:
                hapus_id = int(input("Masukkan ID yang dihapus: "))
                cek = search(root, hapus_id)
                if cek is not None:
                    root = delete(root, hapus_id)
                    print(f"  Data [{hapus_id}] berhasil dihapus.\n")
                else:
                    print(f"  ID {hapus_id} tidak ditemukan.\n")
            except ValueError:
                print("  [!] ID harus berupa angka.\n")
                
        elif pilihan == 4:
            print("=== Inorder (ID Ascending) ===")
            inorder(root)
            print()
            
        elif pilihan == 5:
            print("=== Preorder ===")
            preorder(root)
            print()
            
        elif pilihan == 6:
            print("=== Postorder ===")
            postorder(root)
            print()
            
        elif pilihan == 0:
            print("Keluar dari program. Sampai jumpa!")
            running = False
            
        else:
            print("  Pilihan tidak valid.\n")

if __name__ == "__main__":
    main()