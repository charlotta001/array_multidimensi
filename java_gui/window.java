import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

// dokumentasi
// https://docs.oracle.com/javase/8/docs/api/javax/swing/JComponent.html

class Node {
    Node next;
    String data;
    int nomor;

    Node(String new_data, int new_nomor) {
        this.data = new_data;
        this.nomor = new_nomor;
        this.next = null;
    }
}

class myQueue {
    private Node front;
    private Node back;
    private int nomor;
    private int currSize;

    public myQueue() {
        nomor = currSize = 0;
        front = back = null;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void Enqueue(String new_data) {
        this.nomor++;
        Node new_node = new Node(new_data, nomor);
        if (isEmpty()) {
            front = back = new_node;
        } else {
            back.next = new_node;
            back = new_node;
        }
        this.currSize++;
    }

    public void Dequeue() {
        if (isEmpty()) return;
        front = front.next;
        if (front == null) back = null;
        this.currSize--;
    }

    public String GetFront() {
        if (front == null) return "antrian kosong";
        return front.nomor + "-" + front.data;
    }

    public int size() {
        if (front == null) return 0;
        return this.currSize;
    }
}

public class window {
    public static void main(String[] args) {
        WindowJava obj = new WindowJava();
        obj.InitWindow();
    }

    public static class WindowJava extends JFrame {

        JPanel panel, panelAtas, panelBawah, wrapperAtas;
        JButton buttonSubmit, buttonPanggil, buttonShow;
        JTextField text;
        JTable table;
        JLabel label;
        JDialog popup;
        JLabel popupNomor, popupNama; // [1] field untuk label popup
        DefaultTableModel tableModel;
        ArrayList<String> textArray = new ArrayList<>();

        public void InitWindow() {
            setTitle("window java gui");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(800, 600);
            setLocationRelativeTo(null);
            InitPanel();
            TakeInput();
            arrayToTable();
            setVisible(true);
        }

        public void InitPanel() {
            panel = new JPanel();
            panel.setLayout(new GridLayout(2, 1, 10, 40));
            panel.setBorder(BorderFactory.createTitledBorder("Container menggunakan JPanel"));

            // [2] setup popup window
            // JDialog (Jpanel(jlabel,box))
            popup = new JDialog();
            popup.setTitle("Panggil Antrian");
            popup.setSize(500, 250);       // ukuran bisa kamu ganti bebas
            popup.setModal(false);         // tidak blokir main window
            popup.setLocationRelativeTo(null); // muncul di tengah layar

            // [3] isi konten popup
            JPanel popupPanel = new JPanel();
            popupPanel.setLayout(new BoxLayout(popupPanel, BoxLayout.Y_AXIS)); // component di dalam panel disusun seperti sumbu Y (dari atas ke bawah)
            popupPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            popupNomor = new JLabel("-");
            popupNomor.setFont(new Font("Arial", Font.BOLD, 60));
            popupNomor.setAlignmentX(Component.CENTER_ALIGNMENT);

            popupNama = new JLabel("-");
            popupNama.setFont(new Font("Arial", Font.PLAIN, 30));
            popupNama.setAlignmentX(Component.CENTER_ALIGNMENT);

            popupPanel.add(Box.createVerticalGlue()); // invisible flexible component inside jpanel
            popupPanel.add(popupNomor);
            popupPanel.add(Box.createVerticalStrut(10)); // jarak antar label
            popupPanel.add(popupNama);
            popupPanel.add(Box.createVerticalGlue());

            popup.add(popupPanel); // [4] add panel ke dialog, BUKAN ke panelAtas

            // setup panel utama
            wrapperAtas = new JPanel();
            wrapperAtas.setLayout(new GridBagLayout());

            panelAtas = new JPanel();
            panelAtas.setLayout(new FlowLayout());

            panelBawah = new JPanel(); // [5] fix: ini yang diubah layoutnya, bukan panelAtas lagi
            panelBawah.setLayout(new BorderLayout());

            label = new JLabel("Antrian : ");

            buttonSubmit = new JButton("submit");
            buttonPanggil = new JButton("panggil");
            buttonShow = new JButton("show");

            text = new JTextField(10);

            String[] columns = {"No", "Teks"};
            tableModel = new DefaultTableModel(columns, 0);
            table = new JTable(tableModel);

            panelAtas.add(label);
            panelAtas.add(text);
            panelAtas.add(buttonSubmit);
            panelAtas.add(buttonPanggil);
            panelAtas.add(buttonShow);

            wrapperAtas.add(panelAtas);

            panelBawah.add(new JScrollPane(table), BorderLayout.CENTER);

            panel.add(wrapperAtas);
            panel.add(panelBawah);
            add(panel);
        }

        public void InitSuara(String input) {
            Thread thread = new Thread(() -> {
                try {
                    String script = String.format(
                        "Add-Type -AssemblyName System.Speech; " +
                        "(New-Object System.Speech.Synthesis.SpeechSynthesizer).Speak('%s');",
                        input.replace("'", "")
                    );
                    ProcessBuilder pb = new ProcessBuilder("powershell", "-Command", script);
                    pb.start().waitFor();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }

        public void TakeInput() {
            myQueue queue = new myQueue();

            buttonSubmit.addActionListener(e -> {
                String inputTextField = text.getText().trim();
                if (!inputTextField.isEmpty()) { // [6] fix: kurung kurawal yang benar
                    textArray.add(inputTextField);
                    arrayToTable();
                    text.setText("");
                    text.requestFocus();
                    queue.Enqueue(inputTextField);
                }
            });

            buttonPanggil.addActionListener(e -> {
                String temp = queue.GetFront();

                if (!temp.equals("antrian kosong")) {
                    // [7] pisah "1-Budi" jadi nomor dan nama
                    String[] parts = temp.split("-", 2);
                    popupNomor.setText("Nomor: " + parts[0]);
                    popupNama.setText("Nama: " + parts[1]);

                    popup.setLocationRelativeTo(null);
                    popup.setVisible(true); // tampilkan popup
                } else {
                    // kalau antrian kosong, tutup popup kalau masih terbuka
                    popup.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Antrian kosong!");
                }

                InitSuara(temp);
                queue.Dequeue();
            });

            buttonShow.addActionListener(e -> {
                String data = "antrian terdepan: " + queue.GetFront();
                String antrian = "panjang antrian: " + queue.size();
                JOptionPane.showMessageDialog(null, data, antrian, JOptionPane.INFORMATION_MESSAGE);
            });
        }

        public void arrayToTable() {
            tableModel.setRowCount(0);
            for (int i = 0; i < textArray.size(); i++) {
                tableModel.addRow(new Object[]{i + 1, textArray.get(i)});
            }
        }
    }
}