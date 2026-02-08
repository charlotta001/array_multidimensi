import  java.util.Scanner;

public class kuliah {

    // main program
    public static void main(String[] args){

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
        System.out.print("9 to exit, your choice : ");
          
            if(input.hasNextInt())
            {
            choice = input.nextInt();
            input.nextLine();
            switch(choice)
            {
            case 1: 
                String name,nim;
                System.out.println("Input name of member: ");
                name = input.nextLine();
                System.out.println("Input nim of member: ");
                nim = input.nextLine();

                arr = InsertAtBeggining(arr,name, nim);
                //ShowData(arr);
                break;
            case 2:
                arr = InsertGivenPosition(arr, input);
                //ShowData(arr);
                break;
            case 3:
                arr = InsertAtEnd(arr, input);
               // ShowData(arr);
                break;
            case 4:
                arr = DeleteFromBeggining(arr);
                break;
            case 5:
                arr = DeleteGivenPosition(arr, input);
                //ShowData(arr);
                break;
            case 6: 
                arr = DeleteFromEnd(arr);
                break;
            case 7:
                arr = DeleteFirstOccurence(arr, input);
                break;
            case 8:
                ShowData(arr);
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



    // insert data at beggining
    // param (array, nama, nim)
    static String[][] InsertAtBeggining(String[][] arr, String nama, String nim)
    {
        int row = arr.length + 1;
        String[][] newArr = new String[row][2];
        //int n = newArr.length -1;


        for(int i = 0; i < arr.length; i++){
            newArr[i+1][0] = arr[i][0];
            newArr[i+1][1] = arr[i][1];
        }
        newArr[0][0] = nama;
        newArr[0][1] = nim;
        return newArr;
    }

    static void ShowData(String[][] arr)
    {
        int coloumn = arr[0].length;
        int rowPrevious = arr.length -1;
        
        System.out.println("row previous: " + rowPrevious);
        System.out.println("row: " + arr.length);
        System.out.println("coloumn: " + coloumn);

        for(int i = 0; i < arr.length; i++){
            System.out.println(i + 1 +" : "+ arr[i][0] + "||" + arr[i][1]);
        }
    }

    // insert given position
    static String[][] InsertGivenPosition(String[][] arr, Scanner input){

      //  int position = 0;
        int lengthArr = arr.length;
        String name,nim;
        int position = 0;
        System.out.println("array length is: "+ lengthArr);
        

        
        System.out.print("choose the position: ");
        if (input.hasNextInt()){
        position = input.nextInt();
        //input.nextLine();
    
        if(position <= arr.length){
        
        input.nextLine();
        System.out.print("input name: ");
        name = input.nextLine();

        System.out.print("input nim: ");
        nim = input.nextLine();
        
        arr[position - 1][0] = name;
        arr[position - 1][1] = nim;
        } else{
            System.out.println("range out of the bound");
            input.nextLine();
        }
        } else {
            System.out.println("position must be integer");
            input.nextLine();
        }
    
        return arr;
    }

    static String[][] InsertAtEnd(String[][] arr, Scanner input){
        int lengthArr = arr.length + 1;

       
        String[][] newArr = new String[lengthArr][2];

        for(int i = 0; i < arr.length; i++){
            newArr[i][0] = arr[i][0];
            newArr[i][1] = arr[i][1];
        }
        
        System.out.print("input name: ");
        String name = input.nextLine();
        System.out.print("input nim: ");
        String nim = input.nextLine();


        newArr[newArr.length - 1][0] = name;
        newArr[newArr.length - 1][1] = nim;

        return newArr;
    }

    static String[][] DeleteFromBeggining(String[][] arr){

        int n = arr.length;
        String[][] newArr = new String[n-1][];


        for(int i = 1; i < n; i++){
            newArr[i -1] = arr[i];
        }
        
        return newArr;
    }

    static String[][] DeleteGivenPosition(String[][] arr, Scanner input){

        System.out.print("input position: ");

        int n = arr.length -1;
        String[][] newArr = new String[arr.length][2];

        if(input.hasNextInt()){
        int positionIndex = input.nextInt();
        //input.nextLine();

        if(positionIndex <= arr.length){
            newArr = new String[n][2];

            for(int i =0; i < newArr.length; i++)
                {
                    newArr[i][0] = arr[i][0];
                    newArr[i][1] = arr[i][1];

          
                    if( i == (positionIndex - 1))
                    {
                        newArr[i][0] = arr[i+1][0];
                        newArr[i][1] = arr[i+1][1];
                    } 
                }
        } else{
            System.out.println("range out of the bound");
            input.nextLine();
        }
        } else {
            System.out.println("only accept int");
            input.nextLine();
        }

        
        return newArr;
    }



    static String[][] DeleteFromEnd(String[][] arr){
    
        String[][] newArr = new String[arr.length - 1][2];
        
        
        for(int i=0; i < newArr.length; i++){
            newArr[i][0] =  arr[i][0];
            newArr[i][1] =  arr[i][1];
        }

        return newArr;
    }

    static String[][] DeleteFirstOccurence(String[][] arr, Scanner input){


        int positionToDelete = -1;
        boolean found = false;
        System.out.print("input nim will be delete: ");
        String nim = input.nextLine();

        String nimReplace = nim.replaceAll("\\s","");
        

        for(int i = 0; i < arr.length; i++){
           
            if(arr[i][1] == null){
                continue;
            }
            
            String replace = arr[i][1].replaceAll("\\s","");

            if(replace.equalsIgnoreCase(nimReplace)) {
                positionToDelete = i;
                found = true;
                break;
            }
        }
            int nextArr =0;
            String[][] newArr = new String[arr.length -1 ][2];

        if(found){
           
            for(int i=0; i < arr.length; i ++){

            if(i == positionToDelete){
                continue;
            }
            
            newArr[nextArr][0] = arr[i][0];
            newArr[nextArr][1] = arr[i][1];
            
            nextArr++;
            }
            return newArr;
        }

        System.out.println("nim not found");
        return arr;
    }
}
    



