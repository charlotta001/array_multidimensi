import java.util.Scanner;

// 1. Menggunakan bahasa Java dan Python
// 2. Menggunakan tampilan grafis (GUI)
// 3. Fitur ambil antrian, akan meminta input nama, adapun nomor akan digenerate secara otomatis (1,2,3,...dst)
// 4. Fitur tampilkan data yg ada dalam antrian (nomor dan nama)
// 5. Fitur panggil antrian (menampilkan dan memanggil nomor & nama), usahakan berupa panggilan suara
// 6. Boleh menggunakan fix-sized array atau linked list




public class queue_console {

    Scanner input = new Scanner(System.in);

    public static void main(String[] args){
        Queue kue = new Queue();
        kue.enqueue("jayadi");
        kue.callQueue();
        kue.Show();
        kue.enqueue("rahman");
        kue.callQueue();
        kue.Show();       
        
        // kue.dequeue();
        // kue.callQueue();
        // kue.Show();
    }

    static class Node{
        String nama;
        Node next;
        int nomor = 0;

        Node(String nama){
            this.nama = nama;
            this.next = null;
        }
    };

    static class Queue{
        private Node front;
        private Node back;
        private int count;


        Queue(){
            front = back = null;
            count = 0;
        }

        // check and ensure this queue is empty
        boolean isEmpty(){
            return front == null;
        }

       


        // add new data to the front
        void enqueue(String nama){
            Node new_node = new Node(nama);
            if(isEmpty()){
                front = back = new_node;
                new_node.nomor =  1;
                System.out.println("nomor: " + new_node.nomor);
                System.out.println("nama: "+ nama + " added at index- "+ new_node.nomor);
            } else { 
                back.next = new_node;
                back = new_node;
                int number  = count += 1;
                back.nomor = number;
                System.out.println("nomor: " + back.nomor);
                System.out.println("nama: "+ nama + " added at index- "+ number );
            }
        }

        String dequeue(){
            if(isEmpty()){
                System.out.println("queue is underflow");
                return "-1";
            }

            Node temp = front;
            String removedNama = front.nama;
            front = front.next;
            if(front == null){
                back = null;
            }
            temp = null;
            System.out.println("nama: "+ removedNama + " removed at index- "+ count);
            count--;
            return removedNama;
        }

        String getFront(){
            if(isEmpty()){
                System.out.print("queue is empty");
                return "-1";
            }

            return front.nama;
        }

        void callQueue(){
            if(isEmpty()){
                System.out.println("queue is empty");
                return;
            }

            System.out.println("nomor antrian: " + front.nomor);
        }

        void Show(){
            Node temp = front;
            for(int i=1; i < count + 1; i++){
                System.out.println(i+".) "+ temp.nama);
                temp = temp.next;
            }
        }
    
    };
    
}
