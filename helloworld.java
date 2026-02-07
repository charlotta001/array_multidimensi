import java.util.Scanner;


public class helloworld {
    static int[] StoreArray(int count){
        int[] arr = new int[count];
        return arr;
    }

    static void InputArray(int[] arr){
        Scanner scanner = new Scanner(System.in);

        for(int i=0; i < arr.length; i++){
            System.out.print("input here "+ i +" : ");
            arr[i] = scanner.nextInt();
        }
        scanner.close();
    }
    

    // di java apakah sama seperti cpp pakai & agar tidak duplikat value ?
    // lokasi target pada search target ini adalah lokasi pertama saat ditemukan
    static void SearchTarget(int[] arr,int target){
       boolean validasi = true;
       int index = 0;
        for (int i=0; i < arr.length; i++){
            if(arr[i] == target){
                validasi = false;
                index = i;
                break;
            }
        }
        if(!validasi){
            System.out.println("target : "+ target + "ditemukan di " + index);
        } else {
            System.out.println("target tidak ditemukan");
        }
    }

        //harus buat jatah data yang ingin ditambahkan
    
    // insert add beggining 
    // param (array, new data will be added)
    static void InsertAtBeggining(int[] arr, int add){
        // show the previous array
        
        System.out.println("previous array ");
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println(" ");
        
        int n = arr.length - 1; // 5
        System.out.println(n);
        //arr[0] = from index - 1
        for(int i =n-1; i >= 0; i--){ // initialize i = 4
            arr[i + 1] = arr[i];
        }
       
        for(int val: arr) {
            System.out.print(val + " ");
        }
        System.out.println(" ");
        System.out.println(arr[0]);
        arr[0] = add;

        for(int i = 0; i < arr.length; i++){
            System.out.println("arr index -" + i + " : " + arr[i]);
        }
    }

    static int[] addNew(int[] arr, int addLength){
        int n = arr.length + addLength;
        System.out.println(n);
        arr = new int[n];
        return arr;
    }

    public static void main(String[] args){
    //    int[] arr =  StoreArray(10);
    //    InputArray(arr); 
    //     for(int array: arr ){
    //         System.out.print(array);
    //         System.out.println(" ");
    //     }
    //     SearchTarget(arr,9);

        int[] cek ={30,2,3,4,5,0};
        addNew(cek, 10);
        System.out.println(cek.length);

        // System.out.println(cek.length);
        // InsertAtBeggining(cek, 90);

    }  
}



