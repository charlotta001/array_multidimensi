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
                float hasil = EvaluatePostfix(test);
                System.out.println(hasil);

                // cekString(stringStream);
                showVectorString(test);


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
    static Vector<String> InfixToPostfix(String[] ss){
        Vector<String> postfix = new Vector<>();
        int result= 0;
        for(int i = 0; i < ss.length; i ++){
            if(ss[i].equals("(")){
                st.push(ss[i]);
            } else if(ss[i].equals(")")){
                while(!st.peek().equals("(")){
                    String x = st.peek();
                    postfix.add(x);
                    st.pop();
                    
                } 
                st.pop();

            } else if(isOperator(ss[i])){
                while(!st.isEmpty() && !st.peek().equals("(") && precedence(st.peek()) >= precedence(ss[i])){
                    String x = st.peek();
                    postfix.add(x);
                    st.pop();
                } 
                st.push(ss[i]);
                
            } else{
                postfix.add(ss[i]);
            }
        }

        while(!st.isEmpty()){
            String x = st.peek();
            postfix.add(x);
            st.pop();
        }
        
        return postfix;
    }

 
    // show string
    static void showString(String user){
        System.out.println(user);
    }
    

    // Show vector string
    static void showVectorString(Vector <String> user){
        for(String val: user){
            System.out.print(val);
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
    
    static void cekString(String[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println(" ");
    }


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

  
    // separate string from whitespace
    static String[] SeparateString(String ss){
        String[] result = ss.split(" ");
        return result;
    }
}
