#include <iostream>
#include <vector>

std::vector<int> insert(std::vector<int>& heap, int value);
void deletion(std::vector<int>& heap, int value);
void heapify(std::vector<int>& heap, int i, int n);



int main() {

    std::vector<int> arr ={15,2,8,3,1,7,10};
    std::vector<int> value(0);
    
    
    for(int i = 0; i < arr.size(); i++ ){
        insert(value,arr[i]);
    }

    for(auto val: value){
        std::cout << val << " ";
    }
    std::cout << std::endl;
    

    deletion(value,8);
    
    for(auto val: value){
        std::cout << val << " ";
    }

    for(int i = arr.size();  i >=0; i--){
        heapify(arr, i, arr.size());
    }

    std::cout << std::endl;
    for(auto val: arr){
        std::cout << val << " ";
    }

    return 0;
}

std::vector<int> insert(std::vector<int>& heap, int value){
    
    heap.push_back(value);

    int index = heap.size() - 1;


    while(index > 0 && heap[(index - 1)/2] < heap[index]) {
        std::swap(heap[index], heap[(index -1)/2]);
        index = (index - 1)/2;
    }

    return heap;
}


void deletion(std::vector<int>& heap, int value){

    int index = -1;
    
    for(int i = 0; i < heap.size(); i++){
        if(heap[i] == value){
            index = i;
            break;
        }
    }

    if(index == -1){
        return;
    }


    heap[index] = heap[heap.size()-1];
    heap.pop_back();

    while(true){
        int left_child = 2 * index +1;
        int right_child = 2 * index + 2;    
        int largest = index;
        std::cout << largest << std::endl; 

        if(left_child < heap.size() && heap[left_child] > heap[largest]){
            largest = left_child;
        }
        if(right_child < heap.size() && heap[right_child] > heap[largest]){
            largest = right_child;
        }

        if(largest != index){
            std::swap(heap[index], heap[largest]);
            index = largest;
        } else{
            break;
        }
    }
}

int top_element(std::vector<int>& heap){
    if(heap.empty()){
        return -1;
    } 
    return heap[0];
}

void heapify(std::vector<int>& heap, int i, int n){
    int largest = i;
    int l = 2*i +1;
    int r = 2*i +2;

    if(l < n && heap[l] > heap[i]){
        largest = l;
    } 

    if(r < n && heap[r] > heap[i]){
        largest = r;
    }

    if(largest != i ){
        std::swap(heap[i], heap[largest]);
        heapify(heap,largest, n);
    }
}

void Initialize(){

 
}
