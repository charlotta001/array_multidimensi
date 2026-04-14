import java.util.ArrayList;
import java.util.Scanner;
// Buat program (Java & Python) menggunakan struktur data BST dengan fitur program terdiri dari: 
// tambah data,
//  cari data,
//  hapus data,
//  dan traversal (inorder, preorder, postorder). 
// Untuk testing program, 
// inputkan seluruh data (id dan nama) yang ada dalam file excel berikut (terlampir).


// root, left,right
// left root is less than root 
// right root is greater than root
// O(n)


// search time complexity(O log n), worst case O(n)


public class bst_mine {

    // entry program
    public static void main(String[] args){
        myNode.Input();

    }

    // class Node (make a tree)
    static class Node{
        Node left;
        Node right;
        int data;

        Node(int data){
            this.data = data;
            this.right = this.left = null;
        }
    }

    //linked list node
    static class myNode{
        Node node;
    
    myNode(int key){
        this.node = new Node(key);
    }
   

    //recursive insert function
    static Node insert(Node root, int key){

        Node new_node = new Node(key);
        // check if root is null
        if(root == null)
        {
            return new_node;
        }

        if(key < root.data){
            root.left = insert(root.left, key);
        } else if( key > root.data){
            root.right = insert(root.right, key);
        }
        return root;
    }
    
    // rekursif function to show data inside tree 
    // show data (inorder)
    static void inorder(Node root){
    if(root == null) return;
    inorder(root.left);
    System.out.print(root.data + " ");
    inorder(root.right);
    }
    

    static void search(){

    }


    static void testing(Node root, int data){
        Node new_data = new Node(data);
        new_data = myNode.insert(root);
        System.out.println("root: " + root.data);
        System.out.println("root left: " + root.left.data);
        System.out.println("root right: " + root.right.data);
        
    }


    // input bst
    static void Input(){
        Scanner input = new Scanner(System.in);
        int choice = 0;
        int num = 0;

        Node node = null;
        
        do{
            System.out.print("masukkan pilihan: ");
            choice = input.nextInt();
            switch(choice){
                case 1: 
                System.out.print("masukkan data: ");
                num = input.nextInt();
                node = myNode.insert(node, num);
                break;
                case 2:
                myNode.inorder(node);
                break;
                case 4: 
                testing(node);
                break;
                case 3: 
                System.out.println("exiting program");
                break;
            }
            
        } while(choice != 3);

        input.close();
    }



    
}
}