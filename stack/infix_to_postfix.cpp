#include <iostream>
#include <stack>
#include <string>
#include <sstream>
#include <vector>
#include <cstdlib>
#include <cstring>

// return precedence of operators




// precedence
int precedence(std::string a){
    if(a == "^"){
        return 3;
    } else if(a == "*" || a =="/"){
        return 2;
    } else if(a == "+" || a == "-"){
        return 1;
    } else {
        return -1;
    }
}

// pangkat
bool pangkat(char a){
    return a == '^';
}

//operator
bool isOperator(std::string token){
    return token == "+" || token == "-" || token == "*" || token == "/";
}



// input user
std::string input(){
    std::string inputUser;
    std::cout << "input here: ";
    getline(std::cin, inputUser);

    return inputUser;
}


// infix to postfix
std::vector<std::string> infixToPostfix(const std::string input){
    // https://en.cppreference.com/w/cpp/io/basic_stringstream/basic_stringstream.html
    std::stringstream result(input);
    std::vector <std::string> postfix;
    std::string token;

    std::stack <std::string> op;

    while(result >> token){
      
        if(token == "("){
            op.push(token);
        } else if(token == ")"){
            while(op.top() != "("){
                std::string top= op.top();
                postfix.push_back(top);
                op.pop();
            } 
            op.pop();

        } else if(isOperator(token)){
            while(!op.empty() && op.top() != "(" && precedence(op.top()) >= precedence(token)){
                std::string b = op.top();
                postfix.push_back(b);
                op.pop();
        }
        // + 
        // +
        //
        //
        //
        op.push(token);
        } else { 
            postfix.push_back(token);
        }
    } 
    
    while(!op.empty()){
        std::string x = op.top();
        postfix.push_back(x);
        op.pop();
    }

    return postfix;
}



// show postfix
void show(const char* postfix){
    for(int i =0; i <strlen(postfix); i++){
        std::cout << postfix[i] << " ";
    }
}

int main(){
    std::stack<char> Operator;
    std::vector <std::string> attempt;

    ///std::string inputUser = input();
    attempt = infixToPostfix(" 1 + 8 * ( 9 / 7 ) ");
    for(auto val : attempt){
        std::cout << val;
    }
    


}