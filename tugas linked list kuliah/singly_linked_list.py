class Node:
    def __init__(self, name: str, nim: int):
        self.name = name
        self.nim = nim
        self.next = None

    def __str__(self):
        return f"{self.name}||{self.nim}"


class SinglyLinkedList:
    def __init__(self):
        self.head = None
        self.count = 0

    # ─── INSERT ───────────────────────────────────────────────

    def insert_at_beginning(self):
        name = input("Input name: ").strip()

        nim_input = input("Input nim: ").strip()
        if not nim_input.lstrip('-').isdigit():
            print("Nim have to be int")
            return

        nim = int(nim_input)
        new_node = Node(name, nim)
        new_node.next = self.head
        self.head = new_node
        self.count += 1

    def insert_given_position(self):
        if self.head is None:
            print("Data is empty")
            return

        # hitung total node
        curr = self.head
        total = 0
        while curr:
            total += 1
            curr = curr.next

        print(f"Amount data currently: {total}")
        print(f"Data from 1 - {total + 1}")

        pos_input = input("Input posisi: ").strip()
        if not pos_input.lstrip('-').isdigit():
            print("Input only accept number")
            return

        pos = int(pos_input)
        if pos < 1 or pos > total + 1:
            print(f"Position just accept 1 - {total + 1}")
            return

        name = input("Input nama: ").strip()

        nim_input = input("Input nim: ").strip()
        if not nim_input.lstrip('-').isdigit():
            print("Input only accept number")
            return

        nim = int(nim_input)

        if pos == 1:
            new_node = Node(name, nim)
            new_node.next = self.head
            self.head = new_node
            self.count += 1
            print("Data is complete added to 1")
            return

        curr = self.head
        for i in range(1, pos - 1):
            if curr is None:
                break
            curr = curr.next

        new_node = Node(name, nim)
        new_node.next = curr.next
        curr.next = new_node
        self.count += 1
        print(f"Data is complete added at {pos}")

    def insert_at_end(self):
        if self.head is None:
            print("Head is null")
            return

        name = input("Input name: ").strip()

        nim_input = input("Input nim: ").strip()
        if not nim_input.lstrip('-').isdigit():
            print("Nim only accept int")
            return

        nim = int(nim_input)
        new_node = Node(name, nim)

        last = self.head
        while last.next:
            last = last.next

        last.next = new_node
        self.count += 1

    # ─── DELETE ───────────────────────────────────────────────

    def delete_from_beginning(self):
        if self.head is None:
            print("Head is null")
            return

        self.head = self.head.next
        self.count -= 1

    def delete_from_end(self):
        if self.head is None:
            print("Head is null")
            return

        if self.head.next is None:
            self.head = None
            self.count -= 1
            return

        curr = self.head
        while curr.next.next:
            curr = curr.next

        curr.next = None
        self.count -= 1

    def delete_given_position(self):
        pos_input = input("Input pos: ").strip()
        if not pos_input.lstrip('-').isdigit():
            print("Input only accept number")
            return

        pos = int(pos_input)

        if pos < 1 or pos > self.count:
            print("Pos kurang dari 1 dan lebih dari count")
            return

        if pos == 1:
            self.delete_from_beginning()
            return

        if pos == self.count:
            self.delete_from_end()
            return

        curr = self.head
        for i in range(pos - 2):
            curr = curr.next

        curr.next = curr.next.next
        self.count -= 1

    def delete_first_occurrence(self):
        nim_input = input("Input nim: ").strip()
        if not nim_input.lstrip('-').isdigit():
            print("Nim only accepts int")
            return

        nim = int(nim_input)
        index = -1
        prev = None
        curr = self.head

        for i in range(self.count):
            if curr.nim == nim:
                index = i
                break
            prev = curr
            curr = curr.next

        if index == -1:
            print("Nim not found")
        elif index == 0:
            self.delete_from_beginning()
        elif index == self.count - 1:
            self.delete_from_end()
        else:
            prev.next = curr.next
            self.count -= 1

    # ─── SHOW ─────────────────────────────────────────────────

    def show_data(self):
        print()
        print(f"count = {self.count}")
        print("=========")
        temp = self.head
        i = 1
        while temp:
            print(f"{i}. {temp.name} || {temp.nim}")
            temp = temp.next
            i += 1

    # ─── INIT / MENU ──────────────────────────────────────────

    def init(self):
        print("Choose 1 - 8 for operation and 9 to close program")
        print("1. Insert at beginning")
        print("2. Insert given position")
        print("3. Insert at end")
        print("4. Delete from beginning")
        print("5. Delete given position")
        print("6. Delete from end")
        print("7. Delete first occurrence")
        print("8. Show data")
        print("9. Exit")

        choice = 0
        while choice != 9:
            raw = input("Input only accept 1 - 9: ").strip()

            if not raw.lstrip('-').isdigit():
                print("Can't input string, input again!")
                continue

            choice = int(raw)

            if choice == 1:
                self.insert_at_beginning()
            elif choice == 2:
                self.insert_given_position()
            elif choice == 3:
                self.insert_at_end()
            elif choice == 4:
                self.delete_from_beginning()
            elif choice == 5:
                self.delete_given_position()
            elif choice == 6:
                self.delete_from_end()
            elif choice == 7:
                self.delete_first_occurrence()
            elif choice == 8:
                self.show_data()
            elif choice == 9:
                break
            else:
                print("Input only accept 1 - 9!")


if __name__ == "__main__":
    sll = SinglyLinkedList()
    sll.init()