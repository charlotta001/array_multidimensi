data_awal = [
    (5288,"pensil"),(5993,"pulpen"),(8689,"penghapus"),(8043,"buku"),(8699,"sampul"),
    (2156,"penggaris"),(4457,"kertas"),(8938,"cat"),(2618,"stabilo"),(9033,"mobil"),
    (9971,"motor"),(3874,"becak"),(5914,"sepeda"),(2398,"kereta"),(3725,"pesawat"),
    (5210,"perahu"),(7363,"kapal"),(7631,"rakit"),(4513,"kipas"),(5656,"charger"),
    (6453,"peci"),(8783,"sarung"),(8194,"sajadah"),(9783,"smartphone"),(3685,"jam"),
    (4490,"televisi"),(8294,"laptop"),(8563,"komputer"),(1070,"mouse"),(5408,"keyboard"),
    (8258,"tablet"),(9309,"jendela"),(1138,"kaca"),(2751,"pintu"),(3258,"kompor"),
    (6402,"lemari"),(7921,"kasur"),(9781,"ranjang"),(3818,"bantal"),(5204,"baju"),
    (6119,"kaos"),(1928,"celana"),(4207,"mukena"),(7255,"jilbab"),(5309,"pigura"),
    (2897,"antena"),(8028,"kulkas"),(1660,"dispenser"),(3248,"meja"),(5641,"kursi"),
    (7376,"kemoceng"),(3525,"sapu"),(4492,"gayung"),(7187,"sabun"),(1305,"sikat"),
    (6602,"shampo"),(8153,"botol"),(3561,"gelas"),(5082,"piring"),(7151,"panci"),
    (7524,"wajan"),(9178,"blender"),(9817,"galon"),(4304,"cobek"),(6820,"termos"),
    (9151,"kran"),(3482,"selang"),(3316,"karpet"),(5192,"tikar"),(7572,"keset"),
    (7660,"sepatu"),(9224,"kaos kaki"),(5083,"jaket"),(6362,"piama"),(6465,"piano"),
    (9888,"gitar"),(4159,"angklung"),(4969,"suling"),(5097,"toples"),(6271,"parfum"),
    (9250,"sisir"),(3409,"topi"),(4577,"gunting"),(6244,"pisau"),(8612,"kaleng"),
    (4650,"tisu"),(6799,"tas"),(9298,"ikat pinggang"),(4361,"korek api"),(4379,"kopi"),
    (6928,"gula"),(3195,"cabai"),(5741,"wortel"),(6852,"timun"),(8147,"apel"),
    (8902,"jeruk"),(8967,"tomat"),(1302,"pisang"),(2363,"pepaya"),(6861,"bawang"),
]

# ===== MIN HEAP =====
min_heap = []

def min_swap(i, j):
    min_heap[i], min_heap[j] = min_heap[j], min_heap[i]

def min_up(i):
    while i > 0 and min_heap[(i-1)//2][0] > min_heap[i][0]:
        min_swap(i, (i-1)//2); i = (i-1)//2

def min_down(i):
    n = len(min_heap)
    while True:
        s, l, r = i, 2*i+1, 2*i+2
        if l < n and min_heap[l][0] < min_heap[s][0]: s = l
        if r < n and min_heap[r][0] < min_heap[s][0]: s = r
        if s == i: break
        min_swap(i, s); i = s

def min_insert(id, nama):
    min_heap.append((id, nama)); min_up(len(min_heap)-1)

def min_delete():
    if not min_heap: print("Min-Heap kosong!"); return
    id = int(input("Masukkan ID yang ingin dihapus dari Min-Heap: "))
    idx = next((i for i, x in enumerate(min_heap) if x[0] == id), -1)
    if idx == -1: print("ID tidak ditemukan!"); return
    print(f"Data dihapus: {min_heap[idx][0]} - {min_heap[idx][1]}")
    min_heap[idx] = min_heap[-1]; min_heap.pop()
    if idx < len(min_heap): min_up(idx); min_down(idx)

def min_print():
    if not min_heap: print("Min-Heap kosong!"); return
    sorted_data = sorted(min_heap, key=lambda x: x[0])
    print("\n=== DATA MIN-HEAP (Ascending) ===")
    print(f"{'ID':<10} {'Nama'}")
    print("-" * 25)
    for id, nama in sorted_data: print(f"{id:<10} {nama}")

# ===== MAX HEAP =====
max_heap = []

def max_swap(i, j):
    max_heap[i], max_heap[j] = max_heap[j], max_heap[i]

def max_up(i):
    while i > 0 and max_heap[(i-1)//2][0] < max_heap[i][0]:
        max_swap(i, (i-1)//2); i = (i-1)//2

def max_down(i):
    n = len(max_heap)
    while True:
        s, l, r = i, 2*i+1, 2*i+2
        if l < n and max_heap[l][0] > max_heap[s][0]: s = l
        if r < n and max_heap[r][0] > max_heap[s][0]: s = r
        if s == i: break
        max_swap(i, s); i = s

def max_insert(id, nama):
    max_heap.append((id, nama)); max_up(len(max_heap)-1)

def max_delete():
    if not max_heap: print("Max-Heap kosong!"); return
    id = int(input("Masukkan ID yang ingin dihapus dari Max-Heap: "))
    idx = next((i for i, x in enumerate(max_heap) if x[0] == id), -1)
    if idx == -1: print("ID tidak ditemukan!"); return
    print(f"Data dihapus: {max_heap[idx][0]} - {max_heap[idx][1]}")
    max_heap[idx] = max_heap[-1]; max_heap.pop()
    if idx < len(max_heap): max_up(idx); max_down(idx)

def max_print():
    if not max_heap: print("Max-Heap kosong!"); return
    sorted_data = sorted(max_heap, key=lambda x: x[0], reverse=True)
    print("\n=== DATA MAX-HEAP (Descending) ===")
    print(f"{'ID':<10} {'Nama'}")
    print("-" * 25)
    for id, nama in sorted_data: print(f"{id:<10} {nama}")

# ===== INIT & MAIN =====
for id, nama in data_awal:
    min_insert(id, nama); max_insert(id, nama)
print(f"Data awal berhasil dimuat ({len(data_awal)} item).")

pilihan = 0
while True:
    print("\n========== MENU HEAP ==========")
    print("1. Tambah data (Min-Heap & Max-Heap)")
    print("2. Tampilkan data ascending (Min-Heap)")
    print("3. Tampilkan data descending (Max-Heap)")
    print("4. Hapus data dari Min-Heap")
    print("5. Hapus data dari Max-Heap")
    print("6. Keluar")
    pilihan = int(input("Pilih menu: "))
    if pilihan == 1:
        id = int(input("ID: ")); nama = input("Nama: ")
        min_insert(id, nama); max_insert(id, nama)
        print("Data berhasil ditambahkan ke Min-Heap dan Max-Heap.")
    elif pilihan == 2: min_print()
    elif pilihan == 3: max_print()
    elif pilihan == 4: min_delete()
    elif pilihan == 5: max_delete()
    elif pilihan == 6: print("Program selesai."); break
    else: print("Pilihan tidak valid!")