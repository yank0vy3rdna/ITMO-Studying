#include <iostream>
//#include <fstream>
/*
var a : array [ 1 . . N] o f integer ;
procedure QSort ( l e f t , r i g h t : integer ) ;
var i , j , key , bu f : integer ;
begin
key := a [ ( l e f t + r i g h t ) div 2 ] ;
i := l e f t ;
j := r i g h t ;
repeat
while a [ i ] < key do {ïåðâûé w h i l e }
i n c ( i ) ;
while key < a [ j ] do {âòîðîé w h i l e }
dec ( j ) ;
i f i <= j then begin
bu f := a [ i ] ;
a [ i ] := a [ j ] ;
a [ j ] := bu f ;
i n c ( i ) ;
dec ( j ) ;
end ;
unt i l i > j ;
i f l e f t < j then QSort ( l e f t , j ) ;
i f i < r i g h t then QSort ( i , r i g h t ) ;
end ;
begin
. . .
QSort ( 1 , N ) ;
end .
*/
using namespace std;
void QSort(int left, int right, int a[]){
    int key = a[(left+right) / 2];
    int i = left;
    int j = right;
    do
    {
        while (a[i] < key){
            i++;
        }
        while (key < a[j]){
            j--;
        }
        if (i<=j){
            int buf = a[i];
            a[i] = a[j];
            a[j] = buf;
        }
    }while (i>j);
    if (left < j){
        QSort(left,j, a);
    }
    if (i< right){
        QSort(i, right, a);
    }
}
int a[] = {1, 3, 2};
int main() {
    QSort(0,2,a);
    for (int i = 0; i < sizeof(a); ++i)
    {
        cout << a[i] << " ";
    }
    return 0;
}