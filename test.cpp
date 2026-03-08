#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <cerrno>
#include <vector>
#include <cstring>






int main(){ 






//     std::fstream file("test.txt", std::ios::in | std::ios::out | std::ios::trunc);
//     std::string readUser, readss, readFile;
//     std::stringstream ss;
//     std::vector <std::string> dataString;
//     std::vector <int> dataInt;

//     if(!file.is_open()){
//         std::cout << "file isnt open" << std::endl;
//         return 1;
//     }
    
//     std::cout << "input here: ";
//     getline(std::cin, readUser);
//     file << readUser;
//     file.seekg(0);
//     ss << readUser;


//     while(getline(file, readss)){
//         std::cout << readss << " ";
//     } 
//     std::cout << std::endl;
//     file.clear();
//     file.seekg(0);
//     std::cout << "read file usign >> " << std::endl;
//        while(file >> readFile ){
//         std::cout << readFile << " ";
//     } 
//     std::cout << std::endl;

//     ss.seekg(0);
//     // read base string
//     while(ss >> readUser){
//         // read per kata
//         std::stringstream ifString(readUser); // ifStrign == per kata
//         std::string yesString, boxString;
//         int number;
      
//         //if kata == string -> store
//         if((ifString >> number) && ifString.eof()){
//             dataInt.push_back(number);
//             continue;
//         } else {
//             dataString.push_back(readUser);
//             continue;
//         }
//     }


//     std::cout << "===below is output string after clasification===" << std::endl;
//     std::cout << std::endl;

 
//     // check string
//     std::cout << "string : ";
//      if(!dataString.empty()){
//         for(auto& val: dataString){
//         std::cout << val << " ";
//     }       
//     }else {
//         std::cout << "dataString is empty"<< std::endl;
//     }

//     std::cout << std::endl;
//     //check int 
//     std::cout << "int : ";
//     if(!dataInt.empty()){
//         for(auto& val: dataInt){
//         std::cout << val << " ";
//     }
//     }else {
//         std::cout << "dataInt is empty"<< std::endl;
//     }
  
//     // std::cout << "input here : ";
//     // getline(std::cin, read);
//     // file << read;


//     // file.seekg(0);
//     // ss << file.rdbuf();
//     // ss.seekg(0);
//     // int angka;
//     // ss >> angka;
//     // std::cout << "angka: " << angka << std::endl;

//     // clone = ss.str();
//     // std::cout << "clone : "<<clone << std::endl;
    
   


//     file.close();
 } 