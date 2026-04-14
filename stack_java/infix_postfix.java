import java.util.ArrayDeque;  // better use this
import java.util.Scanner;
import java.util.Deque;
import java.util.Stack;  // old version
import java.util.Vector;




// 1. Input merupakan sebuah ekspresi aritmatika (infix)
// 2. Output meliputi:
// postfix expression, 
// step by step perhitungan (berdasarkan proses push-pop stack), dan
// hasil akhir perhitungan



public class infix_postfix {
    static Scanner input = new Scanner(System.in); 
    static Deque<String> st = new ArrayDeque<String>();
    static Vector<Integer> resultOp = new Vector<>();
    static int top = -1;

    public static void main(String[] args){
        Init();
    }

    static void Init(){
        int choice = 0;

        do{
        
        System.out.println("1.) start the program");
        System.out.println("2.) exit the program");
        System.out.print("input your choice: ");

        if(input.hasNextInt()){
        choice = input.nextInt();
        input.nextLine();
        switch(choice){
            case 1:
            {
                String user = AritmeticUser();
                String[] stringStream = SeparateString(user);
                Vector<String> test = InfixToPostfix(stringStream);
                showVectorString(test);
                //float hasil = EvaluatePostfix(test);

                //System.out.println(hasil);
                //showVectorString(test);
                // test = InfixToPostfix(stringStream);
                
                // showString(user);
                break;
            }
            case 2: 
            {
                System.out.println("exit...");
                break;
            }
            default: 
            {
                System.out.println("input out of the rangge! just 1 - 2!");
            }
        }


        }else  {
            System.out.println("input just accept a int! ");
            input.nextLine();
        }
        }while(choice != 2);
    }

    static String AritmeticUser(){
        System.out.println("aritmetic must be separate with whitespace");
        System.out.print("input aritmetic here: ");
        String InputUser = input.nextLine();
        return InputUser;
    }

    // postfix
    // static Vector<String> InfixToPostfix(String[] ss){
    //     Vector<String> postfix = new Vector<>();
    //     for(int i = 0; i < ss.length; i ++){
    //         if(ss[i].equals("(")){
    //             st.push(ss[i]);
    //         } else if(ss[i].equals(")")){
    //             while(!st.peek().equals("(")){
    //                 String x = st.peek();
    //                 postfix.add(x);
    //                 st.pop();
                    
    //             } 
    //             st.pop();

    //         } else if(isOperator(ss[i])){
    //             while(!st.isEmpty() && !st.peek().equals("(") && precedence(st.peek()) >= precedence(ss[i])){
    //                 String x = st.peek();
    //                 postfix.add(x);
    //                 st.pop();
    //             } 
    //             st.push(ss[i]);
                
    //         } else{
    //             postfix.add(ss[i]);
    //         }
    //     }

    //     while(!st.isEmpty()){
    //         String x = st.peek();
    //         postfix.add(x);
    //         st.pop();
    //     }
        
    //     return postfix;
    // }

/////////////////////////////////////////////////////////////////////////////////////////////
    

