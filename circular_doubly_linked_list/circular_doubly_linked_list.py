import threading
import time

# documentation
# threading      : https://docs.python.org/3/library/threading.html
# time.sleep     : https://docs.python.org/3/library/time.html#time.sleep
# string compare : https://docs.python.org/3/library/stdtypes.html#str.lower

# ===============================================================================================================================
# 1. Insert berita (berita baru selalu ditambahkan di akhir)
# 2. Hapus berita (berdasarkan nomor urut)
# 3. Tampilkan berita secara forward (teks berita akan tampil dari depan ke belakang dengan delay 3 detik antar berita)
# 4. Tampilkan berita secara backward (teks berita akan tampil dari belakang ke depan dengan delay 3 detik antar berita)
# 5. Tampil berita tertentu (berdasarkan nomor urut)
# 6. Exit
# ===============================================================================================================================


class Node:
    def __init__(self, berita):
        self.berita = berita
        self.prev = None
        self.next = None


# ---- Global Variables ----
count = 0
head = None
tail = None


def insert_at_end():
    global count, head, tail

    berita = input("input berita : ")
    new_node = Node(berita)

    if head is None:
        head = tail = new_node
        head.next = head
        head.prev = head
    else:
        tail.next = new_node
        new_node.prev = tail
        new_node.next = head
        head.prev = new_node
        tail = new_node

    count += 1


def delete_given_position():
    global count, head, tail

    if head is None:
        print("this linked list is empty")
        return

    print(f"position can be deleted 1 - {count}")
    try:
        pos = int(input("input position: "))
    except ValueError:
        print("pos just accept a number")
        return

    if pos < 1 or pos > count:
        print("pos more or less of list")
        return

    temp = head

    if head.next == head:
        head = None
        tail = None
    elif pos == 1:
        tail.next = head.next
        head.next.prev = tail
        head = head.next
    else:
        for i in range(1, pos):
            temp = temp.next
        if temp == tail:
            tail = temp.prev
        temp.prev.next = temp.next
        temp.next.prev = temp.prev

    count -= 1


def show_data_forward():
    global head, count

    if head is None:
        print("Linked list is empty")
        return

    pos = 1
    temp = head

    print("1.) ShowDataForward using for loop")
    print("2.) ShowDataForward using while (press 'c' + Enter to stop)")

    try:
        choice = int(input("input here : "))
    except ValueError:
        print("choice just accept number")
        return

    if choice == 1:
        for i in range(1, count + 1):
            print(f"{i}.) {temp.berita}")
            temp = temp.next
            time.sleep(3)

    elif choice == 2:
        # flag array — shared between threads (same as Java boolean[])
        running = [False]

        # input listener thread — same concept as Java Thread(Runnable)
        def listen_input():
            stop = input()
            if stop.lower() == "c":
                running[0] = True

        new_thread = threading.Thread(target=listen_input)
        new_thread.daemon = True  # same as setDaemon(true) in Java
        new_thread.start()

        while not running[0]:
            print(f"{pos}.) {temp.berita}")
            temp = temp.next

            # break sleep into small chunks to check flag frequently
            for _ in range(30):
                if running[0]:
                    break
                time.sleep(0.1)

            pos += 1
            if pos > count:
                pos = 1

    else:
        print("input just accept 1-2")


def show_data_backward():
    global tail, count

    if tail is None:
        print("Linked list is empty")
        return

    pos = count
    temp = tail

    print("1.) ShowDataBackward using for loop")
    print("2.) ShowDataBackward using while (press 'c' + Enter to stop)")

    try:
        choice = int(input("input here : "))
    except ValueError:
        print("choice just accept number")
        return

    if choice == 1:
        for i in range(count, 0, -1):
            print(f"{i}.) {temp.berita}")
            temp = temp.prev
            time.sleep(3)

    elif choice == 2:
        # flag array — shared between threads
        running = [False]

        # input listener thread
        def listen_input():
            stop = input()
            if stop.lower() == "c":
                running[0] = True

        new_thread = threading.Thread(target=listen_input)
        new_thread.daemon = True
        new_thread.start()

        while not running[0]:
            print(f"{pos}.) {temp.berita}")
            temp = temp.prev

            # break sleep into small chunks to check flag frequently
            for _ in range(30):
                if running[0]:
                    break
                time.sleep(0.1)

            pos -= 1
            if pos < 1:
                pos = count

    else:
        print("input just accept 1-2")


def show_data_based_in_order():
    global head, count

    print(f"Amount data currently: {count}")

    if head is None:
        print("data is empty")
        return

    try:
        order = int(input(f"input order that u want among 1 - {count} : "))
    except ValueError:
        print("input just accept number")
        return

    if order < 1 or order > count:
        print("order more or less than sequence")
        return

    temp = head
    for i in range(1, order):
        temp = temp.next

    print(f"Berita in order -{order} : {temp.berita}")


def init():
    print("1.) Insert berita (akan ditambahkan di index terakhir)")
    print("2.) Hapus berita (sesuai nomor urut)")
    print("3.) Tampilkan berita secara forward")
    print("4.) Tampilkan berita secara backward")
    print("5.) Tampil berita tertentu (berdasarkan nomor urut)")
    print("6.) Exit")

    choice = 0
    while choice != 6:
        try:
            choice = int(input("input your choice: "))
        except ValueError:
            print("input choice just accept number")
            continue

        if choice == 1:
            insert_at_end()
        elif choice == 2:
            delete_given_position()
        elif choice == 3:
            show_data_forward()
        elif choice == 4:
            show_data_backward()
        elif choice == 5:
            show_data_based_in_order()
        elif choice == 6:
            print("Exit...")
        else:
            print("choice just accept 1 - 6!")

    print("Program ended.")


if __name__ == "__main__":
    init()