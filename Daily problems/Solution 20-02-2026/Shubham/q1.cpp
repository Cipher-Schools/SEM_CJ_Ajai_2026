#include<iostream>
#include<vector>
using namespace std;

class Solution{
public:
    void reverseString(vector<char>& s) {
        int low = 0;
        int high = s.size() - 1;

        while(low < high){
            swap(s[low], s[high]);
            low++;
            high--;
        }
    }
};

int main(){
    string str = "Hello";

    vector<char> s(str.begin(), str.end());

    Solution obj;
    obj.reverseString(s);

    for(char c : s)
        cout << c;
}