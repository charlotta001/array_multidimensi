import java.util.Scanner;

public class array_fixed {

    static int count = 0;
    static final int MAX = 10;

    public static void main(String[] args){
        Init();
    }

    static void Init(){
        Scanner input = new Scanner(System.in);
        String[][] arr = new String[MAX][2];

        System.out.println("be wise when using sawit program");
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

        int choice = 0;
        do{
            System.out.print("9 to exit, your choice : ");
            
            if(input.hasNextInt())
            {
                choice = input.nextInt();
                input.nextLine();
                
                switch(choice)
                {
                    case 1: 
                        InsertAtBeggining(arr, input);
                        ShowData(arr);
                        break;
                    case 2:
                        InsertGivenPosition(arr, input);
                        ShowData(arr);
                        break;
                    case 3:
                        InsertAtEnd(arr, input);
                        ShowData(arr);
                        break;
                    case 4:
                        DeleteFromBeggining(arr);
                        ShowData(arr);
                        break;
                    case 5:
                        DeleteGivenPosition(arr, input);
                        ShowData(arr);
                        break;
                    case 6: 
                        DeleteFromEnd(arr);
                        ShowData(arr);
                        break;
                    case 7:
                        DeleteFirstOccurence(arr, input);
                        ShowData(arr);
                        break;
                    case 8:
                        ShowData(arr);
                        break;
                    case 9:
                        break;
                    default:
                        System.out.println("input only accept 1-9 ");
                }
            } else {
                System.out.println("can't input string! only accept number");
                input.next();
            }
        } while(choice != 9);

        System.out.println("thank you for using sawit program");
        input.close();
    }

    static void InsertAtBeggining(String[][] arr, Scanner input)
    {
        for (int i = MAX - 1; i > 0; i--) {
            arr[i][0] = arr[i - 1][0];
            arr[i][1] = arr[i - 1][1];
        }

        System.out.print("input name: ");
        arr[0][0] = input.nextLine();
        System.out.print("input nim: ");
        arr[0][1] = input.nextLine();

        if (count < MAX) {
            count++;
        }
    }

    static void InsertGivenPosition(String[][] arr, Scanner input){
        if (count >= MAX) {
            System.out.println("=== Array is full ===");
            //return;
        }

        System.out.print("choose the position (1-10): ");
        if (input.hasNextInt()){
            int position = input.nextInt();
            input.nextLine();

            if(position >= 1 && position <= MAX){
                int index = position - 1;

            if(arr[index][0] != null){
                System.out.print("array sudah ada yang mengisi, Y/N untuk Overwrite: ");
                char force = input.next().charAt(0);
                
                switch(Character.toUpperCase(force)){
                    case 'Y':
                        input.nextLine();
                        System.out.print("input name: ");
                        arr[index][0] = input.nextLine();
                        System.out.print("input nim: ");
                        arr[index][1] = input.nextLine();
                        break;
                    case 'N':
                        input.nextLine();
                        System.out.println("array not overwrite");
                        break;
                    default:
                        System.out.println("only accept Y/N");
                }
            } else {
                System.out.print("input name: ");
                arr[index][0] = input.nextLine();
                System.out.print("input nim: ");
                arr[index][1] = input.nextLine();

            }
            if(count < MAX){
                count++;
            }
                
            } else {
                System.out.println("range out of the bound");
            }
        } else {
            System.out.println("position must be integer");
        }
    }

    static void InsertAtEnd(String[][] arr, Scanner input){
        if (count >= MAX) {
            System.out.println("Array is full");
            return;
        }

        System.out.print("input name: ");
        String name = input.nextLine();
        System.out.print("input nim: ");
        String nim = input.nextLine();

        arr[count][0] = name;
        arr[count][1] = nim;

        count++;
    }

    static void DeleteFromBeggining(String[][] arr){
        if (count == 0) {
            System.out.println("Array is empty");
            return;
        }

        for (int i = 0; i < count - 1; i++) {
            arr[i][0] = arr[i + 1][0];
            arr[i][1] = arr[i + 1][1];
        }

        arr[count - 1][0] = null;
        arr[count - 1][1] = null;
        count--;
    }

    static void DeleteGivenPosition(String[][] arr, Scanner input){
        if (count == 0) {
            System.out.println("Array is empty");
            return;
        }

        System.out.print("input position: ");
        if(input.hasNextInt()){
            int position = input.nextInt();
            input.nextLine();

            if(position >= 1 && position <= MAX){
                int index = position - 1;
                
                if (arr[index][0] == null) {
                    System.out.println("Position is already empty");
                    return;
                }

                // for (int i = index; i < count - 1; i++) {
                //     arr[i][0] = arr[i + 1][0];
                //     arr[i][1] = arr[i + 1][1];
                // }

                arr[position - 1][0] = null;
                arr[position -1 ][1] = null;
                count--;

            } else {
                System.out.println("range out of the bound");
            }
        } else {
            System.out.println("only accept int");
            input.nextLine();
        }
    }

    static void DeleteFromEnd(String[][] arr){
        if (count == 0) {
            System.out.println("Array is empty");
            return;
        }

        arr[count - 1][0] = null;
        arr[count - 1][1] = null;
        count--;
    }

    static void DeleteFirstOccurence(String[][] arr, Scanner input){
        if (count == 0) {
            System.out.println("Array is empty");
            return;
        }

        System.out.print("input nim will be delete: ");
        String nim = input.nextLine();
        String nimReplace = nim.replaceAll("\\s","");
        
        int positionToDelete = -1;
        boolean found = false;

        for(int i = 0; i < count; i++){
            if(arr[i][1] != null){
                String replace = arr[i][1].replaceAll("\\s","");
                if(replace.equalsIgnoreCase(nimReplace)) {
                    positionToDelete = i;
                    found = true;
                    break;
                }
            }
        }

        if(found){
            for (int i = positionToDelete; i < count - 1; i++) {
                arr[i][0] = arr[i + 1][0];
                arr[i][1] = arr[i + 1][1];
            }
            arr[count - 1][0] = null;
            arr[count - 1][1] = null;
            count--;
        } else {
            System.out.println("nim not found");
        }
    }

    static void ShowData(String[][] arr)
    {   
        
        System.out.println("Length array / total data: " + count);
        for(int i = 0; i < arr.length; i++){
            if (arr[i][0] != null) {
                System.out.println((i + 1) +" : "+ arr[i][0] + " || " + arr[i][1]);
            } else {
                System.out.println((i + 1) +" : [Empty]");
            }
        }
    }
}