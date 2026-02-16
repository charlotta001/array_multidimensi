import java.util.Scanner;

public class singly_linked_list {

    static int count = 0;
    static Scanner input = new Scanner(System.in);
    static Node head;





    
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
        // explain this
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
                    break;
                case 6:
                    DeleteFromEnd();
                    break;
                case 7:
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
    
    static void ShowData(){

        Node temp = head;
        System.out.println(" ");
        System.out.println("count = "+count);
        while(temp != null){
            System.out.println(temp.name + " || " + temp.nim);
            temp =  temp.next;
        }

        if(head != null){
        System.out.println("[][][][][]" + head.name);
        }
        System.out.println("head is: "+head);
    }

static void InsertGivenPosition(){
    // Cek apakah linked list kosong
    if(head == null){
        System.out.println("Tidak ada data di list");
        return;
    }
    
    // Hitung total node yang ada
    Node curr = head;
    int total = 0;
    while(curr != null){
        total++;
        curr = curr.next;
    }
    
    // Tampilkan informasi jumlah data
    System.out.println("Jumlah data saat ini: " + total);
    System.out.println("Anda dapat menambahkan data dari posisi 1 - " + (total + 1));
    
    // Input posisi
    int pos = 0;
    System.out.print("Input posisi: ");
    
    if(input.hasNextInt()){
        pos = input.nextInt();
        input.nextLine();
    } else {
        input.nextLine();
        System.out.println("Input hanya menerima angka");
        return;
    }
    
    // Validasi posisi
    if(pos < 1 || pos > total + 1){
        System.out.println("Posisi tidak valid! Masukkan posisi antara 1 - " + (total + 1));
        return;
    }
    
    // Input data

    int nim;
    String name;
    System.out.print("Input nama: ");
    name = input.nextLine();
    System.out.print("Input NIM: ");
    if(input.hasNextInt()){
    nim = input.nextInt();
    input.nextLine();
    } else {
        System.out.println("input hanya bisa angka");
        return;
    }
    // Insert di posisi 1 (awal)
    if(pos == 1){
        Node newNode = new Node(name, nim);
        newNode.next = head;
        head = newNode;
        System.out.println("Data berhasil ditambahkan di posisi 1");
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
    
    System.out.println("Data berhasil ditambahkan di posisi " + pos);
}

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


    // review ulang
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
}