import  java.util.Scanner;

public class kuliah {

    // main program
    public static void main(String[] args){
    
        // String[][] arr= InitFixedArrays();

        // arr = InsertAtBeggining(arr,"bambang", "12");

        // CheckArraySpec(arr);
        Init();
    
    }

    static void Init(){

        Scanner input = new Scanner(System.in);

        String[][] arr= InitFixedArrays();

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
        System.out.print("your choice : ");
          
            if(input.hasNextInt())
            {
            choice = input.nextInt();
            switch(choice)
            {
            case 1: 
                input.nextLine();
                String name,nim;
                System.out.println("Input name of member: ");
                name = input.nextLine();
                System.out.println("Input nim of member: ");
                nim = input.nextLine();

                arr = InsertAtBeggining(arr,name, nim);
                CheckArraySpec(arr);

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


    static int[][]  InitCustomArrays(int row, int coloumn)
    {
        int[][] arr = new int[row][coloumn];
        return arr;
    }

    static String[][]  InitFixedArrays()
    {
        String[][] arr = new String[10][2];
        return arr;
    }


    static String[][] InsertAtBeggining(String[][] arr, String nama, String nim)
    {
        int row = arr.length + 1;
        String[][] newArr = new String[row][2];
        //int n = newArr.length -1;


        for(int i = 0; i < arr.length; i++){
            newArr[i+1][0] = arr[i][0];
            newArr[i+1][1] = arr[i][1];
        }


        // for(int i =n-1 ; i >= 0 ; i--){
        //     newArr[i + 1] = newArr[i];
        // }

        newArr[0][0] = nama;
        newArr[0][1] = nim;

        return newArr;
    }

    static void CheckArraySpec(String[][] arr)
    {
        int coloumn = arr[0].length;
        int rowPrevious = arr.length -1;
        
        System.out.println("row previous: " + rowPrevious);
        System.out.println("row: " + arr.length);
        System.out.println("coloumn: " + coloumn);

        for(int i = 0; i < arr.length; i++){
            System.out.println(arr[i][0] + "||" + arr[i][1]);
        }
    }
    

}
    



