#include <iostream>
#include <string>
#define NUM 10

class Graph{
    private:
    int n;
    int matrix[NUM][NUM];
    
    public:
    Graph(int x)
    {
        this->n = x;
        for(int i=0; i < n; i++){
            for(int j=0; j< n;j++){
                matrix[i][j] = 0;
            }
        }
    }

    void addEdge(int x, int y){
        if(x >= n || y >= n)
        {
            std::cout << "out of bounds" << std::endl;
            return;
        }
        if(x == y)
        {
            std::cout << "self loop detected" << std::endl;
        } else {
            matrix[x][y] = 1;
            matrix[y][x] = 1;
        }
    }

    void addVertex(){
        this->n++;
        std::cout << "N: " << n << std::endl;
        for(int i= 0; i < n; ++i){
            matrix[i][n - 1] = 0;
            matrix[n - 1][i] = 0;
        }
    
    }

    void Print(){
         for(int i=0; i < n; i++){
            for(int j=0; j< n;j++){
                std::cout << matrix[i][j] << " ";
            }
            std::cout << std::endl;
        }
    }

    void removeVertex(int x){
        if(x > n)
        {
            std::cout << "out of bound" << std::endl;
            return;
        }
        while(x < n){
            for(int i = 0; i < n;i++){
                matrix[i][x] = matrix[i][x + 1];
            }

            for(int i=0; i < n; i++){
                matrix[x][i] = matrix[x + 1][i];
            }

            x++;
        }
        n--;
    }
};

int main() {

    Graph g = Graph(10);
    g.Print();

    for(int i = 0; i < 10; i++){
        for(int j = 0; j < 10; j++){
            g.addEdge(i,j);
        }
    }

    g.Print();
    g.removeVertex(5);
    g.Print();
    // g.addVertex();
    // g.addVertex();
    // g.Print();
    return 0;
}