#include "config.h"

int top = -1;
std::vector<std::string> st;

void push(std::string value){
    if(top == (int)MAX_STACK){
        std::cout << "stack is overflow" << std::endl;
        return;
    }
    st.push_back(value);
    top++;
}

int precedence(std::string op){
    if(op == "^"){
        return 3; 
    } else if(op == "*" || op == "/"){
        return 2;
    } else if( op =="+" || op == "-"){
        return 1;
    }
    return -1;
}


std::string pop(){
   if(st.empty()){
        std::cout << "stack is underflow" << std::endl;
        return "";
    }
    std::string val = st.back();
    st.pop_back();
    return val;
}

std::string peek(){
     if(st.empty()){
        std::cout << "stack is empty" << std::endl;
        return "";
    }
    return st.back();
}

std::string inputUser(){
    std::string input;
    std::cout << "input here: ";
    getline(std::cin, input);

    return input;
}

void show (){
    std::cout << "show stack: ";
    for(auto val : st){
        std::cout << val << " ";
    }
}

bool isOperator(std::string op){
    return op=="+" || op == "-" || op == "*" || op == "/";
}
