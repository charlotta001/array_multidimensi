#include <iostream>
#include <stack>




// push
// pop
// peek
// empty

class Stack{
    private:
    int* array;
    int capacity = 0;
    int top;

    public:

    Stack() = default;
    Stack(int capacity): capacity(capacity){
        this->capacity = capacity;
        array = new int[capacity];
        top = -1;
    }

    void push(int x){

        // validation for Stack(0)
        // -1 = -1 
        // cuz stack = 0 so if we push new value we got overflow
        if(top == (capacity -1 )){
            std::cout << "stack overflow" << std::endl;
            return;
        }   

        // time complexity = O(1)
        array[++top] = x;
        //return 0
    }

    int pop() {
        if(top == -1){
            std::cout << "stack underflow" << std::endl;
            return -1;
        }

        return array[top--];
        // return -1 
    }

    int peek(){
        if(top == -1){
            std::cout << "stack is empty" << std::endl;
            return -1;
        }
        return array[top];
        // return -1k
    }

    bool isEmpty(){
        return top == -1;
        // return true if "top == -1"
    }
};



int main(){
    Stack st(123);
    std::cout << (st.isEmpty() == true ) ? "true" : "false";
}