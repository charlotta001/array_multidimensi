#include <iostream> 
#include <vector>

std::vector<int> cekVec(const std::vector<int>& value,const int& target){
    for(int i =0; i < value.size(); i++){
        for(int j = i +1; j < value.size(); j++){
            if(value[i] + value[j] == target){
                return {i, j};
            }
        }
    }

    return{};
}

int main(){
    std::vector<int> value {1,2,3,4,5};
    std::vector<int> result {};
    //result = cekVec(value, 6);
    for(auto val: cekVec(value, 6)){
        std::cout << val << " ";
    }
}