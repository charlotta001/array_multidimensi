import java.util.Scanner;


// ===============================================================================================================================
// 1. Insert berita (berita baru selalu ditambahkan di akhir)
// 2. Hapus berita (berdasarkan nomor urut)
// 3. Tampilkan berita secara forward (teks berita akan tampil dari depan ke belakang dengan delay 3 detik antar berita)
// 4. Tampilkan berita secara backward (teks berita akan tampil dari belakang ke depan dengan delay 3 detik antar berita)
// 4. Tampil berita tertentu (berdasarkan nomor urut)
// 5. Exit
// ===============================================================================================================================

class Node{
    String berita;
    Node prev, next;

    public Node(String berita_construct){
        this.berita = berita_construct;
        this.prev = this.next = null;
    }
};

public class circular_doubly_linked_list {
    
    static int count = 0;
    static int choice = 0;
    static Node head;
    static Node tail;
    static Scanner input = new Scanner(System.in); 

    public static void main(String[] args){
        Init();
    }

    static void Init(){


        System.out.println("1.) Insert berita (akan ditambahkan di index terakhir)");
        System.out.println("2.) Hapus berita(sesuai nomor urut)");
        System.out.println("3.) Tampilkan berita secara forward (teks berita akan tampil dari depan ke belakang dengan delay 3 detik antar berita)");
        System.out.println("4.) Tampilkan berita secara backward (teks berita akan tampil dari belakang ke depan dengan delay 3 detik antar berita)");
        System.out.println("5.) Tampil berita tertentu (berdasarkan nomor urut)");
        System.out.println("6.) Exit");


        do{
        System.out.print("input your choice: ");
        
        if(input.hasNextInt()){
            choice = input.nextInt();
            input.nextLine();


            switch(choice){
                case 1:
                    InsertAtEnd();
                    break;
                case 5:
                    try{
                    ShowData();
                    } catch(InterruptedException e){
                        System.out.print(e);
                    }
                    break;
                default:
                    System.out.println("choice just accept 1 - 6!");
                    
            }
        } else {
            System.out.println("input choice just accept number");
            input.nextLine();
        }
    }while(choice != 6);
}



    static void InsertAtEnd(){

        System.out.print("input berita : ");
        String add_berita = input.nextLine();

        
        Node newNode = new Node(add_berita);


        // head = first list
        // tail = position for last newNode (position of tail  = newNode)
        // example : tail at 1 -> head at 1 || tail at 2 -> head at 1 (so if we add newNode, the tail will occupy the newNode that created )
        
        if(head == null){
           tail = head = newNode;
           head.next = head;
           head.prev = head;
           

        }else {
            tail.next =  newNode;
            newNode.prev = tail;
            newNode.next = head;
            head.prev = newNode;
            tail = newNode;
            
        }
        count++;
    }

    static void ShowData()throws InterruptedException{
    
        Node temp = head;
        if(head != null){
            for(int i =1; i < count + 1; i++){
                System.out.println(i + ".) " + temp.berita);
                temp =  temp.next;
                Thread.sleep(3000);
            }
        }
    }
}