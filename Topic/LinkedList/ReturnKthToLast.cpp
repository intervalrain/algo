#include <iostream>
#include <cstddef>
using namespace std;

class Node{
public:
    int val;
    Node* next;

    Node(){
        val = 0;
        next = NULL;
    }
    Node(int val){
        this->val = val;
        this->next = NULL;
    }
    Node(int val, Node* next){
        this->val = val;
        this->next = next;
    }
};

class LinkedList{
    Node* head;
public:
    LinkedList(){
        head = NULL;
    }
    void insertNode(int val){
        Node* newNode = new Node(val);
        if (head == NULL){
            head = newNode;
            return;
        }
            
        Node* tmp = head;
        while (tmp->next != NULL)
            tmp = tmp->next;
        
        tmp->next = newNode;
    }

    void deleteNode(int index){
        Node* tmp1 = head, *tmp2 = NULL;
        int len = 0;

        if (head == NULL)
            return;
        
        while (tmp1 != NULL){
            tmp1 = tmp1->next;
            len++;
        }

        if (len < index){
            cout << "Index out of range" << endl;
            return;
        }

        tmp1 = head;

        if (index == 1)
            head = head->next;
            delete tmp1;
            return;

        while (index-- > 1){
            tmp2 = tmp1;
            tmp1 = tmp1->next;
        }

        tmp2->next = tmp1->next;
        delete tmp1;
            
    }

    void print(){
        Node* tmp = head;
        if (head == NULL){
            cout << "List empty" << endl;
            return;
        }

        while (tmp != NULL){
            cout << tmp->val << " "; 
            tmp = tmp->next;
        }
        cout << endl;
    }

    Node* getHead(){
        return head;
    }
};

Node* nthToLast(Node* head, int k, int& i){
    if (head == NULL)
        return NULL;
    Node* nd = nthToLast(head->next, k, i);
    i++;
    if (i == k)
        return head;
    return nd;
}

Node* nthToLast(Node* head, int k){
    int i = 0;
    return nthToLast(head, k, i);
}


int main(){
    LinkedList list;
    int arr[] = {7,8,6,4,5,9,1,2,3};
    for (int i = 0; i < sizeof(arr)/sizeof(arr[0]); ++i)
        list.insertNode(arr[i]);

    list.print();
    
    cout << nthToLast(list.getHead(), 3)->val << endl;

    return 0;
}