    // try step by step
    // logic : 
    // aritmatika diterima sebagai input string lalu diubah ke array string pakai .splti(regex : " ");
    // vector postfix
    // stack nilai untuk menampung hasil dari nilai yang sudah di olah pakai countOperator
    // ide : stack operator untuk membuat postfix, stack nilai untuk menghitung hasil
    static Vector<String> InfixToPostfix(String[] ss){
    Vector<String> postfix = new Vector<>();
    Deque<Float> st_nilai = new ArrayDeque<Float>();
    st.clear();

    // loop array string
    for(int i = 0; i < ss.length; i++){
        // jika ( maka push ke stack
        if(ss[i].equals("(")){
            System.out.println("push: " + ss[i]);
            st.push(ss[i]);
            showStack(st);
            
            // jika ) maka loop stack sampai ketemu (, pop operator, masukkan ke postfix, dan hitung hasil
        } else if(ss[i].equals(")")){
            System.out.println("found: )");
            while(!st.peek().equals("(")){
                String x = st.peek();
                st.pop();
                float kedua = st_nilai.peek(); st_nilai.pop();
                float pertama = st_nilai.peek(); st_nilai.pop();
                float hasil = countOperator(x, pertama, kedua);
                st_nilai.push(hasil);
                postfix.add(x);
                System.out.println("pop: " + x);
                showStack(st);
            }
            // pop (
            st.pop();
            System.out.println("pop: (");

            // jika operator maka loop, jika prioritas dari top >= token, pop top, push ke postfix, dan hitung hasil
        } else if(isOperator(ss[i])){
            while(!st.isEmpty() && !st.peek().equals("(") && precedence(st.peek()) >= precedence(ss[i])){
                String x = st.peek();
                st.pop();
                float kedua = st_nilai.peek(); st_nilai.pop();
                float pertama = st_nilai.peek(); st_nilai.pop();
                float hasil = countOperator(x, pertama, kedua);
                st_nilai.push(hasil);
                postfix.add(x);
                System.out.println("pop: " + x);
                showStack(st);
            }
            // push operator to stack
            System.out.println("push: " + ss[i]);
            st.push(ss[i]);
            showStack(st);
        } else {
           // for(int k = 0; k)
            // push operand to postfix dan push operand to st_nilai
            postfix.add(ss[i]);
            st_nilai.push(Float.parseFloat(ss[i]));
            System.out.println("push to postfix: " + ss[i]);
            showPostfix(postfix);
        }
        }
    


    // ketika stack masih ada opertor maka pop dan push ke 
    while(!st.isEmpty()){
        String x = st.peek();
        st.pop();
        float kedua = st_nilai.peek(); st_nilai.pop();
        float pertama = st_nilai.peek(); st_nilai.pop();
        float hasil = countOperator(x, pertama, kedua);
        st_nilai.push(hasil);
        postfix.add(x);
    }


    System.out.println("Final result for aritmetic : " + st_nilai.peek());
    return postfix;
}


 /////////////////////////////////////////////////////////////////////////////////////////////
    // show string
    static void showString(String user){
        System.out.println(user);
    }
    

    // Show vector string
    static void showVectorString(Vector <String> user){
        System.out.print("postfix: ");
        for(String val: user){
            System.out.print(val);
        }
        System.out.println(" ");
    }

    // show postfix
    static void showPostfix(Vector <String> pf){
        System.out.print("postfix: ");
        for(String val: pf){
            System.out.print(val);
        }
        System.out.println(" ");
    }

    // show stack
    static void showStack(Deque<String> st_op){
        System.out.print("stack: ");
        for(String val : st_op){
            System.out.print(val + " ");
        }
         System.out.println(" ");
    }


