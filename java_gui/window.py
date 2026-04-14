import tkinter as tk
from tkinter import ttk, messagebox
import subprocess
import threading

# ─── Data Structure ───────────────────────────────────────────────────────────

class Node:
    def __init__(self, data, nomor):
        self.data   = data
        self.nomor  = nomor
        self.next   = None

class MyQueue:
    def __init__(self):
        self.front    = None
        self.back     = None
        self.nomor    = 0
        self.curr_size = 0

    def is_empty(self):
        return self.front is None

    def enqueue(self, data):
        self.nomor += 1
        node = Node(data, self.nomor)
        if self.is_empty():
            self.front = self.back = node
        else:
            self.back.next = node
            self.back = node
        self.curr_size += 1

    def dequeue(self):
        if self.is_empty():
            return
        self.front = self.front.next
        if self.front is None:
            self.back = None
        self.curr_size -= 1

    def get_front(self):
        if self.front is None:
            return "antrian kosong"
        return f"{self.front.nomor}-{self.front.data}"

    def size(self):
        return self.curr_size

# ─── GUI ──────────────────────────────────────────────────────────────────────

class WindowApp:
    def __init__(self, root: tk.Tk):
        self.root  = root
        self.queue = MyQueue()
        self.rows  = []          # list nama (untuk tabel)

        self._build_main()
        self._build_popup()

    # ── Layout utama ──────────────────────────────────────────────────────────
    def _build_main(self):
        self.root.title("Queue Manager – Python GUI")
        self.root.geometry("800x600")
        self.root.resizable(True, True)
        self.root.configure(bg="#1e1e2e")

        # ── Panel atas ────────────────────────────────────────────────────────
        top = tk.Frame(self.root, bg="#1e1e2e", pady=16)
        top.pack(fill="x", padx=20)

        tk.Label(
            top, text="Nama:", bg="#1e1e2e", fg="#cdd6f4",
            font=("Consolas", 12)
        ).pack(side="left", padx=(0, 8))

        self.entry = tk.Entry(
            top, width=24,
            bg="#313244", fg="#cdd6f4", insertbackground="#cdd6f4",
            relief="flat", font=("Consolas", 12), bd=6
        )
        self.entry.pack(side="left", padx=(0, 8))
        self.entry.bind("<Return>", lambda _: self._submit())

        self._btn(top, "Submit",  self._submit,  "#a6e3a1").pack(side="left", padx=4)
        self._btn(top, "Panggil", self._panggil, "#89b4fa").pack(side="left", padx=4)
        self._btn(top, "Info",    self._show,    "#f9e2af").pack(side="left", padx=4)

        # ── Tabel ─────────────────────────────────────────────────────────────
        bot = tk.Frame(self.root, bg="#1e1e2e")
        bot.pack(fill="both", expand=True, padx=20, pady=(0, 20))

        style = ttk.Style()
        style.theme_use("clam")
        style.configure(
            "Treeview",
            background="#313244", foreground="#cdd6f4",
            fieldbackground="#313244", rowheight=28,
            font=("Consolas", 11)
        )
        style.configure(
            "Treeview.Heading",
            background="#45475a", foreground="#cdd6f4",
            font=("Consolas", 12, "bold")
        )
        style.map("Treeview", background=[("selected", "#585b70")])

        cols = ("No", "Nama")
        self.tree = ttk.Treeview(bot, columns=cols, show="headings")
        for c in cols:
            self.tree.heading(c, text=c)
            self.tree.column("No",   width=60,  anchor="center")
            self.tree.column("Nama", width=600, anchor="w")

        scroll = ttk.Scrollbar(bot, orient="vertical", command=self.tree.yview)
        self.tree.configure(yscrollcommand=scroll.set)

        self.tree.pack(side="left", fill="both", expand=True)
        scroll.pack(side="right", fill="y")

        self.entry.focus()

    # ── Popup panggil ─────────────────────────────────────────────────────────
    def _build_popup(self):
        self.popup = tk.Toplevel(self.root)
        self.popup.title("Panggil Antrian")
        self.popup.geometry("500x250")
        self.popup.configure(bg="#1e1e2e")
        self.popup.resizable(False, False)
        self.popup.withdraw()   # sembunyikan dulu

        self.popup.protocol("WM_DELETE_WINDOW", self.popup.withdraw)

        frame = tk.Frame(self.popup, bg="#1e1e2e")
        frame.place(relx=0.5, rely=0.5, anchor="center")

        self.lbl_nomor = tk.Label(
            frame, text="-", bg="#1e1e2e", fg="#89b4fa",
            font=("Consolas", 52, "bold")
        )
        self.lbl_nomor.pack()

        self.lbl_nama = tk.Label(
            frame, text="-", bg="#1e1e2e", fg="#cdd6f4",
            font=("Consolas", 26)
        )
        self.lbl_nama.pack(pady=(8, 0))

    # ── Helper tombol ─────────────────────────────────────────────────────────
    @staticmethod
    def _btn(parent, text, cmd, color):
        return tk.Button(
            parent, text=text, command=cmd,
            bg=color, fg="#1e1e2e", activebackground=color,
            font=("Consolas", 11, "bold"),
            relief="flat", padx=12, pady=6, cursor="hand2"
        )

    # ── Actions ───────────────────────────────────────────────────────────────
    def _submit(self):
        nama = self.entry.get().strip()
        if not nama:
            return
        self.rows.append(nama)
        self.queue.enqueue(nama)
        self._refresh_table()
        self.entry.delete(0, "end")
        self.entry.focus()

    def _panggil(self):
        temp = self.queue.get_front()
        if temp != "antrian kosong":
            parts = temp.split("-", 1)
            self.lbl_nomor.config(text=f"Nomor: {parts[0]}")
            self.lbl_nama.config(text=f"Nama:  {parts[1]}")
            self.popup.deiconify()
            self.popup.lift()
            self._suara(temp)
            self.queue.dequeue()
        else:
            self.popup.withdraw()
            messagebox.showinfo("Info", "Antrian kosong!")

    def _show(self):
        front  = self.queue.get_front()
        ukuran = self.queue.size()
        messagebox.showinfo(
            f"Panjang antrian: {ukuran}",
            f"Antrian terdepan: {front}"
        )

    def _refresh_table(self):
        for item in self.tree.get_children():
            self.tree.delete(item)
        for i, nama in enumerate(self.rows, start=1):
            self.tree.insert("", "end", values=(i, nama))

    # ── TTS via PowerShell (Windows only) ─────────────────────────────────────
    def _suara(self, text: str):
        def run():
            try:
                script = (
                    "Add-Type -AssemblyName System.Speech; "
                    f"(New-Object System.Speech.Synthesis.SpeechSynthesizer).Speak('{text.replace(chr(39), '')}');"
                )
                subprocess.Popen(
                    ["powershell", "-Command", script],
                    stdout=subprocess.DEVNULL,
                    stderr=subprocess.DEVNULL
                ).wait()
            except Exception as e:
                print(f"TTS error: {e}")
        threading.Thread(target=run, daemon=True).start()

# ─── Entry point ──────────────────────────────────────────────────────────────

if __name__ == "__main__":
    root = tk.Tk()
    app  = WindowApp(root)
    root.mainloop()