import java.util.Scanner;


// ===============================================================================================================================
// 1. Insert berita (berita baru selalu ditambahkan di akhir)
// 2. Hapus berita (berdasarkan nomor urut)
// 3. Tampilkan berita secara forward (teks berita akan tampil dari depan ke belakang dengan delay 3 detik antar berita)
// 4. Tampilkan berita secara backward (teks berita akan tampil dari belakang ke depan dengan delay 3 detik antar berita)
// 5. Tampil berita tertentu (berdasarkan nomor urut)
// 6. Exit
// ===============================================================================================================================


// dokumentasi function that i used
//https://www.geeksforgeeks.org/java/thread-sleep-method-in-java-with-examples/
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
                case 2:
                    break;
                case 3:
                    try{
                    ShowDataForward();
                    } catch(InterruptedException e){
                        System.out.print(e);
                    }
                    break;
                case 4:
                    try{
                        ShowDataBackward();
                    }catch(InterruptedException e){
                        System.out.print(e);
                    }
                    break;
                case 5:
                    ShowDataBasedInOrder();
                    break;
                case 6:
                    System.out.println("Exit...");
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

    static void ShowDataBackward()throws InterruptedException{
        Node temp = tail;
        if(head != null)
            for(int i = count ; i > 0; i--){
                System.out.println(i + ".) "+ temp.berita);
                temp = temp.prev;
                Thread.sleep(3000);
        }
    }

    static void ShowDataForward()throws InterruptedException{
    
        Node temp = head;
        if(head != null){
            for(int i =1; i < count + 1; i++){
                System.out.println(i + ".) " + temp.berita);
                temp =  temp.next;
                Thread.sleep(3000);
            }
        }
    }

    static void ShowDataBasedInOrder(){
        System.out.println("Amount data currently:"+ count);

 
      
        if(head == null){
            System.out.println("data is empty");
            return;
        }

        System.out.print("input order that u want among "+ 1 + " - " + count + " : ");
        
        
        int order = input.nextInt();
        input.nextLine();
        if(order < 1 || order > count){
            System.out.println("order more or less than sequence");
            return;
        }

        Node temp = head;
        for(int i = 1; i < order; i++){
            temp = temp.next;
        }

        System.out.println("Berita in order -" + order + " : " + temp.berita);


   

    }
}