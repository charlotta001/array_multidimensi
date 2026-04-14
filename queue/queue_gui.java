import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// ============================================================
//  STRUKTUR CONTAINER:
//
//  JFrame
//   └── mainPanel [BorderLayout]
//        ├── NORTH  → inputPanel [FlowLayout]
//        │            JLabel | JTextField | JButton "Ambil Antrian"
//        ├── CENTER → JScrollPane → JTextArea (log/display)
//        └── SOUTH  → actionPanel [FlowLayout]
//                     JButton "Panggil" | JButton "Tampilkan"
// ============================================================

public class queue_gui extends JFrame {

    // --- Komponen GUI ---
    private JTextField nameField;
    private JTextArea  displayArea;
    private JButton    enqueueBtn, callBtn, showBtn;

    // --- Data ---
    private Queue queue = new Queue();

    // ============================================================
    //  CONSTRUCTOR — membangun semua komponen GUI
    // ============================================================
    public queue_gui() {
        // Setup JFrame (window utama)
        setTitle("Sistem Antrian");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // tengah layar

        // ── MAIN PANEL (BorderLayout) ────────────────────────────
        // BorderLayout membagi panel jadi 5 zona: NORTH CENTER SOUTH EAST WEST
        // Kita pakai 3: NORTH (input), CENTER (display), SOUTH (aksi)
        JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ── NORTH: Input Panel ───────────────────────────────────
        // FlowLayout = komponen berjejer kiri ke kanan secara otomatis
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel nameLabel = new JLabel("Nama:");
        nameField   = new JTextField(15);  // lebar kira-kira 15 karakter
        enqueueBtn  = new JButton("Ambil Antrian");

        // Masukkan komponen ke inputPanel
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(enqueueBtn);

        // ── CENTER: Display Area ─────────────────────────────────
        // JTextArea untuk menampilkan log/antrian
        // JScrollPane membungkusnya agar bisa di-scroll
        displayArea = new JTextArea();
        displayArea.setEditable(false);          // user tidak bisa ketik langsung
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // ── SOUTH: Action Panel ──────────────────────────────────
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        callBtn = new JButton("Panggil Antrian");
        showBtn = new JButton("Tampilkan Antrian");

        actionPanel.add(callBtn);
        actionPanel.add(showBtn);

        // ── GABUNGKAN ke mainPanel ───────────────────────────────
        // BorderLayout.NORTH/CENTER/SOUTH menentukan zona penempatan
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(actionPanel, BorderLayout.SOUTH);

        // ── Pasang mainPanel ke JFrame ───────────────────────────
        add(mainPanel);

        // ── EVENT LISTENERS ─────────────────────────────────────
        enqueueBtn.addActionListener(e -> handleEnqueue());
        callBtn.addActionListener(e -> handleCall());
        showBtn.addActionListener(e -> handleShow());

        // Tekan Enter di nameField = sama dengan klik enqueueBtn
        nameField.addActionListener(e -> handleEnqueue());

        setVisible(true);
    }

    // ============================================================
    //  HANDLER: Ambil Antrian
    // ============================================================
    private void handleEnqueue() {
        String nama = nameField.getText().trim();
        if (nama.isEmpty()) {
            displayArea.append("[!] Nama tidak boleh kosong.\n");
            return;
        }
        queue.enqueue(nama);
        displayArea.append("[+] Antrian diambil → No. " + queue.getLastNumber() + " - " + nama + "\n");
        nameField.setText("");
        nameField.requestFocus();
    }

    // ============================================================
    //  HANDLER: Panggil Antrian (dequeue + suara)
    // ============================================================
    private void handleCall() {
        if (queue.isEmpty()) {
            displayArea.append("[!] Antrian kosong.\n");
            return;
        }
        int    nomor = queue.getFrontNumber();
        String nama  = queue.getFrontName();
        queue.dequeue();

        String pesan = "Nomor antrian " + nomor + ", " + nama + ", silakan maju.";
        displayArea.append("[>>] DIPANGGIL → No. " + nomor + " - " + nama + "\n");

        // Suara via PowerShell (Windows built-in TTS)
        speakWindows(pesan);
    }

    // ============================================================
    //  HANDLER: Tampilkan semua antrian
    // ============================================================
    private void handleShow() {
        if (queue.isEmpty()) {
            displayArea.append("[i] Antrian kosong.\n");
            return;
        }
        displayArea.append("--- Daftar Antrian ---\n");
        queue.showAll(displayArea);
        displayArea.append("----------------------\n");
    }

    // ============================================================
    //  TEXT-TO-SPEECH via PowerShell (Windows)
    //  Menggunakan System.Speech yang sudah built-in di Windows
    // ============================================================
    private void speakWindows(String text) {
        // Jalankan di thread terpisah agar GUI tidak freeze
        new Thread(() -> {
            try {
                String script =
                    "Add-Type -AssemblyName System.Speech; " +
                    "$s = New-Object System.Speech.Synthesis.SpeechSynthesizer; " +
                    "$s.Speak('" + text.replace("'", "") + "');";
                String[] cmd = { "powershell", "-Command", script };
                Runtime.getRuntime().exec(cmd);
            } catch (Exception ex) {
                // Kalau PowerShell gagal, cukup log saja
                SwingUtilities.invokeLater(() ->
                    displayArea.append("[!] Suara gagal: " + ex.getMessage() + "\n"));
            }
        }).start();
    }

    // ============================================================
    //  ENTRY POINT
    // ============================================================
    public static void main(String[] args) {
        // invokeLater = cara benar menjalankan Swing di EDT (Event Dispatch Thread)
        SwingUtilities.invokeLater(queue_gui::new);
    }


    // ============================================================
    //  NODE
    // ============================================================
    static class Node {
        String nama;
        int    nomor;
        Node   next;

        Node(String nama) {
            this.nama = nama;
            this.next = null;
        }
    }

    // ============================================================
    //  QUEUE (Linked List)
    // ============================================================
    static class Queue {
        private Node front, back;
        private int  count = 0;

        boolean isEmpty() {
            return front == null;
        }

        // Nomor terakhir yang di-enqueue (untuk log GUI)
        int getLastNumber() {
            return back != null ? back.nomor : 0;
        }

        int getFrontNumber() {
            return front != null ? front.nomor : -1;
        }

        String getFrontName() {
            return front != null ? front.nama : "";
        }

        void enqueue(String nama) {
            Node node = new Node(nama);
            count++;
            node.nomor = count;
            if (isEmpty()) {
                front = back = node;
            } else {
                back.next = node;
                back = node;
            }
        }

        void dequeue() {
            if (isEmpty()) return;
            front = front.next;
            if (front == null) back = null;
        }

        // Tulis semua isi antrian ke JTextArea
        void showAll(JTextArea area) {
            Node temp = front;
            while (temp != null) {
                area.append("  No. " + temp.nomor + " → " + temp.nama + "\n");
                temp = temp.next;
            }
        }
    }
}