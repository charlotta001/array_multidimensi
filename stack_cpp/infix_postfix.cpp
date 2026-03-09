#include "config.h"
#include <sstream>


// stack = st, from library
// g++ infix_postfix.cpp stack_h.cpp -o infix_postfix.exe


void infixToPostfix(std::string inputUser){
    std::stringstream ss(inputUser);
    std::string token;
    std::vector <std::string> postfix;
    

    // read stringstream as string (string is sentence untill whitespace)
    // if token == "(" push to stack
    // if token == ")" push operator from stack untill find "(" and pop "("
    // if token == operator -> push to stack
    // else -> push token to postfix
    while(ss >> token){
        if(token  == "("){
            push(token);
        } else if(token == ")"){
            while(peek() != "("){
                postfix.push_back(pop());
            } pop();
        } else if(isOperator(token)){
            while(!st.empty() && peek() != "(" && precedence(peek()) >= precedence(token)){
                postfix.push_back(pop());
            } push(token);
        } else {
            postfix.push_back(token);
        }
    }

    // operator left -> postfix
    while(!st.empty()){
        postfix.push_back(pop());
    }

    // note : fail in precedence of operator


    //   while(ss >> token){
    //     if(token  == "("){
    //         push(token);
    //     } else if (token != ")"){
    //         if(isOperator(token)){
    //             push(token);
    //         } else {
    //             std::string x = token;
    //             postfix.push_back(token);
    //         }
    //     } else if (token == ")"){
    //         while(token != "("){
    //             std::string x = pop();
    //             postfix.push_back(x);
    //         } pop();
    //     } else if(isOperator(token)){
            
    //     }
    // }


    show();
    std::cout << "postfix : ";
    for(auto val: postfix){
         std::cout << val << " ";
    }
}



int main(){
    std::string input = inputUser();
    infixToPostfix(input);
}