#include <iostream>
#include <fstream>
using namespace std;
 
struct Stack{
    Stack* left;
    int value;
 
};
 
int main()
{
    ifstream inp("stack.in");
    ofstream out("stack.out");
    int count_;
    inp >> count_;
    Stack* last;
    for(int i = 0; i < count_; i++){
        char x;
        inp>>x;
        if(x == '-'){
 
            out<< last->value<<endl;
            last = last->left;
        }
        else{
            Stack element;
            element.left = last;
            int tmp;
            inp >> tmp;
            element.value = tmp;
            last = &element;
        }
 
    }
    return 0;
}