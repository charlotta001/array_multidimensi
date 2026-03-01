import java.util.Scanner;
// documentation 
// thread               : https://docs.oracle.com/javase/tutorial/essential/concurrency/runthread.html
// thread is used       : https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html
// thread constructor   : https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html#Thread-java.lang.Runnable-
// interface runnable   : https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html
// compare string       : https://docs.oracle.com/javase/8/docs/api/java/lang/String.html



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
                    DeleteGivenPosition();
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
    input.close();
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
      
        if(head  == null){
            System.out.println("Linked list is empty");
            return;
        }
    
        int choice = 0;
        int pos = count;
        Node temp = tail;
        System.out.println("1.) ShowDataBackward using for loop");
        System.out.println("2.) ShowDataBackward using while");
        System.out.print("input here : ");

        if(input.hasNextInt()){
        choice = input.nextInt();
        input.nextLine();
        switch(choice){
            case 1:
            if(head != null){
              for(int i = count ; i > 0; i--){
                System.out.println(i + ".) "+ temp.berita);
                temp = temp.prev;
                Thread.sleep(3000);
            }  
        } break;
          case 2:
            // array in java called obj, and obj shared memory with thread instead using "boolean"
            boolean running[] = {false};
            // () -> {} is lambda expression in java (ex java 8)
            Thread newThread = new Thread(()-> {
                String stopLoop = input.nextLine();
                // flag stop
                if(stopLoop.equalsIgnoreCase("c")) {
                    running[0] = true;
                    return;
                }
            });
          
            // start Thread
            newThread.start();

            while(temp != null && !running[0]){
                System.out.println(pos + ".) " + temp.berita);
                temp =  temp.prev;
                Thread.sleep(3000);
                pos--;
                if(pos < 1){
                    pos = count;
                }
            }break;
            default:
                System.out.println("input just accept 1-2");
                break;
        
    } 
        pos = count;
    }else{
        System.out.println("choice just accept number ");
    }
    }

    //when thread.sleep()active, this program will sleep and doing nothing
    //thread 
    //flag variable = on/off
    static void ShowDataForward()throws InterruptedException{
    
        if(head  == null){
            System.out.println("Linked list is empty");
            return;
        }
    
        int choice = 0;
        int pos = 1;
        Node temp = head;
        System.out.println("1.) ShowDataForward using for loop");
        System.out.println("2.) ShowDataForward using while");
        System.out.print("input here : ");

        if(input.hasNextInt()){
        choice = input.nextInt();
        input.nextLine();
        switch(choice){
            case 1:
            if(head != null){
                for(int i =1; i < count + 1; i++){
                System.out.println(i + ".) " + temp.berita);
                temp =  temp.next;
                Thread.sleep(3000);
            }
        } break;
          case 2:
            // array in java called obj, and obj shared memory with thread instead using "boolean"
            boolean running[] = {false};
            // () -> {} is lambda expression in java (ex java 8)
            Thread newThread = new Thread(()-> {
                String stopLoop = input.nextLine();
                // flag stop
                if(stopLoop.equalsIgnoreCase("c")) {
                    running[0] = true;
                    return;
                }
            });
          
            // start Thread
            newThread.start();

            while(temp != null && !running[0]){
                System.out.println(pos + ".) " + temp.berita);
                temp =  temp.next;
                Thread.sleep(3000);
                pos++;
                if(pos > count){
                    pos = 1;
                }
            }break;
        
    } 
        pos = 1;
    }else{
        System.out.println("choice just accept number ");
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

    static void DeleteGivenPosition(){
        Node temp = head;
        int pos = 0;


        if(head == null){
            System.out.println("this linked list empty");
            return;
        }

        System.out.println("position can be deleted 1 -"+ count);
        System.out.print("input position: ");

        if(input.hasNextInt() ){
            pos = input.nextInt();
            input.nextLine();

        if(pos < 1 || pos > count){
            System.out.println("pos more or less of list");
            return;
        }

        if(head.next == head){
            head = null;
        } else if(pos == 1){
            tail.next = head.next;
            head.next.prev = tail;
            head = head.next;
        } else{
            for(int i = 1; i < pos; i++){
                temp = temp.next;
            }
            if(temp == tail){
                tail = temp.prev;
            }
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }
     
        count--;
    } else {
        System.out.println("pos just accept a number");
    }
}
}