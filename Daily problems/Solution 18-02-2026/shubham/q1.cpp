#include <iostream>
#include <string>
using namespace std;

class Solution {
public:
    bool isPalindrome(string s) {
        string result = "";

      
        for(int i = 0; i < s.length(); i++) {
            if(isalnum(s[i])) {
                result += tolower(s[i]);
            }
        }
        int left = 0;
        int right = result.length() - 1;

        while(left < right) {
            if(result[left] != result[right]) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
};

int main() {
    Solution obj;

    string s;
    cout << "Enter string: ";
    getline(cin, s);

    if(obj.isPalindrome(s))
        cout << "Palindrome";
    else
        cout << "Not a palindrome";

    return 0;
}