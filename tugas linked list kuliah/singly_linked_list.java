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
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
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

            System.out.println("|||||||||||" + head);
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
        while(temp != null){
            System.out.println(temp.name + " || " + temp.nim);
            temp =  temp.next;
        }

        if(head != null){
        System.out.println("[][][][][]" + head.name);
        }
        System.out.println("head is: "+head);
    }

    
}