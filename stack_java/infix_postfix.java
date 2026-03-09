import java.util.ArrayDeque;  // better use this
import java.util.Scanner;
import java.util.Deque;
import java.util.Stack;  // old version
import java.util.Vector;

public class infix_postfix {
    static Scanner input = new Scanner(System.in); 
    static Deque<String> st = new ArrayDeque<String>();
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

                cekString(stringStream);
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

    static Vector<String> InfixToPostfix(String[] ss){
        Vector<String> postfix = new Vector<>();
        for(int i = 0; i < ss.length; i ++){
            if(ss[i].equals("(")){
                st.push(ss[i]);
            } else if(ss[i].equals(")")){
                while(!st.peek().equals("(")){
                    String x = st.peek();
                    postfix.add(x);
                    st.pop();
                } st.pop();
            } else if(isOperator(ss[i])){
                while(!st.isEmpty() && !st.peek().equals("(") && precedence(st.peek()) >= precedence(ss[i])){
                    String x = st.peek();
                    postfix.add(x);
                    st.pop();
                } st.push(ss[i]);
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
    return op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/");
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


    // separate string from whitespace
    static String[] SeparateString(String ss){
        String[] result = ss.split(" ");
        return result;
    }
}
