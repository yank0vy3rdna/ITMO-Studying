#include <iostream>
#include <string>
#include <vector>
#include <list>
#include <algorithm>
 
using namespace std;
 
class HashTable {
    struct Node {
        string key;
        string value;
    };
    vector<list<Node>> *table;
    const int PRIME_SIZE = 1000001;
 
    int hash_f(string value) {
        const int p = 31;
        long long hash = 0, p_pow = 1;
        for (int i = 0; i < value.length(); ++i) {
            hash += (value[i] - 'a' + 1) * p_pow;
            p_pow *= p;
        }
        return abs(hash % PRIME_SIZE);
    }
 
public:
    HashTable() {
        table = new vector<list<Node>>(PRIME_SIZE);
    }
 
    void add(string key, string item) {
        Node itm = {key, item};
 
        int h = hash_f(key);
        auto cur = find(key);
        if (cur != table->at(h).end()) {
            cur->value = item;
        } else {
            table->at(h).push_back(itm);
        }
    }
 
    list<Node>::iterator find(string keyy) {
        int index = hash_f(keyy);
        return find_if(table->at(index).begin(), table->at(index).end(), [keyy](const Node& n){return n.key == keyy;});
    }
 
    void del(string key) {
        int h = hash_f(key);
        table->at(h).erase(find(key));
    }
 
    string get(string key) {
        int index = hash_f(key);
        auto temp = find(key);
        if (temp == table->at(index).end()){
            return "";
        }
        return find(key)->value;
    };
};
 
 
int main() {
    string s1, s2, s3, temp;
    HashTable t;
    freopen("map.in", "r", stdin);
    freopen("map.out", "w", stdout);
    while (cin >> s1) {
        if (s1 == "put") {
            cin >> s2 >> s3;
            t.add(s2, s3);
        } else if (s1 == "get") {
            cin >> s2;
            temp = t.get(s2);
            if (!temp.empty()){
                cout << temp << "\n";
            }
            else{
                cout << "none\n";
            }
        } else if (s1 == "delete") {
            cin >> s2;
            t.del(s2);
        }
    }
 
 
    return 0;
}