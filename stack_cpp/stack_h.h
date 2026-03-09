#ifndef STACK_H_H
#define STACK_H_H
#include "config.h"

// compile syntax
// g++ -c stack_h.cpp -o stack_h.o
// g++ -c infix_postfix.cpp -o infix_postfix.o
// g++ stack_h.o infix_postfix.o -o infix_postfix.exe
const int MAX_STACK = 100;
extern int top;
//int capacity = 0;
//std::string* arr = new std::string[capacity];
extern std::vector <std::string> st;

void push(std::string);
std::string pop();
std::string peek();
std::string inputUser();
void show();
bool isOperator(std::string);
int precedence(std::string);






#endif 
