import java.util.Scanner;

public class singly_linked_list {

    //static variable (karena function pakai static)
    static int count = 0;
    static Scanner input = new Scanner(System.in);
    static Node head;


    // running program
    public static void main(String[] args) {
    
        Init();
    }
    // class NODE
    static class Node{
        
        int nim;
        String name;
        Node next;

        Node(String name, int nim){
            this.name = name;
            this.nim =  nim;
            this.next =  null;
        }

        // agar ketika print out object di conver ke string , bukan (Node  @12345)
        @Override
        public String toString(){
        return name  + "||" + nim;
    }
    }

    

    // init program
    static void Init(){
        
        int choice = 0;


        System.out.println("choose 1 - 8 for operation and 9 for close program");
        System.out.println("1. insert at beggining");
        System.out.println("2. insert given position");
        System.out.println("3. insert at end");
        System.out.println("4. delete from beggining");
        System.out.println("5. delete given position");
        System.out.println("6. delete frome end");
        System.out.println("7. delete first occurence");
        System.out.println("8. show data");
        System.out.println("9. exit");


        do{
            System.out.print("input only accept 1 - 9: ");

            if(input.hasNextInt()){

            choice = input.nextInt();
            input.nextLine();

            switch(choice){
                case 1:
                    InsertAtBeggining();
                    break;
                case 2:
                    InsertGivenPosition();
                    break;
                case 3:
                    InsertAtEnd();
                    break;
                case 4:
                    DeleteFromBeggining();
                    break;
                case 5:
                    DeleteGivenPosition();
                    break;
                case 6:
                    DeleteFromEnd();
                    break;
                case 7:
                    DeleteFirstOccurence();
                    break;
                case 8:
                    ShowData();
                    break;
                case 9:
                    break;
                default:
                    System.out.println("input only accept 1 - 9!");
            }
        } else {
            System.out.println("can't input string, input again! ");
            input.nextLine();
        }
        
        }while(choice != 9);

        input.close();
    }

    //insert at beggining
    static void InsertAtBeggining (){


            String name;
            int nim;
            
            System.out.print("input name: ");
            name = input.nextLine();

            System.out.print("input nim: ");

            if(input.hasNextInt()){
            nim =  input.nextInt();
            input.nextLine();
            } else {
                System.out.println("nim have to be int");
                input.nextLine();
                return;
            }

            Node newNode = new Node(name, nim);
            newNode.next = head;
            head =  newNode;
            count++;
    }
    

    //show data
    static void ShowData(){
        int i = 1;
        Node temp = head;
        System.out.println(" ");
        System.out.println("count = "+count);
        System.out.print("=========");

        while(temp != null){
            System.out.println( i +". "+ temp.name + " || " + temp.nim);
            temp =  temp.next;
            i++;
        }

        // if(head != null){
        // System.out.println("[][][][][]" + head.name);
        // }
        // System.out.println("head is: "+head);
    }

static void InsertGivenPosition(){
    // Cek apakah linked list kosong
    if(head == null){
        System.out.println("Data is empty");
        return;
    }
    
    // total data di linked list
    Node curr = head;
    int total = 0;
    while(curr != null){
        total++;
        curr = curr.next;
    }
    
    // Tampilkan informasi jumlah data
    System.out.println("Amount data currently: " + total);
    System.out.println("Data from 1- " + (total + 1));
    
    // Input posisi
    int pos = 0;
    System.out.print("Input posisi: ");
    

    // cek input harus int
    if(input.hasNextInt()){
        pos = input.nextInt();
        input.nextLine();
    } else {
        input.nextLine();
        System.out.println("Input only accept number");
        return;
    }
    
    // Validasi posisi
    if(pos < 1 || pos > total + 1){
        System.out.println("Position just accept  1 - " + (total + 1));
        return;
    }
    
    // Input data
    int nim;
    String name;
    System.out.print("Input nama: ");
    name = input.nextLine();
    System.out.print("Input nim: ");
    if(input.hasNextInt()){
    nim = input.nextInt();
    input.nextLine();
    } else {
        System.out.println("input only accept number");
        return;
    }
    // Insert di posisi 1 (awal)
    if(pos == 1){
        Node newNode = new Node(name, nim);
        newNode.next = head;
        head = newNode;
        System.out.println("Data is complete added to 1");
        return;
    }
    
    // Insert di posisi tengah atau akhir
    curr = head;
    for(int i = 1; i < pos - 1 && curr != null; i++){
        curr = curr.next;
    }
    
    Node newNode = new Node(name, nim);
    newNode.next = curr.next;
    curr.next = newNode;
    count++;
    
    System.out.println("Data is complete added at " + pos);
}

    //Insert at end
static void InsertAtEnd(){

    if(head == null){
        System.out.println("head is null");
        return;
    }
    System.out.print("input name: ");
    String name = input.nextLine();
    System.out.print("input nim: ");
    int nim;
    if(input.hasNextInt()){
    nim = input.nextInt();
    input.nextLine();
    } else {
        input.nextLine();
        System.out.println("nim only accept int");
        return;
    
    }

    Node newNode = new Node(name, nim);

    Node last = head;
    while(last.next != null){
        last = last.next;
    }

    last.next = newNode;
    count++;

}
    // Delete from Beggining
    static void DeleteFromBeggining(){

        
        Node curr = head;

        if(curr == null){
            System.out.println("head is null");
            return;
        }
        head = curr.next;
        curr = null;
        count--;
    }


    // Delete From end
    static void DeleteFromEnd(){
        Node curr = head;

        if(curr == null){
            System.out.println("head is null");
            return;
        }

        if(head.next == null){
            head = null;
            count--;
            return;
        }

         while(curr.next.next != null){
            curr = curr.next;
         }
        curr.next = null;
        count--;
    }

    // Delete Given Position
    static void DeleteGivenPosition(){
        
        int pos = 0;

        System.out.print("input pos: ");
        pos = input.nextInt();

        if(pos < 1 || pos > count){
            System.out.println("pos kurang dari 1 dan lebih dari count");
            return;
        }

        if(pos == 1){
            DeleteFromBeggining();
            return;
        }

        if(pos == count){
            DeleteFromEnd();
            return;
        }
        Node curr = head;
        for(int i = 0; i < pos - 2; i++){
            curr = curr.next;
        }
        curr.next = curr.next.next;
        count--;
    }

    // Delete First Occurence
    static void DeleteFirstOccurence() {
    int nim = 0;
    int index = -1;

    
    Node prev = null;
    Node curr = head;

    System.out.print("Input nim: ");


    // finding nim inputed
    if (input.hasNextInt()) {
        nim = input.nextInt();
        input.nextLine();

        for (int i = 0; i < count; i++) {
            if (curr.nim == nim) {
                index = i;
                break;
            }
            prev = curr;       
            curr = curr.next;  
        }

    } else {
        System.out.println("Nim only accepts int");
        input.nextLine();
        return;
    }

    // handle hasil dari index
    if (index == -1) {
        System.out.println("Nim not found");
    } else if (index == 0) {
        DeleteFromBeggining();
    } else if (index == count - 1) {
        DeleteFromEnd();
    } else {
        prev.next = curr.next; 
        count--;
    }
}
}