    // boolean operator
   static boolean isOperator(String op){
    return op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/")  || op.equals("^");
    }

  
static int precedence(String op){
    if(op.equals("^")){
        return 3;
    } else if(op.equals("/") || op.equals("*")){
        return 2;
    } else if(op.equals("+") || op.equals("-")){
        return 1;
    } else {
        return -1;
    }
}
    // print string
    static void cekString(String[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println(" ");
    }

    // result postfix with step
    // static float EvaluatePostfix(Vector<String> value){
    //     float result = 0;
    //     Deque<Float> st_op = new ArrayDeque<Float>();
        
    //     for(int i =0; i < value.size(); i++){
    //         if(!isOperator(value.get(i))){
    //             float x = Float.parseFloat(value.get(i));
    //             System.out.println("push : " + x);
    //             st_op.push(x);
    //         } else if(isOperator(value.get(i))){
    //             float kedua = st_op.peek();
    //             st_op.pop();
    //             float pertama = st_op.peek();
    //             st_op.pop();
    //             switch(value.get(i)){
    //                 case "*":
    //                     {
    //                         result = pertama * kedua;
    //                         System.out.println("operator : " + value.get(i) + "-> "+ pertama + "*" + kedua + "= " + result);
    //                         st_op.push(result);
    //                         break;
    //                     }
    //                 case "^":
    //                     {
    //                         float tampung = pertama;

    //                         for(int j= 0; j < kedua - 1; j++){
    //                            pertama *= tampung;
    //                         }
    //                         result = pertama;
    //                         System.out.println("pop operator : " + value.get(i) + "-> "+ pertama + "^" + kedua + "= " + result);
    //                         st_op.push(result);
    //                         break;
    //                     }
    //                 case "/":
    //                     {
    //                         result = pertama / kedua;
    //                         System.out.println("pop operator : " + value.get(i) + "-> "+ pertama + "/" + kedua + "= " + result);
    //                         st_op.push(result);
    //                         break;
    //                     }
    //                 case "+":
    //                     {
    //                         result = pertama + kedua;
    //                         System.out.println("pop operator : " + value.get(i) + "-> "+ pertama + "+" + kedua + "= " + result);
    //                         st_op.push(result);
    //                         break;
    //                     }
    //                 case "-":
    //                     {
    //                         result = pertama - kedua;
    //                         System.out.println("pop operator : " + value.get(i) + "-> "+ pertama + "-" + kedua + "= " + result);
    //                         st_op.push(result);
    //                         break;
    //                     }
    //             }
    //         } 
    //     }
    //     System.out.println("result of aritmetic is : " + result);
    //     result = st_op.peek();
    //     return result;
    // }

    // result postfix without step
    static float EvaluatePostfix(Vector<String> value){
        float result = 0;
        Deque<Float> st_op = new ArrayDeque<Float>();
        
        for(int i =0; i < value.size(); i++){
            if(!isOperator(value.get(i))){
                float x = Float.parseFloat(value.get(i));
                st_op.push(x);
            } else if(isOperator(value.get(i))){
                float kedua = st_op.peek();
                st_op.pop();
                float pertama = st_op.peek();
                st_op.pop();
                switch(value.get(i)){
                    case "*":
                        {
                            result = pertama * kedua;
                            st_op.push(result);
                            break;
                        }
                    case "^":
                        {
                            float tampung = pertama;

                            for(int j= 0; j < kedua - 1; j++){
                               pertama *= tampung;
                            }
                            result = pertama;
                           
                            st_op.push(result);
                            break;
                        }
                    case "/":
                        {
                            result = pertama / kedua;
                           
                            st_op.push(result);
                            break;
                        }
                    case "+":
                        {
                            result = pertama + kedua;
                            
                            st_op.push(result);
                            break;
                        }
                    case "-":
                        {
                            result = pertama - kedua;
                        
                            st_op.push(result);
                            break;
                        }
                }
            } 
        }
        
        result = st_op.peek();
        return result;
    }


    static float countOperator(String op, float pertama, float kedua){
        float result = 0;
        switch(op){
                case "*":
                        {
                        result = pertama * kedua;
                        System.out.println("operator : " + op + "-> "+ pertama + "*" + kedua + "= " + result);
                        return result;
                        }
                case "^":
                        {
                        float tampung = pertama;

                        for(int j= 0; j < kedua - 1; j++){
                        pertama *= tampung;
                        }
                        result = pertama;
                        System.out.println("pop operator : " + op + "-> "+ pertama + "^" + kedua + "= " + result);
                        return result;
                        }
                case "/":
                        {
                        result = pertama / kedua;
                        System.out.println("pop operator : " + op + "-> "+ pertama + "/" + kedua + "= " + result);
                        return result;
                        }
                case "+":
                        {
                        result = pertama + kedua;
                        System.out.println("pop operator : " + op + "-> "+ pertama + "+" + kedua + "= " + result);
                        return result;
                        }
                    case "-":
                        {
                        result = pertama - kedua;
                        System.out.println("pop operator : " + op + "-> "+ pertama + "-" + kedua + "= " + result);
                        return result;
                        }
                }
        return result;
    }
    
    

    static float stringToFloat(String value){
        return Float.parseFloat(value);
    }

  
    // separate string from whitespace
    static String[] SeparateString(String ss){
        String[] result = ss.split(" ");
        return result;
    }

}