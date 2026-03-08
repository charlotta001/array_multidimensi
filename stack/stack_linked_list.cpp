#include <iostream>

class Node{
    public:
    Node* next;
    int value;

    public: 
    Node (int value) {
        this->value = value;
        this->next = nullptr;
    }

};

Node* push(Node* head,int value){
    Node* newNode = new Node(value);
    newNode->next = head;
    return newNode;
}

void print(Node* head){
    Node* temp = head;
     while(temp != nullptr){
        std::cout << temp->value << " ";
        temp = temp->next;
     }
}


int main(){
    Node* head = new Node(1);
    head = push(head, 2);
    head = push(head, 3);
    print(head);


}