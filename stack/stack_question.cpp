#include <iostream>
#include <vector>
// =================================================================
// O(n2)
// int cekMat(std::vector<std::vector <int>> value){
//     int n = value.size();
//     std::vector<int> kolom (n, 0), baris(n,0);

//     for(int i = 0; i < n ;i++){
//         for(int j = 0; j < n; j++){
//             int x = value[i][j];
//             baris[i] += x;
//             kolom[j] += x;
//         }
//     }

//     for(int i =0; i < n; i++){
//         if(baris[i] ==1 && kolom[i] == 3){
//             return i; 
//         }
//     } return -1;
// }
// ===============================================================

class Stack{
    private:
    int* arr;
    int top = -1;
    int capacity;

    public:
    

    Stack() = default;
    // kenapa arr* tidak bisa
    Stack (int capacity){
        if(capacity < 0){
            std::cout << "capacity cant be lees than 0" << std::endl;
        }
        this->arr = new int[capacity];
        this->capacity = capacity;
    }

    void push(int value){
        if(top == (capacity -1) ){
            std::cout << "array is overflow" << std::endl;
            return;
        }
        arr[++top] = value;
        
    }

    int pop(){
        if(top == -1){
            std::cout << "array is underflow" << std::endl;
            return -1;
        }
        return arr[top--];
    }
    
    bool isEmpty(){
       return top == -1;
    }

    int peek(){
         if(top == -1){
            std::cout << "arr is empty" << std::endl;
            return -1;
         }
         return arr[top];
    }
    
    void show(){
        for(int i = 0; i < top + 1; i++){
            std::cout << arr[i] << std::endl;
        }
    }

    int size(){
        return top + 1;
    }

};



int main(){
    std::vector <std::vector<int>> array;
    // pop 3
    array = {{1,1,1,1}, {0,1,1,0}, {0,1,1,0}, {1,1,1,1}};
    int capacity = 4;
    Stack stack(capacity);
    for(int i = 0; i < capacity; i++){
        stack.push(i);
    } 

    while(capacity > 1){
        int a = stack.pop();
        int b = stack.pop();

        if(array[a][b] == 1){
            stack.push(b);
        
        } else{
            stack.push(a);
            
        }
        capacity--;
    }
    capacity = 4;

    int c = stack.pop();
    for(int i =0; i < capacity; i++){
        if (i == c){
            continue;
        }
        if (array[c][i] == 1 || array[i][c] == 0){
            std::cout << "selebrity at -index: " << -1;
            return -1;
        }
    }
    std::cout << "selebrity at -index: " << c;
    return 0;
        
    


    //=====================================================================
    // O(n2)
    // std::vector <std::vector <int>>trying {{0,0,1}, {1,1,1},{0,0,1}};
    // for(int i = 0; i < trying.size(); i++){
    //     std::cout << trying[i][i];
    // }
    // std::cout << cekMat(trying);
    //=====================================================================

    
    
}