import sys

# Konfigurasi Global
MAX = 10
count = 0
# Inisialisasi array 2D fix (10 baris, 2 kolom) dengan None
arr = [[None, None] for _ in range(MAX)]

def main():
    print("be wise when using sawit program")
    print("choose 1 - 8 for operation and 9 for close program")
    print("1. insert at beggining")
    print("2. insert given position")
    print("3. insert at end")
    print("4. delete from beggining")
    print("5. delete given position")
    print("6. delete frome end")
    print("7. delete first occurence")
    print("8. show data")
    print("9. exit")

    choice = 0
    while choice != 9:
        try:
            raw_input = input("9 to exit, your choice : ")
            choice = int(raw_input)
            
            if choice == 1:
                insert_at_beginning()
            elif choice == 2:
                insert_given_position()
            elif choice == 3:
                insert_at_end()
            elif choice == 4:
                delete_from_beginning()
            elif choice == 5:
                delete_given_position()
            elif choice == 6:
                delete_from_end()
            elif choice == 7:
                delete_first_occurence()
            elif choice == 8:
                show_data()
            elif choice == 9:
                break
            else:
                print("input only accept 1-9 ")
        except ValueError:
            print("can't input string! only accept number")

    print("thank you for using sawit program")

def insert_at_beginning():
    global count
    # Geser data dari index 8 ke 9, 7 ke 8, dst.
    for i in range(MAX - 1, 0, -1):
        arr[i][0] = arr[i - 1][0]
        arr[i][1] = arr[i - 1][1]

    arr[0][0] = input("input name: ")
    arr[0][1] = input("input nim: ")

    if count < MAX:
        count += 1

def insert_given_position():
    global count
    if count >= MAX or count < 0:
        print("=== Array is full ===")
        return

    try:
        position = int(input("choose the position (1-10): "))
        
        if 1 <= position <= MAX:
            index = position - 1

            if arr[index][0] is not None:
                print("array sudah ada yang mengisi, Y/N untuk Overwrite: ", end="")
                force = input().strip().upper()
                
                if force == 'Y':
                    arr[index][0] = input("input name: ")
                    arr[index][1] = input("input nim: ")
                elif force == 'N':
                    print("array not overwrite")
                else:
                    print("only accept Y/N")
            else:
                arr[index][0] = input("input name: ")
                arr[index][1] = input("input nim: ")

            if count < MAX:
                count += 1
        else:
            print("range out of the bound")
    except ValueError:
        print("position must be integer")

def insert_at_end():
    global count
    max_index = 9
    last_occupied = -1

    # Cek dari belakang (9 ke 0)
    for i in range(max_index, -1, -1):
        if arr[i][0] is not None:
            last_occupied = i
            break
    
    # Jika index terakhir (9) penuh
    if last_occupied == max_index:
        print("wanna force input (Y/N): ", end="")
        forced = input().strip().upper()

        if forced == 'Y':
            arr[max_index][0] = input("input name: ")
            arr[max_index][1] = input("input nim: ")
        else:
            print("array not overwrite")
    else:
        # Isi slot kosong setelah index terakhir yang terisi
        pos = last_occupied + 1
        arr[pos][0] = input("input name: ")
        arr[pos][1] = input("input nim: ")
        
        if count < MAX:
            count += 1

def delete_from_beginning():
    global count
    if count == 0:
        print("Array is empty")
        return

    # Geser ke kiri (shifting logic)
    for i in range(0, count - 1):
        arr[i][0] = arr[i + 1][0]
        arr[i][1] = arr[i + 1][1]

    # Kosongkan data terakhir yang tersisa setelah geser
    arr[count - 1][0] = None
    arr[count - 1][1] = None
    count -= 1

def delete_given_position():
    global count
    if count == 0:
        print("Array is empty")
        return

    try:
        position = int(input("input position: "))
        
        if 1 <= position <= MAX:
            index = position - 1
            
            if arr[index][0] is None:
                print("Position is already empty")
                return
            
            # Set null tanpa shifting (sesuai kode Java terakhir)
            arr[index][0] = None
            arr[index][1] = None
            count -= 1
        else:
            print("range out of the bound")
    except ValueError:
        print("only accept int")

def delete_from_end():
    global count
    max_index = 9

    # Cek dari belakang untuk hapus yang paling akhir
    for i in range(max_index, -1, -1):
        if arr[i][0] is not None:
            arr[i][0] = None
            arr[i][1] = None
            
            if count > 0:
                count -= 1
            
            print(f"Deleted data at index {i}")
            return

    print("Array is empty")

def delete_first_occurence():
    global count
    if count == 0:
        print("Array is empty")
        return

    nim_input = input("input nim will be delete: ")
    # Hapus semua spasi dari input
    target_nim = nim_input.replace(" ", "")

    index = -1

    # Scan dari depan (0 ke 9)
    for i in range(MAX):
        if arr[i][1] is not None:
            # Hapus spasi dari data array untuk perbandingan
            current_nim = arr[i][1].replace(" ", "")
            if current_nim.lower() == target_nim.lower():
                index = i
                break
    
    if index != -1:
        # Hapus data (set None) tanpa shifting
        arr[index][0] = None
        arr[index][1] = None

        if count > 0:
            count -= 1
        print("Data deleted successfully")
    else:
        print("nim not found")

def show_data():
    print(f"Length array / total data: {count}")
    for i in range(MAX):
        if arr[i][0] is not None:
            print(f"{i + 1} : {arr[i][0]} || {arr[i][1]}")
        else:
            print(f"{i + 1} : [Empty]")

if __name__ == "__main__":
    main()